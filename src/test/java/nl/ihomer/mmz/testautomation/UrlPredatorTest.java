package nl.ihomer.mmz.testautomation;

import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UrlPredatorTest {

    Journal journal;

    @Before
    public void setup() throws IOException {
        journal = new Journal();
    }

    @Test
    public void shouldReportAnAliveUrl() {
        WebResource mockedWebResource = getMockedWebResource("A nonEmpty Response", "http://localhost/test");
        new UrlPredator(mockedWebResource, "localhost", journal);
        assertTrue(journal.containsAliveUrlMessages());
        assertThat(journal.reportAliveUrlMessages(), containsString("localhost/test"));
    }

    @Test
    public void shouldReportADeadUrl() {
        String randomUrl = UUID.randomUUID().toString();
        new UrlPredator(Arrays.asList("http://" + randomUrl), randomUrl, journal);
        assertTrue(journal.containsDeadUrlMessages());
        assertThat(journal.reportDeadUrlMessages(), containsString(randomUrl));
    }

    @Test
    public void shouldFindUrlsWithinResponses(){
        String responseText = "A response with urls like 'http://localhost/' ";
        WebResource mockedWebResource = getMockedWebResource(responseText, "http://localhost/test");
        new UrlPredator(mockedWebResource, "localhost", journal);
        assertTrue(journal.containsAliveUrlMessages());
        assertThat(journal.reportAliveUrlMessagesDetailed(), containsString("http://localhost/test"));
    }

    private WebResource getMockedWebResource(String response, String url) {
        WebResource mockedWebResource = Mockito.mock(WebResource.class);
        when(mockedWebResource.get(String.class)).thenReturn(response);
        when(mockedWebResource.getURI()).thenReturn(UriBuilder.fromPath(url).build());
        return mockedWebResource;
    }
}

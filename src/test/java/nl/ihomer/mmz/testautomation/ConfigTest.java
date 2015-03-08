package nl.ihomer.mmz.testautomation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConfigTest {

    Config config;

    Journal journal;

    @Before
    public void setup(){
        journal = new Journal();
    }

    @Test
    public void shouldReturnDefaultRootUrlWhenNoArgsAreProvided() {
        config = new Config(new ArrayList<String>(), journal);
        assertThat(config.getUrls().size(), is(1));
        assertThat(config.getUrls().get(0), is("http://localhost:80/"));
    }

    @Test
    public void shouldParseHostOption() {
        config = new Config(Arrays.asList(Config.HOST_OPTION, "HOST"), journal);
        assertThat(config.getHost(), is("HOST"));
    }

    @Test
    public void shouldNotifyUsersWhenHostOptionKeyIsPresentButNoHostOptionValueIsProvided() {
        config = new Config(Arrays.asList(Config.HOST_OPTION), journal);
        assertTrue(journal.containsParseErrorMessages());
        assertThat(journal.reportErrorMessages(), containsString("host"));
    }

    @Test
    public void shouldParsePortOption() {
        config = new Config(Arrays.asList("-port", "PORT"), journal);
        assertThat(config.getPort(), is("PORT"));
    }

    @Test
    public void shouldNotifyUsersWhenPortOptionKeyIsPresentButNoPortOptionValueIsProvided() {
        config = new Config(Arrays.asList(Config.PORT_OPTION), journal);
        assertTrue(journal.containsParseErrorMessages());
        assertThat(journal.reportErrorMessages(), containsString("port"));
    }

    @Test
    public void shouldParsePathOptionWhenSinglePathValueIsProvided() {
        config = new Config(Arrays.asList(Config.PATH_OPTION, "PATH1"), journal);
        assertThat(config.getPaths().get(0), is("PATH1"));
    }

    @Test
    public void shouldParsePathOptionWhenMultiplePathValuesAreProvided() {
        config = new Config(Arrays.asList(Config.PATH_OPTION, "PATH1", "PATH2", "PATH3"), journal);
        assertThat(config.getPaths().size(), is(3));
        assertThat(config.getPaths().get(0), is("PATH1"));
        assertThat(config.getPaths().get(1), is("PATH2"));
        assertThat(config.getPaths().get(2), is("PATH3"));
    }

    @Test
    public void shouldNotifyUsersWhenPathOptionKeyIsPresentButNoPathOptionValueIsProvided() {
        config = new Config(Arrays.asList(Config.PATH_OPTION), journal);
        assertTrue(journal.containsParseErrorMessages());
        assertThat(journal.reportErrorMessages(), containsString("path"));
    }

    @Test
    public void shouldNotifyUsersWhenAnUnknownOptionsIsProvided() {
        config = new Config(Arrays.asList("-fietsbel"), journal);
        assertTrue(journal.containsParseErrorMessages());
        assertThat(journal.reportErrorMessages(), containsString("fietsbel"));
    }

}

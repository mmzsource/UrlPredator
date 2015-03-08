package nl.ihomer.mmz.testautomation;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlPredator {

    private WebResource webResource;

    private String hostName;

    private Journal journal;

    public UrlPredator(List<String> urls, String hostName, Journal journal) {

        if (urls.size() > 1) {
            for (String url : urls) {
                new UrlPredator(Arrays.asList(url), hostName, journal);
            }
        }

        new UrlPredator(Client.create().resource(urls.get(0)), hostName, journal);
    }

    public UrlPredator(WebResource webResource, String hostName, Journal journal) {
        this.webResource = webResource;
        this.hostName = hostName;
        this.journal = journal;
        getResource();
    }

    private void getResource() {
        try {
            String result = webResource.get(String.class);
            List<String> foundUrls = findUrls(result);
            if (foundUrls.size() > 0) {
                new UrlPredator(foundUrls, hostName, journal);
            }
            journal.recordAliveUrl(webResource.getURI().toString(), result, foundUrls);
        } catch (Exception ex) {
            journal.recordDeadUrl(webResource.getURI().toString(), ex.getMessage());
        }
    }

    private List<String> findUrls(String input) {
        List<String> urlsInInput = new ArrayList<>();

        /* split on double quote OR single quote OR space OR greaterThanSign OR smallerThanSign */
        String[] parts = input.split("\"|'|\\s+|>|<");

        for (String part : parts) {
            try {
                URL url = new URL(part);
                if (url.getHost().contains(hostName)) {
                    urlsInInput.add(url.toString());
                }
            } catch (MalformedURLException e) {
                /* The current part clearly wasn't a valid url*/
            }
        }

        return urlsInInput;
    }

}

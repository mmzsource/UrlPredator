package nl.ihomer.mmz.testautomation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Journal {

    private Map<String, UrlReport> aliveUrlToReportMap = new HashMap<>();

    private Map<String, UrlReport> deadUrlToReportMap = new HashMap<>();

    private StringBuffer parseErrors = new StringBuffer();

    public void recordParseErrorMessage(String parseErrorMessage) {
        parseErrors.append(parseErrorMessage);
    }

    public boolean containsParseErrorMessages() {
        return parseErrors.length() > 0;
    }

    public String reportErrorMessages() {
        if (containsParseErrorMessages()) {
            System.out.println(parseErrors.toString());
        }
        return parseErrors.toString();
    }

    public void recordAliveUrl(String url, String result, List<String> urlsFound) {
        UrlReport report = new UrlReport();
        report.url = url;
        report.report = result;
        report.urlsFound = urlsFound;

        aliveUrlToReportMap.put(url, report);
    }

    public boolean containsAliveUrlMessages() {
        return aliveUrlToReportMap.size() > 0;
    }

    public String reportAliveUrlMessages() {
        StringBuffer aliveUrls = new StringBuffer();
        for (String key : aliveUrlToReportMap.keySet()) {
            aliveUrls.append(aliveUrlToReportMap.get(key).url + "\n");
        }
        return aliveUrls.toString();
    }

    public String reportAliveUrlMessagesDetailed() {
        StringBuffer aliveUrls = new StringBuffer();
        for (String key : aliveUrlToReportMap.keySet()) {
            UrlReport report = aliveUrlToReportMap.get(key);
            aliveUrls.append(report.url).append(":\n").append(report.report).append("\n").append(report.urlsFound).append("\n\n");
        }
        return aliveUrls.toString();
    }

    public void recordDeadUrl(String url, String result) {
        UrlReport report = new UrlReport();
        report.url = url;
        report.report = result;

        deadUrlToReportMap.put(url, report);
    }

    public boolean containsDeadUrlMessages() {
        return deadUrlToReportMap.size() > 0;
    }

    public String reportDeadUrlMessages() {
        StringBuffer deadUrls = new StringBuffer();
        for (String key : deadUrlToReportMap.keySet()) {
            deadUrls.append(deadUrlToReportMap.get(key).url + "\n");
        }
        return deadUrls.toString();
    }

    private String reportDeadUrlMessagesDetailed() {
        StringBuffer deadUrls = new StringBuffer();
        for (String key : deadUrlToReportMap.keySet()) {
            UrlReport report = deadUrlToReportMap.get(key);
            deadUrls.append(report.url).append(":\n").append(report.report).append("\n\n");
        }
        return deadUrls.toString();
    }

    public void report() {
        System.out.println(generateSummary());
        if (containsDeadUrlMessages()) {
            System.out.println(reportDeadUrlMessagesDetailed());
        }
    }

    private String generateSummary() {
        int nrOfAliveLinks = aliveUrlToReportMap.size();
        int nrOfDeadLinks = deadUrlToReportMap.size();
        return "\n\n#### Summary #### \n\n"
                + "Hunted down " + (nrOfAliveLinks + nrOfDeadLinks) + " urls: \n "
                + nrOfAliveLinks + " url(s) were eaten alive. \n "
                + nrOfDeadLinks + " url(s) were dead on arrival. \n\n"
                + "#### Alive URLs: #### \n\n"
                + reportAliveUrlMessages() + "\n"
                + "#### Dead URLs: #### \n\n"
                + reportDeadUrlMessages() + "\n";
    }

    class UrlReport {
        String url;
        String report;
        List<String> urlsFound = new ArrayList<>();
    }
}

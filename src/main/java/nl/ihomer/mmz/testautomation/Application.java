package nl.ihomer.mmz.testautomation;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        Journal journal = new Journal();
        Config config = new Config(Arrays.asList(args), journal);

        if (!config.isParseSuccess()){
            handleConfigurationException(journal);
        }

        new UrlPredator(config.getUrls(), config.getHost(), journal);
        journal.report();
    }

    private static void handleConfigurationException(Journal journal) {
        journal.reportErrorMessages();
        throw new IllegalArgumentException("The provided program arguments could not be parsed.");
    }

}

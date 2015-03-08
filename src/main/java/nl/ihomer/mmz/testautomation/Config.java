package nl.ihomer.mmz.testautomation;

import java.util.*;

public class Config {

    public final static String HOST_OPTION = "-host";
    public final static String PORT_OPTION = "-port";
    public final static String PATH_OPTION = "-path";

    private Journal journal;

    private String protocol = "http";

    private String host = "localhost";

    private String port = "80";

    private List<String> paths = new ArrayList<>();

    private boolean parseSuccess = true;

    public Config(List<String> args,  Journal journal) {
        this.journal = journal;
        parseArgs(args);
        if (paths.size() == 0) {
            setDefaultPath();
        }
    }

    private void parseArgs(List<String> args) {
        parseSuccess = true;

        ListIterator<String> argsIterator = args.listIterator();

        parse:
        while (argsIterator.hasNext()){
            String arg = argsIterator.next();
            switch (arg){
                case HOST_OPTION :
                    if (!argsIterator.hasNext()){
                        journal.recordParseErrorMessage("No value found for option '-host'. Please provide hostname like for instance 'localhost'");
                        parseSuccess = false;
                        break parse;
                    }
                    host = argsIterator.next();
                    break;
                case PORT_OPTION :
                    if (!argsIterator.hasNext()){
                        journal.recordParseErrorMessage("Please provide portnumber like for instance '80'");
                        parseSuccess = false;
                        break parse;
                    }
                    port = argsIterator.next();
                    break;
                case PATH_OPTION :
                    if (!argsIterator.hasNext()){
                        journal.recordParseErrorMessage("Please provide path like for instance '/api/v1/products'");
                        parseSuccess = false;
                        break parse;
                    }
                    paths.clear();
                    while(argsIterator.hasNext()) {
                        paths.add(argsIterator.next());
                    }
                    break parse;
                default :
                    journal.recordParseErrorMessage("unknown option or argument: " + arg);
                    parseSuccess = false;
            }
        }
    }

    private void setDefaultPath() {
        paths.add("");
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public List<String> getPaths() {
        return Collections.unmodifiableList(paths);
    }

    public List<String> getUrls(){
        List<String> result = new ArrayList<>();
        for (String path : paths){
            result.add(protocol + "://" + host + ":" + port + path + "/");
        }
        return result;
    }

    public boolean isParseSuccess(){
        return parseSuccess;
    }

}

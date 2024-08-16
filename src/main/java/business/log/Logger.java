package business.log;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final List<String> logHistory;

    private static Logger instance;

    private Logger() {
        logHistory = new ArrayList<>();
        System.out.println("Logger is initialized!");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void addToLogs(String logMessage) {
        getInstance().logHistory.add(logMessage);
    }

    public List<String> getLogHistory() {
        return getInstance().logHistory;
    }
}

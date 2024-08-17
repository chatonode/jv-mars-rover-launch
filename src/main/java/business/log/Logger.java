package business.log;

import common.enums.OperationResult;

import java.util.HashMap;
import java.util.Map;


public class Logger {
    private final Map<String, OperationResult> logHistory;

    private static Logger instance;

    private Logger() {
        logHistory = new HashMap<>();
        System.out.println("Logger is initialized!");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void addToLogs(String resourceId, OperationResult operation) {
        getInstance().logHistory.put(resourceId, operation);
    }

    public Map<String, OperationResult> getLogHistory() {
        return getInstance().logHistory;
    }
}

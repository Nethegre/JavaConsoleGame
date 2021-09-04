package util;

public class LogManager {

    private String className;

    public LogManager() {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        this.className = element.getMethodName();
    }

    public void info()
    {

    }

}

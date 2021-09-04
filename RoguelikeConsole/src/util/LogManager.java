package util;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.UUID;

public class LogManager {

    private final String methodName;
    private final String className;
    private final UUID uuid;
    private static final ConfigManager config = new ConfigManager();
    private boolean setupSuccessful = false;
    private final String logFileName;

    public LogManager(UUID uuid) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        this.methodName = element.getMethodName();
        this.className = element.getClassName();
        this.uuid = uuid;

        //Populate the log file name from the configManager
        logFileName = config.getConfigValue("LOGFILENAME");

        if (setupLogFile())
        {
            //Set the setup successful boolean so that future log attempts will happen
            setupSuccessful = true;
        }
    }

    private boolean setupLogFile()
    {
        boolean alreadyExists = false;
        boolean createdNew = false;

        try
        {
            File logFile = new File(config.getConfigValue(logFileName));
            if (logFile.exists())
            {
                //Don't create the log file because it exists already
                alreadyExists = true;
                System.out.println("[LogManager.setupLogFile] Log file exists already, continuing.");
            }
            else
            {
                if (logFile.createNewFile())
                {
                    //Create a new log file because it didn't exist
                    createdNew = true;
                    System.out.println("[LogManager.setupLogFile] Creating new log file.");
                }
                else
                {
                    //Creating the log file failed
                    System.out.println("[LogManager.setupLogFile] Failed to create new logfile.");
                }
            }
        }
        catch (Exception ex)
        {
            //Exception while setting up log file
            System.out.println("[LogManager.setupLogFile] Exception while creating/setting up logfile [" + ex.getMessage() + "]");
            ex.printStackTrace();
        }

        if (alreadyExists)
        {
            return true;
        }
        else
        {
            return createdNew;
        }
    }

    private boolean writeToLogFile(String msg)
    {
        boolean success = false;

        if (setupSuccessful)
        {
            try
            {
                FileWriter logWriter = new FileWriter(config.getConfigValue(logFileName));
                logWriter.write(msg);
                logWriter.close();
                success = true;
            }
            catch (Exception ex)
            {
                System.out.println("[LogManager.writeToLogFile] Exception while writing to logfile [" + ex.getMessage() + "]");
                ex.printStackTrace();
                success = false;
            }
        }

        return success;
    }

    public void info(String msg)
    {
        //General log format: [<timestamp>] <Log level> <class name>.<method name> "<uuid>" [<message>]
        String formattedMessage = "[" + LocalDateTime.now() + "] INFO " + className + "." + methodName + " \"" + uuid + "\" [" + msg + "]" ;
        writeToLogFile(formattedMessage);
    }

    public void warn(String msg)
    {
        //General log format: [<timestamp>] <Log level> <class name>.<method name> "<uuid>" [<message>]
        String formattedMessage = "[" + LocalDateTime.now() + "] WARN " + className + "." + methodName + " \"" + uuid + "\" [" + msg + "]" ;
        writeToLogFile(formattedMessage);
    }

    public void error(String msg)
    {
        //General log format: [<timestamp>] <Log level> <class name>.<method name> "<uuid>" [<message>]
        String formattedMessage = "[" + LocalDateTime.now() + "] ERROR " + className + "." + methodName + " \"" + uuid + "\" [" + msg + "]" ;
        writeToLogFile(formattedMessage);
    }

    public void debug(String msg)
    {
        //General log format: [<timestamp>] <Log level> <class name>.<method name> "<uuid>" [<message>]
        String formattedMessage = "[" + LocalDateTime.now() + "] DEBUG " + className + "." + methodName + " \"" + uuid + "\" [" + msg + "]" ;
        writeToLogFile(formattedMessage);
    }

}

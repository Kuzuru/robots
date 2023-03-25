package urfu.log;

/**
 * Logger is a simple logging utility that appends log messages to a default LogWindowSource
 */
public final class Logger
{
    private static final LogWindowSource defaultLogSource;

    static {
        defaultLogSource = new LogWindowSource(100);
    }

    private Logger()
    {
    }

    /**
     * Append a debug level log message to the default LogWindowSource
     *
     * @param strMessage the message to be logged
     */
    public static void debug(String strMessage)
    {
        defaultLogSource.append(LogLevel.Debug, strMessage);
    }

    /**
     * Append an error level log message to the default LogWindowSource
     *
     * @param strMessage the message to be logged
     */
    public static void error(String strMessage)
    {
        defaultLogSource.append(LogLevel.Error, strMessage);
    }

    /**
     * Get the default LogWindowSource used by this logger
     *
     * @return the default LogWindowSource
     */
    public static LogWindowSource getDefaultLogSource()
    {
        return defaultLogSource;
    }
}

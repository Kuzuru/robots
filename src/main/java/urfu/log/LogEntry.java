package urfu.log;

/**
 * LogEntry represents a single log entry, containing a log level and a message
 */
public final class LogEntry
{
    private final LogLevel m_logLevel;
    private final String m_strMessage;

    /**
     * Constructor for LogEntry with a log level and a message
     *
     * @param logLevel   the log level of the entry
     * @param strMessage the message of the entry
     */
    public LogEntry(LogLevel logLevel, String strMessage)
    {
        m_strMessage = strMessage;
        m_logLevel = logLevel;
    }

    /**
     * Get the message of the log entry
     *
     * @return the message of the log entry
     */
    public String getMessage()
    {
        return m_strMessage;
    }

    /**
     * Get the log level of the log entry
     *
     * @return the log level of the log entry
     */
    public LogLevel getLevel()
    {
        return m_logLevel;
    }
}

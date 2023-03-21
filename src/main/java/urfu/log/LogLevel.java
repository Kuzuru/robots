package urfu.log;

/**
 * LogLevel enumeration represents the severity levels of log messages.
 */
public enum LogLevel
{
    Trace(0),
    Debug(1),
    Info(2),
    Warning(3),
    Error(4),
    Fatal(5);

    private final int m_iLevel;

    /**
     * Constructor for LogLevel with an integer level.
     *
     * @param iLevel the integer value of the log level
     */
    LogLevel(int iLevel)
    {
        m_iLevel = iLevel;
    }

    /**
     * Get the integer value of the log level.
     *
     * @return the integer value of the log level
     */
    public int level()
    {
        return m_iLevel;
    }

    /**
     * Get the LogLevel enumeration member corresponding to the given integer value.
     *
     * @param iLevel the integer value of the log level
     *
     * @return the LogLevel enumeration member corresponding to the given integer value
     *
     * @throws IllegalArgumentException if the given integer value does not correspond to any LogLevel member
     */
    public static LogLevel fromInt(int iLevel)
    {
        for (LogLevel logLevel : values()) {
            if (logLevel.level() == iLevel) {
                return logLevel;
            }
        }
        throw new IllegalArgumentException("Invalid log level: " + iLevel);
    }
}

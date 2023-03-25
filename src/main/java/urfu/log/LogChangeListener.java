package urfu.log;

/**
 * LogChangeListener is an interface for listening to changes in a LogWindowSource
 */
public interface LogChangeListener
{
    /**
     * Called when a log change occurs in the observed LogWindowSource
     */
    void onLogChanged();
}

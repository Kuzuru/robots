package urfu.log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 1. Использовал WeakReference для слушателей, чтобы предотвратить утечку ресурсов
// 2. Реализовал круговой буфер для хранения сообщений журнала с ограниченным размером, основанным на m_iQueueLength
public class LogWindowSource
{
    private final List<WeakReference<LogChangeListener>> m_listeners;
    private final int m_iQueueLength;
    private final LogEntry[] m_messages;
    private int m_startIndex;
    private int m_endIndex;
    private boolean m_isFull;

    public LogWindowSource(int iQueueLength)
    {
        m_iQueueLength = iQueueLength;
        m_messages = new LogEntry[iQueueLength];
        m_listeners = new ArrayList<>();
        m_startIndex = 0;
        m_endIndex = 0;
        m_isFull = false;
    }

    public void registerListener(LogChangeListener listener)
    {
        synchronized (m_listeners) {
            m_listeners.add(new WeakReference<>(listener));
        }
    }

    public void unregisterListener(LogChangeListener listener)
    {
        synchronized (m_listeners) {
            m_listeners.removeIf(ref -> listener == ref.get());
        }
    }

    public void append(LogLevel logLevel, String strMessage)
    {
        LogEntry entry = new LogEntry(logLevel, strMessage);
        synchronized (m_messages) {
            m_messages[m_endIndex] = entry;
            m_endIndex = (m_endIndex + 1) % m_iQueueLength;

            if (m_isFull) {
                m_startIndex = (m_startIndex + 1) % m_iQueueLength;
            } else if (m_endIndex == m_startIndex) {
                m_isFull = true;
            }
        }

        List<LogChangeListener> activeListeners = new LinkedList<>();
        synchronized (m_listeners) {
            for (WeakReference<LogChangeListener> ref : m_listeners) {
                LogChangeListener listener = ref.get();
                if (listener != null) {
                    activeListeners.add(listener);
                }
            }
        }

        for (LogChangeListener listener : activeListeners) {
            listener.onLogChanged();
        }
    }

    public int size()
    {
        if (m_isFull) {
            return m_iQueueLength;
        } else {
            return m_endIndex;
        }
    }

    public Iterable<LogEntry> range(int startFrom, int count)
    {
        if (startFrom < 0 || startFrom >= size()) {
            return Collections.emptyList();
        }
        int indexTo = Math.min(startFrom + count, size());
        List<LogEntry> subList = new ArrayList<>(indexTo - startFrom);
        synchronized (m_messages) {
            for (int i = startFrom; i < indexTo; i++) {
                subList.add(m_messages[(m_startIndex + i) % m_iQueueLength]);
            }
        }
        return subList;
    }

    public Iterable<LogEntry> all()
    {
        return range(0, size());
    }
}

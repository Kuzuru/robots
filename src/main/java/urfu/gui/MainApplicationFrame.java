package urfu.gui;

import urfu.log.Logger;

import javax.swing.*;
import java.awt.*;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame()
    {
        // Make the big window be indented 50 pixel
        // from each edge of the screen
        int inset = 50;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds(
                inset,
                inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2
        );

        setContentPane(desktopPane);

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(600, 600);
        addWindow(gameWindow);

        setJMenuBar(new CustomMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());

        logWindow.setLocation(100, 100);

        setMinimumSize(logWindow.getSize());
        logWindow.pack();

        Logger.debug("Протокол работает");

        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }
}

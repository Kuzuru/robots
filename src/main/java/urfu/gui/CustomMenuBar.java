package urfu.gui;

import urfu.log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CustomMenuBar extends JMenuBar
{
    public CustomMenuBar()
    {
        add(createLookAndFeelMenu());
        add(createTestMenu());
    }

    private JMenu createLookAndFeelMenu()
    {
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription("Управление режимом отображения приложения");

        lookAndFeelMenu.add(createSystemLookAndFeelItem());
        lookAndFeelMenu.add(createCrossplatformLookAndFeelItem());

        return lookAndFeelMenu;
    }

    private JMenuItem createSystemLookAndFeelItem()
    {
        JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) ->
        {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        });
        return systemLookAndFeel;
    }

    private JMenuItem createCrossplatformLookAndFeelItem()
    {
        JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) ->
        {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        });
        return crossplatformLookAndFeel;
    }

    private JMenu createTestMenu()
    {
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription("Тестовые команды");

        testMenu.add(createAddLogMessageItem());

        return testMenu;
    }

    private JMenuItem createAddLogMessageItem()
    {
        JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) ->
        {
            Logger.debug("Новая строка");
        });
        return addLogMessageItem;
    }

    private void setLookAndFeel(String className)
    {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }
}
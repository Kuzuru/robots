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

    private enum LookAndFeelType
    {
        SYSTEM("Системная схема", UIManager.getSystemLookAndFeelClassName()),
        CROSS_PLATFORM("Универсальная схема", UIManager.getCrossPlatformLookAndFeelClassName());

        private final String displayName;
        private final String className;

        LookAndFeelType(String displayName, String className)
        {
            this.displayName = displayName;
            this.className = className;
        }
    }

    private JMenu createLookAndFeelMenu()
    {
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription("Управление режимом отображения приложения");

        for (LookAndFeelType type : LookAndFeelType.values()) {
            lookAndFeelMenu.add(createLookAndFeelItem(type));
        }

        return lookAndFeelMenu;
    }

    private JMenuItem createLookAndFeelItem(LookAndFeelType type)
    {
        JMenuItem lookAndFeelItem = new JMenuItem(type.displayName, KeyEvent.VK_S);
        lookAndFeelItem.addActionListener((event) -> setLookAndFeel(type.className));
        return lookAndFeelItem;
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
        addLogMessageItem.addActionListener((event) -> Logger.debug("Новая строка"));
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
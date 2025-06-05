package com.wg.item.other;

import javax.swing.*;

public class fff extends JFrame {

    public fff() {
        setTitle("Minecraft Chess");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 隐藏而非退出
        setLocationRelativeTo(null); // 窗口居中

    }



    public static void showWindow() {
        SwingUtilities.invokeLater(() -> {
            fff window = new fff();
            window.setVisible(true);
        });
    }

}

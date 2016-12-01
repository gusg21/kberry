package io.github.gusg21.kberry.supp;

import javax.swing.JOptionPane;

public class PopupHandler
{

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void errorBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "ERROR: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }
}
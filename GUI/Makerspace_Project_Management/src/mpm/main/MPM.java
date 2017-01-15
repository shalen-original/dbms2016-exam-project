/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.main;

import mpm.gui.LoginPanel;
import java.awt.*;
import javax.swing.*;
import mpm.data.entities.*;

/**
 *
 * @author remo
 */
public class MPM {
    
    public static JFrame frame;
    public static User currentUser;
    public static Project currentProject;

    /**
     * Main method of the program.
     * @param args unused.
     */
    public static void main(String[] args) {
       
        frame = new JFrame ("Makerspace Project Management");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 480));
        setPanel (new LoginPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);
        
    }
    
    /**
     * Used to refresh the content of the main frame, with the Panel passed to 
     * the method repainted on the frame.
     * Depending on whether the PAnel is of type {@link NewsPanel} or not, the
     * menu entries to export or show dataset properties are enabled/disabled.
     * @param panel New Panel to display on main frame, which is passed to the method.
     */
    public static void setPanel (JPanel panel)
    {
        JPanel contentPane = (JPanel) frame.getContentPane();

        contentPane.removeAll();
        contentPane.add(panel);
        contentPane.revalidate(); 
        contentPane.repaint();
        frame.pack();
    }
}

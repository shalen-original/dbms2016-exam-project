/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.main;

import mpm.gui.LoginPanel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import mpm.data.entities.*;

/**
 *
 * @author remo
 */
public class MPM {
    
    public static ArrayList<TestObject> testList;
    public static JFrame frame;
    public static String[] userList;
    public static User currentUser;
    public static Project currentProject;

    /**
     * Main method of the program.
     * @param args unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
       
        frame = new JFrame ("Makerspace Project Management");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640, 480));
        setPanel (new LoginPanel());
        frame.pack();
        frame.setVisible (true);
        
        testList = new ArrayList<TestObject>();
        
        for(int i=0; i<6; i++){
            userList = new String[2];
            userList[0] = "user"+i+"a";
            userList[1] = "user"+i+"b";
            testList.add(new TestObject("Projectname"+i, "some very descriptive "
                    +"stuff describing in a very descriptive way Project "+i, 
                    userList)); 
        }
    }
    
    /**
     * Used to refresh the content of the main frame, with the Panel passed to 
     * the method repainted on the frame. <p>
     * Depending on whether the PAnel is of type {@link NewsPanel} or not, the
     * menu entries to export or show dataset properties are enabled/disabled.
     * @param JPanel New Panel to display on main frame, which is passed to the method.
     * @return Nothing.
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

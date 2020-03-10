package Parkinglot;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.event.*; 
import java.util.*; 

public class CurrentTime extends JPanel implements ActionListener

{ 
      Calendar calendar1 = Calendar.getInstance(); 
      int hour = calendar1.get(Calendar.HOUR_OF_DAY); 
      int min = calendar1.get(Calendar.MINUTE); 
      int sec = calendar1.get(Calendar.SECOND); 

      javax.swing.Timer timer; 
      JLabel lbPresent; 

      public CurrentTime()

      { 
            timer = new javax.swing.Timer(1000, this); 
            timer.setInitialDelay(0); 
            timer.start(); 

            lbPresent = new JLabel(hour + "시" + min + "분 " + sec + "초", 
                                               Label.RIGHT); 
            add(lbPresent); 
      } 

      public void actionPerformed(ActionEvent e)

      { 
            ++sec; 
            Calendar calendar2 = Calendar.getInstance(); 
            hour = calendar2.get(Calendar.HOUR_OF_DAY); 
            min = calendar2.get(Calendar.MINUTE); 
            sec = calendar2.get(Calendar.SECOND); 
            lbPresent.setText(hour + "시" + min + "분 " + sec + "초"); 
      } 

      private static void createAndShowGUI()

      { 
            JFrame.setDefaultLookAndFeelDecorated(true); 
            JFrame frame = new JFrame("TimerTest"); 
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            CurrentTime timerTest = new CurrentTime(); 
            timerTest.setOpaque(true); 
            frame.setContentPane(timerTest); 
            frame.pack(); 
            frame.setVisible(true); 
      } 

      public static void main(String[] args)

      { 
            javax.swing.SwingUtilities.invokeLater(new Runnable()

                                                                   {public void run(){createAndShowGUI();}}); 
      } 
}
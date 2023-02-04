package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class GasBookingSystem extends JFrame implements ActionListener{
    
    GasBookingSystem(){
//        setSize(1024,655);
//        setLocation(270, 100);
          setLayout(null);
          setBounds(350,150,1024,655);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/1.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0,0,1024,655);
        add(image);
        
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("Icons/2.jpeg"));
        JLabel image2 = new JLabel(i2);
        image2.setBounds(10,10,550,550);
        image.add(image2);
        
        
 
        JLabel text = new JLabel ("GAS BOOKING SYSTEM");
        text.setBounds(30, 10,1000,90);
        image.add(text);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.BOLD, 65));
        
        JButton next = new JButton("Next");
        next.setBackground(Color.red);
        next.setBounds(200,500,150,50);
        next.setForeground(Color.yellow);
        next.setFont(new Font("ariel", Font.BOLD, 30));
        next.addActionListener(this);
        image.add(next);
        
        setVisible(true);
        
         while(true)
        {
            text.setVisible(false);
            try{
                Thread.sleep(400);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            text.setVisible(true);
            
            try{
                Thread.sleep(400);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        
        new GasBookingSystem();
    }
    
}


package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.*;

public class Delivery extends JFrame implements ActionListener{
    
    JTable table ;
    JButton back;
    
    Delivery()
    {
        getContentPane().setBackground(Color.white);
      
     JLabel text = new JLabel("Delivery Agents");
        text.setBounds(210,5,300,35);
        text.setFont(new Font("Raleway", Font.BOLD,30));
        text.setForeground(Color.RED);
        add(text);
      
      JLabel l1 = new JLabel("Agent-ID");
      l1.setBounds(30,60,80,20);
      add(l1);
      
      JLabel l2 = new JLabel("Name");
      l2.setBounds(130,60,80,20);
      add(l2);
      
      JLabel l3 = new JLabel("Age");
      l3.setBounds(240,60,80,20);
      add(l3);
      
      JLabel l4 = new JLabel("Gender");
      l4.setBounds(350,60,80,20);
      add(l4);
      
      JLabel l5 = new JLabel("Phone");
      l5.setBounds(450,60,80,20);
      add(l5);
      
      JLabel l6 = new JLabel("Vehicle");
      l6.setBounds(550,60,100,20);
      add(l6);
    
      
      

      table = new JTable();
      table.setBounds(0,90,650,250);
      add(table);
      
      try{
          Conn c = new Conn();
          ResultSet rs = c.s.executeQuery("select * from delivery");
          table.setModel(DbUtils.resultSetToTableModel(rs));
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }

      back = new JButton("back");
      back.setBackground(Color.black);
      back.setForeground(Color.white);
      back.setBounds(250,370,120,30);
      back.addActionListener(this);
      add(back);
      
      setLayout(null);
      setBounds(400,250,650,450);  
      setVisible(true);
      
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Booking();
    } 
    
    public static void main(String [] args)
    {
        new Delivery();
    }
}

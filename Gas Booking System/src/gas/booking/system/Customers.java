package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.DbUtils;

public class Customers extends JFrame implements ActionListener{
    
    JTable table ;
    JButton back;
    
    Customers()
    {
        getContentPane().setBackground(Color.white);
      
     JLabel text = new JLabel("Customer Details");
        text.setBounds(350,5,300,35);
        text.setFont(new Font("Raleway", Font.BOLD,30));
        text.setForeground(Color.RED);
        add(text);
      
      JLabel l1 = new JLabel("Customer-ID");
      l1.setBounds(15,60,80,20);
      add(l1);
      
      JLabel l2 = new JLabel("Document");
      l2.setBounds(115,60,80,20);
      add(l2);
      
      JLabel l3 = new JLabel("Phone");
      l3.setBounds(215,60,80,20);
      add(l3);
      
      JLabel l4 = new JLabel("Name");
      l4.setBounds(315,60,80,20);
      add(l4);
      
      JLabel l5 = new JLabel("Gender");
      l5.setBounds(415,60,80,20);
      add(l5);
      
      JLabel l6 = new JLabel("Country");
      l6.setBounds(515,60,100,20);
      add(l6);
      
      JLabel l7 = new JLabel("Place");
      l7.setBounds(615,60,100,20);
      add(l7);
      
      JLabel l8 = new JLabel("Time");
      l8.setBounds(715,60,100,20);
      add(l8);
      
      JLabel l9 = new JLabel("Deposit");
      l9.setBounds(815,60,100,20);
      add(l9);
      

      table = new JTable();
      table.setBounds(15,90,900,400);
      add(table);
      
      try{
          Conn c = new Conn();
          ResultSet rs = c.s.executeQuery("select * from customer");
          table.setModel(DbUtils.resultSetToTableModel(rs));
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }
        
    
      back = new JButton("back");
      back.setBackground(Color.black);
      back.setForeground(Color.white);
      back.setBounds(400,500,120,30);
      back.addActionListener(this);
      add(back);
      
      setLayout(null);
      setBounds(300,200,950,600);  
      setVisible(true);
      
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Booking();
    } 
    
    public static void main(String [] args)
    {
        new Customers();
    }
}

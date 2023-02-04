
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.*;

public class Connections extends JFrame implements ActionListener{
    
    JTable table ;
    JButton back;
    Connections()
    {
      
      getContentPane().setBackground(Color.white);
      
        JLabel text = new JLabel("Connection Details");
        text.setBounds(120,5,300,30);
        text.setFont(new Font("Raleway", Font.BOLD,25));
        text.setForeground(Color.RED);
        add(text);
        
        ImageIcon i1 = new ImageIcon (ClassLoader.getSystemResource("Icons/9.jpg"));
      Image i2 = i1.getImage().getScaledInstance(580,600,Image.SCALE_DEFAULT);
      ImageIcon i3 = new ImageIcon(i2);
      JLabel Image = new JLabel(i3);
      Image.setBounds(500,0,540,550);
      add(Image);
      
      JLabel l1 = new JLabel("Area-ID");
      l1.setBounds(5,35,60,20);
      add(l1);
      
      JLabel l2 = new JLabel("Place");
      l2.setBounds(80,35,60,20);
      add(l2);
      
      JLabel l3 = new JLabel("Area Name");
      l3.setBounds(165,35,80,20);
      add(l3);
      
      JLabel l4 = new JLabel("Pin-Code");
      l4.setBounds(250,35,60,20);
      add(l4);
      
      JLabel l5 = new JLabel("Phone No.");
      l5.setBounds(335,35,80,20);
      add(l5);
      
      JLabel l6 = new JLabel("Connections");
      l6.setBounds(420,35,100,20);
      add(l6);
      
      table = new JTable();
      table.setBounds(0,60,500,400);
      add(table);
      
      try{
          Conn c = new Conn();
          ResultSet rs = c.s.executeQuery("select * from connections");
          table.setModel(DbUtils.resultSetToTableModel(rs));
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }

      back = new JButton("back");
      back.setBackground(Color.black);
      back.setForeground(Color.white);
      back.setBounds(200,500,120,30);
      back.addActionListener(this);
      add(back);
      
      setLayout(null);
      setBounds(300,200,1050,600);  
      setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        new Booking();
    } 
    
    public static void main(String [] args)
    {
        new Connections();
    }
    }
    
    


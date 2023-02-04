
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.*;

public class BookingStatus extends JFrame implements ActionListener{
    
    JTable table ;
    Choice cid;
    JButton search, cancel;
    JCheckBox available;
    
    BookingStatus()
    {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(330,200,800,600);
        setVisible(true);
        
        JLabel text = new JLabel("Search Booking Status");
        text.setBounds(200,10,400,35);
        text.setFont(new Font("Raleway", Font.BOLD,30));
        text.setForeground(Color.RED);
        add(text);
        
        JLabel lblid = new JLabel("Customer-ID");
        lblid.setBounds(80,80,150,25);
        add(lblid);
        
        cid = new Choice();
        
        try{
            Conn conn = new Conn();
            String query = "select * from customer";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next())
            {
                cid.add(rs.getString("Customer_id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        cid.setBounds(250,80,150,25);
        add(cid);
        
        
        available = new JCheckBox("Only Display Pending");
        available.setBounds(440,80,150,20);
        add(available);
        
        table = new JTable();
        table.setBounds(0,170,800,350);
        add(table);
        
        try{
          Conn c = new Conn();
          ResultSet rs = c.s.executeQuery("select * from cylinder ");
          table.setModel(DbUtils.resultSetToTableModel(rs));
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }
        
        search = new JButton("Search");
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        search.setBounds(280,520,100,25);
        add(search);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(430,520,100,25);
        add(cancel);
        
        
        JLabel lblbid = new JLabel("Booking_ID");
        lblbid.setBounds(10,130,150,20);
        add(lblbid);
        
        JLabel lblcid = new JLabel("Customer_ID");
        lblcid.setBounds(90,130,150,20);
        add(lblcid);
        
        JLabel lblloc = new JLabel("Name");
        lblloc.setBounds(200,130,150,20);
       
        add(lblloc);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(280,130,150,20);
        add(lblphone);
        
        JLabel lblmethod = new JLabel("Method");
        lblmethod.setBounds(360,130,150,20);
        add(lblmethod);
        
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(450,130,150,20);
        add(lblcountry);
        
        JLabel lblplace = new JLabel("Place");
        lblplace.setBounds(540,130,150,20);
        add(lblplace);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(630,130,150,20);
        add(lblmonth);

        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(720,130,150,20);
        add(lblstatus); 
        
        search.addActionListener(this);
        cancel.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== search)
        {
            try{
            Conn conn = new Conn();
            String query = "select * from cylinder where customer_id = '" + cid.getSelectedItem()+"'";
            String query2 = "select * from cylinder where payment = 'Pending' AND customer_id = '" + cid.getSelectedItem()+"'";
       
            ResultSet rs;
            if(available.isSelected())
            {
                rs = conn.s.executeQuery(query2);
            }else
            {
                 rs = conn.s.executeQuery(query);
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
       
        }catch(Exception e){
            e.printStackTrace();
        } 
        }else if(ae.getSource()==cancel){
            setVisible(false);
            new Booking();
    }
    }
    
    public static void main(String [] args)
    {
        new BookingStatus();
    }
}

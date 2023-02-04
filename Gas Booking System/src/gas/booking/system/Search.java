
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.*;

public class Search extends JFrame implements ActionListener{
    
    JTable table ;
    JComboBox cblocation;
    JButton search, cancel;
    JCheckBox available;
    
    Search()
    {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(450,200,600,500);
        setVisible(true);
        
        JLabel text = new JLabel("Search Connection");
        text.setBounds(150,0,300,35);
        text.setFont(new Font("Raleway", Font.BOLD,30));
        text.setForeground(Color.RED);
        add(text);
        
        JLabel lblplace = new JLabel("Location");
        lblplace.setBounds(80,60,150,25);
        add(lblplace);
        
        String str[] = {"West", "North","East", "South", "South West", "North West", "North East"};
        cblocation = new JComboBox(str);
        cblocation.setBounds(180,60,150,20);
        add(cblocation);
        
        available = new JCheckBox("Only Display Available");
        available.setBounds(400,60,150,20);
        add(available);
        
        table = new JTable();
        table.setBounds(0,140,600,250);
        add(table);
        
        try{
          Conn c = new Conn();
          ResultSet rs = c.s.executeQuery("select * from connections ");
          table.setModel(DbUtils.resultSetToTableModel(rs));
          
      }catch(Exception e)
      {
          e.printStackTrace();
      }
        
        search = new JButton("Search");
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        search.setBounds(180,400,100,25);
        add(search);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(320,400,100,25);
        add(cancel);
        
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(30,100,150,20);
        add(lblid);
        
        JLabel lblloc = new JLabel("Name");
        lblloc.setBounds(120,100,150,20);
       
        add(lblloc);
        
        JLabel lblarea = new JLabel("Area");
        lblarea.setBounds(240,100,150,20);
        add(lblarea);
        
        JLabel lblpincode = new JLabel("Pin-Code");
        lblpincode.setBounds(320,100,150,20);
        add(lblpincode);
        
        
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(420,100,150,20);
        add(lblphone);
        
    
        JLabel lblcon = new JLabel("Available");
        lblcon.setBounds(510,100,150,20);
        add(lblcon);
        
        search.addActionListener(this);
        cancel.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== search)
        {
            try{
                int count=0;
            Conn conn = new Conn();
            String query = "select * from connections where place = '" + cblocation.getSelectedItem()+"'";
            String query2 = "select * from connections where con_num != 0 AND place = '" + cblocation.getSelectedItem()+"'";
       
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
        new Search();
    }
}

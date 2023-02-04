
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener{
    
    JComboBox cbid;
    JTextField tfnumber, tfname, tfcountry, tfdeposit;
    JRadioButton rmale, rfemale;
    Choice cconn;
    JLabel tftime;
    JButton add, back;
    
    
    AddCustomer()
    {
        getContentPane().setBackground(Color.white);
        
        JLabel text = new JLabel("New Customer Form");
        text.setBounds(100,20,300,30);
        text.setFont(new Font("Raleway", Font.BOLD,25));
        text.setForeground(Color.RED);
        add(text);
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(35,80,100,20);
        lblid.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblid);
        
        String options[] = {"Adhaar Card", "Passport", "Driving Lisence", "Voter-id"};
        cbid = new JComboBox(options);
        cbid.setBounds(200,80,150,25);
        cbid.setBackground(Color.white);
        add(cbid);
        
        JLabel lblnumber = new JLabel("Doc-Number");
        lblnumber.setBounds(35,120,130,20);
        lblnumber.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200,120,150,25);
        add(tfnumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,160,100,20);
        lblname.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);
        
         JLabel lblbgender = new JLabel("Gender");
        lblbgender.setBounds(35,200,100,20);
        lblbgender.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblbgender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.white);
        rmale.setBounds(200,200,60,25);
        add(rmale);
        
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.white);
        rfemale.setBounds(270,200,90,25);
        add(rfemale);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(35,240,100,25);
        lblcountry.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);
        
        JLabel lblconnection = new JLabel("Place");
        lblconnection.setBounds(35,280,150,20);
        lblconnection.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblconnection);
        
        cconn = new Choice();
        
        try{
            Conn conn = new Conn();
            String query = "select * from connections where con_num != 0";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next())
            {
                cconn.add(rs.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        cconn.setBounds(200,280,150,25);
        add(cconn);
        
        JLabel lbltime = new JLabel("Booking-Time");
        lbltime.setBounds(35,320,150,25);
        lbltime.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbltime);
        
        Date date = new Date();
        tftime = new JLabel("" + date);
        tftime.setBounds(200,320,200,25);
        tftime.setFont(new Font("Raleway", Font.PLAIN, 12));
        add(tftime);
        
         JLabel lbldeposit = new JLabel("Number");
        lbldeposit.setBounds(35,360,100,25);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lbldeposit);
        
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200,360,150,25);
        add(tfdeposit);
        
        add = new JButton("Add");
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.setBounds(60,410,120,30);
        add(add);
        add.addActionListener(this);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(220,410,120,30);
        back.addActionListener(this);

        add(back);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/8.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450,50,300,400);
        add(image);
        
        setBounds(350,200,800,500);
        setLayout(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == add)
        {
            String id = (String) cbid.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;
            
            if(rmale.isSelected())
            {
                gender ="Male";
            } else {
                gender = "Female";
            }
            
            String country = tfcountry.getText();
            String con = cconn.getSelectedItem();
            String time = tftime.getText();
            String deposit = tfdeposit.getText();
            
            if(name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Name cannot be null");
                return ;
            }
            
             else if(number.length()!=12)
            {
                JOptionPane.showMessageDialog(null, "Invalid Phone number");
                return ;
            }
             
             else if(deposit.length()!=10)
            {
                JOptionPane.showMessageDialog(null, "Deposit cannot be Zero");
                return ;
            }
            
            
            try{
                 Conn conn = new Conn();
                String query3 = "select * from customer ";
            ResultSet rs = conn.s.executeQuery(query3);
            while(rs.next())
            {
                String a= rs.getString("doc_number");
                String b= rs.getString("number");
                if(a.equals(number))
                {
                   JOptionPane.showMessageDialog(null, "Duplicate Adhaar Number");
                   return ;
                }
                
                if(b.equals(deposit))
                {
                   JOptionPane.showMessageDialog(null, "Duplicate Phone Number");
                   return ;
                }
            }
            String query = "insert into customer(document,doc_number, name,gender,country, place,time,number) values('" + id + "', '" + number + "', '" + name + "', '" + gender + "', '" + country + "', '" + con + "','" + time + "','" + deposit + "')";
            String query2 = "update connections set con_num = con_num - 1 where name = '" + con + "'";
            
       
            
            conn.s.executeUpdate(query);
            conn.s.executeUpdate(query2);
            
            JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
            setVisible(false);
            new Booking();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == back)
        {
            setVisible(false);
            new Booking();
        }
    }
    
    public static void main(String [] args)
    {
        new AddCustomer();
    }
}

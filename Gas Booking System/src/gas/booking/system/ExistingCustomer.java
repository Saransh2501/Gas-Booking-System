
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;


public class ExistingCustomer extends JFrame implements ActionListener{
    
    Choice cid;
    JTextField tfname,tfnumber,tfgender, tfplace,tfcountry;
    JButton search, book , cancel;
    JComboBox cbmonth, cbpayment;
       
    ExistingCustomer()
    {
        
        getContentPane().setBackground(Color.white);
        
        JLabel text = new JLabel("Book Gas Cylinder");
        text.setBounds(100,20,300,30);
        text.setFont(new Font("Raleway", Font.BOLD,25));
        text.setForeground(Color.RED);
        add(text);
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(35,80,100,20);
        lblid.setFont(new Font("Raleway", Font.PLAIN,20));
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
        cid.setBounds(200,80,150,25);
        add(cid);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35,120,300,20);
        lblname.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblname);
        
        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(35,160,300,20);
        lblnumber.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblnumber);
        
        JLabel lblpayment = new JLabel("Payment");
        lblpayment.setBounds(35,200,300,20);
        lblpayment.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblpayment);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(35,240,300,25);
        lblcountry.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblcountry);
        
        JLabel lblplace = new JLabel("Place");
        lblplace.setBounds(35,280,300,20);
        lblplace.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblplace);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35,320,300,20);
        lblmonth.setFont(new Font("Raleway", Font.PLAIN,20));
        add(lblmonth);
        
        String options[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        cbmonth = new JComboBox(options);
        cbmonth.setBounds(200,320,150,25);
        cbmonth.setBackground(Color.white);
        add(cbmonth);
        
        String options2[] = {"Credit Card", "Debit Card", "UPI","Cash"};
        cbpayment = new JComboBox(options2);
        cbpayment.setBounds(200,200,150,25);
        cbpayment.setBackground(Color.white);
        add(cbpayment);
        
        tfname = new JTextField();
        tfname.setBounds(200,120,150,25);
        add(tfname);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200,160,150,25);
        add(tfnumber);
        
        tfplace = new JTextField();
        tfplace.setBounds(200,280,150,25);
        add(tfplace);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200,240,150,25);
        add(tfcountry);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/8.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450,50,300,400);
        add(image);
        
        search = new JButton("Search User");
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        search.setBounds(20,400,120,30);
        search.addActionListener(this);
        add(search);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(280,400,120,30);
        cancel.addActionListener(this);
        add(cancel);
        
        book = new JButton("Book");
        book.setBackground(Color.black);
        book.setForeground(Color.white);
        book.setBounds(150,400,120,30);
        book.addActionListener(this);
        add(book);
        
        setBounds(350,200,800,500);
        setLayout(null);
        setVisible(true);        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== search)
        {
            try{
            Conn conn = new Conn();
            String query = "select * from customer where customer_id = '" + cid.getSelectedItem()+"'";
       
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next())
            {
                tfname.setText(rs.getString("name"));
                tfnumber.setText(rs.getString("number"));
                tfcountry.setText(rs.getString("country"));
                tfplace.setText(rs.getString("place"));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        }else if(ae.getSource()==cancel){
            setVisible(false);
            new Booking();
        }else if(ae.getSource()== book){
            String ids = (String)cid.getSelectedItem();
            int id = Integer.parseInt(ids);
            String number = tfnumber.getText();
            String name = tfname.getText();
            String payment = (String)cbpayment.getSelectedItem();
            String month = (String)cbmonth.getSelectedItem();
            String country = tfcountry.getText();
            String place = tfplace.getText();
            
            try{
            String query = "insert into cylinder(customer_id,name,number,pay_method,country,place,month) values('" + id + "', '" + name + "', '" + number + "', '" + payment + "', '" + country + "', '" + place + "','" + month + "')";            
            String query2 ="select * from cylinder where customer_id= '" + id +"'";
            String query3 = "insert into payment(customer_id,name,month,pay_method) values('" + id + "', '" + name + "', '" + month + "', '" + payment + "')";            
            Conn conn = new Conn();
            
            ResultSet rs = conn.s.executeQuery(query2);
            while(rs.next())
            {
                String m = rs.getString("month");
                if(m.equals(month))
                {
                    JOptionPane.showMessageDialog(null, "You cannot book 2 Cylinders in a Month");
                    setVisible(false);
                    new Booking();
                    return ;
                    
                }
            }
            
            conn.s.executeUpdate(query3);
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Cylinder Booked Successfully");
            setVisible(false);
            new Booking();
            
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    
    public static void main(String [] args)
    {
        new ExistingCustomer();
    }
}


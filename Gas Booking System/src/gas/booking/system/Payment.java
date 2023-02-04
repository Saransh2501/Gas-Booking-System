
package gas.booking.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Payment extends JFrame implements ActionListener{
    
    JComboBox cbmonth;
    JTextField tfid, tfcard, tfM,tfY,tfname;
    JPasswordField pass;
    JButton submit, cancel;
    
    Payment()
    {
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(500,250,500,400);
        setVisible(true);
        
        JLabel text = new JLabel("Payment Portal");
        text.setBounds(130,10,400,35);
        text.setFont(new Font("Raleway", Font.BOLD,30));
        text.setForeground(Color.RED);
        add(text);
        
        JLabel lblid = new JLabel("Customer-ID");
        lblid.setBounds(80,80,150,25);
        add(lblid);
        
        tfid = new JTextField();
        tfid.setBounds(250,80,150,25);
        add(tfid);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(80,110,150,25);
        add(lblmonth);
        
        String options[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        cbmonth = new JComboBox(options);
        cbmonth.setBounds(250,110,150,25);
        cbmonth.setBackground(Color.white);
        add(cbmonth);
        
        JLabel lblcard = new JLabel("Card Number");
        lblcard.setBounds(80,140,150,25);
        add(lblcard);
        
        tfcard = new JTextField();
        tfcard.setBounds(250,140,150,25);
        add(tfcard);
        
        JLabel lblname = new JLabel("Name on Card");
        lblname.setBounds(80,170,150,25);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(250,170,150,25);
        add(tfname);
        
        JLabel lbldate = new JLabel("Expiry Date");
        lbldate.setBounds(80,200,150,25);
        add(lbldate);
        
        JLabel lblM = new JLabel("MM");
        lblM.setBounds(250,200,30,25);
        add(lblM);
        
        JLabel lblY = new JLabel("YY");
        lblY.setBounds(340,200,30,25);
        add(lblY);
        
        tfM = new JTextField();
        tfM.setBounds(280,200,50,25);
        add(tfM);
        
        tfY = new JTextField();
        tfY.setBounds(360,200,50,25);
        add(tfY);
        
        JLabel lblcvv = new JLabel("CVV");
        lblcvv.setBounds(80,230,150,25);
        add(lblcvv);
        
        pass = new JPasswordField();
        pass.setBounds(250,230,150,25);
        add(pass);
        
        submit = new JButton("Pay");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setBounds(80,290,150,25);
        submit.addActionListener(this);
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(260,290,150,25);
        cancel.addActionListener(this);
        add(cancel);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit){
            String month = (String)cbmonth.getSelectedItem();
            
            if(tfid.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null, "Invalid Customer Id");
                return ;
            }
            int id = Integer.parseInt(tfid.getText());
                
            if(tfcard.getText().length()!=12)
            {
                JOptionPane.showMessageDialog(null, "Invalid Card Number");
                return ;
            }
            
            else if(tfname.getText().length()==0)
            {
                JOptionPane.showMessageDialog(null, "Invalid Card Name");
                return ;
            }
            
            else if(tfM.getText().length()!=2 || tfY.getText().length()!=2)
            {
                JOptionPane.showMessageDialog(null, "Invalid Expiry");
                return ;
            }
            try{
                
                String query = "select * from payment where customer_id = '" + id + "'" ;
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery(query);
                Boolean flag = false;
                while(rs.next())
                {
                    String m = rs.getString("month");
                    if(m.equals(month))
                    {
                        flag = true;
                    }
                }
                if(flag.equals(false))
                {
                    JOptionPane.showMessageDialog(null, "You Don't have any booking in that month");

                    return ;
                }
                
                String query2 = "update cylinder set payment = 'Paid' where month = '" + month + "' AND customer_id = '" + id +"'";
                String query3 = "update payment set payment = 'Paid' where month = '" + month + "' AND customer_id = '" + id +"'";
                
                conn.s.executeUpdate(query3);
                conn.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Payment Done");
                setVisible(false);
                new Booking();
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }else if(ae.getSource()==cancel)
        {
            setVisible(false);
            new Booking();
        }
    }
    
    
    public static void main(String [] args)
    {
        new Payment();
    }
    
    
}

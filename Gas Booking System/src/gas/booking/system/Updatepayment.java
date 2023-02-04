
package gas.booking.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Updatepayment extends JFrame implements ActionListener{
    
    JComboBox cbmonth;
    JTextField tfid, tfcard, tfdate,tfname;
    JPasswordField pass;
    JButton submit, cancel;
    
    Updatepayment()
    {
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(500,250,500,400);
        setVisible(true);
        
        JLabel text = new JLabel("Payment Status");
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
        
        
        submit = new JButton("Update");
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
            int id = Integer.parseInt(tfid.getText());

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
                    JOptionPane.showMessageDialog(null, "Don't have any booking in that month");

                    return ;
                }
                
                String query2 = "update cylinder set payment = 'Paid' where month = '" + month + "' AND customer_id = '" + id +"'";
                String query3 = "update payment set payment = 'Paid' where month = '" + month + "' AND customer_id = '" + id +"'";
                
                conn.s.executeUpdate(query3);
                conn.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Payment Done");
                setVisible(false);
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }else if(ae.getSource()==cancel)
        {
            setVisible(false);
        }
    }
    
    
    public static void main(String [] args)
    {
        new Updatepayment();
    }
    
    
}

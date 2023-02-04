
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class AddConnection extends JFrame implements ActionListener{
    
    JTextField tfname,tfnumber, tfphone, tfpincode;
    JComboBox cbpincode, cbplaces;
    JButton submit, cancel;
    
    AddConnection()
    {
        setLayout(null);
        setBounds(300,200,1000,500);
        
        JLabel heading = new JLabel("Add Connection");
        heading.setFont(new Font("Tahoma", Font.BOLD,20));
        heading.setBounds(150,20,200,30);
        add(heading);
        
        JLabel lblname = new JLabel ("Place");
        lblname.setFont(new Font("Tahoma",Font.BOLD,16));
        lblname.setBounds(60,80,120,30);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(180,80,170,30);
        add(tfname);
        
        JLabel lblavailable = new JLabel ("Pin-Code");
        lblavailable.setFont(new Font("Tahoma",Font.BOLD,16));
        lblavailable.setBounds(60,130,120,30);
        add(lblavailable);
        
        tfpincode = new JTextField();
        tfpincode.setBounds(180,130,170,30);
        add(tfpincode);
        
        JLabel lblplace = new JLabel ("Area");
        lblplace.setBounds(60,180,120,30);
        lblplace.setFont(new Font("Tahoma", Font.BOLD,16));
        
        add(lblplace);
        
        String str[] = {"West", "North","East", "South", "South West", "North West", "North East"};
        cbplaces = new JComboBox(str);
        cbplaces.setBounds(180,180,170,30);
        cbplaces.setBackground(Color.white);
        add(cbplaces);
        
        JLabel lblphone = new JLabel ("Phone");
        lblphone.setFont(new Font("Tahoma",Font.BOLD,16));
        lblphone.setBounds(60,230,120,30);
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(180,230,170,30);
        add(tfphone);
        
        JLabel lblnumber = new JLabel ("Connections");
        lblnumber.setBounds(60,280,120,30);
        lblnumber.setFont(new Font("Tahoma", Font.BOLD,16));
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(180,280,120,30);
        add(tfnumber);
        
        submit = new JButton ("ADD");
        submit.setFont(new Font("Tahome", Font.BOLD,16));
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setBounds(80,350,120,30);
        submit.addActionListener(this);
        add(submit);
        
        cancel = new JButton ("CANCEL");
        cancel.setFont(new Font("Tahome", Font.BOLD,16));
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.setBounds(250,350,120,30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/7.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i1);
        image.setBounds(450,-20,480,500);
        add(image);
        setVisible(true);
    }
    
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==submit)
        {
            String name = tfname.getText();
            String number = tfnumber.getText();
            String phone = tfphone.getText();
            String pincode = tfpincode.getText();
            String place = (String)cbplaces.getSelectedItem();
            
            if(name.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Name cannot be null");
                return ;
            }
            
             else if(phone.length()!=10)
            {
                JOptionPane.showMessageDialog(null, "Invalid Phone number");
                return ;
            }
             
             else if(pincode.length()!=6)
            {
                JOptionPane.showMessageDialog(null, "Invalid Pin-Code");
                return ;
            }
            
            else{
        try{
            Conn conn = new Conn();
            
            String query = "insert into connections(name,place, pin_code,phone,con_num) values('" + name + "', '" + place + "', '" + pincode + "', '" + phone + "', '" + number + "')";
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Data added Successfully");
            setVisible(false);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
            
            
        }else if(ae.getSource()==cancel)
        {
            setVisible(false);
        }

        }
    
    public static void main(String [] args)
    {
        new AddConnection();
    }
    
}

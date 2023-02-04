
package gas.booking.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Deliveryagents extends JFrame implements ActionListener{
    
    
    JTextField tfname,tfage, tfphone;
    JComboBox cbjob, cbvehicle;
    JButton submit, cancel;
    
    Deliveryagents()
    {
        setLayout(null);
        setBounds(300,200,980,470);
        
        JLabel heading = new JLabel("Add Agents");
        heading.setFont(new Font("Tahoma", Font.BOLD,20));
        heading.setBounds(150,20,200,30);
        add(heading);
        
        JLabel lblname = new JLabel ("Name");
        lblname.setFont(new Font("Tahoma",Font.BOLD,16));
        lblname.setBounds(60,80,120,30);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(180,80,170,30);
        add(tfname);
        
        JLabel lblage = new JLabel ("Age");
        lblage.setFont(new Font("Tahoma",Font.BOLD,16));
        lblage.setBounds(60,130,120,30);
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(180,130,170,30);
        add(tfage);
        
        JLabel lblgender = new JLabel ("Gender");
        lblgender.setBounds(60,180,120,30);
        lblgender.setFont(new Font("Tahoma", Font.BOLD,16));
        
        add(lblgender);
        
        String str[] = {"Male", "Female","Others"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(180,180,170,30);
        cbjob.setBackground(Color.white);
        add(cbjob);
        
        JLabel lblphone = new JLabel ("Phone");
        lblphone.setFont(new Font("Tahoma",Font.BOLD,16));
        lblphone.setBounds(60,230,120,30);
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(180,230,170,30);
        add(tfphone);
        
        JLabel lblvehicle = new JLabel ("Vehicle");
        lblvehicle.setBounds(60,280,120,30);
        lblvehicle.setFont(new Font("Tahoma", Font.BOLD,16));
        add(lblvehicle);
        
        String str2[] = {"Cycle", "Tempo","Bike"};
        cbvehicle = new JComboBox(str2);
        cbvehicle.setBounds(180,280,170,30);
        cbvehicle.setBackground(Color.white);
        add(cbvehicle);
        
        submit = new JButton ("Add Driver");
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
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/6.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450,30,500,350);
        add(image);
        setVisible(true);
        
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
            String name = tfname.getText();
            String age = tfage.getText();
            String phone = tfphone.getText();
            String gender = (String)cbjob.getSelectedItem();
            String vehicle = (String)cbvehicle.getSelectedItem();
            
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
            
            else{
        try{
            Conn conn = new Conn();
            
            String query = "insert into delivery(name,age, gender,phone,vehicle) values('" + name + "', '" + age + "', '" + gender + "', '" + phone + "', '" + vehicle + "')";
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
        new Deliveryagents();
    }
}

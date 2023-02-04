
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Booking extends JFrame implements ActionListener{
    
    JButton newCustomer, connections, existing, allEmployee, customers,managerInfo;
    JButton payment, update, delivery, search,logout;
    Booking()
    {
        
        getContentPane().setBackground(Color.white);
        
        newCustomer = new JButton("New Customer Form");
        newCustomer.setBounds(10,30,200,30);
        newCustomer.setBackground(Color.black);
        newCustomer.setForeground(Color.white);
        newCustomer.addActionListener(this);
        add(newCustomer);
        
        connections = new JButton("Connections");
        connections.setBounds(10,70,200,30);
        connections.setBackground(Color.black);
        connections.setForeground(Color.white);
        connections.addActionListener(this);
        add(connections);
        
        existing = new JButton("Existing Customer");
        existing.setBounds(10,110,200,30);
        existing.setBackground(Color.black);
        existing.setForeground(Color.white);
        existing.addActionListener(this);
        add(existing);
        
        allEmployee = new JButton("All Employees");
        allEmployee.setBounds(10,150,200,30);
        allEmployee.setBackground(Color.black);
        allEmployee.setForeground(Color.white);
        allEmployee.addActionListener(this);
        add(allEmployee);
        
        customers = new JButton("Customers");
        customers.setBounds(10,190,200,30);
        customers.setBackground(Color.black);
        customers.setForeground(Color.white);
        customers.addActionListener(this);
        add(customers);
        
        managerInfo = new JButton("Manager Info");
        managerInfo.setBounds(10,230,200,30);
        managerInfo.setBackground(Color.black);
        managerInfo.setForeground(Color.white);
        managerInfo.addActionListener(this);
        add(managerInfo);
        
        payment = new JButton("Payment");
        payment.setBounds(10,270,200,30);
        payment.setBackground(Color.black);
        payment.setForeground(Color.white);
        payment.addActionListener(this);
        add(payment);
        
        update = new JButton("Booking Status");
        update.setBounds(10,310,200,30);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);
        
        delivery = new JButton("Delivery Agents");
        delivery.setBounds(10,350,200,30);
        delivery.setBackground(Color.black);
        delivery.setForeground(Color.white);
        delivery.addActionListener(this);
        add(delivery);
        
        search = new JButton("Search Connections");
        search.setBounds(10,390,200,30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        search.addActionListener(this);
        add(search);
        
        logout = new JButton("LOGOUT");
        logout.setBounds(200,430,200,30);
        logout.setBackground(Color.blue);
        logout.setForeground(Color.white);
        logout.addActionListener(this);       
        add(logout);
      
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/8.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350,400,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(195,-10,400,470);
        add(image);
        setLayout(null);
        setBounds(450,200,600,500);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
       if(ae.getSource()== newCustomer)
       {
           setVisible(false);
           new AddCustomer();
       }else if(ae.getSource()==connections){
           setVisible(false);
           new Connections();
       }else if(ae.getSource()==existing){
           setVisible(false);
           new ExistingCustomer();
       }else if(ae.getSource()==allEmployee){
           setVisible(false);
           new Employees();
       }else if(ae.getSource()==customers){
           setVisible(false);
           new Customers();
       }else if(ae.getSource()==managerInfo){
           setVisible(false);
           new ManagerInfo();
       }else if(ae.getSource()==delivery){
           setVisible(false);
           new Delivery();
       }else if(ae.getSource()==payment){
           setVisible(false);
           new Payment();
       }else if(ae.getSource()==update){
           setVisible(false);
           new BookingStatus();
       }else if(ae.getSource()==search){
           setVisible(false);
           new Search();
       }else if(ae.getSource()==logout){
           setVisible(false);
  
       }
     }
    
    public static void main(String [] args)
    {
        new Booking();
    }
    
}

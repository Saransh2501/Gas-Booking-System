
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Dashboard extends JFrame implements ActionListener{
    
    Dashboard(){
        
        setBounds(70,0,1550,1000);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/4.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 1000,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1550,1000);
        add(image);
        
        JLabel text = new JLabel("WELCOME TO INDIAN GAS");
        text.setBounds(350,80,1000,70);
        text.setFont(new Font("Tahoma", Font.BOLD, 65));
        text.setForeground(Color.black);
        image.add(text);
        
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0,0,1550,30);
        image.add(mb);
        
        JMenu gas = new JMenu ("GAS BOOKING");
        gas.setForeground(Color.blue);
        mb.add(gas);
        
        JMenu admin = new JMenu ("ADMIN");
        admin.setForeground(Color.red);
        mb.add(admin);
        
        JMenuItem addemployee = new JMenuItem("ADD EMPLOYEE");
        addemployee.addActionListener(this);
        admin.add(addemployee);
        
        JMenuItem addconnections = new JMenuItem("ADD CONNECTIONS");
        admin.add(addconnections);
        addconnections.addActionListener(this);
        
        JMenuItem addagents = new JMenuItem("ADD DELIVERY AGENTS");
        addagents.addActionListener(this);
        admin.add(addagents);
        
        JMenuItem updatepayment = new JMenuItem("PAYMENT STATUS");
        updatepayment.addActionListener(this);
        admin.add(updatepayment);
        
        JMenuItem Dashboard = new JMenuItem("Book Cylinder");
        Dashboard.addActionListener(this);
        gas.add(Dashboard);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("ADD EMPLOYEE")){
            new AddEmployee();  
        }else if(ae.getActionCommand().equals("ADD DELIVERY AGENTS")) {
            new Deliveryagents();
        }else if(ae.getActionCommand().equals("ADD CONNECTIONS")) {
            new AddConnection();
        }else if(ae.getActionCommand().equals("Book Cylinder")) {
            new Booking(); 
        }else if(ae.getActionCommand().equals("PAYMENT STATUS")) {
            new Updatepayment(); }
    }
    public static void main(String [] args)
    {
        new Dashboard();
    }
}

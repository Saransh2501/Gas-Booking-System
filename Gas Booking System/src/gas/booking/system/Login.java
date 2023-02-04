
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    
    JTextField username;
    JPasswordField password;
    JButton Login, cancel;
    
    Login(){
        
        getContentPane().setBackground(Color.black);
        
        setLayout(null);
        
        JLabel user = new JLabel ("Username");
        user.setBounds(40,30,100,30);
        user.setForeground(Color.WHITE);
        add(user);
        
        username = new JTextField();
        username.setBounds(150,35,130,20);
        add(username);
        
        JLabel pass = new JLabel ("Password");
        pass.setBounds(40,80,100,30);
        pass.setForeground(Color.WHITE);
        add(pass);
        
        password = new JPasswordField();
        password.setBounds(150,85,130,20);
        add(password);
        
        Login = new JButton("Login");
        Login.setBounds(40,160,120,30);
        Login.setBackground(Color.white);
        Login.setForeground(Color.black);
        Login.addActionListener(this);
        add(Login);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(200,160,120,30);
        cancel.setBackground(Color.white);
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);

        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/3.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,20,200,200);
        add(image);
        
        
        setBounds(600,300,600,300);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        int count=0;
        if(ae.getSource()==Login)
        {
            String user = username.getText();
            String pass = password.getText();
            
            try{
                
                Conn c = new Conn();
                String query = "select * from login where username = '" + user + "'and password = '" + pass + "'";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next())
                {
                    setVisible(false);
                    new Dashboard();
                }else {
                    count++;
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    JOptionPane.showMessageDialog(null, 3 - count + " Attempts left");
                    
                    if(count==3)
                        setVisible(false);
                }
                
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==cancel)
        {
            setVisible(false);
        }
    }
    
    public static void main(String []args)
    {
        new Login();
}
}
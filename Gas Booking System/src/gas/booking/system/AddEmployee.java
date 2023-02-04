
package gas.booking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener{
    
    JTextField tfname, tfmail, tfphone, tfage, tfsal,tfadhaar;
    JRadioButton rbmale, rbfemale;
    JButton submit;
    JComboBox cbjob;
    AddEmployee(){
        setLayout(null);
        
        JLabel lblname = new JLabel ("NAME");
        lblname.setBounds(60,30,120,30);
        lblname.setFont(new Font("Tahoma", Font.BOLD,17));
        lblname.setForeground(Color.cyan);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200,30,150,25);
        add(tfname);
        
        JLabel lblage = new JLabel ("AGE");
        lblage.setBounds(60,80,120,30);
        lblage.setFont(new Font("Tahoma", Font.BOLD,17));
        lblage.setForeground(Color.cyan);
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,80,150,25);
        add(tfage);
        
        JLabel lblgender = new JLabel ("GENDER");
        lblgender.setBounds(60,130,120,30);
        lblgender.setFont(new Font("Tahoma", Font.BOLD,17));
        lblgender.setForeground(Color.cyan);
        add(lblgender);
        
        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200,130,70,25);
        rbmale.setFont(new Font("Tahoma",Font.BOLD,12));
        rbmale.setBackground(Color.red);
        rbmale.setForeground(Color.yellow);
        add(rbmale);
        
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280,130,70,25);
        rbfemale.setFont(new Font("Tahoma",Font.BOLD,12));
        rbfemale.setBackground(Color.red);
        rbfemale.setForeground(Color.yellow);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        JLabel lbljob = new JLabel ("JOB");
        lbljob.setBounds(60,180,120,30);
        lbljob.setFont(new Font("Tahoma", Font.BOLD,17));
        lbljob.setForeground(Color.cyan);
        add(lbljob);
        
        String str[] = {"Porters", "Security", "Fire Safety", "Manager"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(200,180,150,30);
        cbjob.setBackground(Color.yellow);
        add(cbjob);
        
        JLabel lblsal = new JLabel ("SALARY");
        lblsal.setBounds(60,230,120,30);
        lblsal.setFont(new Font("Tahoma", Font.BOLD,17));
        lblsal.setForeground(Color.cyan);
        add(lblsal);
        
        tfsal = new JTextField();
        tfsal.setBounds(200,230,150,25);
        add(tfsal);
        
        JLabel lblphone = new JLabel ("PHONE");
        lblphone.setBounds(60,280,120,30);
        lblphone.setFont(new Font("Tahoma", Font.BOLD,17));
        lblphone.setForeground(Color.cyan);
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200,280,150,25);
        add(tfphone);
        
        JLabel lblmail = new JLabel ("E-MAIL");
        lblmail.setBounds(60,330,120,30);
        lblmail.setFont(new Font("Tahoma", Font.BOLD,17));
        lblmail.setForeground(Color.cyan);
        add(lblmail);
        
        tfmail = new JTextField();
        tfmail.setBounds(200,330,150,25);
        add(tfmail);
        
         JLabel lbladhaar = new JLabel ("ADHAAR NO.");
        lbladhaar.setBounds(60,380,120,30);
        lbladhaar.setFont(new Font("Tahoma", Font.BOLD,17));
        lbladhaar.setForeground(Color.cyan);
        add(lbladhaar);
        
        tfadhaar = new JTextField();
        tfadhaar.setBounds(200,380,150,25);
        add(tfadhaar);
        
        submit = new JButton ("SUBMIT");
        submit.setBackground(Color.green);
        submit.setForeground(Color.red);
        submit.setBounds(200,430,150,30);
        submit.addActionListener(this);
        add(submit);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/5.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380,30,450,450);
        add(image);
        
        
        getContentPane().setBackground(Color.black);
        setBounds(350,200,850,540);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String name = tfname.getText();
        String age = tfage.getText();
        String phone = tfphone.getText();
        String mail = tfmail.getText();
        String salary = tfsal.getText();
        String adhaar = tfadhaar.getText();
        
        String gender = null;
        
        
        if(rbmale.isSelected())
        {
            gender = "Male";
            
        }else if(rbfemale.isSelected())
        {
            gender = "Female";
             
        }
        
        String job = (String)cbjob.getSelectedItem();
        
        if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Name cannot be null");
            return ;
        }
        
        else if(phone.length()!=10)
        {
            JOptionPane.showMessageDialog(null, "Invalid Phone number");
            return ;
        }
        
        else if(adhaar.length()!=12)
        {
            JOptionPane.showMessageDialog(null, "Invalid Adhaar number");
            return ;

        }
        
        else{
        try{
            Conn conn = new Conn();
            String query2 = "select * from employee ";
            ResultSet rs = conn.s.executeQuery(query2);
            while(rs.next())
            {
                String a= rs.getString("adhaar");
                String b= rs.getString("phone");
                if(a.equals(adhaar))
                {
                   JOptionPane.showMessageDialog(null, "Duplicate Adhaar Number");
                   return ;
                }
                
                if(b.equals(phone))
                {
                   JOptionPane.showMessageDialog(null, "Duplicate Phone Number");
                   return ;
                }
            }
            String query = "insert into employee(name,age, gender,job,salary,phone,email,adhaar) values('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + mail + "', '" + adhaar + "')";
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Data added Successfully");
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
    }
                
      
    public static void main(String [] args)
    {
        new AddEmployee();
    }
}


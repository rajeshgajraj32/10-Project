package Swing_Awt_ui_ux;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RandomPass {
    static File file;
    static FileWriter writer;
    static int count=0,wpass=0,regu=0;
    static void login(){
        
       JFrame frame = new JFrame();
       frame.setSize(400,400);
       frame.getContentPane().setBackground(Color.getHSBColor(60,20,50));
       frame.setLayout(null);
       frame.setVisible(true);

       JLabel uname = new JLabel("User Name:");
       uname.setBounds(70,65,150,20);
       frame.add(uname);
       JTextField user = new JTextField();
       user.setBounds(70,85,200,30);
       frame.add(user);
       JLabel pass = new JLabel("Enter Password:");
       pass.setBounds(70,125,150,20);
       frame.add(pass);
       JPasswordField pwd = new JPasswordField();
       pwd.setBounds(70,145,200,30);
       frame.add(pwd);
       JButton login = new JButton("Log In");
       login.setBounds(75,185,90,30);
       login.setBackground(Color.white);
       frame.add(login);
       JButton reg = new JButton("Register");
       reg.setBounds(170,185,90,30);
       reg.setBackground(Color.magenta);
       frame.add(reg);
       JLabel result = new JLabel();
       result.setBounds(70,220,300,30);
       frame.add(result);
       ActionListener Registr = new   ActionListener(){
        public void actionPerformed(ActionEvent e){
            registration();
        }
    }; reg.addActionListener(Registr);
    ActionListener Login = new   ActionListener(){
        public void actionPerformed(ActionEvent e){
            try {
                Scanner read = new Scanner(file);
                while(read.hasNextLine()){
                    String str =read.nextLine();
                    String check = user.getText()+" "+pwd.getText();
                    if(str.contains(check)){
                        count=1;
                    }
                    else if(str.contains(user.getText())){
                        wpass=1;
                    }
                }
                read.close();
                if(count==1){
                    result.setForeground(Color.green);
                   result.setText("Well Come !! You Loged In");
                   count=0;
                }
                else if(wpass==1){
                    result.setForeground(Color.red);
                    result.setText("You Entred Wrong Password");
                    wpass=0;
                }
                else{
                    result.setForeground(Color.RED);
                    result.setText("You Are not Registred");
                }
            } catch (Exception f) {
                // TODO: handle exception
            }
           

        }
    };login.addActionListener(Login);



    }
    static void registration(){
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.getHSBColor(60,20,93));
        frame.setVisible(true);
 
        JLabel name = new JLabel("Name:");
        name.setBounds(70,65,150,20);
        frame.add(name);
        JTextField naam = new JTextField();
        naam.setBounds(70,85,200,30);
        frame.add(naam);
        JLabel uname = new JLabel("Enter User Name:");
        uname.setBounds(70,125,150,20);
        frame.add(uname);
        JTextField userid = new JTextField();
        userid.setBounds(70,145,200,30);
        frame.add(userid);
        JButton Cancel = new JButton("Cancel");
        Cancel.setBounds(75,185,90,30);
        Cancel.setBackground(Color.orange);
        frame.add(Cancel);
        JButton reg = new JButton("Register");
        reg.setBounds(170,185,90,30);
        reg.setBackground(Color.cyan);
        frame.add(reg);
        JLabel result = new JLabel();
        result.setBounds(70,220,300,30);
        frame.add(result);
        ActionListener Registr = new   ActionListener(){
         public void actionPerformed(ActionEvent e){
            try {
                file = new File("RandomPass.txt");
                writer = new FileWriter(file,true);
                Scanner read1 = new Scanner(file);
                while(read1.hasNextLine()){
                    String str =read1.nextLine();
                    if(str.contains(userid.getText())){
                        regu=1;

                    }
                    
                }
                read1.close();
                if(regu==1){
                    result.setForeground(Color.RED);
                    result.setText("This User Name Already Taken !!");
                }
                else{
                    int min = 1000000;
                    int max = 9999999;
                    int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                    writer.write(naam.getText()+" "+userid.getText()+" "+random_int+"\n");
                    result.setForeground(Color.GREEN);

                    result.setText("Successfull !! And your Password: "+random_int);
                }
                writer.close();
            } catch (Exception d) {
                // TODO: handle exception
            }
          

         }
        };reg.addActionListener(Registr);
        ActionListener cancel = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
                login(); 
            }
        };Cancel.addActionListener(cancel);
    }
    public static void main(String[] args) {
        login();
    }
}

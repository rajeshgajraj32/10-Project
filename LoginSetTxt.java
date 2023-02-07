package Swing_Awt_ui_ux;
import java.awt.event.*;

import javax.sound.midi.SoundbankResource;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.format.TextStyle;
import java.util.*;
public class LoginSetTxt {
    void login(){

        JFrame f = new JFrame();
        f.setSize(300,400);
        f.setLayout(null);
        f.setVisible(true);
        JLabel l = new JLabel("Email Id: ");
        l.setBounds(10,80,70,20);
        f.add(l);
        JTextField txt = new JTextField();
        txt.setBounds(95,80,150,20);
        f.add(txt);
        JLabel l2 = new JLabel("Password: ");
        l2.setBounds(10,120,70,20);
        f.add(l2);
        JPasswordField txt2 = new JPasswordField();
        txt2.setBounds(95,120,150,20);
        f.add(txt2);
        File file =new File("Data.txt");
        JButton log = new JButton("login");
        log.setBounds(50,160,90,30);
        f.add(log);
        JButton reg = new JButton("Registation");
        reg.setBounds(152,160,90,30);
        f.add(reg);
        JLabel result = new JLabel();
        result.setBounds(40,200,150,20);
        f.add(result);
        ActionListener login = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                   String email = txt.getText();
                        String pass = txt2.getText();
                        int count=0,a=0;
                        try {
                            Scanner read = new Scanner(file);
                            while(read.hasNextLine()){
                                String str =read.nextLine();
                                if(str.contains(pass)&&str.contains(email)&&!txt.getText().isEmpty()&&!txt2.getText().isEmpty()){
                                    count=1;
                                }
                                else if(str.contains(email)&&!txt.getText().isEmpty()&&!txt2.getText().isEmpty()){
                                  a=1;
                                }
                            }

                            if(count==1){
                                result.setForeground(Color.GREEN);
                                result.setText("you logedin");   
                            }
                            else if(a==1){
                                result.setForeground(Color.RED);
                                result.setText("you enterd wrong password");
                                
                            }
                            else{
                                result.setForeground(Color.RED);
                                result.setText("you enterd wrong data");

                            }
                            read.close();
                            
                        } catch (Exception g) {
                            // TODO: handle exception
                        }
                
            }
        };log.addActionListener(login);
        ActionListener regstr = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFrame f2 = new JFrame();
                f2.setSize(400,500);
                f2.setLayout(null);
                f2.setVisible(true);
                JLabel l = new JLabel("Enter Name: ");
                l.setBounds(5,80,80,20);
                f2.add(l);
                JTextField tf = new JTextField();
                tf.setBounds(90,80,150,20);
                f2.add(tf);
                JLabel l2 = new JLabel("Enter Email: ");
                l2.setBounds(5,120,80,20);
                f2.add(l2);
                JTextField tf2 = new JTextField();
                tf2.setBounds(90,120,150,20);
                f2.add(tf2);
                JLabel l3 = new JLabel("Enter Password: ");
                l3.setBounds(5,160,80,20);
                f2.add(l3);
                JPasswordField tf3 = new JPasswordField();
                tf3.setBounds(90,160,150,20);
                f2.add(tf3);
                JLabel l4 = new JLabel("Re-Enter Password: ");
                l4.setBounds(5,200,80,20);
                f2.add(l4);
                JPasswordField tf4 = new JPasswordField();
                tf4.setBounds(90,200,150,20);
                f2.add(tf4);
                JButton bt = new JButton("registration");
                bt.setBounds(50,240,150,30);
                f2.add(bt);
                JLabel l7 = new JLabel();
                l7.setBounds(40,290,200,20);
                l7.setFont(new Font("Serif", Font.PLAIN,18));
                l7.setForeground(Color.RED);
                f2.add(l7);
                ActionListener register = new   ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        int tru=0;
                        if((tf3.getText()).equals(tf4.getText())){
                        try {
                            FileWriter writer = new FileWriter(file,true);
                            Scanner read1 = new Scanner(file);
                            
                            if(!(tf.getText().isEmpty())&&!(tf2.getText().isEmpty())&&tf2.getText().contains("@gmail.com")&&!(tf3.getText().isEmpty())){
                                while(read1.hasNextLine()){
                                    if(read1.nextLine().contains(tf2.getText())){
                                      tru=1;
                                    }
    
                                }
                                if(tru==1){
                                writer.write(tf.getText()+"\t"+tf2.getText()+"\t"+tf3.getText()+"\n");
                                writer.close();
                                
                                l7.setText("you registration successfully");
                                }
                                else{
                                    
                                    l7.setText("you Already registert");
                                }
                            }else if(!tf2.getText().contains("@gmail.com")&&!(tf.getText().isEmpty())){
                               
                                l7.setText("Email Id is Not right");
                            }else{
                                l7.setText("Please fill all Box");
                            }
                                
                            
                        } catch (Exception f) {
                            // TODO: handle exception
                        }
                    }else{
                       
                        l7.setText("you enterd Mismatch password");
                        
                    }
                        
                    }
                };
                bt.addActionListener(register);

            }
        };
        log.addActionListener(login);
        reg.addActionListener(regstr);

    }
    public static void main(String[] args) {
        LoginSetTxt lt = new LoginSetTxt();
        lt.login();
    }
    
}

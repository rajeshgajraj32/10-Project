package CreateServer;
import javax.swing.*;

import java.awt.Color;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.text.AttributeSet.ColorAttribute;

public class ChatRoomServer {
    static ServerSocket ss;
    static Socket s;
    static Scanner sc1 = new Scanner(System.in);
    static Scanner sc;
    static PrintWriter ps;
    static JTextField sendmsg;
    static JLabel resive;
    static String str="",str2="";
    public static void main(String[] args) {
        JFrame server = new JFrame();
        server.setSize(300,600);
        server.setLayout(null);
        server.setVisible(true);
        sendmsg = new JTextField();
        sendmsg.setBounds(0,520,200,40);
        server.add(sendmsg);
        JButton send = new JButton("Send");
        send.setBounds(200,520,85,40);
        send.setBackground(Color.green);
        server.add(send);
        resive = new JLabel();
        resive.setBounds(5,450,100,20);
        server.add(resive);
        try {
            ss = new ServerSocket(0640);
            System.out.println("Listening");
            s=ss.accept();
            System.out.println("connected");
            sc=new Scanner(s.getInputStream());
            ps= new PrintWriter(s.getOutputStream(),true);
            reader();
            ActionListener hello = new   ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(!(sendmsg.getText().equals(""))){
                    writer();
                    }
                }
            };send.addActionListener(hello);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
    static void writer(){
        Thread tr = new Thread(()->{
                try {
                    str=sendmsg.getText();
                    //resive.setText(str);
                    ps.println(str);
                    sendmsg.setText("");
                    //if(str.equals("Bye")){break;}

                } catch (Exception e) {
                    // TODO: handle exception
                }

        });
        tr.start();
    }
    static void reader(){
        Thread tw = new Thread(()->{
            while(true){
                try {
                    str2=sc.nextLine();
                    resive.setText(str2);
                    //System.out.println(str2);
                    if(str2.equals("Bye")){break;}

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e);
                }
            }
        });
        tw.start();
    
    }
}

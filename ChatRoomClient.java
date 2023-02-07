package CreateServer;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.*;
import java.util.Scanner;

public class ChatRoomClient {
    static ServerSocket ss;
    static Socket s;
    static Scanner sc1 = new Scanner(System.in);
    static Scanner sc;
    static JLabel resive;
    static JTextField sendmsg2;
    static PrintStream ps;
    static String str="",str2="";
    public static void main(String[] args) {
        JFrame server = new JFrame();
        server.setSize(300,600);
        server.setLayout(null);
        server.setVisible(true);
        sendmsg2 = new JTextField();
        sendmsg2.setBounds(0,520,200,40);
        server.add(sendmsg2);
        JButton send = new JButton("Send");
        send.setBounds(200,520,85,40);
        send.setBackground(Color.green);
        server.add(send);
        resive = new JLabel();
        resive.setBounds(5,450,100,20);
        server.add(resive);
        try {
            s = new Socket("localhost",0640);
            System.out.println("Connected");
            sc=new Scanner(s.getInputStream());
            ps=new PrintStream(s.getOutputStream(),true);
            reade();
            ActionListener hello = new   ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(!(sendmsg2.getText().equals(""))){
                    write();
                    }
                }
            };send.addActionListener(hello);
        } catch (Exception e) {
            // TODO: handle exception
            
        }
   }
    static void reade(){
        Thread tr = new Thread(()->{
            while(true){
                try {
                    str=sc.nextLine();
                    resive.setText(str);
                    if(str.equals("Bye")){break;}

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        });
        tr.start();
    }
    static void write(){
        Thread tw = new Thread(()->{
                try {
                    str2=sendmsg2.getText();
                    //System.out.println(str2);
                    ps.println(str2);
                    sendmsg2.setText("");

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e);
                }

        });
        tw.start();
    }
}

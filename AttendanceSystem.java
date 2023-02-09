import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class AttendanceSystem {
    static JFrame loginfram,loginfram1,welcomefram;
    static JTextField emailfield,emailfield1,empidfield1;
    static JLabel emaillabel,passlabel,emaillabel1,empidlabel1,ranklabel,welcomlabel,todaylabel,timelabel,timelabel1,attstatuslabel,shiftlabel,shiftlabel1;     
    static JPasswordField passfield;
    static JButton loginbt, registerbt,submitbt,logoutbt,submitwelbt; 
    static JComboBox rank,attstatus;
    static JFormattedTextField today;
    static DateFormat dateFormat; 
    static  SimpleDateFormat timeFormat;
    static Random rn;
     static String name,id;
    static void tablecreate(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gajraj", "root", "1234567");
            Statement stmt = con.createStatement();        
            System.out.println("connetion ho gya");
    String sql = "CREATE TABLE registration1("
    + "SN INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
    + "Employeeid VARCHAR (20) NOT NULL UNIQUE, "+ "Name VARCHAR (20) NOT NULL, "+"Designation VARCHAR (20) NOT NULL, "+
    "Password INT (20) NOT NULL, "+"Date VARCHAR(25) , "
    +"Status VARCHAR (20) )";
    
     stmt.executeUpdate(sql);
         System.out.println("Created table in given database...");
        } catch (Exception e) {
           System.out.println(e);
        }
    }
    static void sin(){
        tablecreate();
        loginfram=new JFrame("Attendance Login");
        loginfram.setSize(300, 350);
        loginfram.setLayout(null);
        emaillabel=new JLabel("Employee Id");
        emaillabel.setBounds(2, 2, 100, 30);
        loginfram.add(emaillabel);
        emailfield=new JTextField();
        emailfield.setBounds(0, 30, 280,40);
        loginfram.add(emailfield);
        passlabel=new JLabel("Password");
        passlabel.setBounds(2, 71, 100, 30);
        loginfram.add(passlabel);
        passfield=new JPasswordField();
        passfield.setBounds(0, 102, 280,40);
        loginfram.add(passfield);
        loginbt=new JButton("Login");
        loginbt.setBounds(15, 170, 80, 40);
        loginbt.setForeground(Color.WHITE);
        loginbt.setBackground(Color.BLUE);
        loginfram.add(loginbt);
        registerbt=new JButton("Register New");
        registerbt.setBounds(120, 170, 120, 40);
        registerbt.setBackground(Color.BLUE);
        registerbt.setForeground(Color.WHITE);
        loginfram.add(registerbt);
                loginfram.setVisible(true);
                registerbt.addActionListener(e->{
                    try { sinup();
                        loginfram.setVisible(false);            
                    } catch (Exception e2) {} });              
                    loginbt.addActionListener(e->{
                        try {int flag=0;
                            String email=emailfield.getText();
                            char data[]=passfield.getPassword();
                            String pass=new String(data);
                            Class.forName("com.mysql.cj.jdbc.Driver");        
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gajraj", "root", "1234567");
        System.out.println("connetion ho gya");
String qr="Select * from registration1";
Statement st=con.createStatement();
ResultSet rs=st.executeQuery(qr);
while(rs.next())
{     
    int snnumber=rs.getInt("Password"); 
    String snsn=Integer.toString(snnumber);
     String employid=rs.getString("Employeeid");
     name=rs.getString("Employeeid");
     id= rs.getString("Name");
     
     if(snsn.equals(pass)&&employid.equals(email)){JOptionPane.showMessageDialog(loginfram, "You are Login Successfully"); flag=1; loginfram.setVisible(false);status();break;}
     
    } if(flag==0){JOptionPane.showMessageDialog(loginfram, "Something Going to Wrong Please chek your Id Password");}
                         } catch (Exception e2) {} });
            }
            static void sinup(){
                loginfram1=new JFrame("Attendance New Register");
                loginfram1.setSize(300, 350);
                loginfram1.setLayout(null);
                emaillabel1=new JLabel("Name");
                emaillabel1.setBounds(2, 2, 100, 30);
                loginfram1.add(emaillabel1);
                emailfield1=new JTextField();
                emailfield1.setBounds(0, 30, 280,40);
                loginfram1.add(emailfield1);
                empidlabel1=new JLabel("Employee Id");
                empidlabel1.setBounds(2, 71, 100, 30);
                loginfram1.add(empidlabel1);
                empidfield1=new JTextField();
                empidfield1.setBounds(0, 102, 280,40);
                loginfram1.add(empidfield1);
                ranklabel=new JLabel("Rank");
                ranklabel.setBounds(2, 145, 80, 30);
                loginfram1.add(ranklabel);
                String a[]={"Inspector","Sub.Insp.","A.S.I","H.C.","Constable"};
                rank=new JComboBox(a);
                rank.setBounds(85, 150, 150, 30);
                loginfram1.add(rank);
                submitbt=new JButton("Submit");
                submitbt.setBounds(60, 200, 100, 40);
                submitbt.setForeground(Color.WHITE);
                submitbt.setBackground(Color.BLUE);
                loginfram1.add(submitbt);         
                loginfram1.setVisible(true);
                submitbt.addActionListener(e->{
                    try {
                        rn=new Random();
                        int c=rn.nextInt(10000);
                        String str=Integer.toString(c);
                        Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gajraj", "root", "1234567");           

         PreparedStatement st = con.prepareStatement("INSERT INTO registration1 (Employeeid,Name,Designation,Password) VALUES (?,?,?,?)");
          
             st.setString(1,empidfield1.getText());
           st.setString(2, emailfield1.getText());
           st.setString(3, (String) rank.getSelectedItem());
           st.setString(4, str);
            st.executeUpdate(); 
            JOptionPane.showMessageDialog(loginfram1, "Your Are Register Succssfully And Your System Genrated password is :"+"\n"+c);
            loginfram1.setVisible(false);
            sin();
                    } catch (Exception e1) {
                        // TODO: handle exception
                    }
                   
                     
                });
            }        
            static void status(){
                welcomefram=new JFrame("Status Fram");
            welcomefram.setLayout(null);
            welcomefram.setSize(500, 350);
            welcomlabel=new JLabel("Welcome Mr./Mis."+" "+id+" "+"On Your Attendance Account");
            welcomlabel.setBounds(20, 30, 350, 50);
           welcomlabel.setForeground(Color.BLUE);
            welcomefram.add(welcomlabel);
         dateFormat = new SimpleDateFormat("dd MMM YYYY");
         today = new JFormattedTextField(dateFormat);
         today.setBounds(66, 85, 80, 30);
      today.setName("Today");
      
      today.setEditable(false);
        todaylabel = new JLabel("Date:");
      todaylabel.setBounds(05, 85, 50, 30);
      timeFormat = new SimpleDateFormat("HH:mm"); 
      String timeString = timeFormat.format(new Date());
      today.setValue(new Date());
      timelabel1=new JLabel("Time :");
      timelabel1.setBounds(160, 85, 50, 30);
      welcomefram.add(timelabel1);
      timelabel = new JLabel(timeString, JLabel.CENTER);
       timelabel.setBounds(200, 85,80, 30);
       welcomefram.add(timelabel);

       shiftlabel=new JLabel("Shift :");
       shiftlabel.setBounds(285, 85, 50, 30);
       welcomefram.add(shiftlabel);
       shiftlabel1 = new JLabel();
       shiftlabel1.setBounds(340, 85,80, 30);
        welcomefram.add(shiftlabel1);
        String[] hourMin = timeString.split(":");
          int hour = Integer.parseInt(hourMin[0]);
         if(hour>=8&&hour<=14){shiftlabel1.setText("Morning");}
         else if(hour>=15&&hour<=20){shiftlabel1.setText("Evening");}
         else shiftlabel1.setText("Night");
       attstatuslabel=new JLabel("Attendance Status");
       attstatuslabel.setBounds(05, 120, 150, 30);
       welcomefram.add(attstatuslabel);
       String b[]={"Persent","C.L."};
       attstatus=new JComboBox(b);
       attstatus.setBounds(156, 120, 100, 30);
       welcomefram.add(attstatus);      

       submitwelbt=new JButton("Submit");
       submitwelbt.setBounds(60, 230, 100, 40);
       submitwelbt.setForeground(Color.WHITE);
       submitwelbt.setBackground(Color.BLUE);
       welcomefram.add(submitwelbt);

     welcomefram.add(today);
     welcomefram.add(todaylabel);
            welcomefram.setVisible(true);
            submitwelbt.addActionListener(e->{
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gajraj", "root", "1234567");           
             
         PreparedStatement st = con.prepareStatement("update registration1 SET Date=? where Employeeid=?");
         PreparedStatement st1 = con.prepareStatement("update registration1 SET Status=? where Employeeid=?");
           
          System.out.println(today.getText());
          System.out.println((String) attstatus.getSelectedItem());
            st.setString(1,today.getText());          
           st.setString(2,name);
           st1.setString(1,(String) attstatus.getSelectedItem());
           st1.setString(2,name);
           st.executeUpdate(); 
           st1.executeUpdate(); 
            JOptionPane.showMessageDialog(welcomefram, "Your Attendance Status Send To Commanding Officer");
            welcomefram.setVisible(false);
            sin();
                    } catch (Exception e3) {
                        // TODO: handle exception
                    }
            });
            }
            public static void main(String[] args) {  
                sin();  
              
            }

}

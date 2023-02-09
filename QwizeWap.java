import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class QwizeWap{
    static ButtonGroup bg;
    static JLabel option1;
    static JLabel option2;
    static JLabel option3;
    static JLabel option4;
    static JLabel lable;
    static JSONArray opt;
    void qwz(){
        JFrame fram= new JFrame();
        fram.setSize(600,800);
        fram.setVisible(true);
        fram.setLayout(null);
        JButton start = new JButton("START QUIZ");
        start.setBounds(100, 250, 200, 50);
        start.setBackground(Color.MAGENTA);
        fram.add(start);
        ActionListener star = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
                start.setVisible(false);
        lable = new JLabel();
        lable.setBounds(10,100,500,30);
        lable.setForeground(Color.red);
        fram.add(lable);

        JLabel lable1 = new JLabel("-: Quiz :-");
        lable1.setBounds(100,50,400,40);
        lable1.setFont(new Font("Verdana", Font.BOLD, 18));
        lable1.setForeground(Color.BLUE);
        fram.add(lable1);

        JRadioButton rbutt = new JRadioButton();
        rbutt.setBounds(20,140,20,20);
        fram.add(rbutt);
        option1 = new JLabel();
        option1.setBounds(40,140,400,20);
        fram.add(option1);

        JRadioButton rbutt1 = new JRadioButton();
        rbutt1.setBounds(20,160,20,20);
        fram.add(rbutt1);
        option2 = new JLabel();
        option2.setBounds(40,160,400,20);
        fram.add(option2);

        JRadioButton rbutt2 = new JRadioButton();
        rbutt2.setBounds(20,180,20,20);
        fram.add(rbutt2);
        option3 = new JLabel();
        option3.setBounds(40,180,400,20);
        fram.add(option3);

        JRadioButton rbutt3 = new JRadioButton();
        rbutt3.setBounds(20,200,20,20);
        fram.add(rbutt3);
        option4 = new JLabel();
        option4.setBounds(40,200,400,20);
        fram.add(option4);
        bg = new ButtonGroup();
        bg.add(rbutt1);
        bg.add(rbutt2);
        bg.add(rbutt3);
        bg.add(rbutt);

        JButton next = new JButton("Next");
        next.setBounds(137, 250, 120, 40);
        next.setBackground(Color.PINK);
        fram.add(next);
        JButton ans = new JButton("SHOW RESULT");
        ans.setBounds(15, 250, 120, 40);
        ans.setBackground(Color.green);
        fram.add(ans);
        select();
        JLabel right = new JLabel();
        right.setBounds(20,300,400,20);
        fram.add(right);
        ActionListener nex = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
                right.setText("");
             select();
            }
        };
        next.addActionListener(nex);
        ActionListener answer = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
             right.setText("Right Answer: "+opt.get(0).toString());
            }
        };
        ans.addActionListener(answer);
    }};start.addActionListener(star);

    }
    static void select(){
        try {
        bg.clearSelection();
        JSONParser par = new JSONParser();
         JSONArray arr =(JSONArray)par.parse(new FileReader("Quis.json"));
         Random rand = new Random();
         int indx=rand.nextInt(0,10);
         JSONObject obj = (JSONObject)arr.get(indx);
         String Question=obj.get("Q").toString();
         lable.setText(" Que: "+Question);
         String option=obj.get("option").toString();
         opt =(JSONArray)par.parse(option);
         int i=0;
         while(i<4){
         String optio=opt.get(i).toString();
         if(i==0){
            option1.setText(" "+optio);
         }else if(i==1){
            option2.setText(" "+optio);
         }else if(i==2){
            option3.setText(" "+optio);
         }else{
            option4.setText(" "+optio);
         }
         i++;
         }
    }catch(Exception h){
        System.out.println(h);
    }
 }
    public static void main(String[] args) {
        QwizeWap qwap =new QwizeWap();
        qwap.qwz();
    }
}
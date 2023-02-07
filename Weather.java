import java.io.*;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.*;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.*;
import java.beans.Visibility;

import javax.swing.*;
public class Weather {
    public static void main(String[] args) {
        
        JFrame fram = new JFrame();
        fram.setSize(400,400);
        fram.setLayout(null);
        fram.getContentPane().setBackground(Color.LIGHT_GRAY);
        fram.setVisible(true);
        
        JLabel email = new JLabel("Enter Your City name");
        email.setBounds(20,20,200,20);
        fram.add(email);
        JLabel latlon = new JLabel();
        latlon.setBounds(20,130,250,40);
        fram.add(latlon);
        JTextField city = new JTextField();
        city.setBounds(20,50,200,30);
        fram.add(city);
        JButton submit = new JButton("Get Weather Report");
        submit.setBounds(20,90,150,30);
        submit.setBackground(Color.pink);
        fram.add(submit);
        JLabel visibility = new JLabel();
        visibility.setBounds(30,210,400,20);
        fram.add(visibility);
        JLabel weather = new JLabel("WEATHER REPOT:- ");
        weather.setBounds(20,180,400,20);
        fram.add(weather);
        JLabel wth = new JLabel();
        wth.setBounds(30,220,400,40);
        fram.add(wth);
        ActionListener result = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
        try {
            String cit = city.getText();
            URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q="+cit+"&limit=1&appid=ba59726589fcb0f59b16355371f6f660");
            Scanner sc = new Scanner(url.openStream());
            String str="";
            while(sc.hasNextLine()){
                str+=sc.nextLine();
            }
            //System.out.println(str);
            JSONParser par = new JSONParser();
            JSONArray arr = (JSONArray)par.parse(str);
            JSONObject obj = (JSONObject)arr.get(0);
            //System.out.println(obj+"\n");
            double lon=(double)obj.get("lon");
            double lat=(double)obj.get("lat");
            latlon.setText("Latitude: "+lat+", Longitude: "+lon);
            latlon.setForeground(Color.blue);
            //JSONObject obj1 = (JSONObject)obj.get("local_names");
            //System.out.println(obj1.get("eo"));

            URL url1 = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=ba59726589fcb0f59b16355371f6f660");
            Scanner sc1 = new Scanner(url1.openStream());
            String str1="";
            while(sc1.hasNextLine()){
                str1+=sc1.nextLine();
            }
           // System.out.println(str1);
            JSONParser par1 = new JSONParser();
            //JSONArray arr1 = (JSONArray)par1.parse(str1);
            JSONObject obj1= (JSONObject)par1.parse(str1);
            System.out.println(obj1);
            visibility.setForeground(Color.MAGENTA);
            weather.setForeground(Color.RED);
            
            //System.out.println(obj1.get("timezone"));
           // System.out.println(obj1.get("weather"));
            
           // String nssm = (String)obj1.get("weather");
            //System.out.println(nssm);
            JSONArray arr2=(JSONArray)par1.parse(obj1.get("weather").toString());
            JSONObject obj2 = (JSONObject)arr2.get(0);
            
            //System.out.println(obj2.get("icon"));
           // System.out.println(obj2.get("description"));
            
            JSONObject obj3 =(JSONObject)par1.parse(obj1.get("main").toString());
            System.out.println((double)obj3.get("temp")-273);
            visibility.setText("Visibility: "+obj1.get("visibility").toString()+",  Temprature: "+((double)obj3.get("temp")-273));
            wth.setText("Icon: "+obj2.get("icon").toString()+",    Description: "+obj2.get("description").toString());;
            wth.setForeground(Color.MAGENTA);
        }catch (Exception f) {
            // TODO: handle exception
        }
    }};
    submit.addActionListener(result);
    }
}

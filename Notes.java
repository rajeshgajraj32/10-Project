package Swing_Awt_ui_ux;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Notes {
    static void note(){
        JFrame frame =new JFrame("Notes Saver");
        frame.setSize(600,700);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);

        JLabel file = new JLabel("Enter File Name:");
        file.setBounds(10,40,550,20);
        frame.add(file);
        JTextField filename = new JTextField();
        filename.setBounds(10,60,550,30);
        frame.add(filename);
        JLabel heading = new JLabel("Heading:");
        heading.setBounds(10,100,550,20);
        frame.add(heading);
        JTextField Heading = new JTextField();
        Heading.setBounds(10,120,550,30);
        frame.add(Heading);
        JLabel note = new JLabel("Type Your Notes :");
        note.setBounds(10,160,550,20);
        frame.add(note);
        JTextArea notes = new JTextArea();
        notes.setBounds(10,180,550,400);
        frame.add(notes);
        JButton submit =new JButton("Submit");
        submit.setBounds(10,590,100,25);
        submit.setBackground(Color.CYAN);
        frame.add(submit);
        JLabel result = new JLabel();
        result.setBounds(10,630,500,20);
        frame.add(result);
        ActionListener ready = new   ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(!(filename.getText().isEmpty())){
                        File file = new File(filename.getText()+".txt");
                        FileWriter writer = new FileWriter(file,true);
                        writer.write(Heading.getText()+":-\n"+notes.getText()+"\n");
                        writer.close();
                        result.setForeground(Color.green);
                        result.setText("Your Notes Saved");
                    }
                    else{
                        result.setForeground(Color.red);
                        result.setText("please Enter File Name");
                    }
                    
                }catch(Exception f){
                    System.out.println(f);
                }

            }
        };submit.addActionListener(ready);


    }
    public static void main(String[] args) {
        note();
    }
}

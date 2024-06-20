import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Rules implements ActionListener
{
	JFrame j=new JFrame();
	JLabel wlcLabel,rules;
	String username;
	JButton start,back;
	public Rules(String username)
	{
		j.getContentPane().setBackground(Color.WHITE);
		j.setVisible(true);
		j.setLayout(null);
		j.setLocation(300,70);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(700,600);
		j.setTitle("Rules Page");

		this.username=username;
		wlcLabel=new JLabel("Welcome "+username+" to Examination");
		wlcLabel.setBounds(40,20,550,25);
		wlcLabel.setFont(new Font("Mongolian Baiti",1,25));
		wlcLabel.setForeground(new Color(30,144,254));
		j.add(wlcLabel);

		rules=new JLabel();
		rules.setBounds(40,50,550,410);
		rules.setFont(new Font("Tahoma",Font.PLAIN,15));
		rules.setText(
			"<html>"+
				"Rules before you begin your examination:"+"<br><br>"+
				"1. Read all questions carefully before answering."+"<br><br>"+
				"2. Select only one answer for each Multiple Choice Question (MCQ)."+"<br><br>"+
				"3. You have 15 seconds to complete 1 question and 3.45 minutes to complete your exam."+"<br>"+"The timer will start when you begin and cannot be paused."+"<br><br>"+
				"4. The system will automatically submit your answers when the time expires."+"<br><br>"+
				"5. After completing the exam, use the "+"<b>Submit</b>"+"<br>"+" button to finalize your answers if you finish before the time limit."+"<br><br>"+				
			"</html>"
		);
		j.add(rules);

		start=new JButton("Start");
        start.setBounds(120,455,120,25);
        start.setFont(new Font("Mongolian Baiti",Font.BOLD,15));
        start.setBackground(Color.RED);
        start.setForeground(Color.WHITE);
        j.add(start);
        start.addActionListener(this);

        back=new JButton("Back");
        back.setBounds(280,455,120,25);
        back.setFont(new Font("Mongolian Baiti",Font.BOLD,15));
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        j.add(back);
        back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==start)
		{
			j.setVisible(false);
 			new Quiz();
		}
		else if(ae.getSource()==back)
		{
			j.setVisible(false);
			new Login();
		}
	}
	public static void main(String[] args) {
		new Rules("UserName");
	}
}

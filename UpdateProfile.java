import java.util.regex.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class UpdateProfile implements ActionListener
{
	private static final String passwordPattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private static final Pattern pattern=Pattern.compile(passwordPattern);	
	JLabel userId,username,password,confirmpassword;
	JTextField userIdField,nameTextField;
	JPasswordField passwordField,confirmpasswordField;
	JButton update,view1,view2,back;
	JFrame j=new JFrame();
	public UpdateProfile()
	{
		j.setVisible(true);
		j.setLayout(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocation(300,95);
		j.setSize(550,350);
		j.setTitle("Update Profile");

		userId=new JLabel("Enter Your signUp Id:");
		userId.setBounds(40,30,280,25);
		userId.setFont(new Font("Mongolian Baiti",1,15));
		j.add(userId);

		userIdField=new JTextField();
		userIdField.setBounds(230,30,200,25);
		userIdField.setFont(new Font("Arial",1,15));
		j.add(userIdField);

		username=new JLabel("Username:");
		username.setBounds(40,70,280,25);
		username.setFont(new Font("Mongolian Baiti",1,15));
		j.add(username);

		nameTextField=new JTextField();
		nameTextField.setBounds(230,70,200,25);
		nameTextField.setFont(new Font("Arial",1,15));
		j.add(nameTextField);

		password=new JLabel("New Password:");
		password.setBounds(40,110,280,25);
		password.setFont(new Font("Mongolian Baiti",1,15));
		j.add(password);

		passwordField=new JPasswordField();
		passwordField.setBounds(230,110,200,25);
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Arial",1,15));
		j.add(passwordField);

		view1=new JButton("view");
		view1.setBounds(435,110,70,25);
		view1.setBackground(Color.BLUE);
		view1.setForeground(Color.WHITE);
		j.add(view1);
		view1.addActionListener(this);

		confirmpassword=new JLabel("New Confirm Password:");
		confirmpassword.setBounds(40,150,280,25);
		confirmpassword.setFont(new Font("Mongolian Baiti",1,15));
		j.add(confirmpassword);

		confirmpasswordField=new JPasswordField();
		confirmpasswordField.setBounds(230,150,200,25);
		confirmpasswordField.setEchoChar('*');
		confirmpasswordField.setFont(new Font("Arial",1,15));
		j.add(confirmpasswordField);

		view2=new JButton("view");
		view2.setBounds(435,150,70,25);
		view2.setBackground(Color.BLUE);
		view2.setForeground(Color.WHITE);
		j.add(view2);
		view2.addActionListener(this);

		update=new JButton("Update");
        update.setBounds(115,190,120,25);
        update.setFont(new Font("Mongolian Baiti",Font.BOLD,15));
        update.setBackground(Color.RED);
        update.setForeground(Color.WHITE);
        j.add(update);
        update.addActionListener(this);

        back=new JButton("Back");
        back.setBounds(240,190,120,25);
        back.setFont(new Font("Mongolian Baiti",Font.BOLD,15));
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        j.add(back);
        back.addActionListener(this);       
	}
	private Connection getConnection() throws SQLException
	{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentExamData", "root", "devansh93215");
    }
    private void initDatabase() throws Exception
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    }
    private boolean autheticateUser(int id,String username) throws Exception
    {
    	String qry="select * from User where id=? and username=?";
    	try(Connection cn=getConnection();
    		PreparedStatement pstmt=cn.prepareStatement(qry))
    	{
    		pstmt.setInt(1,id);
    		pstmt.setString(2,username);
    		try(ResultSet rs=pstmt.executeQuery())
    		{
    			return rs.next(); 
    		}
    	}
    }
    private void updateUser(String username,String password,int id) throws Exception
    {    	
    	String sql="update User set password=? where username=? and id=?";
    	try(Connection cn=getConnection();
    		PreparedStatement pstmt=cn.prepareStatement(sql))
    	{
    		pstmt.setString(1,password);
    		pstmt.setString(2,username);
    		pstmt.setInt(3,id);
    		pstmt.executeUpdate();
    	}
    }
	public void actionPerformed(ActionEvent ae)
	{
		String username=nameTextField.getText();
		char[] passwordChars=passwordField.getPassword();
		String password=new String(passwordChars);
		char[] confirmPasswordChars=confirmpasswordField.getPassword();
		String confirmPassword=new String(confirmPasswordChars);
		String userIdFieldStr=userIdField.getText();
		Arrays.fill(passwordChars,'0');
		Arrays.fill(confirmPasswordChars,'0');
		if(ae.getSource()==update)
		{
			if(userIdFieldStr.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please fill the details!");
			}
			else if(password.length()<8 || !pattern.matcher(password).find()) 
			{
				JOptionPane.showMessageDialog(null,"Please Enter atleast 8 Character, one special character, one Digit, one UpperCase, one Lowercase and do not enter space!");
			}
			else
			{
				if(isValidPassword(password))
				{
					if(password.equals(confirmPassword))
					{						
						try
						{
							int id=Integer.parseInt(userIdFieldStr);
							initDatabase();
							if(autheticateUser(id,username))
							{								
								updateUser(username,password,id);
								JOptionPane.showMessageDialog(null,"Update Profile Successful!");
								clearFields();
								j.setVisible(false);
								Login l=new Login();
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Invalid username or Id!");							
							}							
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Passwords do not match!");				
					}
				}			
			}
		}
		else if(ae.getSource()==view1) 
		{
        	if(passwordField.getEchoChar()==0) 
        	{
            	passwordField.setEchoChar('*'); // Hide the password
            	view1.setText("view");
        	} 
        	else 
        	{
            	passwordField.setEchoChar((char) 0); // Show the password
            	view1.setText("hide");
        	}
    	}
    	else if(ae.getSource()==view2) 
		{
        	if(confirmpasswordField.getEchoChar()==0) 
        	{
            	confirmpasswordField.setEchoChar('*'); // Hide the password
            	view2.setText("view");
        	} 
        	else 
        	{
            	confirmpasswordField.setEchoChar((char) 0); // Show the password
            	view2.setText("hide");
        	}
    	}
    	else if(ae.getSource()==back)
    	{
    		try
    		{
            	j.setVisible(false); 
            	Login l=new Login();		  			
    		}
    		catch(Exception e)
    		{	
    			System.out.println(e);
    		} 
    	}   	
	}
	private void clearFields()
	{
		nameTextField.setText("");
		passwordField.setText("");
		confirmpasswordField.setText("");
		userIdField.setText("");
	}
	public static boolean isValidPassword(String password)
	{
		return pattern.matcher(password).matches();
	}
	public static void main(String[] args) {
		new UpdateProfile();
	}
}
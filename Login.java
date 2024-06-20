import java.util.regex.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Login implements ActionListener
{
	private static final String passwordPattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private static final Pattern pattern=Pattern.compile(passwordPattern);
	JLabel username,password,confirmpassword,startExam;
	JTextField nameTextField;
	JPasswordField passwordField,confirmpasswordField;
	JButton signUp,signIn,view1,view2,updateBtn;
	JFrame j=new JFrame();
	public Login()
	{		
		j.setVisible(true);
		j.setLayout(null);
		j.setLocation(300,95);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(500,350);
		j.setTitle("Login Page");

		username=new JLabel("Username:");
		username.setBounds(40,30,120,25);
		username.setFont(new Font("Mongolian Baiti",1,15));
		j.add(username);

		nameTextField=new JTextField();
		nameTextField.setBounds(185,30,200,25);
		nameTextField.setFont(new Font("Times New Roman",1,15));
		j.add(nameTextField);

		password=new JLabel("Password:");
		password.setBounds(40,70,120,25);
		password.setFont(new Font("Mongolian Baiti",1,15));
		j.add(password);

		passwordField=new JPasswordField();
		passwordField.setBounds(185,70,200,25);
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Times New Roman",1,15));
		j.add(passwordField);

		view1=new JButton("view");
		view1.setBounds(390,70,70,25);
		view1.setBackground(Color.GREEN);
		view1.setForeground(Color.WHITE);
		j.add(view1);
		view1.addActionListener(this);

		confirmpassword=new JLabel("Confirm Password:");
		confirmpassword.setBounds(40,110,150,25);
		confirmpassword.setFont(new Font("Mongolian Baiti",1,15));
		j.add(confirmpassword);

		confirmpasswordField=new JPasswordField();
		confirmpasswordField.setBounds(185,110,200,25);
		confirmpasswordField.setEchoChar('*');
		confirmpasswordField.setFont(new Font("Times New Roman",1,15));
		j.add(confirmpasswordField);

		view2=new JButton("view");
		view2.setBounds(390,110,70,25);
		view2.setBackground(Color.GREEN);
		view2.setForeground(Color.WHITE);
		j.add(view2);
		view2.addActionListener(this);

		signUp=new JButton("Sign Up");
		signUp.setBounds(40,150,120,25);
		signUp.setFont(new Font("Mongolian Baiti",1,15));
		signUp.setBackground(Color.RED);
		signUp.setForeground(Color.WHITE);
		j.add(signUp);
		signUp.addActionListener(this);

		signIn=new JButton("Sign In");
		signIn.setBounds(185,150,120,25);
		signIn.setFont(new Font("Mongolian Baiti",1,15));
		signIn.setBackground(Color.RED);
		signIn.setForeground(Color.WHITE);
		j.add(signIn);
		signIn.addActionListener(this);

		updateBtn=new JButton("Update Password");
		updateBtn.setBounds(320,150,150,25);
		updateBtn.setFont(new Font("Mongolian Baiti",1,15));
		updateBtn.setBackground(Color.RED);
		updateBtn.setForeground(Color.WHITE);
		j.add(updateBtn);
		updateBtn.addActionListener(this);

		startExam=new JLabel("Sign In to Start the Exam");
		startExam.setBounds(170,190,200,25);
		startExam.setFont(new Font("Mongolian Baiti",1,15));
		j.add(startExam);		
	}
	private Connection getConnection() throws SQLException
	{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentExamData", "root", "devansh93215");
    }
    private void initDatabase() throws Exception
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    }
    private void registerUser(String username,String password) throws Exception
    {    	
    	String sql="insert into User(username,password) values(?,?)";
    	try(Connection cn=getConnection();
    		PreparedStatement pstmt=cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS))
    	{
    		pstmt.setString(1,username);
    		pstmt.setString(2,password);
    		pstmt.executeUpdate();
    		ResultSet rs=pstmt.getGeneratedKeys();
            if(rs.next())
            {
                int id=rs.getInt(1);
                JOptionPane.showMessageDialog(null,"Your Id is: "+id+" Please remember your Id!");
            }
    	}
    }
    private boolean autheticateUser(String username,String password) throws Exception
    {
    	String qry="select * from User where username=? and password=?";
    	try(Connection cn=getConnection();
    		PreparedStatement pstmt=cn.prepareStatement(qry))
    	{
    		pstmt.setString(1,username);
    		pstmt.setString(2,password);
    		try(ResultSet rs=pstmt.executeQuery())
    		{
    			return rs.next(); 
    		}
    	}
    }
	public void actionPerformed(ActionEvent ae)
	{
		String username=nameTextField.getText();
		char[] passwordChars=passwordField.getPassword();
		String password=new String(passwordChars);
		char[] confirmPasswordChars=confirmpasswordField.getPassword();
		String confirmPassword=new String(confirmPasswordChars);

		Arrays.fill(passwordChars,'0');
		Arrays.fill(confirmPasswordChars,'0');
		if(ae.getSource()==signUp)
		{
			if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
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
							initDatabase();
							registerUser(username,password);
							JOptionPane.showMessageDialog(null,"Sign Up Successful!");
							clearFields();
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
		else if(ae.getSource()==signIn)
		{
			if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please fill the details!");
			}
			else if(password.length()<8 || !pattern.matcher(password).find()) 
			{
				JOptionPane.showMessageDialog(null,"PLease Enter atleast 8 Character, one special character, one Digit, one UpperCase, one Lowercase and do not enter space!");
			}
			else
			{
				if(isValidPassword(password))
				{
					if(password.equals(confirmPassword))
					{
						try
						{
							initDatabase();
							if(autheticateUser(username,password))
							{
								JOptionPane.showMessageDialog(null,"Sign In Successful!");
								clearFields();
								int yesOption=JOptionPane.showConfirmDialog(null,"Are you ready for your Test?","Confirmation",JOptionPane.YES_NO_OPTION);
								if(yesOption==JOptionPane.YES_OPTION)
								{
									j.setVisible(false);
									Rules r=new Rules(username);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Invalid username or password!");
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
    	else if(ae.getSource()==updateBtn)
    	{   
    		j.setVisible(false); 		
    		UpdateProfile up=new UpdateProfile();     		  		 		
    	}
	}
	private void clearFields()
	{
		nameTextField.setText("");
		passwordField.setText("");
		confirmpasswordField.setText("");
	}
	public static boolean isValidPassword(String password)
	{
		return pattern.matcher(password).matches();
	}

	public static void main(String[] args) {
		new Login();
	}
}
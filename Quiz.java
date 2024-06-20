import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Quiz implements ActionListener 
{
    JFrame j;
    JPanel quizPanel;
    JLabel qno,question;
    JRadioButton opt1,opt2,opt3,opt4;
    ButtonGroup groupOption;
    JButton next, submit;
    String questions[][]=new String[15][5];
    String answers[][]=new String[15][2];
    int timer=15;
    int count=0;
    int score=0;
    String userAnswers[]=new String[15];
    Timer quizTimer;

    public Quiz() 
    {
        questions[0][0] = "Which is used to find and fix bugs in the Java programs?";
        questions[0][1] = "JVM";
        questions[0][2] = "JDB";
        questions[0][3] = "JDK";
        questions[0][4] = "JRE";

        questions[1][0] = "What is the return type of the hashCode() method in the Object class?";
        questions[1][1] = "int";
        questions[1][2] = "Object";
        questions[1][3] = "long";
        questions[1][4] = "void";

        questions[2][0] = "Which package contains the Random class?";
        questions[2][1] = "java.util package";
        questions[2][2] = "java.lang package";
        questions[2][3] = "java.awt package";
        questions[2][4] = "java.io package";

        questions[3][0] = "An interface with no fields or methods is known as?";
        questions[3][1] = "Runnable Interface";
        questions[3][2] = "Abstract Interface";
        questions[3][3] = "Marker Interface";
        questions[3][4] = "CharSequence Interface";

        questions[4][0] = "In which memory a String is stored, when we create a string using new operator?";
        questions[4][1] = "Stack";
        questions[4][2] = "String memory";
        questions[4][3] = "Random storage space";
        questions[4][4] = "Heap memory";

        questions[5][0] = "Which of the following is a marker interface?";
        questions[5][1] = "Runnable interface";
        questions[5][2] = "Remote interface";
        questions[5][3] = "Readable interface";
        questions[5][4] = "Result interface";

        questions[6][0] = "Which keyword is used for accessing the features of a package?";
        questions[6][1] = "import";
        questions[6][2] = "package";
        questions[6][3] = "extends";
        questions[6][4] = "export";

        questions[7][0] = "In java, jar stands for?";
        questions[7][1] = "Java Archive Runner";
        questions[7][2] = "Java Archive";
        questions[7][3] = "Java Application Resource";
        questions[7][4] = "Java Application Runner";

        questions[8][0] = "Which of the following is a mutable class in java?";
        questions[8][1] = "java.lang.StringBuilder";
        questions[8][2] = "java.lang.Short";
        questions[8][3] = "java.lang.Byte";
        questions[8][4] = "java.lang.String";

        questions[9][0] = "Which of the following option leads to the portability and security of Java?";
        questions[9][1] = "Bytecode is executed by JVM";
        questions[9][2] = "The applet makes the Java code secure and portable";
        questions[9][3] = "Use of exception handling";
        questions[9][4] = "Dynamic binding between objects";

        questions[10][0] = "Which of the following is not a Java features?";
        questions[10][1] = "Dynamic";
        questions[10][2] = "Architecture Neutral";
        questions[10][3] = "Use of pointers";
        questions[10][4] = "Object-oriented";

        questions[11][0] = "Which of the following is true about the anonymous inner class?";
        questions[11][1] = "It has only methods";
        questions[11][2] = "Objects can't be created";
        questions[11][3] = "It has a fixed class name";
        questions[11][4] = "It has no class name";

        questions[12][0] = "What is the return type of the hashCode() method in the Object class?";
        questions[12][1] = "int";
        questions[12][2] = "Object";
        questions[12][3] = "long";
        questions[12][4] = "void";

        questions[13][0] = "Which method of the Class.class is used to determine the name of a class represented by the class object as a String?";
        questions[13][1] = "getClass()";
        questions[13][2] = "intern()";
        questions[13][3] = "getName()";
        questions[13][4] = "toString()";

        questions[14][0] = "In which process, a local variable has the same name as one of the instance variables?";
        questions[14][1] = "Serialization";
        questions[14][2] = "Variable Shadowing";
        questions[14][3] = "Abstraction";
        questions[14][4] = "Multi-threading";
        
        answers[0][1] = "JDB";
        answers[1][1] = "int";
        answers[2][1] = "java.util package";
        answers[3][1] = "Marker Interface";
        answers[4][1] = "Heap memory";
        answers[5][1] = "Remote interface";
        answers[6][1] = "import";
        answers[7][1] = "Java Archive";
        answers[8][1] = "java.lang.StringBuilder";
        answers[9][1] = "Bytecode is executed by JVM";
        answers[10][1] = "Use of pointers";
        answers[11][1] = "It has no class name";
        answers[12][1] = "int";
        answers[13][1] = "getName()";
        answers[14][1] = "Variable Shadowing";

        j=new JFrame("Java Quiz");
        j.setSize(1200,650);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        quizPanel=new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String time="Time left: "+timer+" seconds";
                g.setColor(Color.RED);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                if (timer > 0) {
                    g.drawString(time, 900, 100);
                } else {
                    g.drawString("Times up!!", 900, 100);
                }
            }
        };
        quizPanel.setLayout(null);
        quizPanel.setBackground(Color.WHITE);

       	qno = new JLabel();
    qno.setBounds(30, 20, 35, 25);
    qno.setFont(new Font("Tahoma", Font.PLAIN, 20));
    quizPanel.add(qno);

    question = new JLabel();
    question.setBounds(65, 20, 1140, 25);
    question.setFont(new Font("Tahoma", Font.PLAIN, 20));
    quizPanel.add(question);

    opt1 = new JRadioButton();
    opt1.setBounds(80, 70, 400, 25);
    opt1.setBackground(Color.WHITE);
    opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
    quizPanel.add(opt1);

    opt2 = new JRadioButton();
    opt2.setBounds(80, 110, 400, 25);
    opt2.setBackground(Color.WHITE);
    opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
    quizPanel.add(opt2);

    opt3 = new JRadioButton();
    opt3.setBounds(80, 150, 400, 25);
    opt3.setBackground(Color.WHITE);
    opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
    quizPanel.add(opt3);

    opt4 = new JRadioButton();
    opt4.setBounds(80, 190, 400, 25);
    opt4.setBackground(Color.WHITE);
    opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
    quizPanel.add(opt4);

    groupOption = new ButtonGroup();
    groupOption.add(opt1);
    groupOption.add(opt2);
    groupOption.add(opt3);
    groupOption.add(opt4);

    next = new JButton("Next");
    next.setBounds(950, 130, 120, 25);
    next.setFont(new Font("Tahoma", Font.BOLD, 15));
    next.setBackground(new Color(30, 144, 255));
    next.setForeground(Color.WHITE);
    quizPanel.add(next);
    next.addActionListener(this);

    submit = new JButton("Submit");
    submit.setBounds(950, 170, 120, 25);
    submit.setFont(new Font("Tahoma", Font.BOLD, 15));
    submit.setBackground(new Color(30, 144, 255));
    submit.setForeground(Color.WHITE);
    submit.setEnabled(false);
    quizPanel.add(submit);
    submit.addActionListener(this);

    j.add(quizPanel);
    quizPanel.setVisible(true);
    j.setVisible(true);

    start(0);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            if (groupOption.getSelection() == null) {
                userAnswers[count] = "";
            } else {
                userAnswers[count] = groupOption.getSelection().getActionCommand();
            }
            
            if (count == 13) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            
            count++;
            start(count);
        } else if (ae.getSource() == submit) {
            if (groupOption.getSelection() == null) {
                userAnswers[count] = "";
            } else {
                userAnswers[count] = groupOption.getSelection().getActionCommand();
            }

            for (int i = 0; i < userAnswers.length; i++) {
                if (userAnswers[i].equals(answers[i][1])) {
                    score++;
                }
            }

            quizTimer.stop();
            JOptionPane.showMessageDialog(j, "Your score is " + score + " out of 15");
            System.exit(0);
        }
    }

    public void start(int count) {
        if (count == 14) { // Last question
            next.setEnabled(false);
            submit.setEnabled(true);
        }

        qno.setText("" + (count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);

        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);

        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);

        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);

        groupOption.clearSelection();
        timer = 15;
        startTimer();
    }

    private void startTimer() {
        if (quizTimer != null) {
            quizTimer.stop();
        }

        quizTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer > 0) {
                    timer--;
                    quizPanel.repaint();
                } else {
                    quizTimer.stop();
                    next.doClick();
                }
            }
        });
        quizTimer.start();
    }

    public static void main(String[] args) {
        new Quiz();
    }
}
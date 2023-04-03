import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Login extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JLabel label1 = new JLabel("User ID");
    JLabel label2 = new JLabel("Password");
    JLabel label3 = new JLabel("User Name");
    JLabel label4 = new JLabel("Mobile No.");
    JLabel label5 = new JLabel("Email Address");
    JLabel label6 = new JLabel("User ID");
    JLabel label7 = new JLabel("Password");

    JTextField field1 = new JTextField(19);
    JTextField field2 = new JTextField(19);
    JTextField field3 = new JTextField(19);
    JTextField field4 = new JTextField(19);
    JTextField field5 = new JTextField(19);
    JTextField field6 = new JTextField(19);
    JTextField field7 = new JTextField(19);
    JTextField field8 = new JTextField(37);
    JButton button1 = new JButton("Register");
    JButton button2 = new JButton("Login");

//    Client client = new Client();

    Login(Client client) {
        setTitle("Login");
        setSize(450, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        panel1.setLayout(new GridLayout(2,2));
        panel1.setPreferredSize(new Dimension(420, 90));
        panel1.setBorder(BorderFactory.createTitledBorder("Login"));
        panel3.setLayout(new GridLayout(5,2));
        panel3.setPreferredSize(new Dimension(420, 200));
        panel3.setBorder(BorderFactory.createTitledBorder("Registration"));


//        client.run();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field3.getText().equals("")) {
                    field8.setText("Please Enter ID");
                } else if (field4.getText().equals("")) {
                    field8.setText("Please Enter Name");
                } else if (field7.getText().equals("")) {
                    field8.setText("Please Enter Password");
                } else {
                    client.send("Add\n");
                    String line = field3.getText()+":"+field7.getText()+":"+field4.getText()+":"+field5.getText()+":"+field6.getText()+":"+"0\n";
                    client.send(line);
                    client.recieve();
                    field8.setText(client.recievedLine);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field1.getText().equals("")) {
                    field8.setText("Please Enter ID");
                } else if (field2.getText().equals("")) {
                    field8.setText("Please Enter Password");
                } else {
                    client.send("Login\n");
                    String line = field1.getText()+":"+field2.getText()+"\n";
                    client.send(line);
                    client.recieve();
                    field8.setText(client.recievedLine);

                    if(client.recievedLine.equals("Login Successful")){
                        dispose();
                        Application startApp = new Application(client, field1.getText());
                    }
                }
            }
        });

        panel1.add(label6);
        panel1.add(field1);
        panel1.add(label7);
        panel1.add(field2);
        panel2.add(button2);
        panel2.add(button1);
        panel3.add(label1);
        panel3.add(field3);
        panel3.add(label3);
        panel3.add(field4);
        panel3.add(label4);
        panel3.add(field5);
        panel3.add(label5);
        panel3.add(field6);
        panel3.add(label2);
        panel3.add(field7);
        panel4.add(field8);
        field8.setEditable(false);
        add(panel3);
        add(panel1);
        add(panel2);
        add(panel4);
        setVisible(true);
    }
}


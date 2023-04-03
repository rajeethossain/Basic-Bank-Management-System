import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Application extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JLabel label1 = new JLabel("Account No.");
    JLabel label2 = new JLabel("User ID");
    JLabel label3 = new JLabel("User Name");
    JLabel label4 = new JLabel("Mobile No.");
    JLabel label5 = new JLabel("Email Address");
    JLabel label6 = new JLabel("Current Balance");
    JLabel label7 = new JLabel("Ammount");
    JTextField field1 = new JTextField(19);
    JTextField field2 = new JTextField(19);
    JTextField field3 = new JTextField(19);
    JTextField field4 = new JTextField(19);
    JTextField field5 = new JTextField(19);
    JTextField field6 = new JTextField(19);
    JTextField field7 = new JTextField(25);
    JTextField field8 = new JTextField(37);
    JButton button1 = new JButton("Deposit");
    JButton button2 = new JButton("Withdraw");
    JButton button3 = new JButton("Logout");

    Application(Client client, String id) {
        setTitle("Banking System");
        setSize(450, 430);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        panel1.setLayout(new GridLayout(6,2));
        panel1.setPreferredSize(new Dimension(420, 200));
        panel1.setBorder(BorderFactory.createTitledBorder("Account Details"));
        panel2.setPreferredSize(new Dimension(420, 95));
        panel2.setBorder(BorderFactory.createTitledBorder("Transaction"));

        field1.setEditable(false);
        field2.setEditable(false);
        field3.setEditable(false);
        field4.setEditable(false);
        field5.setEditable(false);
        field6.setEditable(false);
        field8.setEditable(false);

        client.send("Search\n");
        client.send(id+"\n");
        client.recieve();

        String[] info = client.recievedLine.split(":");
        field1.setText(info[0]);
        field2.setText(info[1]);
        field3.setText(info[3]);
        field4.setText(info[4]);
        field5.setText(info[5]);
        field6.setText(info[6]);



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field7.getText().equals("")) {
                    field8.setText("Please Enter Amount");
                } else {
                    int newAmount = Integer.parseInt(field7.getText());
                    int curBalance = Integer.parseInt(field6.getText());
                    int newBalance = curBalance + newAmount;
                    String newData = info[0]+":"+info[1]+":"+info[2]+":"+info[3]+":"+info[4]+":"+info[5]+":"+newBalance+"\n";
                    client.send("Update\n");
                    client.send(newData+"\n");
                    client.recieve();
                    field8.setText(client.recievedLine);
                    field6.setText(newBalance+"");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field7.getText().equals("")) {
                    field8.setText("Please Enter Amount");
                } else {
                    int newAmount = Integer.parseInt(field7.getText());
                    int curBalance = Integer.parseInt(field6.getText());
                    int newBalance = curBalance - newAmount;
                    String newData = info[0]+":"+info[1]+":"+info[2]+":"+info[3]+":"+info[4]+":"+info[5]+":"+newBalance+"\n";
                    client.send("Update\n");
                    client.send(newData+"\n");
                    client.recieve();
                    field8.setText(client.recievedLine);
                    field6.setText(newBalance+"");
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login(client);
            }
        });

        panel1.add(label1);
        panel1.add(field1);
        panel1.add(label2);
        panel1.add(field2);
        panel1.add(label3);
        panel1.add(field3);
        panel1.add(label4);
        panel1.add(field4);
        panel1.add(label5);
        panel1.add(field5);
        panel1.add(label6);
        panel1.add(field6);
        panel2.add(label7);
        panel2.add(field7);
        panel2.add(button1);
        panel2.add(button2);
        panel3.add(button3);
        panel4.add(field8);
        add(panel1);
        add(panel2);
        add(panel4);
        add(panel3);
        setVisible(true);
    }
}


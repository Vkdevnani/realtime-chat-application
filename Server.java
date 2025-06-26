import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Server implements ActionListener
{
    static JFrame f = new JFrame();
    static JTextField textBox;
    static JPanel chatPanel;
    static Box vertical = Box.createVerticalBox();

    static DataInputStream dInput;
    static DataOutputStream dOutput;

    Server()
    {
        // Frame
        f.setLayout(null);
        f.setSize(500, 610);
        f.setLocation(45, 45);
        f.getContentPane().setBackground(Color.WHITE);
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0,0, 500, 50);
        headerPanel.setBackground(Color.DARK_GRAY);
        f.add(headerPanel);

        // Profile Picture
        ImageIcon profilePicture = new ImageIcon(ClassLoader.getSystemResource("profile photo.jpg"));
        Image image = profilePicture.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon picture = new ImageIcon(image);
        JLabel photo = new JLabel(picture);
        photo.setBounds(15, 5, 40, 40);
        headerPanel.add(photo);

        // Profile Title
        JLabel name = new JLabel("Vinay");
        name.setBounds(70, 15, 100, 20);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        headerPanel.add(name);

        // Chat Panel
        chatPanel = new JPanel();
        chatPanel.setBounds(5, 55, 475, 470);
        f.add(chatPanel);

        // Text Field
        textBox = new JTextField();
        textBox.setBounds(5, 530, 380, 40);
        textBox.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        f.add(textBox);

        // Send Button
        JButton sendButton = new JButton("Send");
        sendButton.setBounds(385, 530, 95, 40);
        sendButton.setBackground(Color.DARK_GRAY);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        sendButton.addActionListener(this);
        f.add(sendButton);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            String output = textBox.getText();
            JPanel sentText = formatLabel(output);
            chatPanel.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(sentText, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            chatPanel.add(vertical, BorderLayout.PAGE_START);
            
            dOutput.writeUTF(output);
            
            textBox.setText("");
            
            f.repaint();
            f.invalidate();
            f.validate();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static JPanel formatLabel(String output)
    {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        JLabel o = new JLabel(output);
        o.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        o.setBackground(Color.DARK_GRAY);
        o.setForeground(Color.WHITE);
        o.setOpaque(true);
        o.setBorder(new EmptyBorder(15, 15, 15, 50));
        messagePanel.add(o);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(calendar.getTime()));
        messagePanel.add(time);

        return messagePanel;
    }

    public static void main(String[] args)
    {
        new Server();

        try
        {
            ServerSocket socket = new ServerSocket(999);
            
            while (true)
            {
                Socket s = socket.accept();
                dInput = new DataInputStream(s.getInputStream());
                dOutput = new DataOutputStream(s.getOutputStream());

                while (true)
                {
                    String msg = dInput.readUTF();
                    JPanel panel = formatLabel(msg);
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
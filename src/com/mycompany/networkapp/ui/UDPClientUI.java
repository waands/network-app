package com.mycompany.networkapp.ui;
import com.mycompany.networkapp.UDPClient;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UDPClientUI {
    private JFrame frame;
    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField messageTextField;
    private JTextArea textArea;
    private UDPClient udpClient;

    public UDPClientUI() {
        frame = new JFrame("UDP Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel ipLabel = new JLabel("IP Address:");
        ipLabel.setBounds(10, 10, 100, 25);
        frame.add(ipLabel);

        ipTextField = new JTextField("127.0.0.1");
        ipTextField.setBounds(120, 10, 150, 25);
        frame.add(ipTextField);

        JLabel portLabel = new JLabel("Port:");
        portLabel.setBounds(10, 40, 100, 25);
        frame.add(portLabel);

        portTextField = new JTextField("4445");
        portTextField.setBounds(120, 40, 150, 25);
        frame.add(portTextField);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(10, 70, 100, 25);
        frame.add(messageLabel);

        messageTextField = new JTextField();
        messageTextField.setBounds(120, 70, 150, 25);
        frame.add(messageTextField);

        JButton connectButton = new JButton("Connect");
        connectButton.setBounds(10, 100, 100, 25);
        frame.add(connectButton);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(120, 100, 100, 25);
        frame.add(sendButton);

        textArea = new JTextArea();
        textArea.setBounds(10, 130, 360, 120);
        frame.add(textArea);

        udpClient = new UDPClient();

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        String ip = ipTextField.getText();
                        int port = Integer.parseInt(portTextField.getText());
                        udpClient.startConnection(ip, port);
                        SwingUtilities.invokeLater(() -> textArea.append("Connected to " + ip + " on port " + port + "\n"));
                    } catch (Exception exception) {
                        SwingUtilities.invokeLater(() -> textArea.append("Failed to connect\n"));
                        exception.printStackTrace();
                    }
                }).start();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        String message = messageTextField.getText();
                        udpClient.sendMessage(message);
                        String response = udpClient.receiveMessage();
                        SwingUtilities.invokeLater(() -> textArea.append("Received response: " + response + "\n"));
                    } catch (Exception exception) {
                        SwingUtilities.invokeLater(() -> textArea.append("Failed to send message\n"));
                        exception.printStackTrace();
                    }
                }).start();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UDPClientUI();
    }
}

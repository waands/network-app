package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.UDPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UDPClientUI extends JFrame {
    private JPanel panel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;
    private JButton connectButton;
    private JTextField ipTextField;
    private JTextField portTextField;
    private UDPClient udpClient;

    public UDPClientUI() {
        setTitle("UDP Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        textField = new JTextField(20);
        textArea = new JTextArea(10, 30);
        sendButton = new JButton("Send");
        connectButton = new JButton("Connect");
        ipTextField = new JTextField("127.0.0.1", 10);
        portTextField = new JTextField("4445", 5);

        panel.add(new JLabel("IP:"));
        panel.add(ipTextField);
        panel.add(new JLabel("Port:"));
        panel.add(portTextField);
        panel.add(connectButton);
        panel.add(textField);
        panel.add(sendButton);
        panel.add(new JScrollPane(textArea));
        add(panel);

        udpClient = new UDPClient();

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    udpClient.startConnection(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
                    textArea.append("UDP connection established\n");
                } catch (Exception exception) {
                    textArea.append("Failed to establish UDP connection\n");
                    exception.printStackTrace();
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                if (udpClient != null) {
                    try {
                        String response = udpClient.sendMessage(message);
                        textArea.append("Server: " + response + "\n");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        UDPClientUI udpClientUI = new UDPClientUI();
        udpClientUI.setVisible(true);
    }
}

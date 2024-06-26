package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.Client;
import com.mycompany.networkapp.UDPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientUI extends JFrame {
    private JPanel panel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;
    private JButton connectTcpButton;
    private JButton connectUdpButton;
    private JTextField ipTextField;
    private JTextField portTextField;
    private Client tcpClient;
    private UDPClient udpClient;

    public ClientUI() {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        textField = new JTextField(20);
        textArea = new JTextArea(10, 30);
        sendButton = new JButton("Send");
        connectTcpButton = new JButton("Connect TCP");
        connectUdpButton = new JButton("Connect UDP");
        ipTextField = new JTextField("127.0.0.1", 10);
        portTextField = new JTextField("6666", 5);

        panel.add(new JLabel("IP:"));
        panel.add(ipTextField);
        panel.add(new JLabel("Port:"));
        panel.add(portTextField);
        panel.add(connectTcpButton);
        panel.add(connectUdpButton);
        panel.add(textField);
        panel.add(sendButton);
        panel.add(new JScrollPane(textArea));
        add(panel);

        tcpClient = new Client();
        udpClient = new UDPClient();

        connectTcpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tcpClient.startConnection(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
                    textArea.append("TCP connection established\n");
                } catch (IOException ioException) {
                    textArea.append("Failed to establish TCP connection\n");
                    ioException.printStackTrace();
                }
            }
        });

        connectUdpButton.addActionListener(new ActionListener() {
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
                if (tcpClient != null) {
                    try {
                        String response = tcpClient.sendMessage(message);
                        textArea.append("Server: " + response + "\n");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
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
        ClientUI clientUI = new ClientUI();
        clientUI.setVisible(true);
    }
}

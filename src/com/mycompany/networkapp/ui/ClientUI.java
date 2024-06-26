package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientUI extends JFrame {
    private JPanel panel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;
    private JButton connectButton;
    private JTextField ipTextField;
    private JTextField portTextField;
    private Client tcpClient;
    private BufferedReader inFromServer; // Usado para leitura
    private Thread readThread;

    public ClientUI() {
        setTitle("TCP Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        textField = new JTextField(20);
        textArea = new JTextArea(10, 30);
        sendButton = new JButton("Send");
        connectButton = new JButton("Connect");
        ipTextField = new JTextField("127.0.0.1", 10);
        portTextField = new JTextField("6666", 5);

        panel.add(new JLabel("IP:"));
        panel.add(ipTextField);
        panel.add(new JLabel("Port:"));
        panel.add(portTextField);
        panel.add(connectButton);
        panel.add(textField);
        panel.add(sendButton);
        panel.add(new JScrollPane(textArea));
        add(panel);

        tcpClient = new Client();

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        tcpClient.startConnection(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
                        SwingUtilities.invokeLater(() -> textArea.append("TCP connection established\n"));
                        inFromServer = new BufferedReader(new InputStreamReader(tcpClient.getInputStream())); // Inicializa o BufferedReader para leitura
                        startReading(); // Iniciar leitura das mensagens após conexão estabelecida
                    } catch (IOException ioException) {
                        SwingUtilities.invokeLater(() -> textArea.append("Failed to establish TCP connection\n"));
                        ioException.printStackTrace();
                    }
                }).start();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    String message = textField.getText();
                    if (tcpClient != null) {
                        try {
                            String response = tcpClient.sendMessage(message);
                            SwingUtilities.invokeLater(() -> textArea.append("Server: " + response + "\n"));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void startReading() {
        readThread = new Thread(() -> {
            try {
                String message;
                while ((message = inFromServer.readLine()) != null) {
                    String finalMessage = message;
                    SwingUtilities.invokeLater(() -> textArea.append("Received: " + finalMessage + "\n"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        readThread.start();
    }

    public static void main(String[] args) {
        ClientUI clientUI = new ClientUI();
        clientUI.setVisible(true);
    }
}

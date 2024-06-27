package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.Client;
import com.mycompany.networkapp.Server;
import com.mycompany.networkapp.UDPClient;
import com.mycompany.networkapp.UDPServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NetworkApp extends JFrame {
    private JPanel panel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton tcpSendButton;
    private JButton udpSendButton;
    private JButton connectButton;
    private JTextField ipTextField;
    private JTextField tcpPortTextField;
    private JTextField udpPortTextField;
    private Client tcpClient;
    private Server tcpServer;
    private UDPClient udpClient;
    private UDPServer udpServer;
    private BufferedReader tcpInFromServer; // Usado para leitura TCP
    private Thread tcpReadThread;
    private Thread udpReadThread;

    public NetworkApp() {
        setTitle("TCP/UDP Network App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        textField = new JTextField(30);
        textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Connection Settings
        JLabel settingsLabel = new JLabel("Connection Settings:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(settingsLabel, constraints);

        JLabel ipLabel = new JLabel("IP:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(ipLabel, constraints);

        ipTextField = new JTextField("127.0.0.1", 10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(ipTextField, constraints);

        JLabel tcpPortLabel = new JLabel("TCP Port:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(tcpPortLabel, constraints);

        tcpPortTextField = new JTextField("6666", 5);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(tcpPortTextField, constraints);

        JLabel udpPortLabel = new JLabel("UDP Port:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(udpPortLabel, constraints);

        udpPortTextField = new JTextField("4445", 5);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(udpPortTextField, constraints);

        connectButton = new JButton("Connect");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(connectButton, constraints);

        // Message Area
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);

        // Message Sending Components
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(textField, constraints);

        tcpSendButton = new JButton("Send TCP");
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(tcpSendButton, constraints);

        udpSendButton = new JButton("Send UDP");
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(udpSendButton, constraints);

        add(panel);

        tcpClient = new Client();
        tcpServer = new Server();
        udpClient = new UDPClient();
        udpServer = new UDPServer();

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        tcpClient.startConnection(ipTextField.getText(), Integer.parseInt(tcpPortTextField.getText()));
                        udpClient.startConnection(ipTextField.getText(), Integer.parseInt(udpPortTextField.getText()));
                        SwingUtilities.invokeLater(() -> textArea.append("TCP and UDP connections established\n"));
                        tcpInFromServer = new BufferedReader(new InputStreamReader(tcpClient.getInputStream())); // Inicializa o BufferedReader para leitura
                        startTCPReading(); // Iniciar leitura das mensagens TCP após conexão estabelecida
                    } catch (IOException ioException) {
                        SwingUtilities.invokeLater(() -> textArea.append("Failed to establish TCP connection\n"));
                        ioException.printStackTrace();
                    } catch (Exception exception) {
                        SwingUtilities.invokeLater(() -> textArea.append("Failed to establish UDP connection\n"));
                        exception.printStackTrace();
                    }
                }).start();
            }
        });

        tcpSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    String message = textField.getText();
                    if (tcpClient != null) {
                        try {
                            String response = tcpClient.sendMessage(message);
                            SwingUtilities.invokeLater(() -> textArea.append("Server (TCP): " + response + "\n"));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        udpSendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    String message = textField.getText();
                    if (udpClient != null) {
                        try {
                            udpClient.sendMessage(message);
                            String response = udpClient.receiveMessage();
                            SwingUtilities.invokeLater(() -> textArea.append("Server (UDP): " + response + "\n"));
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startTCPReading() {
        tcpReadThread = new Thread(() -> {
            try {
                String message;
                while ((message = tcpInFromServer.readLine()) != null) {
                    String finalMessage = message;
                    SwingUtilities.invokeLater(() -> textArea.append("Received from Server (TCP): " + finalMessage + "\n"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tcpReadThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetworkApp());
    }
}

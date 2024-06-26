package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerUI extends JFrame {
    private JTextArea textArea;
    private Server tcpServer;

    public ServerUI() {
        setTitle("Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea(10, 30);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startTcpServerButton = new JButton("Start TCP Server");
        buttonPanel.add(startTcpServerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        tcpServer = new Server();

        startTcpServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        tcpServer.start(6666);
                        textArea.append("TCP Server started on port 6666\n");
                    } catch (IOException ex) {
                        textArea.append("Failed to start TCP Server\n");
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
    }

    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
        serverUI.setVisible(true);
    }
}

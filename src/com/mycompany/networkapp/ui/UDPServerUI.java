package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.UDPServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UDPServerUI extends JFrame {
    private JTextArea textArea;
    private UDPServer udpServer;

    public UDPServerUI() {
        setTitle("UDP Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea(10, 30);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startUdpServerButton = new JButton("Start UDP Server");
        buttonPanel.add(startUdpServerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        udpServer = new UDPServer() {
            @Override
            public void onReceive(String message) {
                SwingUtilities.invokeLater(() -> textArea.append("Received: " + message + "\n"));
            }
        };

        startUdpServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        udpServer.start(4445);
                        textArea.append("UDP Server started on port 4445\n");
                    } catch (Exception ex) {
                        textArea.append("Failed to start UDP Server\n");
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
    }

    public static void main(String[] args) {
        UDPServerUI udpServerUI = new UDPServerUI();
        udpServerUI.setVisible(true);
    }
}

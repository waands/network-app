package com.mycompany.networkapp.ui;
import com.mycompany.networkapp.UDPServer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UDPServerUI {
    private JFrame frame;
    private JTextArea textArea;
    private UDPServer udpServer;

    public UDPServerUI() {
        frame = new JFrame("UDP Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JButton startUdpServerButton = new JButton("Start UDP Server");
        startUdpServerButton.setBounds(10, 10, 150, 25);
        frame.add(startUdpServerButton);

        textArea = new JTextArea();
        textArea.setBounds(10, 50, 360, 200);
        frame.add(textArea);

        udpServer = new UDPServer();

        startUdpServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = 4445;
                new Thread(() -> {
                    try {
                        udpServer.start(port, message -> 
                            SwingUtilities.invokeLater(() -> textArea.append("Received message: " + message + "\n"))
                        );
                        SwingUtilities.invokeLater(() -> textArea.append("UDP Server started on port " + port + "\n"));
                    } catch (Exception exception) {
                        SwingUtilities.invokeLater(() -> textArea.append("Failed to start UDP server\n"));
                        exception.printStackTrace();
                    }
                }).start();
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UDPServerUI();
    }
}

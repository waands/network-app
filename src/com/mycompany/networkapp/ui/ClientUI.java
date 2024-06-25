package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientUI extends JFrame {
    private JPanel panel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;
    private Client client;

    public ClientUI() {
        setTitle("Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        textField = new JTextField(20);
        textArea = new JTextArea(10, 30);
        sendButton = new JButton("Enviar");
        panel.add(textField);
        panel.add(sendButton);
        panel.add(new JScrollPane(textArea));
        add(panel);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String response = client.sendMessage(textField.getText());
                    textArea.append("Server: " + response + "\n");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static void main(String[] args) {
        ClientUI clientUI = new ClientUI();
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientUI.setClient(client);
        clientUI.setVisible(true);
    }
}
 

package com.mycompany.networkapp.ui;

import com.mycompany.networkapp.Server;

import javax.swing.*;
import java.io.IOException;

public class ServerUI extends JFrame {
    private JTextArea textArea;
    private Server server;

    public ServerUI() {
        setTitle("Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea(10, 30);
        add(new JScrollPane(textArea));
        server = new Server();
    }

    public void startServer(int port) {
        new Thread(() -> {
            try {
                System.out.println("Iniciando servidor na porta " + port);
                server.start(port);
                System.out.println("Servidor iniciado com sucesso na porta " + port);
            } catch (IOException e) {
                System.err.println("Erro ao iniciar servidor: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        ServerUI serverUI = new ServerUI();
        serverUI.setVisible(true);
        serverUI.startServer(6666);
    }
}

package com.mycompany.networkapp;

import java.io.*;
import java.net.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connected to server on " + ip + ":" + port);
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        System.out.println("Sent message: " + msg);
        String response = in.readLine();
        System.out.println("Received response: " + response);
        return response;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        System.out.println("Connection closed");
    }

    // Método para acessar o InputStream do clientSocket
    public InputStream getInputStream() throws IOException {
        return clientSocket.getInputStream();
    }

    // Método para verificar se a conexão está estabelecida
    public boolean isConnected() {
        return clientSocket != null && clientSocket.isConnected();
    }

    // Método para fechar a conexão
    public void closeConnection() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

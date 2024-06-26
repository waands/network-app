package com.mycompany.networkapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    private byte[] buf;

    public void startConnection(String ip, int port) throws Exception {
        socket = new DatagramSocket();
        address = InetAddress.getByName(ip);
        this.port = port;
    }

    public String sendMessage(String msg) throws Exception {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);

        // Increase the buffer size to 256 bytes for receiving the message
        buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void stopConnection() {
        socket.close();
    }

    public static void main(String[] args) {
        UDPClient client = new UDPClient();
        int port = 4445; // Porta para se conectar ao servidor UDP
        try {
            client.startConnection("127.0.0.1", port); // IP do servidor UDP (neste exemplo, localhost)
            String response = client.sendMessage("Hello, server!");
            System.out.println("Server response: " + response);
            client.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

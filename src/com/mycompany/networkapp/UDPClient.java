package com.mycompany.networkapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public void startConnection(String ip, int port) throws Exception {
        address = InetAddress.getByName(ip);
        this.port = port;
        socket = new DatagramSocket();
        System.out.println("UDP Client started");
    }

    public void sendMessage(String message) throws Exception {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        System.out.println("Sent message: " + message + " to " + address + ":" + port);
    }

    public String receiveMessage() throws Exception {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received response: " + received);
        return received;
    }

    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("UDP Client socket closed");
        }
    }
}

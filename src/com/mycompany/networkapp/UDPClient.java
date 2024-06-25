package com.mycompany.networkapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public void startConnection(String ip, int port) throws Exception {
        socket = new DatagramSocket();
        address = InetAddress.getByName(ip);
        this.port = port;
    }

    public String sendMessage(String msg) throws Exception {
        byte[] buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void stopConnection() {
        socket.close();
    }
}

package com.mycompany.networkapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private DatagramSocket socket;

    public void start(int port) throws Exception {
        socket = new DatagramSocket(port);
        byte[] buf = new byte[256];

        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            int portNumber = packet.getPort();
            String received = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                break;
            }

            String response = "Server received: " + received;
            buf = response.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, portNumber);
            socket.send(packet);
        }
        socket.close();
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        int port = 6666; // Define a porta que o servidor UDP irá escutar
        try {
            server.start(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

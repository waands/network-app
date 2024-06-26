package com.mycompany.networkapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    private DatagramSocket socket;

    public void start(int port, MessageReceivedCallback callback) {
        new Thread(() -> {
            try {
                socket = new DatagramSocket(port);
                System.out.println("UDP Server started on port " + port);

                byte[] buffer = new byte[1024];

                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Received message: " + received);

                    // Echo the message back to the client
                    DatagramPacket responsePacket = new DatagramPacket(packet.getData(), packet.getLength(),
                            packet.getAddress(), packet.getPort());
                    socket.send(responsePacket);
                    System.out.println("Sent response to " + packet.getAddress() + ":" + packet.getPort());

                    // Notify callback with received message
                    callback.messageReceived(received);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                    System.out.println("UDP Server socket closed");
                }
            }
        }).start();
    }

    public interface MessageReceivedCallback {
        void messageReceived(String message);
    }
}

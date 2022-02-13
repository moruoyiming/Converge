package com.example.converge.note.androidbasics.network.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        //服务器启动必备
        ServerSocket serverSocket = new ServerSocket();
        //表示服务器在哪个端口上监听
        serverSocket.bind(new InetSocketAddress(10003));
        System.out.println("----------------- Start server --------------------");
        try {
            while (true) {
                System.out.println("----------------- wait connect --------------------");
                new Thread(new ServerTask(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
            System.out.println("----------------- End server --------------------");
        }
    }

    private static class ServerTask implements Runnable {

        private Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                String userName = inputStream.readUTF();
                System.out.println("Accept client message " + userName);
                outputStream.writeUTF("Hello " + userName);
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

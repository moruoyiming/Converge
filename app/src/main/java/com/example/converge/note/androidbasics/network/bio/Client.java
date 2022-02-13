package com.example.converge.note.androidbasics.network.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        //客户端必备
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        InetSocketAddress serverAddr = new InetSocketAddress("127.0.0.1",10003);

        try {
            socket = new Socket();
            socket.connect(serverAddr);
            System.out.println("-----------------  connect --------------------");
            outputStream = new ObjectOutputStream(socket.getOutputStream());// 先后顺序有影响
            inputStream = new ObjectInputStream(socket.getInputStream());// 先后顺序有影响

            outputStream.writeUTF("Lance");
            outputStream.flush();

            System.out.println(inputStream.readUTF());
        } finally {
            System.out.println("-----------------  close --------------------");
            if(socket!= null) socket.close();
            if(outputStream!=null ) outputStream.close();
            if(inputStream!=null ) inputStream.close();
        }

    }
}

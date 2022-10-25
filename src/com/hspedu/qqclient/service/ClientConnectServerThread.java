package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @version 1.0
 * @Author Ace
 * @create 2022/10/11 16:52
 */
public class ClientConnectServerThread extends Thread{
    // 该线程需要持有Socket
    private Socket socket;

    // 构造器可以接受一个Socket对象
    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        // 因为Thread需要在后台和服务器同学，因为我们while循环
        while (true){

            try {
                System.out.println("客户端线程，等待从读取服务器端发送的消息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 如果服务器没有发送Message对象，线程会阻塞在这里
                Message message = (Message) ois.readObject();
                // 注意，后面我们需要去使用message
                // 判断这个message类型，然后做相应的业务处理
                // 如果读取到的是 服务器返回的在线用户列表
                if(message.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    // 取出在线列表信息，并显示
                    // 规定
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("\n=====当前在线用户列表=====");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户：" + onlineUsers[i]);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // 为了更方便得到Socket
    public Socket getSocket() {
        return socket;
    }
}

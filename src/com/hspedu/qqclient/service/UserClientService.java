package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;
import com.hspedu.qqcommon.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @version 1.0
 * @Author Ace
 * @create 2022/10/11 16:15
 * 该类完成用户登录和用户注册功能
 */
public class UserClientService {

    // 因为我们可能在其它地方使用user信息，因此做成成员属性
    private User u = new User();
    // 因为Socket在其它地方可能使用，因为做成属性
    private Socket socket;


    // 根据userId 和 pwd 到服务器验证该用户是否合法
    public boolean checkUser(String userId, String pwd) {
        boolean b = false;
        // 创建User对象
        u.setUserId(userId);
        u.setPasswd(pwd);

        // 连接到服务端，发送u对象
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            // 得到ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u); // 发送User对象

            // 读取从服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) { // 登录OK

                // 创建一个和服务器端保持通信的线程-> 创建一个类 ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                // 启动客户端的线程
                clientConnectServerThread.start();
                // 这里为了后面客户端扩展，我们将线程放入到集合管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);

                b = true;

            } else {
                // 如果登录失败，我们就不能启动和服务器通信的线程，关闭socket
                socket.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    //向服务器请求在线用户列表
    public void onlineFriendList(){

        // 发送一个Message,类型MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);

        // 发送给服务器
        try {
            // 从管理线程的集合中，通过userId,得到这个线程
            ClientConnectServerThread clientConnectServerThread =
                    ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId());
            // 通过这个线程得到关联的socket
            Socket socket = clientConnectServerThread.getSocket();
            // 得到当前线程Socket 对应的 ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message); // 发送一个Message对象，向服务器索要在线用户列表
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

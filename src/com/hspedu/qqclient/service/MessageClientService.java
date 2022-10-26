package com.hspedu.qqclient.service;

import com.hspedu.qqcommon.Message;
import com.hspedu.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @version 1.0
 * @Author Ace
 * @create 2022/10/26 11:31
 * 用于用户私聊/群聊消息的服务
 */
public class MessageClientService {
    /**
     *
     * @param content 内容
     * @param senderId 发送者
     */
    // 群发消息
    public void sendMessageToAll(String content, String senderId) {
        // 构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//群聊消息的类型
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString()); // 发送事件设置到message对象
        System.out.println(senderId + "对大家说" + content);
        // 发送给服务器
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param content  内容
     * @param senderId 发送用户id
     * @param getterId 接收用户id
     */
    public void sendMessageToOne(String content, String senderId, String getterId) {
        // 构建message
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);//普通的聊天消息的类型
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setSendTime(new Date().toString()); // 发送事件设置到message对象
        System.out.println(senderId + "对" + getterId + "说" + content);
        // 发送给服务器
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.hspedu.qqclient.service;

import java.util.HashMap;

/**
 * @version 1.0
 * @Author Ace
 * @create 2022/10/12 11:49
 * 该类管理客户端连接到服务器的线程的类
 */
public class ManageClientConnectServerThread {
    // 我们把多个线程放入一个HasMap集合，key就是用户id，value就是线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    // 将某个线程加入到集合
    public static void addClientConnectServerThread(String userId,
                                                    ClientConnectServerThread clientConnectServerThread) {
        hm.put(userId, clientConnectServerThread);
    }

    // 通过userId,可以得到对应线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hm.get(userId);
    }
}

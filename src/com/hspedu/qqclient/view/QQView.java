package com.hspedu.qqclient.view;

import com.hspedu.qqclient.Utility.Utility;
import com.hspedu.qqclient.service.UserClientService;

/**
 * @version 1.0
 * @Author Ace
 * @create 2022/10/11 15:40
 * 客户端的菜单界面
 */
@SuppressWarnings("all")
public class QQView {

    private boolean loop = true; //控制是否显示菜单
    private String key = ""; // 接收用户的键盘输入
    private UserClientService userClientService = new UserClientService();//对象用于登录服务/注册用户

    public static void main(String[] args) {
        new QQView().mainMenu();
        System.out.println("客户端退出系统.....");
    }

    // 显示主菜单
    private void mainMenu() {

        while (loop) {
            System.out.println("======欢迎登录网络通信系统======");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);

            // 根据用户的输入，来处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.println("请输入用户号：");
                    String userId = Utility.readString(50);
                    System.out.println("请输入密   码");
                    String pwd = Utility.readString(50);
                    // 这里还需要去服务端验证用户是否合法
                    // 这里很多代码，编写一个类 UserClientService[用户登录/注册]

                    if (userClientService.checkUser(userId, pwd)) {
                        System.out.println("欢迎用户" + userId + "登录成功");
                        // 进入到二级菜单
                        while (loop) {
                            System.out.println("=====网络通信系统二级菜单(用户" + userId + ")=====");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);

                            switch (key) {
                                case "1":
                                    System.out.println("显示在线用户列表");
                                    break;
                                case "2":
                                    System.out.println("群发消息");
                                    break;
                                case "3":
                                    System.out.println("私聊消息");
                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                            }

                        }
                    } else { // 登录服务器失败
                        System.out.println("登录失败，用户名或密码错误");

                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}
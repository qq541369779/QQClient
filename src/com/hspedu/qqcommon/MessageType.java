package com.hspedu.qqcommon;

/**
 * @version 1.0
 * @Author Ace
 * @create 2022/10/11 15:19
 * 表示消息类型
 */
public interface MessageType {
    // 解读
    // 1.在接口中定义一些常量
    // 2.不同的常量的值，表示不同的消息类型
    String MESSAGE_LOGIN_SUCCEED = "1";//表示登录成功
    String MESSAGE_LOGIN_FAIL = "2";//表示登录失败
}

package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CsWebSocketHandler extends TextWebSocketHandler {
    ConcurrentHashMap<String, CopyOnWriteArrayList<WebSocketSession>> concurrentHashMap = new ConcurrentHashMap<>();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("接收到消息：" + message.getPayload());
        String id = getParam(session.getUri().toString(),"id");
        String token = getParam(session.getUri().toString(),"token");
        if(token!=null && !token.equals("undefined")) {
            // 执行获取id
            id= (String) StpUtil.getLoginIdByToken(token);
        }
        massSendMessage(id,message.getPayload(),session);
    }
    public void massSendMessage(String roomId,String message,WebSocketSession session) throws IOException {
        if(!concurrentHashMap.containsKey(roomId)) {
            session.sendMessage(new TextMessage("系统^&^房间不存在。"));
            session.close();
            return;
        }
        CopyOnWriteArrayList<WebSocketSession> list = concurrentHashMap.get(roomId);
        for (WebSocketSession ss:list){
            if(ss.getId().equals(session.getId())){
                continue;
            }
            try {
                ss.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                list.remove(ss);
                continue;
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //获取 token和id
        String token = getParam(session.getUri().toString(),"token");
        String id = getParam(session.getUri().toString(),"id");
        System.out.println(session.getUri());
        if(id!=null) {
            // 执行加入房间操作
            if(!concurrentHashMap.containsKey(id)){
                session.sendMessage(new TextMessage( "系统^&^房间未创建或不存在。"));
                session.close();
                return;
            }
            concurrentHashMap.get(id).add(session);
            session.sendMessage(new TextMessage( "系统^&^您已加入房间。"));
        }
        if(token!=null && !token.equals("undefined")) {
            // 执行创建房间操作
            this.createRoom(session,token);
        }
    }
    public void createRoom(WebSocketSession session, String token) throws IOException {
        // 解密token获取用户id
        String id = null;
        id= (String) StpUtil.getLoginIdByToken(token);
        if (id == null) {
            session.sendMessage(new TextMessage( "系统^&^身份验证失败，您无法创建房间。"));
            session.close();
        }else {
            // 清空原房间的链接
            if(concurrentHashMap.containsKey(id)){
                this.closeRoom(concurrentHashMap.get(id));
            }
            // 创建房间
            CopyOnWriteArrayList<WebSocketSession> webSocketList=new CopyOnWriteArrayList<>();
            // 添加房主
            webSocketList.add(session);
            // 加入到集合
            concurrentHashMap.put(id,webSocketList);
            session.sendMessage(new TextMessage( "系统^&^您的房间已经创建好了。"));
        }
    }
    public static String getParam(String url, String name) {
        String params = url.substring(url.indexOf("?") + 1, url.length());
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(params);
        return split.get(name);
    }

    public void closeRoom(CopyOnWriteArrayList<WebSocketSession> webSocketList) throws IOException {
        for (WebSocketSession ss:webSocketList){
            ss.close();
        }
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String id = getParam(session.getUri().toString(),"id");
        String token = getParam(session.getUri().toString(),"token");
        if(token!=null && !token.equals("undefined")) {
            // 执行获取id
            id= (String) StpUtil.getLoginIdByToken(token);
            this.closeRoom(concurrentHashMap.get(id));
            System.out.println(id+"房间已全部断开连接！");
            concurrentHashMap.remove(id);
            return;
        }
        if(id!=null) {
            // 执行删除用户操作
            concurrentHashMap.get(id).remove(session);
            System.out.println(session.getId()+"断开连接！");
        }
    }
}


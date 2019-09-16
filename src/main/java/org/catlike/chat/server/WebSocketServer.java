package org.catlike.chat.server;

import org.catlike.chat.model.Message;
import org.catlike.chat.model.Result;
import org.catlike.chat.model.UserCenter;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @program: chat
 * @description: 聊天控制中心
 * @author: feifei.li
 * @create: 2019-09-16 08:49
 **/
@Component
@ServerEndpoint("/echo")
public class WebSocketServer {
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet();

    private static Map<String, Session> socket = new HashMap<>();

    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    private static Set<String> names = new HashSet<>();

    /** 存储地址到list中 */
    private static Map<String, String> ADDRESSS = new HashMap<>();

    /** 与某个客户端的连接会话，需要通过它来给客户端发送数据 */
    private Session session;

    private String name;

    public WebSocketServer() {
        name = "访客:" + onlineCount.getAndIncrement();
    }

    @OnOpen
    public void open(Session session) throws UnsupportedEncodingException {
        this.session = session;
        webSocketSet.add(this);
        /*获取问号后面的参数*/
        String queryStr = session.getQueryString();
        String param = URLDecoder.decode(queryStr.substring(queryStr.indexOf("=") + 1), "utf-8");
        String[] params = param.split("&");
        this.name = params[0];
        socket.put(this.name, session);
        names.add(name);
        //存入用户中心 2016-08-04 15:16
        UserCenter.userCenter.addSession(this.name, session);
        Message msg = new Message();
        msg.setContent(this.name);
        msg.setName(this.name);
        String adres = params[1];

        if (adres.indexOf("省") > -1) {
            adres = adres.split("省")[0];
        }

        if (adres.indexOf("市") > -1) {
            adres = adres.split("市")[0];
        }
        /*截取地区中的城市名称*/
        msg.setAddress(ADDRESSS);
        ADDRESSS.put(this.name, adres);
        msg.setNames(names);
        msg.setDbType(2);
        broadcast(JSONObject.toJSONString(msg));
    }

    @OnClose
    public void close() {
        webSocketSet.remove(this);
        Set<String> kset = socket.keySet();
        for (String string : kset) {
            if (session.getId().equals(socket.get(string).getId())) {
                Message m = new Message();
                names.remove(string);
                m.setDbType(3);
                m.setContent(string);
                m.setNames(names);
                broadcast(JSONObject.toJSONString(m));
            }
        }


    }

    @OnMessage
    public void onmessage(Session session, String msg) {
        Result result = Result.result;
        result.setMsg(msg);
        try {
            Message m = new Message();
            m.setDbType(1);

            m.setContent(msg);
            m.setName(this.name);
            m.setPublisher(this.name);
            m.setDate(new Date().toLocaleString());
            this.broadcast(JSONObject.toJSONString(m));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * 群发
     *
     * @param info
     */
    private void broadcast(String info) {

        for (WebSocketServer w : webSocketSet) {
            try {
                synchronized (WebSocketServer.class) {
                    w.session.getBasicRemote().sendText(info);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRoom(Session session){

    }
}

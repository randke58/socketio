package com.innodealing.international.authservice.service;

import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DefaultExceptionListener;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;
import com.google.common.collect.Lists;
import com.innodealing.international.authservice.service.listener.MyConnectListener;
import com.innodealing.international.authservice.service.listener.MyDisconnectionListener;
import com.innodealing.international.authservice.service.listener.NotifyAllUserListener;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.client.RedisClient;
import org.redisson.codec.SerializationCodec;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class IOTest {
    public static SocketIOServer server = null;

    public static void main(String[] args) throws Exception {
        String[] nss = {"dm_client_web",
                "dm_client_web_bond",
                "dm_client_mobile",
                "dm_client_mobile_bond",
                "dm_dashboard_web",
                "dm_management",
                "socketio_management",
                "broker_market_web"};
        List<String> namespaceList = Lists.newArrayList(nss);
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(9092);
        RedissonClient redissonClient = Redisson.create();
        RedissonStoreFactory storeFactory = new RedissonStoreFactory(redissonClient);

        configuration.setStoreFactory(storeFactory);
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpKeepAlive(true);
        configuration.setSocketConfig(socketConfig);
        configuration.setExceptionListener(new DefaultExceptionListener());
        server = new SocketIOServer(configuration);
        for (String ns : namespaceList) {
            SocketIONamespace socketIONamespace = server.addNamespace("/" + ns);
            socketIONamespace.addConnectListener(new MyConnectListener());
            socketIONamespace.addDisconnectListener(new MyDisconnectionListener());

            //通知所有人
            NotifyAllUserListener<ChatObject> notifyAllUserListener = new NotifyAllUserListener(server);
            socketIONamespace.addEventListener(notifyAllUserListener.getEventName(), ChatObject.class, notifyAllUserListener);
        }
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
    }


}

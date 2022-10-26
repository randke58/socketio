package com.innodealing.international.authservice.service;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;
import com.corundumstudio.socketio.store.pubsub.DispatchMessage;
import com.google.common.collect.Lists;
import com.innodealing.international.authservice.service.listener.MyConnectListener;
import com.innodealing.international.authservice.service.listener.MyDisconnectionListener;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.SerializationCodec;

import java.util.List;

public class IOTest2 {
    public static SocketIOServer server =null;

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
        configuration.setPort(9093);
        RedissonClient redissonClient =Redisson.create();
        RedissonStoreFactory storeFactory = new RedissonStoreFactory(redissonClient);
//        redissonClient.getTopic("single",new SerializationCodec()).addListener(String.class, new MessageListener<String>() {
//            @Override
//            public void onMessage(CharSequence charSequence, String s) {
//                System.out.println("##############################################event redissonClient" + s);
//            }
//        });
        configuration.setStoreFactory(storeFactory);
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpKeepAlive(true);
        configuration.setSocketConfig(socketConfig);
         server = new SocketIOServer(configuration);
        for (String ns : namespaceList) {
            SocketIONamespace socketIONamespace = server.addNamespace("/" + ns);
            socketIONamespace.addConnectListener(new MyConnectListener());
            socketIONamespace.addDisconnectListener(new MyDisconnectionListener());
            socketIONamespace.addEventListener("chat-msg1", DispatchMessage.class, new DataListener<DispatchMessage>() {
                @Override
                public void onData(SocketIOClient socketIOClient, DispatchMessage data, AckRequest ackRequest) throws Exception {
                    System.out.println("##############################################event listener 'chat-msg' success" + data);
                }
            });
        }
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
    }


}

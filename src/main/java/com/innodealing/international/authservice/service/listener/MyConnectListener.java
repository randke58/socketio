package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.google.common.collect.Lists;

import java.util.List;

public class MyConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        socketIOClient.getHandshakeData().getHttpHeaders().add("Cookie", Lists.newArrayList("uid=123"));
        List<String> uidList = socketIOClient.getHandshakeData().getHttpHeaders().getAll("Cookie");
        String uid = uidList != null && uidList.size() > 0 ? uidList.get(0) : null;
        String privateRoom = getPrivateRoomName(uid);
        socketIOClient.joinRoom(privateRoom);
        System.out.println("##############################################join room success");
    }

    protected static String getPrivateRoomName(String clientName) {
        if (clientName!= null && clientName.trim()!="") {
            return "private_" + clientName.toLowerCase().trim();
        } else {
            return "";
        }
    }
}

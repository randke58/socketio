package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class MyDisconnectionListener implements DisconnectListener {
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        System.out.println("##############################################leave room success");
    }
}

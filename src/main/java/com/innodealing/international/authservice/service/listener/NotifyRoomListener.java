package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.innodealing.international.authservice.service.socketIOClientUtil;

//TODO
public class NotifyRoomListener<T> implements EventListener<T> {
    private String eventType = "notify_room";
    private SocketIOServer socketIOServer;

    public NotifyRoomListener(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    @Override
    public String getEventName() {
        return eventType;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, T data, AckRequest ackRequest) throws Exception {
//        socketIOClient.getNamespace().getRoomOperations()
//        String userId =socketIOClientUtil.getUserId(socketIOClient);
    }
}

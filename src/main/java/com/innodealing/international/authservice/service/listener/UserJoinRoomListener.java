package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.innodealing.international.authservice.service.socketIOClientUtil;
//TODO
public class UserJoinRoomListener implements EventListener<String> {
    private String eventType ="user_join_room";

    @Override
    public String getEventName() {
        return eventType;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
        String userId =socketIOClientUtil.getUserId(socketIOClient);
        socketIOClient.joinRoom(data);
        System.out.println("user join room:"+eventType);
    }
}

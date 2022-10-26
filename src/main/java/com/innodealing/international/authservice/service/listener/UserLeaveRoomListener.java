package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.innodealing.international.authservice.service.socketIOClientUtil;
//TODO
public class UserLeaveRoomListener implements EventListener<String> {
    private String eventType ="user_leave_room";
    @Override
    public String getEventName() {
        return eventType;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, String data, AckRequest ackRequest) throws Exception {
        socketIOClient.leaveRoom(data);
        String userId =socketIOClientUtil.getUserId(socketIOClient);
        System.out.println("user leave room:"+eventType);
        //this.logger.infoFormat("%s[%s] receive ack messageId: %s", user, socket.id, data.messageId                     );
    }
}

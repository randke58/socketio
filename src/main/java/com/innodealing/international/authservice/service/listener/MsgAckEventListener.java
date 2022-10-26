package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.innodealing.international.authservice.service.listener.EventListener;
import com.innodealing.international.authservice.service.socketIOClientUtil;

public class MsgAckEventListener<T> implements EventListener<T> {
    private String eventType ="innodealing_msg_ack";
    @Override
    public String getEventName() {
        return eventType;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, Object o, AckRequest ackRequest) throws Exception {
        String userId =socketIOClientUtil.getUserId(socketIOClient);
        System.out.println("listener eventType:"+eventType);
        //this.logger.infoFormat("%s[%s] receive ack messageId: %s", user, socket.id, data.messageId                     );
    }
}

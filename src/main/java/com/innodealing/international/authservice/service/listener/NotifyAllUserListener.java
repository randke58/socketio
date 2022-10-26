package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.innodealing.international.authservice.service.socketIOClientUtil;

import java.util.Iterator;

public class NotifyAllUserListener<T> implements EventListener<T> {
    private String eventType ="notify_all";
    private SocketIOServer socketIOServer;

    public NotifyAllUserListener(SocketIOServer socketIOServer){
        this.socketIOServer =  socketIOServer;
    }

    @Override
    public String getEventName() {
        return eventType;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, T data, AckRequest ackRequest) throws Exception {
        Iterator<SocketIONamespace> socketIONamespaces = socketIOServer.getAllNamespaces().iterator();
        while (socketIONamespaces.hasNext()) {
            socketIONamespaces.next().getBroadcastOperations().sendEvent(eventType, data);
        }
        System.out.println("##############################################event listener 'chat-msg' success" + data);
    }
}

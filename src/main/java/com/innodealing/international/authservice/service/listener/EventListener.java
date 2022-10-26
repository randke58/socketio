package com.innodealing.international.authservice.service.listener;

import com.corundumstudio.socketio.listener.DataListener;

public interface EventListener<T> extends DataListener<T> {
    String getEventName();
}

package com.practice.project.service.eventdriven;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class EventPublisher {
    private List<EventListener> listeners = new ArrayList<>();
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }
    public void removeListener(EventListener listener) {
        listeners.remove(listener);
    }
//    public void publishEvent(Event event) {
//        for (EventListener listener : listeners) {
//            listener.onEvent(event);
//        }
//    }
}

package com.wg.gichess;

public interface Subscriber<T extends Event> {

    void handleEvent(T event);
}

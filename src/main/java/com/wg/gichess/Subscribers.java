package com.wg.gichess;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Subscribers {

    public List<Subscriber<?>> subscribers = new CopyOnWriteArrayList<>();
    public int priority = 0;
    public String id;
    public Object owner;
    public SubscribersType subscribersType;

    public boolean allowWork = true;
    public boolean shouldRender = false;

    public Subscribers(Object owner){
        this.owner = owner;
    }



    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSubscribersType(SubscribersType subscribersType) {
        this.subscribersType = subscribersType;
    }

    public void setAllowWork(boolean allowWork) {
        this.allowWork = allowWork;
    }


    public Subscribers add(Subscriber<?> subscriber){
        subscribers.add(subscriber);
        return this;
    }


    public void handleEvent(Event event){
        for(Subscriber<? extends Event> subscriber : subscribers ){

            if (!canHandle(subscriber, event)) continue;

            if (!event.isPaused) {
                event.currentSubscribers = this;
                handleSafely(subscriber, event);
            }

        }

    }


    private boolean canHandle(Subscriber<? extends Event> subscriber, Object event) {
        try {
            ParameterizedType type = (ParameterizedType) subscriber.getClass()
                    .getGenericInterfaces()[0];
            Class<?> eventType = (Class<?>) type.getActualTypeArguments()[0];
            return eventType.isInstance(event);
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Event> void handleSafely(Subscriber<T> subscriber, Event event) {
        subscriber.handleEvent((T) event);
    }


}

package com.wg.gichess;


import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class EventBus {
    private final List<Subscribers> subscribersList = new CopyOnWriteArrayList<>();

    public synchronized void register(Subscribers subscribers) {
        subscribersList.add(subscribers);
        subscribersList.sort(Comparator.comparingInt(Subscribers::getPriority));
    }

    public synchronized void unregister(Subscribers subscribers) {
        subscribersList.remove(subscribers);
    }


    public boolean post(Event event,Object owner,boolean find) {

        if(subscribersList.isEmpty()){
            return find;
        }

        for (Subscribers subscribers : subscribersList) {
            if(event.isPaused){
                return find;
            }
            if(subscribers.owner != owner){
                continue;
            }
            if(find){
                subscribers.handleEvent(event);
            }
            if(event.currentSubscribers == subscribers){
                find = true;
            }

        }
        return find;
    }


    public void post(Event event,List<Object> owners){
        event.eventBus = this;

        boolean find = (event.currentSubscribers == null);

        for (Object owner : owners) {
            if (event.isPaused) return;
            find = post(event, owner, find);
        }

        EventManager.overEvent(event);
    }



    public int getSubscribersCount() {
        return subscribersList.size();
    }

    public synchronized void clear() {
        subscribersList.clear();
    }


}


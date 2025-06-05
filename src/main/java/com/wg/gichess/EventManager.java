package com.wg.gichess;

import com.wg.gichess.chessboard.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    public static void pauseEvent(Event event){
        event.isPaused = true;
    }

    public static void restoreEvent(Event event){
        if(event.eventBus == User.eventBus){
            User.post(event);
        }
        else if(event.eventBus == User.chessBoard.eventBus){
            User.chessBoard.post(event);
        }

    }

    public static void addChildEvent(Event parentEvent,Event childEvent){
        parentEvent.childEvents.add(childEvent);
        parentEvent.activeChild +=1 ;
        childEvent.parentEvent = parentEvent;
    }

    public static void overEvent(Event childEvent){

        if(childEvent.parentEvent == null){
            return;
        }
        childEvent.parentEvent.activeChild -= 1;
        childEvent.parentEvent.childEvents.remove(childEvent);

        if(childEvent.parentEvent.activeChild == 0){
            restoreEvent(childEvent.parentEvent);
        }

    }
}

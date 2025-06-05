package com.wg.gichess.chess.rolechess;

import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.rolechess.hilichurls.*;
import com.wg.gichess.chess.rolechess.inazuma.Kirara;
import com.wg.gichess.chess.rolechess.liyue.Xiangling;
import com.wg.gichess.chess.rolechess.mondstadt.*;


import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;


public class RoleFactoryV1 {

    public static final Map<String, RoleConstructor> ROLE_MAP = new LinkedHashMap<>();

    static {
        registerRoles();
    }

    private static void registerRoles() {


        //蒙德
        //4
        add(Jean.class);
        //3
        add(Amber.class);
        add(Barbara.class);
        add(Kaeya.class);
        add(Lisa.class);
        add(Noelle.class);
        //璃月
        //4
        //3
        add(Xiangling.class);
        //稻妻
        //4
        //3
        add(Kirara.class);
        //须弥
        //枫丹
        //纳塔
        //至冬
        //丘丘部族
        //2
        add(BlazingAxeMitachurl.class);
        //1
        add(ElectroHilichurlShooter.class);
        add(Hilichurl.class);
        add(HilichurlBerserker.class);
        add(HilichurlFighter.class);
        add(ElectroHilichurlShooter.class);






    }

    public static RoleChess create(String key, Coord coord, int level, int constellation, int skin) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        RoleConstructor constructor = ROLE_MAP.get(key);
        if (constructor == null) {
            throw new IllegalArgumentException("No role registered with key: " + key);
        }
        return constructor.create(coord, level, constellation, skin);
    }

    @FunctionalInterface
    public interface RoleConstructor {
        RoleChess create(Coord coord, int level, int constellation, int skin) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    }


    public static void add(Class<?> clazz){
        AutoRegisterRole annotation = clazz.getAnnotation(AutoRegisterRole.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " is missing @AutoRegisterRole annotation.");
        }

        try {

            var constructor = clazz.getConstructor(Coord.class, int.class, int.class, int.class);
            RoleConstructor roleConstructor = (coord, level, constellation, skin) ->
                    (RoleChess) constructor.newInstance(coord, level, constellation, skin);

            ROLE_MAP.put(annotation.key(), roleConstructor);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to register role: " + clazz.getName(), e);
        }


    }
}
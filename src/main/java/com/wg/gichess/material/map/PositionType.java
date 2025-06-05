package com.wg.gichess.material.map;

import com.wg.gichess.RegionType;
import com.wg.gichess.chessboard.ChessBoard;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public enum PositionType {
    //蒙德
    STARTINGPOINT(1.1,"startingpoint",RegionType.MONDSTADT),
    MONDSTADTGATE(1.2,"mondstadtgate",RegionType.MONDSTADT),
    MONDSTADTCITY(1.3,"mondstadtcity",RegionType.MONDSTADT),
    WINDRISE(1.4,"windrise",RegionType.MONDSTADT);



    public final double id;
    public final String materialID;
    public final RegionType regionType;

    private static final Map<String, Class<? extends ChessBoard>> CB = new HashMap<>();
    private static final String SUFFIX = "CB";

    static {
        scanGetCB();
    }

    PositionType(double id, String materialID, RegionType regionType){
        this.id = id;
        this.materialID = materialID;
        this.regionType = regionType;
    }


    private static void scanGetCB() {
        try {

            String packageName = ChessBoard.class.getPackage().getName();


            for (PositionType pos : PositionType.values()) {
                String className = pos.materialID.substring(0, 1).toUpperCase()
                        + pos.materialID.substring(1)
                        + SUFFIX;
                try {
                    Class<?> loadedClass = Class.forName(packageName + "."+pos.regionType.id +"." + className);
                    if (!ChessBoard.class.isAssignableFrom(loadedClass)) {
                        throw new ClassCastException(className + " 不是 ChessBoard 的子类");
                    }
                    @SuppressWarnings("unchecked")
                    Class<? extends ChessBoard> chessBoardClass =
                            (Class<? extends ChessBoard>) loadedClass;
                    CB.put(pos.materialID, chessBoardClass);

                } catch (ClassNotFoundException e) {
                    System.err.println("警告：未找到类 " + className + "，跳过加载");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化 CB 类缓存失败", e);
        }
    }


    public static ChessBoard getCB(String materialID) {
        Class<? extends ChessBoard> clazz = CB.get(materialID);
        if (clazz == null) {
            throw new IllegalArgumentException("未找到 materialID 对应的类: " + materialID);
        }
        else {
            ChessBoard chessBoard;
            try {
                chessBoard = clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            return chessBoard;
        }
    }

    public static ChessBoard getCB(PositionType positionType) {
        return getCB(positionType.materialID);
    }


}

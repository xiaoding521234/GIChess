package com.wg.gichess.chess;

public enum ElementType {
    //---------- 原神七元素 ----------
    ANEMO(1, "风元素",
            new float[]{0.45f, 0.84f, 0.76f},   // 主色 (青绿色)
            new float[]{0.7f, 1.0f, 0.9f}       // 高光色 (荧光绿)
    ),
    PYRO(2, "火元素",
            new float[]{1.0f, 0.3f, 0.1f},      // 主色 (橙红)
            new float[]{1.0f, 0.6f, 0.2f}       // 高光色 (亮橙)
    ),
    HYDRO(3, "水元素",
            new float[]{0.2f, 0.5f, 1.0f},      // 主色 (蔚蓝)
            new float[]{0.4f, 0.7f, 1.0f}       // 高光色 (浅天蓝)
    ),
    ELECTRO(4, "雷元素",
            new float[]{0.6f, 0.3f, 1.0f},      // 主色 (紫罗兰)
            new float[]{0.8f, 0.5f, 1.0f}       // 高光色 (荧光紫)
    ),
    CRYO(5, "冰元素",
            new float[]{0.6f, 0.9f, 1.0f},      // 主色 (冰蓝)
            new float[]{0.8f, 1.0f, 1.0f}       // 高光色 (淡青)
    ),
    GEO(6, "岩元素",
            new float[]{0.93f, 0.76f, 0.3f},    // 主色 (金黄)
            new float[]{1.0f, 0.9f, 0.5f}       // 高光色 (亮金)
    ),
    DENDRO(7, "草元素",
            new float[]{0.3f, 0.8f, 0.2f},      // 主色 (翠绿)
            new float[]{0.5f, 1.0f, 0.3f}       // 高光色 (荧光绿)
    ),



    //---------- 扩展元素 ----------
    PHYSICAL(8, "物理",
            new float[]{0.8f, 0.8f, 0.8f},      // 主色 (银灰)
            new float[]{1.0f, 1.0f, 1.0f}       // 高光色 (纯白)
    ),
    PRIMALUX(9, "源光",
            new float[]{1.0f, 0.9f, 0.2f},      // 主色 (日光金)
            new float[]{1.0f, 1.0f, 0.7f}       // 高光色 (星芒白)
    ),
    ETERNALABYSS(10, "永黯",
            new float[]{0.1f, 0.0f, 0.3f},      // 主色 (深紫黑)
            new float[]{0.4f, 0.1f, 0.6f}       // 高光色 (暗紫)
    );

    public final int id;
    public final String displayName;
    public final float[] mainColor;  // RGB 0-1
    public final float[] glowColor;  // RGB 0-1

    ElementType(int id, String displayName, float[] mainColor, float[] glowColor) {
        this.id = id;
        this.displayName = displayName;
        this.mainColor = mainColor;
        this.glowColor = glowColor;
    }

    // 获取颜色方法
    public int getMainColorRGB() {
        return toRGB(mainColor);
    }

    public int getGlowColorRGB() {
        return toRGB(glowColor);
    }

    private int toRGB(float[] color) {
        return ((int) (color[0] * 255) << 16 |
                ((int) (color[1] * 255) << 8 |
                        ((int) (color[2] * 255))));
    }
}

package com.wg.gichess.material.battlelobbyscreen;

import com.wg.gichess.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class BattleLobbyButton extends Material {
    private static final float HOVER_RANGE = 0.2f; // 鼠标影响范围
    private static final float MAX_OFFSET = 0.1f;  // 最大偏移量

    private final BlackDivider blackDividerL;
    private final BlackDivider blackDividerR;

    Material darkenMaterial;
    String displayName ;
    int color;
    float scale=1f;
    float difY = 0f ;
    boolean dropShadow;


    Runnable onClick;

    public BattleLobbyButton (String buttonName,String displayName,Runnable onClick,int x,BlackDivider blackDividerL,BlackDivider blackDividerR) {
        super("textures/battlelobbyscreen/"+buttonName+".png", x, 0);
        this.blackDividerL = blackDividerL;
        this.blackDividerR = blackDividerR;
        setSize((int) (width*0.4),height);
        darkenMaterial = new Material("textures/battlelobbyscreen/"+buttonName+"darken.png");
        darkenMaterial.setSize((int) (width*0.4),height);
        darkenMaterial.setXY(x,0);
        this.displayName = displayName;
        this.onClick = onClick;
    }

    @Override
    public void render() {


        guiGraphics.enableScissor((int) (x+width*MAX_OFFSET+ blackDividerL.currentOffset)+1,0,
                (int) (x+width*(MAX_OFFSET+HOVER_RANGE)+blackDividerR.currentOffset)+1,height );
        {

            if (isMouseOn()) {
                poseStack.pushPose();
                {
                    scale = Mth.clamp(scale*1.0045f , 1.0f ,1.1f);
                    difY = Mth.clamp(difY+0.002f , 0f ,0.04f);

                    poseStack.translate(widthh / 2f + x, heightt / 2f + y, 0);
                    poseStack.scale(scale, scale, 1);
                    poseStack.translate(-widthh / 2f, -heightt / 2f - difY * heightt, 0);
                    render00();

                }
                poseStack.popPose();


                color = 0xFFFFFF00;
                dropShadow = true;

            } else {

                poseStack.pushPose();
                {
                    scale = Mth.clamp(scale/1.0045f , 1.0f ,1.1f);
                    difY = Mth.clamp(difY-0.002f , 0f ,0.04f);

                    poseStack.translate(widthh / 2f + x, heightt / 2f + y, 0);
                    poseStack.scale(scale, scale, 1);
                    poseStack.translate(-widthh / 2f, -heightt / 2f - difY * heightt , 0);
                    darkenMaterial.render00();

                }
                poseStack.popPose();

                color = 0xFFFFFFFF;
                dropShadow = false;
            }

            poseStack.pushPose();
            {
                poseStack.translate(blackDividerL.currentOffset,0,100);

                for (int i = 0; i < displayName.length(); i++) {
                    char c = displayName.charAt(i);

                    guiGraphics.drawString(
                            Minecraft.getInstance().font,
                            String.valueOf(c),
                            blackDividerL.x + 3  , y + i * 10,
                            color,
                            dropShadow
                    );
                }
            }
            poseStack.popPose();
        }
        guiGraphics.disableScissor();

    }

    @Override
    public boolean isMouseOn() {
        return (mouseX >= x + width * MAX_OFFSET && mouseX < x + width * (MAX_OFFSET+HOVER_RANGE));
    }

    @Override
    public boolean click() {
        if(isMouseOn()){
            onClick.run();
            return true;
        }
        return false;
    }
}

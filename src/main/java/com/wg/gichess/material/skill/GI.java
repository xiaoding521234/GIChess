package com.wg.gichess.material.skill;

import com.mojang.blaze3d.systems.RenderSystem;
import com.wg.gichess.User;
import com.wg.gichess.chess.rolechess.BottomSkill;
import com.wg.gichess.chess.rolechess.TriggerType;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.chessboard.ShineCBType;
import com.wg.gichess.material.Interval;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.SListV2;
import net.minecraft.client.Minecraft;

public class GI extends Material {

    private static final int GOLD_COLOR = 0xFFF8D070; // 金色ARGB


    public BottomSkill skill;


    public GI(BottomSkill skill,String id){
        super("textures/skill/"+id);
        this.skill = skill;
    }

    @Override
    public void renderf() {


        poseStack.pushPose();
        {
            poseStack.translate(xf,yf,0);

            int color= 0xFFFFFFFF;
            float scale = 1f;
            if(User.chessBoard.chooseSkill==this.skill){
                color = 0xFFFF00FF;
            }
            if(isMouseOnf()){
                scale = 1.2f;
            }
            poseStack.translate(widthh/2f,heightt/2f,0);
            poseStack.scale(scale,scale,1f);
            poseStack.translate(-widthh/2f,-heightt/2f,0);



            guiGraphics.blit(RENDER_FUNCTION,resourceLocation,
                    0,0,
                    0,0,
                    widthh,heightt,
                    widthh,heightt,
                    color);




            if(skill.getTriggerType() == TriggerType.ACTIVE){
                poseStack.translate(0,heightt,0);
                if(skill.canWork()){
                    guiGraphics.fill(0,0,widthh,1,GOLD_COLOR);
                }
                else {
                    guiGraphics.fill(0,0,widthh,1,0xFFFFFFFF);
                }
            }


        }
        poseStack.popPose();

        if(!isMouseOnf()){
            return;
        }

        int lineWidth = (int) (Material.width *0.2);

        int RHeight = SListV2.getRHeight(skill.getIntroduce(),lineWidth);
        Interval interval = new Interval(0,(int) (Material.width*0.01),0,0);
        int lineHeight = Minecraft.getInstance().font.lineHeight;
        SListV2 sListV2 = new SListV2(skill.getIntroduce(),0,0,
                lineWidth,RHeight,
                lineWidth,
                interval,
                lineWidth,lineHeight,true,false
                );

        poseStack.pushPose();
        {
            poseStack.translate(xf+widthh/2f-lineWidth/2f, yf-RHeight-height*0.07, 0);
            sListV2.render();
        }
        poseStack.popPose();




    }

    @Override
    public boolean click() {
        if(!isMouseOnf()){
            return false;
        }

        if(this.skill.getTriggerType()!=TriggerType.ACTIVE){
            return true;
        }


        if(User.chessBoard.chooseSkill == null){
            User.chessBoard.chooseSkill = this.skill;
            User.lastGameMode = User.gameMode;
            User.gameMode = GameModes.SKILL;
            User.lastShineCBType = User.shineCBType;
            User.shineCBType = ShineCBType.ONCOORD;
            this.skill.onChoose();
        }

        else if (User.chessBoard.chooseSkill == this.skill) {
            User.chessBoard.chooseSkill = null;
            User.gameMode = User.lastGameMode;
            User.shineCBType = User.lastShineCBType;
            this.skill.onCancel();
        } else {
            User.chessBoard.chooseSkill.onCancel();
            User.chessBoard.chooseSkill = this.skill;
            this.skill.onChoose();

        }

        return true;

    }
}

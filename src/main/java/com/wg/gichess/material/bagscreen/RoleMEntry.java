package com.wg.gichess.material.bagscreen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.MEntry;
import com.wg.gichess.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;


public class RoleMEntry extends MEntry {
    public RoleChess roleChess;
    public Material blue = new Material("textures/item/blue.png");
    public Material bottom = new Material("textures/item/bottom.png");

    public int fontWidthh;
    public int fontHeightt;




    public RoleMEntry(RoleChess roleChess) {
        super(new Material("textures/item/"+roleChess.star +"star.png"));
        this.roleChess = roleChess;
        material.widthh = Material.width/15;
        material.heightt = (int) (material.widthh * 1.36);

        int size = Material.width/17;
        for(int i=0;i<roleChess.skins.length;i++){
            roleChess.icons[i].setSize(size,size);
            roleChess.skins[i].setSize( Material.width /4,Material.width /6);
        }
        blue.setSize(size,size);
        bottom.setSize(size,size);

        if(roleChess.constellation==-1){
            fontWidthh = Minecraft.getInstance().font.width("未拥有");

        }else {
            fontWidthh = Minecraft.getInstance().font.width(String.valueOf(roleChess.constellation));

        }
        fontHeightt = Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void render(int x, int y) {
        material.x =x;
        material.y =y;

        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {

            if ((isMouseOn() && RoleMList.isMouseOn) || RoleMList.target == this) {
                poseStack.translate(material.widthh / 2f + x, material.heightt / 2f + y, 0);
                poseStack.scale(1.2f, 1.2f, 1);
                poseStack.translate(-material.widthh / 2f, -material.heightt / 2f, 0);
            } else {
                poseStack.translate(x, y, 0);
            }

            material.render00();

            poseStack.pushPose();
            {
                poseStack.translate(
                        (material.widthh - fontWidthh) / 2f + fontWidthh / 2f,
                        material.heightt * 0.79 + fontHeightt / 2f,
                        0f);
                poseStack.scale(0.7f, 0.7f, 1f);
                poseStack.translate(-fontWidthh / 2f, -fontHeightt / 2f, 0f);

                if (roleChess.constellation == 3) {
                    Material.guiGraphics.drawString(Minecraft.getInstance().font,
                            Component.literal(String.valueOf(roleChess.constellation)),
                            0, 0,
                            0xFFAA00,
                            true);

                } else if(roleChess.constellation == -1)  {
                    Material.guiGraphics.drawString(Minecraft.getInstance().font,
                            Component.literal("未拥有"),
                            0, 0,
                            0xFF000000,
                            false);
                }else {
                    Material.guiGraphics.drawString(Minecraft.getInstance().font,
                            Component.literal(String.valueOf(roleChess.constellation)),
                            0, 0,
                            0xFF000000,
                            false);

                }
            }
            poseStack.popPose();


            poseStack.translate((material.widthh - roleChess.icons[roleChess.skin].widthh) / 2f, (material.heightt - roleChess.icons[roleChess.skin].heightt) / 2f - material.heightt / 8f, 0);

            bottom.render();
            roleChess.icons[roleChess.skin].render();


            poseStack.translate(blue.widthh / 2f, blue.heightt / 2f, 0);
            poseStack.scale(1.1f, 1.1f, 1);
            poseStack.translate(-blue.widthh / 2f, -blue.heightt / 2f, 0);

            blue.render();

        }
        poseStack.popPose();
    }

    @Override
    public void click() {
        if(isMouseOn() && RoleMList.target!=this){
            RoleMList.target = this;
            RoleMList.isNew = true;
        }
    }

    public boolean isMouseOn(){
        return material.isMouseOn() ;
    }

}


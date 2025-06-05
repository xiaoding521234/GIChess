package com.wg.gichess.material;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class Button extends Material {

    public Material buttonShine = new Material("textures/bagscreen/buttonshine.png");
    public Material resetButton = new Material("textures/battlelobbyscreen/resetbutton.png");
    public Material shouldRenderM = this;
    public String text;


    public Button(int x, int y,String text) {
        super("textures/bagscreen/button.png", x, y);
        this.widthh = (int) (width * 0.13);
        this.heightt = (int) (widthh * 0.27);
        this.buttonShine.setSize(this.widthh,this.heightt);
        this.resetButton.setSize(this.widthh,this.heightt);
        this.text = text;
    }

    @Override
    public void render() {

        poseStack.pushPose();
        {
            poseStack.translate(this.x, this.y, 0);
            shouldRenderM.render00();

            poseStack.pushPose();
            {
                poseStack.translate(widthh*0.4,(heightt-  Minecraft.getInstance().font.lineHeight)/2f,0);

                guiGraphics.drawString(Minecraft.getInstance().font,
                        Component.literal(String.valueOf(text)),
                        0, 0,
                        0xFF000000,
                        false);

            }
            poseStack.popPose();

            if(isMouseOn()){
                buttonShine.render();
            }


        }
        poseStack.popPose();



    }

    public void setShouldRenderM(Material m){
        this.shouldRenderM = m;
    }


}

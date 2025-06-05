package com.wg.gichess.material.map;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.material.Interval;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.SListV1;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class MapSList extends SListV1 {
    public MapSList(String text, boolean isFirst) {
        super(text, 0, 0,
                (int) (Material.width *0.2), (int) (Material.height*0.2),
                (int) (Material.width *0.2),
                new Interval(0, (int) (Material.width*0.01),0,0)
                , (int) (Material.width *0.2), 0, isFirst);
    }

    @Override
    public void render() {
        PoseStack poseStack = Material.poseStack;


        material.render00();


        currentX = x + interval.x;
        currentY = y + interval.y;


        poseStack.pushPose();
        {
            poseStack.translate(currentX, currentY - scrollY, 0);
            poseStack.scale(0.8f, 0.8f, 1f);


            for (String line : lines) {

                // 处理空白行标记
                if (line.trim().equals("\\s")) {
                    currentY += blankLineHeight;
                    poseStack.translate(0, blankLineHeight, 0);
                    continue;
                }

                Component component = Component.literal(line);
                List<FormattedCharSequence> wrappedLines = Minecraft.getInstance().font.split(component, (int) (maxWidthh / 0.8));

                for (FormattedCharSequence wrappedLine : wrappedLines) {
                    Material.guiGraphics.drawString(
                            Minecraft.getInstance().font,
                            wrappedLine,
                            0, 0,
                            0xFF000000,
                            false
                    );
                    poseStack.translate(0, Minecraft.getInstance().font.lineHeight, 0);
                    currentY += Minecraft.getInstance().font.lineHeight;
                }


            }
        }
        poseStack.popPose();

        heightt = (int) ((currentY - y) * 0.8);
        currentY = 0;
    }


}

package com.wg.gichess.chessboard;

import com.mojang.blaze3d.vertex.PoseStack;

import com.wg.gichess.TeamType;
import com.wg.gichess.User;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.Material;
import net.minecraft.util.Mth;



import java.util.ArrayList;
import java.util.List;

public class Team {
    public TeamType teamType;
    public List<RoleChess> roles = new ArrayList<>();
    public int STA = 0;
    public int goWarSTA = 1;
    public boolean isFirst = true;

    public ChessBoard chessBoard;


    int widthh;
    int heightt;

    double flickerTime = 0;
    double flickerPeriod = 0.6;

    double rotateTime = 0;
    double rotatePeriod = 0.2;

    double lineTime = 0;
    double linePeriod = 1;
    int lineX =0;
    int lineLength;




    public Team(TeamType teamType) {
        this.teamType = teamType;
    }

    public void initUpdate() {
        isFirst = true;
        rotateTime = 0;
        chessBoard = User.chessBoard;
        widthh = (int) (Material.width * 0.095);
        heightt = (int) (widthh * 0.666);
        lineLength = (int) (widthh*0.4);

    }

    public void render() {
        PoseStack poseStack = Material.poseStack;
        int size = roles.size();
        int ashColor;
        int flickerColor;


        flickerTime += Material.delta / 20;

        if (flickerTime >= flickerPeriod) {
            flickerTime -= flickerPeriod;
        }
        float t = (float) ((1 + Math.sin(Math.PI * flickerTime / flickerPeriod)) / 2);
        int alpha = 128 - (int) (128 * t);
        flickerColor = (alpha << 24);

        rotateTime = Mth.clamp(rotateTime +Material.delta / 20 , 0, rotatePeriod);

        lineTime += Material.delta / 20;
        if (lineTime >= linePeriod) {
            lineTime -= linePeriod;
        }

        lineX = (int) ((widthh+heightt)*2*(lineTime/linePeriod));

        poseStack.pushPose();
        {


        if (isFirst) {



            if (teamType == TeamType.RED) {
                poseStack.translate(-widthh*1.2*(1-(rotateTime/rotatePeriod)),0,0);


            } else {
                poseStack.translate(widthh*1.2*(1-(rotateTime/rotatePeriod)),0,0);
            }

        }





            if (teamType == TeamType.RED) {//红队


                for (int i = 0; i < User.goWarRoleNum; i++) {

                    if (i < chessBoard.redTeam.roles.size()) {
                        RoleChess roleChess = chessBoard.redTeam.roles.get(i);
                        Material starIntroduce = new Material("textures/item/" + roleChess.star + "starintroduce.png");

                        if (i == 0) {
                            poseStack.translate(0, Material.height * 0.12f, 5);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                            poseStack.translate(0, 1, 0);

                            starIntroduce.render00(widthh, heightt);
                            roleChess.skins[roleChess.skin].render00(widthh, heightt);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType && goWarSTA > 0) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                            poseStack.translate(0, 1 + heightt * 1.1, 0);

                        } else {

                            if (i == 1) {
                                Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                                poseStack.translate(0, 1, 0);
                            }
                            starIntroduce.render00(widthh, heightt);
                            roleChess.skins[roleChess.skin].render00(widthh, heightt);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType && goWarSTA > 0) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                            poseStack.translate(0, 1, 0);

                        }
                    } else {
                        Material unknownRole = new Material("textures/item/unknownrole.png");
                        if (i == chessBoard.goWarIndex) {
                            ashColor = 0xFFFFFFFF;
                        } else {
                            ashColor = 0xFF808080;
                        }

                        if (i == 0) {
                            poseStack.translate(0, Material.height * 0.12f, 5);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                            poseStack.translate(0, 1, 0);
                            unknownRole.render00(widthh, heightt, ashColor);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                            poseStack.translate(0, 1 + heightt * 1.1, 0);


                        } else {

                            if (i == 1) {
                                Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                                poseStack.translate(0, 1, 0);
                            }
                            unknownRole.render00(widthh, heightt, ashColor);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFFFF0000);
                            poseStack.translate(0, 1, 0);
                        }
                    }
                }

            } else {//蓝队

                poseStack.translate(Material.width - widthh, 0, 0);

                for (int i = 0; i < User.goWarRoleNum; i++) {

                    if (i < chessBoard.blueTeam.roles.size()) {//有角色
                        RoleChess roleChess = chessBoard.blueTeam.roles.get(i);
                        Material starIntroduce = new Material("textures/item/" + roleChess.star + "starintroduce.png");

                        if (i == 0) {//蓝队第一个
                            poseStack.translate(0, Material.height * 0.12f, 5);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                            poseStack.translate(0, 1, 0);

                            starIntroduce.render00(widthh, heightt);
                            roleChess.skins[roleChess.skin].render00(widthh, heightt);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType && goWarSTA > 0) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                            poseStack.translate(0, 1 + heightt * 1.1, 0);

                        } else {

                            if (i == 1) {//蓝队第二个
                                Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                                poseStack.translate(0, 1, 0);
                            }
                            starIntroduce.render00(widthh, heightt);
                            roleChess.skins[roleChess.skin].render00(widthh, heightt);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType && goWarSTA > 0) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                            poseStack.translate(0, 1, 0);

                        }
                    } else {//无角色
                        Material unknownRole = new Material("textures/item/unknownrole.png");
                        if (i == chessBoard.goWarIndex) {
                            ashColor = 0xFFFFFFFF;
                        } else {
                            ashColor = 0xFF808080;
                        }

                        if (i == 0) {
                            poseStack.translate(0, Material.height * 0.12f, 5);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                            poseStack.translate(0, 1, 0);
                            unknownRole.render00(widthh, heightt, ashColor);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                            poseStack.translate(0, 1 + heightt * 1.1, 0);

                        } else {

                            if (i == 1) {
                                Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                                poseStack.translate(0, 1, 0);
                            }
                            unknownRole.render00(widthh, heightt, ashColor);
                            if (chessBoard.goWarIndex == i && chessBoard.currentTurnTeamType == teamType) {
                                Material.guiGraphics.fill(0, 0, widthh, heightt, flickerColor);
                                renderLine(lineX,lineLength);
                            }
                            poseStack.translate(0, heightt, 0);
                            Material.guiGraphics.fill(0, 0, widthh, 1, 0xFF0000FF);
                            poseStack.translate(0, 1, 0);

                        }
                    }

                }

            }

        }
        poseStack.popPose();


    }


    public void renderLine(int lineX,int lineLength){
        // 确定线段处于哪个边上以及是否跨越拐角
        if (lineX < widthh) {
            // 第一阶段：从左到右（上边）
            int remainingLineLengthOnCurrentEdge = Math.min(widthh - lineX, lineLength);
            Material.guiGraphics.fill(lineX, 0, lineX + remainingLineLengthOnCurrentEdge, 1, 0xFFFFFFFF);

            // 如果线段长度超出当前边，则在下一边继续绘制
            int overflowLength = lineLength - remainingLineLengthOnCurrentEdge;
            if (overflowLength > 0) {
                Material.guiGraphics.fill(widthh - 1, 0, widthh, overflowLength, 0xFFFFFFFF);
            }
        } else if (lineX < widthh + heightt) {
            // 第二阶段：从上到下（右边）
            int adjustedLineX = lineX - widthh;
            int remainingLineLengthOnCurrentEdge = Math.min(heightt - adjustedLineX, lineLength);
            Material.guiGraphics.fill(widthh - 1, adjustedLineX, widthh, adjustedLineX + remainingLineLengthOnCurrentEdge, 0xFFFFFFFF);

            // 如果线段长度超出当前边，则在下一边继续绘制
            int overflowLength = lineLength - remainingLineLengthOnCurrentEdge;
            if (overflowLength > 0) {
                Material.guiGraphics.fill(widthh - 1 - overflowLength, heightt - 1, widthh - 1, heightt, 0xFFFFFFFF);
            }
        } else if (lineX < 2 * widthh + heightt) {
            // 第三阶段：从右到左（下边）
            int adjustedLineX = lineX - widthh - heightt;
            int remainingLineLengthOnCurrentEdge = Math.min(widthh - adjustedLineX, lineLength);
            Material.guiGraphics.fill(widthh - 1 - adjustedLineX - remainingLineLengthOnCurrentEdge, heightt - 1, widthh - 1 - adjustedLineX, heightt, 0xFFFFFFFF);

            // 如果线段长度超出当前边，则在下一边继续绘制
            int overflowLength = lineLength - remainingLineLengthOnCurrentEdge;
            if (overflowLength > 0) {
                Material.guiGraphics.fill(0, heightt - 1 - overflowLength, 1, heightt - 1, 0xFFFFFFFF);
            }
        } else if (lineX < 2 * widthh + 2 * heightt) {
            // 第四阶段：从下到上（左边）
            int adjustedLineX = lineX - 2 * widthh - heightt;
            int remainingLineLengthOnCurrentEdge = Math.min(heightt - adjustedLineX, lineLength);
            Material.guiGraphics.fill(0, heightt  - adjustedLineX - remainingLineLengthOnCurrentEdge, 1, heightt  - adjustedLineX, 0xFFFFFFFF);

            // 如果线段长度超出当前边，则在下一边继续绘制
            int overflowLength = lineLength - remainingLineLengthOnCurrentEdge;
            if (overflowLength > 0) {
                Material.guiGraphics.fill(0, 0, overflowLength, 1, 0xFFFFFFFF);
            }
        }
    }
}

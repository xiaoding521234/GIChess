package com.wg.gichess.chessboard;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.User;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.objectchess.ObjectChess;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.AnimationManager;
import com.wg.gichess.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public RoleChess roleChess;
    public ObjectChess objectChess;
    public List<TerrainType> terrains = new ArrayList<>();
    public CoverType cover = null;
    public Coord coord;
    public int cellSize;
    public int roleSize;
    public AnimationManager animationManager = new AnimationManager();

    public int mode;
    public static final int DEFAULT = 1;


    public boolean shouldShine = false;

    public Cell(){
    }

    public void initUpdate(int cellSize,int roleSize){
        this.cellSize = cellSize;
        this.roleSize = roleSize;
    }

    public void render(){
        PoseStack poseStack = Material.poseStack;
        {

            if (User.chessBoard.viewMode != 0) {
                renderTerrain();
            }
            else{

                renderTerrain();
                renderCover();

                if(roleChess != null){
                    roleChess.animationManager.renderLockAnimations();
                    roleChess.animationManager.renderAnimations();
                    roleChess.renderChess(cellSize,roleSize,true);
                }


                switch (mode) {
                    case DEFAULT:

                        break;

                }

                if (shouldShine) {
                    shine();
                }

                animationManager.renderAll();




            }

        }
    }



    public void renderTerrain(){
        if(terrains.get(User.chessBoard.viewMode)==null){
            return;
        }
        Material terrain =  new Material(terrains.get(User.chessBoard.viewMode).materialId);
        terrain.render00(cellSize,cellSize);
    }

    public void renderCover(){
        if (cover == null) {
        return;
        }

            PoseStack poseStack = Material.poseStack;
            Material.poseStack.pushPose();
            {
                poseStack.translate(0, 0, 200);
                Material coverM = new Material(cover.materialId);
                coverM.render00(cellSize, cellSize);
            }
            Material.poseStack.popPose();

    }

    public void shine(){
        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            poseStack.translate(0,0,150);
            Material.guiGraphics.fill(0, 0, cellSize, cellSize, 0x4CFFFFFF);

        }
        poseStack.popPose();
    }

    public void setMode(int mode){
        this.mode = mode;

    }


}

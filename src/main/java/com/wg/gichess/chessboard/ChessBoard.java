package com.wg.gichess.chessboard;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.*;
import com.wg.gichess.chess.Coord;
import com.wg.gichess.chess.rolechess.BottomSkill;
import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.material.AnimationManager;
import com.wg.gichess.material.Material;
import com.wg.gichess.material.animation.screen.NoticeBoxA;
import com.wg.gichess.material.skill.GIIntrodeceSkill;
import com.wg.gichess.sub.ChooseCellS;

import java.util.*;

public class ChessBoard {

    public int x;
    public int y;
    public static final int column =13;//列
    public static final int row=9;//行

    public int cellSize;
    public int roleSize;
    public int storey = 0;
    public int viewMode = 0;

    public Coord onCoord;//鼠标悬停在的格子
    public Cell clickCell;//鼠标点击的格子
    public Cell[][] cells = new Cell[column][row];

    public int currentTurnNum = 1;//当前行动回合数
    public TeamType currentTurnTeamType = TeamType.RED;//当前回合队伍
    public RoleChess goWarRole;//选中的出战角色
    public int goWarIndex =0;//当前出战编码
    public Cell chooseCell;//选中的格子
    public BottomSkill chooseSkill;//选中的技能

    public Team redTeam = new Team(TeamType.RED);
    public Team blueTeam = new Team(TeamType.BLUE);
    public AnimationManager animationManager = new AnimationManager();


    public List<RoleChess> firstRoles = new ArrayList<>();//先执行队伍
    public List<RoleChess> secondRoles = new ArrayList<>();//次执行队伍
    public EventBus eventBus = new EventBus();
    public boolean hasInitEventBusCB = false;//已经初始化棋盘订阅者

    public double time=0;



    public ChessBoard(int storey){
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                cells[i][j] = new Cell();
                cells[i][j].coord = new Coord(i,j);
                for(int z = 0;z<storey+1;z++){
                    cells[i][j].terrains.add(TerrainType.AIR);
                }
            }
        }
        this.storey = storey;
    }


    public void initUpdate(){

        this.cellSize = (int) (Material.width*0.055);
        this.roleSize = (int) (cellSize*0.85);

        this.x = (Material.width-cellSize*13)/2;
        this.y = (Material.height-cellSize*9)/2;



        Arrays.stream(cells).forEach(row -> {
            Arrays.stream(row).forEach(num -> num.initUpdate(cellSize,roleSize));
        });

        redTeam.initUpdate();
        blueTeam.initUpdate();

        time=0;


    }

    public void render(){

        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            poseStack.translate(this.x,this.y,5);
            for(int i=0;i<column;i++){
                for (int j=0;j<row;j++){
                    poseStack.pushPose();
                    {
                        poseStack.translate(i* cellSize,j* cellSize,0);

                        cells[i][j].shouldShine = controlShine(onCoord, i, j);
                        cells[i][j].render();


                    }
                    poseStack.popPose();

                }

            }
        }
        poseStack.popPose();

        redTeam.render();
        blueTeam.render();

        animationManager.renderAll();
        renderSkills();
    }

    public Coord getCoord(){
        int x = (int) (Material.mouseX - this.x)/ cellSize;
        int y = (int) (Material.mouseY - this.y)/ cellSize;
        return new Coord(x,y);
    }

    public Cell getCell(Coord coord){
        if(coord != null){
            return cells[coord.x][coord.y];
        }
        else return null;

    }

    public boolean isOnCB(){
        return Material.mouseX>=x && Material.mouseX<x+column*cellSize &&
                Material.mouseY>=y && Material.mouseY<y+row*cellSize;
    }

    public boolean controlShine(Coord coord, int i, int j){

        switch (User.shineCBType){

            case ONCOORD -> {
                return isOnCoord(coord,i,j);
            }
            case ONDIAGONAL -> {
                return isOnDiagonal(coord,i,j);
            }
            case ONROWORCOLUMN -> {
                return isOnRowOrColumn(coord,i,j);
            }


        }
        return false;

    }

    //十字
    public boolean isOnRowOrColumn(Coord coord, int i, int j){
        if (coord == null) return false;
        return coord.x == i || coord.y == j ;

    }
    //X字
    public boolean isOnDiagonal(Coord coord, int i, int j) {
        if (coord == null) return false;

        return (coord.x - coord.y == i - j) ||
                (coord.x + coord.y == i + j);
    }

    //所指
    public boolean isOnCoord(Coord coord, int i, int j) {
        if (coord == null) return false;

        return (coord.x ==  i ) &
                (coord.y == j);
    }



    public void renderPreview(float x,float y){
        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            poseStack.translate(x,y,5);
            poseStack.scale(0.4f,0.4f,1f);
            for(int i=0;i<column;i++){
                for (int j=0;j<row;j++){
                    poseStack.pushPose();
                    {
                        poseStack.translate(i* cellSize,j* cellSize,0);
                        cells[i][j].render();
                    }
                    poseStack.popPose();
                }
            }


        }
        poseStack.popPose();


    }

    public void changeViewMode(){
        viewMode = (viewMode + 1) % (storey + 1);
    }


    public void start(){
        viewMode = 0;
        User.gameMode = GameModes.GOWAR;
        User.teamType = TeamType.RED;
        currentTurnTeamType = TeamType.RED;
        setTeamPriority(currentTurnTeamType);

        animationManager.addLockAnimations(new NoticeBoxA("红方出战回合"),null);

        if(!hasInitEventBusCB){
            eventBus.register(new Sub().putGoWarRoleS());
            eventBus.register(new ChooseCellS());
            eventBus.register(new Sub().turnOverS());


            hasInitEventBusCB = true;
        }

    }

    public void setTeamPriority(TeamType teamType){
        if(teamType == TeamType.RED){
            firstRoles = redTeam.roles;
            secondRoles = blueTeam.roles;
        }
        else {
            firstRoles = blueTeam.roles;
            secondRoles = redTeam.roles;
        }
    }

    public void post(Event event){

        List<Object> owners = new ArrayList<>();

        if(!firstRoles.isEmpty()){
            owners.addAll(firstRoles);
        }
        if(!secondRoles.isEmpty()){
            owners.addAll(secondRoles);
        }
        owners.add(this);

        eventBus.post(event,owners);

    }

    public void click(int button){
        System.out.println("点击事件");
        clickCell = getCell(onCoord);
        clickSkills(button);

        if(button==0){
            post(new Event().clickE());
        }



    }

    public void mouseMoved() {
        if(isOnCB()){
            onCoord = getCoord();
        }
        else{
            onCoord = null;
        }
    }

    public Team getTeam(TeamType teamType){
        return (teamType==TeamType.RED)? redTeam:blueTeam ;
    }

    public Team getCurrentTurnTeam(){
        return getTeam(currentTurnTeamType);
    }

    public void renderSkills(){

        time = Math.min(time+Material.delta/20,0.2);
        double progress = time/0.2;

        if(chooseCell==null){
            return;
        }

        List<BottomSkill> skills = new ArrayList<>();
        skills.add(new GIIntrodeceSkill(chooseCell.roleChess));
        if(chooseCell.roleChess!=null){
            skills.addAll(chooseCell.roleChess.skills);
        }

        int iconSize = (int) (cellSize*0.8);
        float spacing =  iconSize*0.5f;

        float length = iconSize*skills.size()+spacing*(skills.size()-1);

        float currentX = (Material.width-length)/2f;
        float currentY = Material.height*0.92f;

        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            poseStack.translate(0,(1-progress)*iconSize,350);
            for (int i = 0; i < skills.size(); i++) {
                Material icon = skills.get(i).getIcon();
                icon.setXYf(currentX,currentY);
                icon.setSize(iconSize);
                icon.renderf();

                currentX+=iconSize+spacing;
            }


        }
        poseStack.popPose();


    }

    public void clickSkills(int button){
        if(chooseCell==null){
            return;
        }
        if(chooseCell.roleChess==null){
            return;
        }
        if(chooseCell.roleChess.skills.isEmpty()){
            return;
        }
        switch (button){
            case 0 :
            {
                if(chooseSkill!=null){
                    chooseSkill.onClick(onCoord);
                }

                List<BottomSkill> skills = chooseCell.roleChess.skills;

                for (BottomSkill skill : skills) {
                    Material icon = skill.getIcon();
                    icon.click();
                }
                break;
            }

            case 1:
            {

                if(chooseSkill!=null){
                    chooseSkill.onRightClick();
                }


                break;
            }



        }




    }









    public class BuildCB {

        public BuildCB setCellT(int i, int j, int storey, TerrainType terrain) {
            Cell cell = cells[i][j];
            List<TerrainType> terrains = cell.terrains;
            terrains.set(storey, terrain);
            return this;
        }

        public BuildCB setAllCellsT(int storey, TerrainType terrain) {

            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    setCellT(i, j, storey, terrain);
                }
            }
            return this;
        }



        public BuildCB setRowCellsT(int j, int storey, TerrainType terrain) {
            for (int i = 0; i < column; i++) {
                setCellT(i, j, storey, terrain);
            }

            return this;
        }

        public BuildCB setColumnCellsT(int i, int storey, TerrainType terrain) {
            for (int j = 0; j < row; j++) {
                setCellT(i, j, storey, terrain);
            }

            return this;
        }

        public BuildCB setRectangleCellT(int i1,int j1,int i2,int j2,int storey,TerrainType terrain ){

            for(int i = i1;i<=i2;i++){
                for (int j = j1;j<=j2;j++){
                    setCellT(i,j,storey,terrain);
                }
            }
            return this;
        }

        public BuildCB setCellC(int i, int j, CoverType cover) {
            Cell cell = cells[i][j];
            cell.cover = cover;
            return this;
        }

        public BuildCB setRectangleCellC(int i1,int j1,int i2,int j2,CoverType cover ){

            for(int i = i1;i<=i2;i++){
                for (int j = j1;j<=j2;j++){
                    setCellC(i,j,cover);
                }
            }
            return this;
        }





    }

    public BuildCB builder() {
        return new BuildCB();
    }


}


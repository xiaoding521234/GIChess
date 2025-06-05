package com.wg.gichess.chess.rolechess;


import com.mojang.blaze3d.vertex.PoseStack;
import com.wg.gichess.RegionType;
import com.wg.gichess.chess.*;
import com.wg.gichess.material.Introduce;
import com.wg.gichess.material.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RoleChess extends BottomChess implements Introduce{
    //必要
    public int level;
    public int constellation;
    public int skin;

    //数值
    public int maxHealth = 20;
    public int currentHealth  = 0;
    public int maxEnergy = 3;
    public int currentEnergy  = 0;

    public int baseAttack = 4;
    public int baseDefense = 0;
    public int baseSpeed = 3;

    public int elementalMastery = 0;//元素精通
    public int tenacity = 0;//韧性
    public int shield = 0;//护盾

    //类型
    public MovementType movementType;
    public ElementType elementType;
    public WeaponType weaponType;
    public RegionType regionType;


    //信息
    public String id;
    public String displayName;
    public int star = 0;
    public int maxSkin = 1;


    //其它
    public int attackBonus = 0;//攻击力加成
    public int treatmentBonus = 0;//治疗加成
    public int defenseBonus = 0;//防御加成
    public int speedBonus = 0;//移速加成
    public int castRangeBonus = 0;//施法范围加成
    public int effectAreaBonus = 0;//生效范围加成

    public double attackMultiplierBonus = 0;//攻击倍率加成
    public double treatmentMultiplierBonus = 0;//治疗倍率加成

    public int additionalDamage = 0;//额外伤害


    public List<RoleTag> roleTags = new ArrayList<>();




    public List<ChessEffect> effects = new ArrayList<>();
    public List<BottomSkill> skills = new ArrayList<>();

    public Material[] icons;
    public Material[] skins;



//    代码	颜色名称	示例	十六进制值
//§0	黑色	§0Black	#000000
//§1	深蓝色	§1Dark Blue	#0000AA
//§2	深绿色	§2Dark Green	#00AA00
//§3	深青色（湖蓝）	§3Dark Aqua	#00AAAA
//§4	深红色	§4Dark Red	#AA0000
//§5	深紫色	§5Dark Purple	#AA00AA
//§6	金色（橙色）	§6Gold	#FFAA00
//§7	灰色	§7Gray	#AAAAAA
//§8	深灰色	§8Dark Gray	#555555
//§9	蓝色	§9Blue	#5555FF
//§a	绿色	§aGreen	#55FF55
//§b	青色（浅蓝）	§bAqua	#55FFFF
//§c	红色	§cRed	#FF5555
//§d	粉色（粉紫）	§dPink	#FF55FF
//§e	黄色	§eYellow	#FFFF55
//§f	白色	§fWhite	#FFFFFF


//    代码	效果	示例
//§k	随机字符（乱码）	§kObfuscated
//§l	粗体	§lBold
//§m	~~删除线~~	§mStrikethrough
//§n	<u>下划线</u>	§nUnderline
//§o	斜体	§oItalic
//§r	重置（恢复默认）	§rReset

    public RoleChess(){
        super(null,ChessType.ROLE);

    }
    public RoleChess(Coord coord, int level , int constellation ,int skin){
        super(coord,ChessType.ROLE);
        this.level = level;
        this.constellation=constellation;
        this.skin = skin;

    }



    public String getId(){
        return this.id;
    }

    public String introduceSkill(){
        return "";
    }

    public RoleChess setInformation(String id,String displayName,int star,int maxSkin){
        this.id = id;
        this.displayName = displayName;
        this.star = star;
        this.maxSkin = maxSkin;

        return this;
    }

    public RoleChess setBaseStats(int maxHealth, int maxEnergy) {
        this.maxHealth  = maxHealth;
        this.currentHealth = maxHealth;
        this.maxEnergy = maxEnergy;
        return this;
    }

    public RoleChess setBaseStats(int baseAttack, int baseDefense, int baseSpeed, int elementalMastery) {
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.elementalMastery = elementalMastery;

        return this;
    }


    public RoleChess setTypes(ElementType elementType, WeaponType weaponType, RegionType regionType, MovementType movementType) {
        this.elementType = elementType;
        this.weaponType = weaponType;
        this.regionType = regionType;
        this.movementType = movementType;

        return this;
    }
    public RoleChess setTypes(ElementType elementType, WeaponType weaponType, MovementType movementType) {
        this.elementType = elementType;
        this.weaponType = weaponType;
        this.movementType = movementType;

        return this;
    }

    public void setRegionType(RegionType regionType){
        this.regionType = regionType;
    }

    public void build(){
        icons = new Material[maxSkin];
        skins = new Material[maxSkin];

        for(int i=0;i<maxSkin;i++){
            icons[i] = new Material("textures/item/rolechess/"+ this.regionType.id +"/" + this.id + i + "icon.png");
            skins[i] = new Material("textures/item/rolechess/" +this.regionType.id +"/" + this.id + i + "skin.png");
        }

    }

    public void renderChess(int cellSize,int chessSize,boolean concern){

        if(concern){
            if(!shouldRender){
                return;
            }
        }


        PoseStack poseStack = Material.poseStack;
        poseStack.pushPose();
        {
            Material bottom = new Material("textures/item/bottom.png");
            poseStack.translate((cellSize-chessSize)/2f,(cellSize-chessSize)/2f,10);
            bottom.render00(chessSize,chessSize);
            poseStack.translate(0,0,1);
            icons[skin].render00(chessSize,chessSize);

            Material team = new Material("textures/item/"+ teamType.name +".png");
            poseStack.translate(chessSize/2f,chessSize/2f,1);
            poseStack.scale(1.1f,1.1f,0);
            poseStack.translate(-chessSize/2f,-chessSize/2f,0);
            team.render00(chessSize,chessSize);

        }
        poseStack.popPose();
    }

    public RoleChess addTag(RoleTag... roleTags) {
        Collections.addAll(this.roleTags, roleTags);
        return this;
    }

    protected String getTagsDisplay() {
        if (roleTags.isEmpty()) return "";

        return "\n§9§l" +
                roleTags.stream()
                        .map(RoleTag::getDisplayName)
                        .collect(Collectors.joining(" "))
                +"§r\n"
                ;
    }

    @Override
    public String getIntroduce() {
        return "";
    }


}

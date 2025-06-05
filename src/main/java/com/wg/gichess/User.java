package com.wg.gichess;


import com.wg.gichess.chess.rolechess.RoleChess;
import com.wg.gichess.chess.rolechess.RoleFactoryV1;
import com.wg.gichess.chessboard.ChessBoard;
import com.wg.gichess.chessboard.GameModes;
import com.wg.gichess.chessboard.ShineCBType;
import com.wg.gichess.item.propitem.PropItem;
import com.wg.gichess.item.valuableitem.ValuableItem;
import com.wg.gichess.material.AnimationManager;
import com.wg.gichess.material.MouseCursor;
import com.wg.gichess.material.animation.screen.PromptBoxA;
import com.wg.gichess.material.map.PositionType;
import com.wg.gichess.screen.FastScreen;
import com.wg.gichess.screen.LobbyScreen;
import net.minecraft.client.Minecraft;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class User {

    public static PositionType positionType = PositionType.MONDSTADTGATE;
    public static MouseCursor mouseCursor = new MouseCursor();
    public static FastScreen lastScreen = new LobbyScreen(true);
    public static EventBus eventBus = new EventBus();
    public static User user = new User();
    public static User getInstance(){
        return user;
    }

    public static AnimationManager animationManager = new AnimationManager();



    public static List<RoleChess> roles = new ArrayList<>();
    public static List<ValuableItem> valuableItems = new ArrayList<>();
    public static List<PropItem> propItems = new ArrayList<>();

    public static GameModes gameMode = GameModes.DEFAULT;
    public static GameModes lastGameMode = GameModes.DEFAULT;
    public static ChessBoard chessBoard;
    public static TeamType teamType = TeamType.RED;
    public static String displayName = Minecraft.getInstance().level.players().getFirst().getScoreboardName();


    public static ShineCBType lastShineCBType = ShineCBType.ONROWORCOLUMN;
    public static ShineCBType shineCBType = ShineCBType.ONROWORCOLUMN;
    public static int goWarRoleNum = 4;//出战角色数量
    public static int constellationLockNum = 3;//命座上限锁
    public static int celestialJudgmentRound = 30;//天理裁决回合


    public static boolean fastOpen = true;//快速启动原神棋
    public static boolean clockClick = false;//禁止点击
    public static boolean isOnline = false;//联机对战

    static {

        for (Map.Entry<String, RoleFactoryV1.RoleConstructor> entry : RoleFactoryV1.ROLE_MAP.entrySet()) {

            try {
                roles.add(  entry.getValue().create(null,1,3,0) ) ;
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }



    }

    public static void sendPromptBoxA(String text){
        User.animationManager.addAnimations(new PromptBoxA(text),null);
    }

    public static void post(Event event){
        List<Object> owner = new ArrayList<>();
        owner.add(User.getInstance());
        eventBus.post(event,owner);
    }







}

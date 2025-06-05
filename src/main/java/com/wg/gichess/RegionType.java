package com.wg.gichess;

import com.wg.gichess.material.map.Map;
import com.wg.gichess.material.map.abyssalmine.AbyssalmineMap;
import com.wg.gichess.material.map.celestia.CelestiaMap;
import com.wg.gichess.material.map.enkanomiya.EnkanomiyaMap;
import com.wg.gichess.material.map.khaenriah.KhaenriahMap;
import com.wg.gichess.material.map.remuria.RemuriaMap;
import com.wg.gichess.material.map.sacredmountain.SacredmountainMap;
import com.wg.gichess.material.map.theseven.fontaine.FontaineMap;
import com.wg.gichess.material.map.theseven.inazuma.InazumaMap;
import com.wg.gichess.material.map.theseven.liyue.LiyueMap;
import com.wg.gichess.material.map.theseven.mondstadt.MondstadtMap;
import com.wg.gichess.material.map.theseven.natlan.NatlanMap;
import com.wg.gichess.material.map.theseven.snezhnaya.SnezhnayaMap;
import com.wg.gichess.material.map.theseven.sumeru.SumeruMap;

import java.util.function.Supplier;

public enum RegionType {

    MONDSTADT("mondstadt","蒙德", MondstadtMap::new),
    LIYUE("liyue","璃月",LiyueMap::new),
    INAZUMA("inazuma","稻妻",InazumaMap::new),
    SUMERU("sumeru","须弥",SumeruMap::new),
    FONTAINE("fontaine","枫丹",FontaineMap::new),
    NATLAN("natlan","纳塔",NatlanMap::new),
    SNEZHNAYA("snezhnaya","至冬",SnezhnayaMap::new),

    CELESTIA("celestia","天空岛",CelestiaMap::new),
    KHAENRIAH("khaenriah","坎瑞亚", KhaenriahMap::new),
    ENKANOMIYA("enkanomiya","渊下宫", EnkanomiyaMap::new),
    ABYSSALMINE("abyssalmine","巨渊矿区", AbyssalmineMap::new),
    REMURIA("remuria","旧日之海", RemuriaMap::new),
    SACREDMOUNTAIN("sacredmountain","远古圣山", SacredmountainMap::new),

    HILICHURLS("hilichurls","丘丘部族",null);

    public final String id;
    public final String displayName;
    public final Supplier<Map> mapSupplier;


    RegionType(String id, String displayName, Supplier<Map> mapSupplier){
        this.id = id;
        this.displayName = displayName;
        this.mapSupplier = mapSupplier;
    }

    public Map getMap() {

        if(mapSupplier != null){
            return mapSupplier.get();
        }
        return null;

    }

}

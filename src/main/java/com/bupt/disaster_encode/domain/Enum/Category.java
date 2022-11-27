package com.bupt.disaster_encode.domain.Enum;

public enum Category {

    EARTHQUAKE_INFO(1, "01", "震情信息"),
    PERSON_Die(2, "01", "人员死亡"),
    PERSON_INJURE(2, "02", "人员受伤"),
    PERSON_DISAPPEAR(2, "03", "人员失踪"),
    BUILDING_WOOD(3, "01", "土木房屋"),
    BUILDING_BRICK(3, "02", "砖木房屋"),
    BUILDING_FRAME(3, "04", "框架房屋"),
    BUILDING_OTHER(3, "05", "其他房屋"),
    LIFELINE_TRANS(4, "01", "交通"),
    LIFELINE_WATER(4, "02", "供水"),
    LIFELINE_OIL(4, "03", "输油"),
    LIFELINE_GAS(4, "04", "燃气"),
    LIFELINE_ELEC(4, "05", "电力"),
    LIFELINE_TELE(4, "06", "通信"),
    LIFELINE_HYDRA(4, "07", "水利"),
    SUB_COLLAPSE(5, "01", "崩塌"),
    SUB_LANDSLIDE(5, "02", "滑坡"),
    SUB_MUD_FLOW(5, "03", "泥石流"),
    SUB_KARST_COLLAPSE(5, "04", "熔岩坍塌"),
    SUB_GROUND_TEARS(5, "05", "地裂缝"),
    SUB_GROUND_DOWN(5, "06", "地面沉降"),
    SUB_OTHER(5, "07", "其他"),


    ;

    public int bigCategory;
    public String subClassCode;
    public String subClassName;

    Category(int bigCategory, String subClassCode, String subClassName) {
        this.bigCategory = bigCategory;
        this.subClassCode = subClassCode;
        this.subClassName = subClassName;
    }
}

package com.bupt.disaster_encode.domain.Enum;

public enum Label {

    EARTHQUAKE_POS(1, "001", "地理位置"),
    EARTHQUAKE_TIME(1, "002", "时间"),
    EARTHQUAKE_LEVEL(1, "003", "级别"),
    EARTHQUAKE_DEPTH(1, "004", "震源深度"),
    EARTHQUAKE_FIERCE(1, "005", "烈度"),

    PERSON_COUNT(2, "001", "受灾人数"),
    PERSON_LEVEL(2, "002", "受灾程度"),

    BUILDING_NORMAL_AREA(3, "001", "一般损坏面积"),
    BUILDING_SERIOUS_AREA(3, "002", "严重损坏面积"),
    BUILDING_LEVEL(3, "003", "受灾程度"),

    LIFELINE_FACILITY(4, "001", "受灾设施数"),
    LIFELINE_SCOPE(4, "002", "受灾范围"),
    LIFELINE_LEVEL(4, "003", "受灾程度"),

    SUB_LOSS(5, "001", "灾害损失"),
    SUB_SCOPE(5, "002", "灾害范围"),
    SUB_LEVEL(5, "003", "受灾程度"),


    ;

    public int bigCategory;
    public String CODE;
    public String labelName;

    Label(int bigCategory, String CODE, String labelName) {
        this.bigCategory = bigCategory;
        this.CODE = CODE;
        this.labelName = labelName;
    }
}

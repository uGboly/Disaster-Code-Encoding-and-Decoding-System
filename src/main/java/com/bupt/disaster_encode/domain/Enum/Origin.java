package com.bupt.disaster_encode.domain.Enum;


public enum Origin {

    FEEC(1, "业务报送数据", "00", "前方地震应急指挥部", "100"),
    BEEC(1, "业务报送数据", "01", "后方地震应急指挥部", "101"),
    ECTS(1, "业务报送数据", "20", "应急指挥技术系统", "120"),
    SSEERS(1, "业务报送类", "21", "社会服务工程应急救援系统", "121"),
    HAPAWG(1, "业务报送类", "40", "危险区预评估工作组", "140"),
    EECTCG(1, "业务报送类", "41", "地震应急指挥技术协调组", "141"),
    PEGISWP(1, "业务报送类", "42", "震后政府信息支持工作项目", "142"),
    DERE(1, "业务报送组", "80", "灾情快速上报接收处理系统", "180"),
    TSRT(1, "业务报送组", "81", "地方地震局应急信息服务相关技术系统", "181"),
    OTHER(1, "业务报送组", "99", "其他", "199"),

    IA(2, "泛在感知数据", "00", "互联网感知", "200"),
    CNA(2, "泛在感知数据", "01", "通信网感知", "201"),
    PONP(2, "泛在感知数据", "02", "舆情感知", "202"),
    PSS(2, "泛在感知数据", "03", "电力系统感知", "203"),
    TSA(2, "泛在感知数据", "04", "交通系统感知", "204"),
    OTHER2(2, "泛在感知数据", "05", "其他", "205"),

    OTHER3(3, "其他数据", "00", "其他", "300"),
    ;

    public int LargeCategoriesCode;
    public String LargeCategoriesName;
    public String subClassCode;
    public String subClassName;
    public String CODE;

    Origin(int largeCategoriesCode, String largeCategoriesName, String subClassCode, String subClassName, String CODE) {
        LargeCategoriesCode = largeCategoriesCode;
        LargeCategoriesName = largeCategoriesName;
        this.subClassCode = subClassCode;
        this.subClassName = subClassName;
        this.CODE = CODE;
    }
}

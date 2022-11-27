package com.bupt.disaster_encode.domain.Enum;

public enum Carrier {
    WORD(0, "文字"),
    GRAPH(1, "图像"),
    VOICE(2, "音频"),
    VIDEO(3, "视频"),
    OTHER(4, "其他"),
    ;

    public int CODE;
    public String FORM;

    Carrier(int CODE, String FORM) {
        this.CODE = CODE;
        this.FORM = FORM;
    }
}

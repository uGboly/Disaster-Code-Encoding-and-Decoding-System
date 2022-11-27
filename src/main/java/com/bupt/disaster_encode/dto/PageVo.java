package com.bupt.disaster_encode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    Integer pageNum = 0;

    Integer pageSize = 20;

    public Integer getOffset() {
        Integer pageIndex = this.getPageNum() > 0 ? this.getPageNum() - 1 : 0;
        return pageIndex * this.pageSize;
    }

    public Integer getLimit() {
        return pageSize;
    }

}

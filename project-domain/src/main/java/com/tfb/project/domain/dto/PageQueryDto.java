package com.tfb.project.domain.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 分页参数
 */
@Slf4j
@Data
public class PageQueryDto implements Serializable {

    /**
     * 页码的参数名称，默认：page
     */
    private int page = 1;

    /**
     * 每页数据量的参数名，默认：limit
     */
    private int limit = 10;

    /**
     * 排序字段，对应 cols 设定的各字段名
     */
    private String field;

    /**
     * 排序方式  asc: 升序、desc: 降序、
     */
    private String type = "desc";

    public int getPage() {
        return page <= 0 ? 1 : page;
    }

    public int getLimit() {
        return limit <= 0 ? 10 : limit;
    }
}

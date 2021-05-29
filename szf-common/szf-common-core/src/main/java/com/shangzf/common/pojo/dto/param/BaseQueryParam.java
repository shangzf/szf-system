package com.shangzf.common.pojo.dto.param;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "基本查询参数")
public class BaseQueryParam extends BaseParam {
    private static final long serialVersionUID = -598315986294728276L;

    @Schema(name = "查询的值")
    private String query;

    public BaseQueryParam() {
    }

    public BaseQueryParam(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

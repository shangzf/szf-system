package com.shangzf.common.vo.request;

import org.apache.commons.lang3.StringUtils;

public class DefaultQueryRequestData implements RequestData {

    private static final long serialVersionUID = 7694213510727876128L;
    private int page = 1;
    private int limit = 20;
    private String sort;

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(final String sort) {
        this.sort = sort;
    }
    public String orderBy() {
        String order = this.sort;
        if (StringUtils.isBlank(order)) {
            order = "-id";
        }
        order = order.replaceAll("\\+([\\w]+)", " $1" + " asc,");
        order = order.replaceAll("\\-([\\w]+)", " $1" + " desc,");
        order = order.substring(0, order.length() - 1);
        return order;
    }
}

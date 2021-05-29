package com.shangzf.common.vo.page;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 数据表格
 */
@Schema(name = "数据表格（分页显示数据）")
public class DataGrid<T> implements Serializable {

    private static final long serialVersionUID = -5448331939875020289L;

    @Schema(name = "查询数据列表")
    private List<T> records;

    @Schema(name = "总数")
    private long total;

    @Schema(name = "每页显示条数，默认 10")
    private long size;

    @Schema(name = "当前页")
    private long current;

    public DataGrid() {
        this(Collections.emptyList(), 0, 1);
    }

    public DataGrid(List<T> records, long total, long current) {
        this(records, total, 10, current);
    }

    public DataGrid(List<T> records, long total, long size, long current) {
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}

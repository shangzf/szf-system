package com.shangzf.common.pojo.vo.page;

import com.shangzf.common.constant.PageConstant;
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
    private Long total;

    @Schema(name = "每页显示条数，默认10")
    private Long size;

    @Schema(name = "当前页")
    private Long current;

    public DataGrid() {
        this(Collections.emptyList(), PageConstant.DEFAULT_TOTAL, PageConstant.DEFAULT_CURRENT);
    }

    public DataGrid(List<T> records, Long total, Long current) {
        this(records, total, PageConstant.DEFAULT_SIZE, current);
    }

    public DataGrid(List<T> records, Long total, Long size, Long current) {
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}

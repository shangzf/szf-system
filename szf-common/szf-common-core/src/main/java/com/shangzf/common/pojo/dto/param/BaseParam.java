package com.shangzf.common.pojo.dto.param;

import com.shangzf.common.constant.PageConstant;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Schema(name = "基础分页查询参数")
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 7694213510727876128L;

    @Schema(name = "当前页")
    private Long current;

    @Schema(name = "每页显示条数，默认10")
    private Long size;

    @Schema(name = "升序排序的列名")
    private List<String> asc;

    @Schema(name = "降序序排序的列名")
    private List<String> desc;

    public BaseParam() {
        this(PageConstant.DEFAULT_CURRENT);
    }

    public BaseParam(Long current) {
        this(current, PageConstant.DEFAULT_SIZE);
    }

    public BaseParam(Long current, Long size) {
        this(current, size, Collections.emptyList(), Collections.emptyList());
    }

    public BaseParam(Long current, List<String> asc, List<String> desc) {
        this(current, PageConstant.DEFAULT_SIZE, asc, desc);
    }

    public BaseParam(Long current, Long size, List<String> asc, List<String> desc) {
        this.current = current;
        this.size = size;
        this.asc = asc;
        this.desc = desc;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<String> getAsc() {
        return asc;
    }

    public void setAsc(List<String> asc) {
        this.asc = asc;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }
}

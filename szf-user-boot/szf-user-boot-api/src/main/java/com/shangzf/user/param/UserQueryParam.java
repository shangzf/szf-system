package com.shangzf.user.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam {
    private Integer currentPage;
    private Integer pageSize;
    private String phone;
    private Long userId;
    private Date startCreateTime;
    private Date endCreateTime;
}

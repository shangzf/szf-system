package com.shangzf.authority.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shangzf.authority.constant.FillConstant;
import com.shangzf.common.user.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 自动填充功能
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, FillConstant.CREATE_BY, String.class, UserManager.getUsername());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, FillConstant.LAST_MODIFY_BY, String.class, UserManager.getUsername());
    }
}

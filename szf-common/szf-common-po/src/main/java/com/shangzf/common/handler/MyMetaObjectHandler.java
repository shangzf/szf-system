package com.shangzf.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shangzf.common.constant.FillConstant;
import com.shangzf.common.user.UserManager;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 自动填充功能
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FillConstant.CREATE_BY, String.class, UserManager.getUsername());
        this.strictInsertFill(metaObject, FillConstant.CREATE_TIME, Date.class, Date.from(ZonedDateTime.now().toInstant()));
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, FillConstant.LAST_MODIFY_BY, String.class, UserManager.getUsername());
        this.strictInsertFill(metaObject, FillConstant.LAST_MODIFY_TIME, Date.class, Date.from(ZonedDateTime.now().toInstant()));
    }
}

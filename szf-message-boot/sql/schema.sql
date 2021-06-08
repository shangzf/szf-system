CREATE DATABASE szf_message DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS szf_message.message;
CREATE TABLE szf_message.message
(
    id               BIGINT      NOT NULL COMMENT '消息ID',
    user_id          BIGINT      NOT NULL COMMENT '用户ID',
    theme            VARCHAR(50) NOT NULL COMMENT '主题',
    content          VARCHAR(500) DEFAULT '' COMMENT '内容',
    reference_url    VARCHAR(200) DEFAULT '' COMMENT '参考地址',
    unread           BIT(1)       DEFAULT b'1' COMMENT '是否已读，0-已读，1-未读',
    deleted          BIT(1)       DEFAULT b'0' COMMENT '是否删除，0-未删除，1-已删除',
    remark           VARCHAR(200) DEFAULT '' COMMENT '备注',
    create_time      DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '消息';
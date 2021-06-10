CREATE DATABASE szf_user DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS szf_user.user;
CREATE TABLE szf_user.user
(
    id                      BIGINT      NOT NULL COMMENT '用户ID',
    username                VARCHAR(20) NOT NULL COMMENT '用户名',
    portrait                VARCHAR(200) DEFAULT '' COMMENT '用户头像地址',
    phone                   CHAR(11)    NOT NULL COMMENT '注册手机',
    secret                  VARCHAR(100) DEFAULT '' COMMENT '用户密码(可以为空，支持只用验证码注册、登录)',
    reg_ip                  INT          DEFAULT 0 COMMENT '注册IP',
    account_non_expired     BIT(1)       DEFAULT b'1' COMMENT '是否有效用户',
    credentials_non_expired BIT(1)       DEFAULT b'1' COMMENT '账号是否未过期',
    account_non_locked      BIT(1)       DEFAULT b'1' COMMENT '是否未锁定',
    sign                    CHAR(1)      DEFAULT 'E' COMMENT '用户状态：E-能登录，D-不能登录',
    deleted                 BIT(1)       DEFAULT b'0' COMMENT '是否删除',
    create_time             DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time        DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    remark                  VARCHAR(200) DEFAULT '' COMMENT '描述',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE KEY idx_phone_deleted (phone, deleted) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '用户信息';

DROP TABLE IF EXISTS szf_user.weixin;
CREATE TABLE szf_user.weixin
(
    id              BIGINT       NOT NULL COMMENT 'ID',
    user_id         BIGINT       NOT NULL COMMENT '用户ID',
    union_id        VARCHAR(100) NOT NULL COMMENT '认证ID，对应微信的unionID',
    open_id         VARCHAR(100) DEFAULT '' COMMENT 'openID',
    nick_name       VARCHAR(100) NOT NULL COMMENT '昵称',
    portrait        VARCHAR(200) DEFAULT '' COMMENT '头像',
    city            VARCHAR(50)  DEFAULT '' COMMENT '城市',
    sex             CHAR(1)      DEFAULT 'U' COMMENT '性别',
    deleted         BIT(1)       DEFAULT b'0' COMMENT '是否删除',
    create_time     DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    remark          VARCHAR(200) DEFAULT '' COMMENT '描述',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE KEY idx_union_open_deleted (union_id, open_id, deleted) USING BTREE,
    UNIQUE KEY idx_user_open_delete (user_id, open_id, deleted) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '微信绑定信息';

DROP TABLE IF EXISTS szf_user.user_phone_verification_code;
CREATE TABLE szf_user.user_phone_verification_code
(
    id                BIGINT   NOT NULL COMMENT 'ID',
    phone             CHAR(11) NOT NULL COMMENT '手机号码',
    verification_code CHAR(6)  NOT NULL COMMENT '验证码',
    create_time       DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    checked           BIT(1)      DEFAULT b'0' COMMENT '验证码是否校验过',
    check_times        INT(2)      DEFAULT '0' COMMENT '校验次数',
    PRIMARY KEY (id) USING BTREE,
    KEY phone_verification_code_time (phone, create_time) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '验证码';
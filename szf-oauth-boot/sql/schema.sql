CREATE DATABASE szf_oauth DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS szf_oauth.oauth_client_details;
CREATE TABLE szf_oauth.oauth_client_details
(
    id                      BIGINT AUTO_INCREMENT COMMENT 'ID',
    client_id               VARCHAR(50)  NOT NULL COMMENT '客户端ID',
    resource_ids            VARCHAR(200) DEFAULT '' COMMENT '资源ID,多个值以逗号分隔',
    client_secret           VARCHAR(100) NOT NULL COMMENT '客户端密钥',
    scope                   VARCHAR(50)  DEFAULT '' COMMENT '权限范围,多个权限以逗号隔开',
    authorized_grant_types  VARCHAR(100) NOT NULL COMMENT '授权类型,多个类型以逗号隔开',
    web_server_redirect_uri VARCHAR(100) DEFAULT '' COMMENT '客户端的重定向URI,可为空',
    authorities             VARCHAR(256) DEFAULT '' COMMENT '拥有Spring Security的权限值,多个值以逗号隔开',
    access_token_validity   INT          DEFAULT 43200 COMMENT 'access_token有效时间值(单位:秒),默认12小时',
    refresh_token_validity  INT          DEFAULT 2592000 COMMENT 'refresh_token有效时间值(单位:秒),默认30天',
    additional_information  VARCHAR(200) DEFAULT '' COMMENT '预留的字段',
    archived                BIT(1)       DEFAULT b'0' COMMENT '是否已存档',
    trusted                 BIT(1)       DEFAULT b'0' COMMENT '是否为受信任的',
    autoapprove             VARCHAR(10)  DEFAULT 'false' COMMENT '是否自动,可选值包括(true,false,read,write)',
    create_time             DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time         DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE,
    KEY client_deleted (client_id, archived) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '客户端';

DROP TABLE IF EXISTS szf_oauth.oauth_client_token;
CREATE TABLE szf_oauth.oauth_client_token
(
    id                BIGINT AUTO_INCREMENT COMMENT 'ID',
    token_id          VARCHAR(100) NOT NULL COMMENT '从服务器端获取到的access_token的值',
    token             BLOB COMMENT '二进制数据',
    authentication_id VARCHAR(100) NOT NULL COMMENT 'client_id与scope通过MD5加密生成的',
    user_name         VARCHAR(50)  NOT NULL COMMENT '登录用户名',
    client_id         VARCHAR(50)  NOT NULL COMMENT '客户端ID',
    create_time       DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time   DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE,
    KEY idx_authentication_id (authentication_id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT 'client_token数据';

DROP TABLE IF EXISTS szf_oauth.oauth_access_token;
CREATE TABLE szf_oauth.oauth_access_token
(
    id                BIGINT AUTO_INCREMENT COMMENT 'ID',
    token_id          VARCHAR(100) NOT NULL COMMENT '将access_token的值通过MD5加密后存储的',
    token             BLOB COMMENT '二进制数据',
    authentication_id VARCHAR(100) NOT NULL COMMENT 'client_id与scope通过MD5加密生成的',
    user_name         VARCHAR(50)  NOT NULL COMMENT '登录用户名',
    client_id         VARCHAR(50)  NOT NULL COMMENT '客户端ID',
    authentication    BLOB COMMENT '二进制数据',
    refresh_token     VARCHAR(100) DEFAULT '' COMMENT '将refresh_token的值通过MD5加密后存储的',
    create_time       DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time   DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE,
    KEY idx_authentication_id (authentication_id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT 'access_token数据';

DROP TABLE IF EXISTS szf_oauth.oauth_refresh_token;
CREATE TABLE szf_oauth.oauth_refresh_token
(
    id              BIGINT AUTO_INCREMENT COMMENT 'ID',
    token_id        VARCHAR(100) NOT NULL COMMENT '将access_token的值通过MD5加密后存储的',
    token           BLOB COMMENT '二进制数据',
    authentication  BLOB COMMENT '二进制数据',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE,
    KEY idx_token (token_id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT 'refresh_token数据';

DROP TABLE IF EXISTS szf_oauth.oauth_code;
CREATE TABLE szf_oauth.oauth_code
(
    id              BIGINT AUTO_INCREMENT COMMENT 'ID',
    code            VARCHAR(50) NOT NULL COMMENT '授权码',
    authentication  BLOB COMMENT '二进制数据',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE,
    KEY idx_code (code) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '授权码数据';

DROP TABLE IF EXISTS szf_oauth.oauth_approvals;
CREATE TABLE szf_oauth.oauth_approvals
(
    id              BIGINT AUTO_INCREMENT COMMENT 'ID',
    expiresAt       DATETIME(6)  NOT NULL COMMENT '过期时间',
    status          VARCHAR(10)  NOT NULL COMMENT '审批状态',
    lastModifiedAt  DATETIME(6)  NOT NULL COMMENT '最后修改时间',
    userId          VARCHAR(100) NOT NULL COMMENT '用户ID',
    clientId        VARCHAR(100) NOT NULL COMMENT '客户端ID',
    scope           VARCHAR(10)  NOT NULL COMMENT '范围',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '授权数据';
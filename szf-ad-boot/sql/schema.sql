CREATE DATABASE szf_ad DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS szf_ad.promotion_space;
CREATE TABLE szf_ad.promotion_space
(
    id              BIGINT       NOT NULL COMMENT 'ID',
    name            VARCHAR(100) NOT NULL COMMENT '名称',
    space_key       VARCHAR(100) NOT NULL COMMENT '广告位Key',
    deleted         BIT(1)       DEFAULT b'0' COMMENT '是否删除',
    create_time     DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    remark          VARCHAR(200) DEFAULT '' COMMENT '描述',
    PRIMARY KEY (id) USING BTREE,
    KEY promotion_space_key_deleted (space_key, deleted) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '广告位';

DROP TABLE IF EXISTS szf_ad.promotion_ad;
CREATE TABLE szf_ad.promotion_ad
(
    id              BIGINT       NOT NULL COMMENT 'ID',
    name            VARCHAR(100) NOT NULL COMMENT '名称',
    space_id        BIGINT       NOT NULL COMMENT '广告位ID',
    keyword         VARCHAR(200) DEFAULT '' COMMENT '精确搜索关键词',
    html_content    TEXT COMMENT '静态广告的内容',
    text            VARCHAR(200) DEFAULT '' COMMENT '文字',
    link            VARCHAR(200) DEFAULT '' COMMENT '链接',
    start_time      DATETIME     NOT NULL COMMENT '开始时间',
    end_time        DATETIME     NOT NULL COMMENT '结束时间',
    status          CHAR(1)      DEFAULT 'S' COMMENT '状态(S/U/D)',
    priority        INT(4)       DEFAULT 0 COMMENT '优先级',
    img             VARCHAR(200) DEFAULT '' COMMENT '展示图片地址',
    create_time     DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    last_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    remark          VARCHAR(200) DEFAULT '' COMMENT '描述',
    PRIMARY KEY (id) USING BTREE,
    KEY promotion_ad_seg (space_id, start_time, end_time, status) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '广告';
CREATE DATABASE szf_authority DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS szf_authority.roles;
CREATE TABLE szf_authority.roles
(
    id              BIGINT      NOT NULL COMMENT 'ID',
    code            VARCHAR(50) NOT NULL COMMENT '编码',
    name            VARCHAR(50) NOT NULL COMMENT '名称',
    remark          VARCHAR(200) DEFAULT '' COMMENT '描述',
    create_time     DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50)  DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50)  DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '角色';

DROP TABLE IF EXISTS szf_authority.menu;
CREATE TABLE szf_authority.menu
(
    id              BIGINT      NOT NULL COMMENT 'ID',
    parent_id       BIGINT      NOT NULL COMMENT '父ID',
    href            VARCHAR(100) DEFAULT '' COMMENT '菜单地址',
    icon            VARCHAR(100) DEFAULT '' COMMENT '图标',
    name            VARCHAR(50) NOT NULL COMMENT '名称',
    remark          VARCHAR(200) DEFAULT '' COMMENT '描述',
    shown           BIT(1)       DEFAULT b'1' COMMENT '是否显示',
    order_num       INT          DEFAULT 1 COMMENT '序号',
    level           INT          DEFAULT 1 COMMENT '等级',
    create_time     DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50)  DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50)  DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '菜单';

DROP TABLE IF EXISTS szf_authority.resource;
CREATE TABLE szf_authority.resource
(
    id              BIGINT      NOT NULL COMMENT 'ID',
    name            VARCHAR(50) NOT NULL COMMENT '名称',
    url             VARCHAR(200) DEFAULT '' COMMENT '资源地址',
    category_id     BIGINT      NOT NULL COMMENT '资源类别',
    remark          VARCHAR(200) DEFAULT '' COMMENT '描述',
    create_time     DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50)  DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50)  DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '资源';

DROP TABLE IF EXISTS szf_authority.resource_category;
CREATE TABLE szf_authority.resource_category
(
    id              BIGINT      NOT NULL COMMENT 'ID',
    name            VARCHAR(50) NOT NULL COMMENT '类别名称',
    sort            CHAR(1)     DEFAULT '' COMMENT '类别',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50) DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50) DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '资源类别';

DROP TABLE IF EXISTS szf_authority.user_role;
CREATE TABLE szf_authority.user_role
(
    id              BIGINT NOT NULL COMMENT 'ID',
    user_id         BIGINT NOT NULL COMMENT '用户ID',
    role_id         BIGINT NOT NULL COMMENT '角色ID',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50) DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50) DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE,
    KEY user_role (user_id, role_id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '用户-角色';

DROP TABLE IF EXISTS szf_authority.role_menu;
CREATE TABLE szf_authority.role_menu
(
    id              BIGINT NOT NULL COMMENT 'ID',
    role_id         BIGINT NOT NULL COMMENT '角色ID',
    menu_id         BIGINT NOT NULL COMMENT '菜单ID',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50) DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50) DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE,
    KEY role_menu (role_id, menu_id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '角色-菜单';

DROP TABLE IF EXISTS szf_authority.role_resource;
CREATE TABLE szf_authority.role_resource
(
    id              BIGINT NOT NULL COMMENT 'ID',
    role_id         BIGINT NOT NULL COMMENT '角色ID',
    resource_id     BIGINT NOT NULL COMMENT '资源ID',
    create_time     DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    las_modify_time DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '最后修改时间',
    create_by       VARCHAR(50) DEFAULT '' COMMENT '创建者',
    las_modify_by   VARCHAR(50) DEFAULT '' COMMENT '最后修改者',
    PRIMARY KEY (id) USING BTREE,
    KEY role_resource (role_id, resource_id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT '角色-资源';
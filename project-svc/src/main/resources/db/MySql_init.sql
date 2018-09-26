-- 创建数据库.
-- CREATE DATABASE test001;
use test001;

-- 创建表
/* ====================================================================================================================
    userinfo 用户信息表
==================================================================================================================== */
DROP TABLE IF EXISTS userinfo;
CREATE TABLE userinfo (
    id                  bigint          PRIMARY KEY    auto_increment       COMMENT '编号',
    name                varchar(32)     NOT NULL       UNIQUE               COMMENT '用户名',
    phone               varchar(20)     NOT NULL                            COMMENT '手机号码',
    create_at           datetime(3)     NOT NULL                            COMMENT '创建时间',
    update_at           datetime(3)                                         COMMENT '更新时间'
) COMMENT = '用户信息表';

-- 创建索引
CREATE INDEX userinfo_name ON userinfo (name);
-- drop INDEX userinfo_name
/*------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------*/

-- 录入测试数据
INSERT INTO userinfo VALUES('1','张珊','15507552211',CURRENT_TIMESTAMP,null);
INSERT INTO userinfo VALUES('2','李思','15507552212',CURRENT_TIMESTAMP,null);
INSERT INTO userinfo VALUES('3','王舞','15507552213',CURRENT_TIMESTAMP,null);

-- 查询所有
select * from userinfo;

-- 条件查询
SELECT ID, CREATE_AT, NAME, PHONE FROM userinfo u WHERE u.NAME LIKE '李思' AND u.create_at >= '2017-09-25 00:00:00' AND u.create_at <= '2017-09-25 23:59:59'



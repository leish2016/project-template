use test001;

DROP TABLE IF EXISTS userinfo;
CREATE TABLE userinfo(
	id int PRIMARY KEY auto_increment,
	name VARCHAR(50)  COMMENT '用户名',
	phone VARCHAR(20) COMMENT '手机号码',
	create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
);
CREATE INDEX userinfo_name ON userinfo (name);
-- drop INDEX userinfo_name

INSERT INTO userinfo VALUES('1','张珊','15507552211',CURRENT_TIMESTAMP);
INSERT INTO userinfo VALUES('2','李思','15507552212',CURRENT_TIMESTAMP);
INSERT INTO userinfo VALUES('3','王舞','15507552213',CURRENT_TIMESTAMP);
select * from userinfo;

SELECT ID, CREATE_AT, NAME, PHONE FROM userinfo u WHERE u.NAME LIKE '李思' AND u.create_at >= '2017-09-25 00:00:00' AND u.create_at <= '2017-09-25 23:59:59'



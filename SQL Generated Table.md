### SQL Generated Query
```sql_query
DROP TABLE IF EXISTS tbtmp_registration_temp;
CREATE TABLE IF NOT EXISTS tbtmp_registration_temp
(
    regi_rgi_id                     BIGINT(20)        NOT NULL,
    regi_rgi_email                  VARCHAR(255)      NOT NULL,
    regi_rgi_type                   VARCHAR(255)      NOT NULL,
    regi_rgi_password               VARCHAR(255)      NOT NULL,
    regi_rgi_date                   DATETIME          NOT NULL,
    regi_rgi_ip                     DATETIME          NOT NULL,
    CONSTRAINT                      pk_regis_regi_rgi_id PRIMARY KEY (regi_rgi_id),
    CONSTRAINT                      uk_regis_regi_rgi_id UNIQUE (regi_rgi_id)
);
DROP TABLE IF EXISTS tbtmp_user_profile;
CREATE TABLE IF NOT EXISTS tbtmp_user_profile
(
    usrpro_user_id                  BIGINT(20)        NOT NULL,
    usrpro_first_name               VARCHAR(255)      NOT NULL,
    usrpro_mid_name                 VARCHAR(255)      NULL,
    usrpro_last_name                VARCHAR(255)      NULL,
    CONSTRAINT                      pk_userp_usrpro_user_id PRIMARY KEY (usrpro_user_id)
);
DROP TABLE IF EXISTS tbtmp_userrole;
CREATE TABLE IF NOT EXISTS tbtmp_userrole
(
    usrro_role_id                   BIGINT(20)        NULL,
    usrro_role_title                VARCHAR(255)      NULL,
    usrro_role_priority             INT(3)            NULL,
    usrro_role_is_default           BOOLEAN           NULL,
    usrro_role_create_date          DATETIME          NULL,
    usrro_role_modify_date          DATETIME          NULL,
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id),
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title)
);
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15170386003021', 'registration_temp', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15170386005146', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15170386007914', 'userrole', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15170386007914, '15170386012174', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386007914, '15170386015004', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386007914, '15170386014489', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386007914, '15170386018028', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386007914, '15170386011637', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386007914, '15170386017855', 'role_modify_date', 'DATETIME', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15170386012174', '15170386014232', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15170386015004', '15170386018746', 'UNIQUE', null, null);
-- -|END- USERROLE table property end



-- -|START- REGISTRATION_TEMP table property started
INSERT INTO tbl_column_property VALUES (15170386003021, '15170386013692', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386003021, '15170386018785', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386003021, '15170386011805', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386003021, '15170386013335', 'rgi_password', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386003021, '15170386016020', 'rgi_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386003021, '15170386011262', 'rgi_ip', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15170386013692', '15170386017720', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15170386013692', '15170386019100', 'UNIQUE', null, null);
-- -|END- REGISTRATION_TEMP table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15170386005146, '15170386014227', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386005146, '15170386013980', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170386005146, '15170386014941', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15170386005146, '15170386015272', 'last_name', 'VARCHAR', '255', '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15170386014227', '15170386014713', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end
```
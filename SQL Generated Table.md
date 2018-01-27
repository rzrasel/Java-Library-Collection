### SQL Generated Query
```sql_query
DROP TABLE IF EXISTS tbtmp_registration_temp;
CREATE TABLE IF NOT EXISTS tbtmp_registration_temp
(
    regi_rgi_id                     BIGINT(20)        NOT NULL,
    regi_rgi_email                  VARCHAR(255)      NOT NULL,
    regi_rgi_password               TEXT              NOT NULL,
    regi_rgi_wifi_ip                VARCHAR(255)      NOT NULL,
    regi_rgi_device_ip              VARCHAR(255)      NOT NULL,
    regi_rgi_host_trace_ip          VARCHAR(255)      NOT NULL,
    regi_rgi_type                   VARCHAR(255)      NOT NULL,
    regi_rgi_build_id               VARCHAR(255)      NOT NULL,
    regi_rgi_tele_device_id         VARCHAR(255)      NOT NULL,
    regi_rgi_tele_device_serial     VARCHAR(255)      NOT NULL,
    regi_rgi_fcm_id                 TEXT              NOT NULL,
    regi_rgi_con_code               VARCHAR(255)      NOT NULL,
    regi_rgi_package                VARCHAR(255)      NOT NULL,
    regi_rgi_ver_code               VARCHAR(255)      NOT NULL,
    regi_rgi_ver_name               VARCHAR(255)      NOT NULL,
    regi_rgi_auth_key               TEXT              NOT NULL,
    regi_rgi_date                   DATETIME          NOT NULL,
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
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title),
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id)
);
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15170679167369', 'registration_temp', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15170679168436', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15170679161606', 'userrole', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15170679161606, '15170679167528', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679161606, '15170679167388', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679161606, '15170679169132', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679161606, '15170679166508', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679161606, '15170679169544', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679161606, '15170679165339', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15170679167388', '15170679161876', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15170679167528', '15170679169352', 'PRIMARY', null, null);
-- -|END- USERROLE table property end



-- -|START- REGISTRATION_TEMP table property started
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679164631', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679161399', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679167843', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679165828', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679179063', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679177408', 'rgi_host_trace_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679171310', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679173232', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679174478', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679176309', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679177710', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679178507', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679173682', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679176812', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679177756', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679177066', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679167369, '15170679172710', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15170679164631', '15170679171608', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15170679164631', '15170679171789', 'UNIQUE', null, null);
-- -|END- REGISTRATION_TEMP table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15170679168436, '15170679174509', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679168436, '15170679179690', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15170679168436, '15170679179792', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15170679168436, '15170679179969', 'last_name', 'VARCHAR', '255', '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15170679174509', '15170679172654', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end
```
### SQL Generated Query
```sql_query
DROP TABLE IF EXISTS tbtmp_country;
CREATE TABLE IF NOT EXISTS tbtmp_country
(
    cntry_ctry_id                   BIGINT(20)        NOT NULL,
    cntry_ctry_name                 VARCHAR(255)      NOT NULL,
    cntry_ctry_iso2                 VARCHAR(5)        NOT NULL,
    cntry_ctry_iso3                 VARCHAR(5)        NOT NULL,
    cntry_ctry_num_code             VARCHAR(10)       NOT NULL,
    cntry_ctry_continent            VARCHAR(255)      NOT NULL,
    CONSTRAINT                      uk_count_cntry_ctry_iso3 UNIQUE (cntry_ctry_iso3),
    CONSTRAINT                      pk_count_cntry_ctry_id PRIMARY KEY (cntry_ctry_id),
    CONSTRAINT                      uk_count_cntry_ctry_iso2 UNIQUE (cntry_ctry_iso2),
    CONSTRAINT                      uk_count_cntry_ctry_num_code UNIQUE (cntry_ctry_num_code)
);
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
INSERT INTO tbl_table_property VALUES ('15171661174781', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15171661176930', 'registration_temp', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15171661173500', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15171661175853', 'userrole', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15171661173500, '15171661178561', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661173500, '15171661178495', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661173500, '15171661172894', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15171661173500, '15171661174333', 'last_name', 'VARCHAR', '255', '1', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15171661178561', '15171661175069', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end



-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15171661175853, '15171661174691', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661175853, '15171661171685', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661175853, '15171661177361', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661175853, '15171661179283', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661175853, '15171661177642', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661175853, '15171661175481', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15171661174691', '15171661176150', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15171661171685', '15171661174008', 'UNIQUE', null, null);
-- -|END- USERROLE table property end



-- -|START- REGISTRATION_TEMP table property started
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661177148', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661177068', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661178214', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661176005', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661177526', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661175624', 'rgi_host_trace_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661174021', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661177471', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661178399', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661172236', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661187627', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661182729', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661186460', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661184473', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661182749', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661186834', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661176930, '15171661181136', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15171661177148', '15171661186496', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15171661177148', '15171661183091', 'UNIQUE', null, null);
-- -|END- REGISTRATION_TEMP table property end



-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15171661174781, '15171661189323', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661174781, '15171661182854', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661174781, '15171661189118', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661174781, '15171661183946', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661174781, '15171661186974', 'ctry_num_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15171661174781, '15171661184268', 'ctry_continent', 'VARCHAR', '255', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15171661183946', '15171661186990', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15171661189323', '15171661185842', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15171661186974', '15171661186182', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15171661189118', '15171661184431', 'UNIQUE', null, null);
-- -|END- COUNTRY table property end
```
```create_table_sql
CREATE TABLE `app_countries` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`country_code` varchar(2) NOT NULL DEFAULT (null,
	`country_name` varchar(100) NOT NULL DEFAULT (null,
	PRIMARY KEY (`id`),
	KEY `cc` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
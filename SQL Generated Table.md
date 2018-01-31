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
    CONSTRAINT                      uk_count_cntry_ctry_num_code UNIQUE (cntry_ctry_num_code),
    CONSTRAINT                      uk_count_cntry_ctry_iso2 UNIQUE (cntry_ctry_iso2)
);
DROP TABLE IF EXISTS tbtmp_registration;
CREATE TABLE IF NOT EXISTS tbtmp_registration
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
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id),
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title)
);
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15173327428982', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15173327425750', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15173327426122', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15173327422535', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15173327423453', 'userrole', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327428547', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327423231', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327424135', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327429862', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327425052', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327421148', 'rgi_host_trace_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327427621', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327429367', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327428475', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327423001', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327424254', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327422183', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327427361', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327425003', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327425652', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327425624', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327426122, '15173327423498', 'rgi_date', 'DATETIME', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15173327428547', '15173327428439', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15173327428547', '15173327426563', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15173327422535, '15173327424160', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327422535, '15173327429615', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327422535, '15173327426288', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15173327422535, '15173327429415', 'last_name', 'VARCHAR', '255', '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15173327424160', '15173327424588', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end



-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15173327423453, '15173327427008', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327423453, '15173327428549', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327423453, '15173327428793', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327423453, '15173327423201', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327423453, '15173327439894', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327423453, '15173327435731', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15173327428549', '15173327438261', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15173327427008', '15173327431833', 'PRIMARY', null, null);
-- -|END- USERROLE table property end



-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15173327425750, '15173327436090', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15173327425750, '15173327438432', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327425750, '15173327434901', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15173327425750, '15173327437905', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327425750, '15173327436535', 'meta_value', 'TEXT', null, '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15173327438432', '15173327435524', 'PRIMARY', null, null);
-- -|END- METADATA table property end



-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15173327428982, '15173327434609', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327428982, '15173327438132', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327428982, '15173327434229', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327428982, '15173327431232', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327428982, '15173327438900', 'ctry_num_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15173327428982, '15173327434315', 'ctry_continent', 'VARCHAR', '255', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15173327431232', '15173327438733', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15173327438900', '15173327432280', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15173327434609', '15173327432991', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15173327434229', '15173327436473', 'UNIQUE', null, null);
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
Country Codes List
<br />
http://www.nationsonline.org/oneworld/country_code_list.htm
<br />
http://mainfacts.com/world-countries-capitals-cities-codes/BD-BGD-Bangladesh
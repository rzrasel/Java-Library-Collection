### SQL Generated Query
```sql_query
DROP TABLE IF EXISTS tbtmp_app_project;
CREATE TABLE IF NOT EXISTS tbtmp_app_project
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    apjt_aproj_name                 VARCHAR(255)      NOT NULL,
    apjt_aproj_details              TEXT              NOT NULL,
    apjt_aproj_pakg_bundle          VARCHAR(255)      NOT NULL,
    apjt_aproj_latest_ver_code      VARCHAR(255)      NOT NULL,
    apjt_aproj_latest_ver_name      VARCHAR(255)      NOT NULL,
    apjt_aproj_lowest_valid_code    VARCHAR(255)      NOT NULL,
    apjt_aproj_lowest_valid_name    VARCHAR(255)      NOT NULL,
    apjt_aproj_status               BOOLEAN           NOT NULL,
    apjt_aproj_on_published         BOOLEAN           NOT NULL,
    apjt_aproj_create_date          DATETIME          NOT NULL,
    apjt_aproj_modify_date          DATETIME          NOT NULL,
    apjt_aproj_created_by           BIGINT(20)        NOT NULL,
    apjt_aproj_modified_by          BIGINT(20)        NOT NULL,
    CONSTRAINT                      pk_apppr_apjt_aproj_id PRIMARY KEY (apjt_aproj_id)
);
DROP TABLE IF EXISTS tbtmp_country;
CREATE TABLE IF NOT EXISTS tbtmp_country
(
    cntry_ctry_id                   BIGINT(20)        NOT NULL,
    cntry_ctry_name                 VARCHAR(255)      NOT NULL,
    cntry_ctry_iso2                 VARCHAR(5)        NOT NULL,
    cntry_ctry_iso3                 VARCHAR(5)        NOT NULL,
    cntry_ctry_numeric_code         VARCHAR(10)       NOT NULL,
    cntry_ctry_continent            VARCHAR(255)      NOT NULL,
    CONSTRAINT                      uk_count_cntry_ctry_iso2 UNIQUE (cntry_ctry_iso2),
    CONSTRAINT                      pk_count_cntry_ctry_id PRIMARY KEY (cntry_ctry_id),
    CONSTRAINT                      uk_count_cntry_ctry_iso3 UNIQUE (cntry_ctry_iso3),
    CONSTRAINT                      uk_count_cntry_ctry_numeric_code UNIQUE (cntry_ctry_numeric_code)
);
DROP TABLE IF EXISTS tbtmp_metadata;
CREATE TABLE IF NOT EXISTS tbtmp_metadata
(
    mta_ref_id                      BIGINT(20)        NULL,
    mta_meta_id                     BIGINT(20)        NOT NULL,
    mta_meta_identity               TEXT              NULL,
    mta_meta_key                    TEXT              NOT NULL,
    mta_meta_value                  TEXT              NULL,
    CONSTRAINT                      pk_metad_mta_meta_id PRIMARY KEY (mta_meta_id)
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
    usrpro_last_name                VARCHAR(255)      NULL
);
DROP TABLE IF EXISTS tbtmp_userrole;
CREATE TABLE IF NOT EXISTS tbtmp_userrole
(
    usrro_role_id                   BIGINT(20)        NOT NULL,
    usrro_role_title                VARCHAR(255)      NOT NULL,
    usrro_role_priority             INT(3)            NOT NULL,
    usrro_role_is_default           BOOLEAN           NOT NULL,
    usrro_role_create_date          DATETIME          NOT NULL,
    usrro_role_modify_date          DATETIME          NOT NULL,
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title),
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id)
);
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15174215656495', 'app_project', 'tbtmp', 'apjt', null);
INSERT INTO tbl_table_property VALUES ('15174215656278', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15174215652547', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15174215652164', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15174215654407', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15174215659346', 'userrole', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15174215654407, '15174215654064', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215654407, '15174215654368', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215654407, '15174215653738', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15174215654407, '15174215658691', 'last_name', 'VARCHAR', '255', '1', null, null);

DELETE FROM tbl_constraint_property;
-- -|END- USER_PROFILE table property end



-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15174215659346, '15174215653487', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215659346, '15174215656389', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215659346, '15174215655698', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215659346, '15174215657822', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215659346, '15174215655832', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215659346, '15174215654588', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15174215656389', '15174215652905', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15174215653487', '15174215657409', 'PRIMARY', null, null);
-- -|END- USERROLE table property end



-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215653376', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215659295', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215655127', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215654676', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215657555', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215654685', 'rgi_host_trace_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215651235', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215653565', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215653974', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215654093', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215655596', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215655700', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215666339', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215663066', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215664430', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215662679', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652164, '15174215661381', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15174215653376', '15174215665871', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15174215653376', '15174215662557', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end



-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215666610', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215662839', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215662005', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215667071', 'aproj_pakg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215666032', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215662465', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215665574', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215669983', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215667434', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215662784', 'aproj_on_published', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215663357', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215667731', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215665544', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656495, '15174215662525', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15174215666610', '15174215665462', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end



-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15174215656278, '15174215668529', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656278, '15174215666757', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656278, '15174215669005', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656278, '15174215662519', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656278, '15174215667945', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215656278, '15174215662206', 'ctry_continent', 'VARCHAR', '255', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15174215669005', '15174215661142', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15174215668529', '15174215662186', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15174215662519', '15174215667446', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15174215667945', '15174215663685', 'UNIQUE', null, null);
-- -|END- COUNTRY table property end



-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15174215652547, '15174215668777', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15174215652547, '15174215661159', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652547, '15174215669538', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15174215652547, '15174215663229', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15174215652547, '15174215666389', 'meta_value', 'TEXT', null, '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15174215661159', '15174215661424', 'PRIMARY', null, null);
-- -|END- METADATA table property end
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
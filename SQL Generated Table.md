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
-- FCM Table -> FCM token, Build Number, package, version, last ip, last country, last city
-- Login Log -> 
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15176655557559', 'app_key_store', 'tbtmp', 'akstor', null);
INSERT INTO tbl_table_property VALUES ('15176655569633', 'app_play_store', 'tbtmp', 'apstre', null);
INSERT INTO tbl_table_property VALUES ('15176655562318', 'app_project', 'tbtmp', 'apjt', null);
INSERT INTO tbl_table_property VALUES ('15176655567260', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15176655561553', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15176655563265', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15176655564722', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15176655565816', 'user_role', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655568384', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655563039', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655564183', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655578104', 'aproj_type', 'VARCHAR', '255', '0', null, 'APP, GAME ETC');
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655577587', 'aproj_pakg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655577539', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655578894', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655575771', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655572324', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655572376', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655576070', 'aproj_on_published', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655574543', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655571537', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655588580', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655562318, '15176655584379', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176655568384', '15176655581333', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15176655564722, '15176655583747', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655564722, '15176655581178', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655564722, '15176655582730', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15176655564722, '15176655586444', 'last_name', 'VARCHAR', '255', '1', null, null);

-- DELETE FROM tbl_constraint_property;
-- -|END- USER_PROFILE table property end



-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655587997', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655585048', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655587931', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655596819', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655593997', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655594123', 'rgi_host_trace_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655592976', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655599849', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655599231', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655591558', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655591990', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655599631', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655599522', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655609485', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655601319', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655607541', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655563265, '15176655602564', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176655587997', '15176655605176', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15176655587997', '15176655601737', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end



-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15176655565816, '15176655602660', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655565816, '15176655608063', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655565816, '15176655609939', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655565816, '15176655616614', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655565816, '15176655616983', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655565816, '15176655619319', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176655608063', '15176655611664', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15176655602660', '15176655614549', 'PRIMARY', null, null);
-- -|END- USER_ROLE table property end



-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655619906', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655618764', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655618571', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655617712', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655615422', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655621366', 'ctry_continent', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655621466', 'ctry_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655567260, '15176655623367', 'ctry_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176655617712', '15176655626534', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15176655619906', '15176655628174', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15176655615422', '15176655622988', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15176655618571', '15176655623778', 'UNIQUE', null, null);
-- -|END- COUNTRY table property end



-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15176655561553, '15176655623347', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15176655561553, '15176655625592', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655561553, '15176655627105', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15176655561553, '15176655634628', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176655561553, '15176655638643', 'meta_value', 'TEXT', null, '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176655625592', '15176655634492', 'PRIMARY', null, null);
-- -|END- METADATA table property end



-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15176655557559, '15176655636261', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15176655557559, '15176655638786', 'aukey_id', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176655638786', '15176655634289', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end



-- -|START- APP_PLAY_STORE table property started
INSERT INTO tbl_column_property VALUES (15176655569633, '15176655639595', 'aplstor_id', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
-- -|END- APP_PLAY_STORE table property end
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
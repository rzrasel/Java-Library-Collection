### SQL Generated Query
```sql_query
DROP TABLE IF EXISTS tbtmp_app_key_store;
CREATE TABLE IF NOT EXISTS tbtmp_app_key_store
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    akstor_aukey_id                 BIGINT(20)        NOT NULL,
    CONSTRAINT                      pk_appke_akstor_aukey_id PRIMARY KEY (akstor_aukey_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_app_project;
CREATE TABLE IF NOT EXISTS tbtmp_app_project
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    apjt_aproj_name                 VARCHAR(255)      NOT NULL,
    apjt_aproj_details              TEXT              NOT NULL,
    apjt_aproj_type                 VARCHAR(255)      NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_country;
CREATE TABLE IF NOT EXISTS tbtmp_country
(
    cntry_ctry_id                   BIGINT(20)        NOT NULL,
    cntry_ctry_name                 VARCHAR(255)      NOT NULL,
    cntry_ctry_iso2                 VARCHAR(5)        NOT NULL,
    cntry_ctry_iso3                 VARCHAR(5)        NOT NULL,
    cntry_ctry_numeric_code         VARCHAR(10)       NOT NULL,
    cntry_ctry_continent            VARCHAR(255)      NOT NULL,
    cntry_ctry_create_date          DATETIME          NOT NULL,
    cntry_ctry_modify_date          DATETIME          NOT NULL,
    CONSTRAINT                      uk_count_cntry_ctry_numeric_code UNIQUE (cntry_ctry_numeric_code),
    CONSTRAINT                      uk_count_cntry_ctry_iso3 UNIQUE (cntry_ctry_iso3),
    CONSTRAINT                      uk_count_cntry_ctry_iso2 UNIQUE (cntry_ctry_iso2),
    CONSTRAINT                      pk_count_cntry_ctry_id PRIMARY KEY (cntry_ctry_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_metadata;
CREATE TABLE IF NOT EXISTS tbtmp_metadata
(
    mta_ref_id                      BIGINT(20)        NULL,
    mta_meta_id                     BIGINT(20)        NOT NULL,
    mta_meta_identity               TEXT              NULL,
    mta_meta_key                    TEXT              NOT NULL,
    mta_meta_value                  TEXT              NULL,
    CONSTRAINT                      pk_metad_mta_meta_id PRIMARY KEY (mta_meta_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_registration;
CREATE TABLE IF NOT EXISTS tbtmp_registration
(
    regi_rgi_id                     BIGINT(20)        NOT NULL,
    regi_rgi_email                  VARCHAR(255)      NOT NULL,
    regi_rgi_password               TEXT              NOT NULL,
    regi_rgi_wifi_ip                VARCHAR(255)      NOT NULL,
    regi_rgi_device_ip              VARCHAR(255)      NOT NULL,
    regi_rgi_traced_ip              VARCHAR(255)      NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_user_device_key;
CREATE TABLE IF NOT EXISTS tbtmp_user_device_key
(
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivkey_udkey_fcm_token         TEXT              NOT NULL,
    udivkey_udkey_build_id          VARCHAR(255)      NOT NULL,
    udivkey_udkey_android_id        VARCHAR(255)      NOT NULL,
    udivkey_udkey_uuid_id           VARCHAR(255)      NULL,
    udivkey_udkey_create_date       DATETIME          NOT NULL,
    udivkey_udkey_modify_date       DATETIME          NOT NULL,
    udivkey_udkey_created_by        BIGINT(20)        NOT NULL,
    udivkey_udkey_modified_by       BIGINT(20)        NOT NULL,
    CONSTRAINT                      pk_userd_udivkey_udkey_id PRIMARY KEY (udivkey_udkey_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_user_profile;
CREATE TABLE IF NOT EXISTS tbtmp_user_profile
(
    usrpro_user_id                  BIGINT(20)        NOT NULL,
    usrpro_first_name               VARCHAR(255)      NOT NULL,
    usrpro_mid_name                 VARCHAR(255)      NULL,
    usrpro_last_name                VARCHAR(255)      NULL,
    usrpro_regi_type                VARCHAR(255)      NULL
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_user_role;
CREATE TABLE IF NOT EXISTS tbtmp_user_role
(
    usrro_role_id                   BIGINT(20)        NOT NULL,
    usrro_role_title                VARCHAR(255)      NOT NULL,
    usrro_role_priority             INT(3)            NOT NULL,
    usrro_role_is_default           BOOLEAN           NOT NULL,
    usrro_role_create_date          DATETIME          NOT NULL,
    usrro_role_modify_date          DATETIME          NOT NULL,
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id),
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_insert
-- Forgot, Change,
-- FCM Table -> FCM token, Build Number, package, version, last ip, last country, last city
-- Login Log ->
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15184338391260', 'app_key_store', 'tbtmp', 'akstor', null);
INSERT INTO tbl_table_property VALUES ('15184338393781', 'app_project', 'tbtmp', 'apjt', null);
INSERT INTO tbl_table_property VALUES ('15184338391765', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15184338397502', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15184338396835', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15184338394091', 'user_device_key', 'tbtmp', 'udivkey', null);
INSERT INTO tbl_table_property VALUES ('15184338404900', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15184338401803', 'user_role', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338406232', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338405808', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338404379', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338408929', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338405836', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338401815', 'ctry_continent', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338402598', 'ctry_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338391765, '15184338413633', 'ctry_modify_date', 'DATETIME', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338408929', '15184338412968', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184338404379', '15184338413480', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184338405836', '15184338418543', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184338406232', '15184338416366', 'PRIMARY', null, null);
-- -|END- COUNTRY table property end



-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338413605', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338411972', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338416317', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338415886', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, 'IP get by other online api');
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338412138', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338426965', 'rgi_traced_ip', 'VARCHAR', '255', '0', null, 'IP traced by php host script');
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338426145', 'rgi_type', 'VARCHAR', '255', '0', null, 'email, fb, gplus');
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338427031', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338421990', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338426881', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338427930', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338422525', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338428296', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338429066', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338432883', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338438870', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338396835, '15184338437675', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338413605', '15184338436944', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184338413605', '15184338435880', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end



-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338433404', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338431461', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338439568', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338437252', 'aproj_type', 'VARCHAR', '255', '0', null, 'APP, GAME ETC');
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338436944', 'aproj_pakg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338449417', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338443929', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338448415', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338444144', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338442952', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338444957', 'aproj_on_published', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338444304', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338443620', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338448397', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338393781, '15184338457539', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338433404', '15184338452856', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end



-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15184338391260, '15184338454141', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15184338391260, '15184338455162', 'aukey_id', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338455162', '15184338451588', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end



-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15184338397502, '15184338452228', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184338397502, '15184338454398', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338397502, '15184338452136', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15184338397502, '15184338451565', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338397502, '15184338459102', 'meta_value', 'TEXT', null, '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338454398', '15184338461767', 'PRIMARY', null, null);
-- -|END- METADATA table property end



-- -|START- USER_DEVICE_KEY table property started
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338461221', 'udkey_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338461936', 'udkey_fcm_token', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338468068', 'udkey_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338468292', 'udkey_android_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338469217', 'udkey_uuid_id', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338467389', 'udkey_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338468041', 'udkey_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338463704', 'udkey_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338394091, '15184338472343', 'udkey_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338461221', '15184338479894', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_KEY table property end



-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15184338401803, '15184338475926', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338401803, '15184338476117', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338401803, '15184338473747', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338401803, '15184338476136', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338401803, '15184338472871', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338401803, '15184338479479', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184338476117', '15184338478366', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184338475926', '15184338475681', 'PRIMARY', null, null);
-- -|END- USER_ROLE table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15184338404900, '15184338489556', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338404900, '15184338488747', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184338404900, '15184338485491', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184338404900, '15184338482516', 'last_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184338404900, '15184338487848', 'regi_type', 'VARCHAR', '255', '1', null, 'email, fb, gplus');

-- DELETE FROM tbl_constraint_property;
-- -|END- USER_PROFILE table property end
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
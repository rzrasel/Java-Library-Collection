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
    CONSTRAINT                      uk_count_cntry_ctry_iso3 UNIQUE (cntry_ctry_iso3),
    CONSTRAINT                      uk_count_cntry_ctry_iso2 UNIQUE (cntry_ctry_iso2),
    CONSTRAINT                      uk_count_cntry_ctry_numeric_code UNIQUE (cntry_ctry_numeric_code),
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
    udivkey_user_id                 BIGINT(20)        NOT NULL,
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivkey_udkey_fcm_token         TEXT              NOT NULL,
    udivkey_udkey_build_id          VARCHAR(255)      NOT NULL,
    udivkey_udkey_android_id        VARCHAR(255)      NOT NULL,
    udivkey_udkey_uuid_id           VARCHAR(255)      NULL,
    udivkey_udkey_app_bundle        VARCHAR(255)      NULL,
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
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title),
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_insert
-- Forgot, Change,
-- FCM Table -> FCM token, Build Number, package, version, last ip, last country, last city
-- Login Log ->crood, snap shoot, tintin (movie)
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15184346985233', 'app_key_store', 'tbtmp', 'akstor', null);
INSERT INTO tbl_table_property VALUES ('15184346985045', 'app_project', 'tbtmp', 'apjt', null);
INSERT INTO tbl_table_property VALUES ('15184346984758', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15184346985705', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15184346989338', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15184346982096', 'user_device_key', 'tbtmp', 'udivkey', null);
INSERT INTO tbl_table_property VALUES ('15184346981384', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15184346991952', 'user_role', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15184346985705, '15184346992612', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184346985705, '15184346994924', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985705, '15184346998484', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15184346985705, '15184346992379', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985705, '15184346997924', 'meta_value', 'TEXT', null, '1', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184346994924', '15184346997039', 'PRIMARY', null, null);
-- -|END- METADATA table property end



-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15184346985233, '15184346998840', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15184346985233, '15184346998841', 'aukey_id', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184346998841', '15184347004654', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end



-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347007692', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347006569', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347007798', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347001985', 'aproj_type', 'VARCHAR', '255', '0', null, 'APP, GAME ETC');
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347001420', 'aproj_pakg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347007743', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347001512', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347007553', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347008062', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347011541', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347016253', 'aproj_on_published', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347019136', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347019300', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347014557', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346985045, '15184347019303', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184347007692', '15184347015102', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end



-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347015938', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347016992', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347012368', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347029455', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347022155', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347024824', 'ctry_continent', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347021304', 'ctry_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346984758, '15184347021339', 'ctry_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184347012368', '15184347022418', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184347022155', '15184347023204', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184347015938', '15184347029065', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184347029455', '15184347027595', 'UNIQUE', null, null);
-- -|END- COUNTRY table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15184346981384, '15184347034655', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346981384, '15184347033579', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346981384, '15184347033830', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184346981384, '15184347038697', 'last_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184346981384, '15184347037187', 'regi_type', 'VARCHAR', '255', '1', null, 'email, fb, gplus');

-- DELETE FROM tbl_constraint_property;
-- -|END- USER_PROFILE table property end



-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347033887', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347031843', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347038794', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347038707', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, 'IP get by other online api');
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347033348', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347046575', 'rgi_traced_ip', 'VARCHAR', '255', '0', null, 'IP traced by php host script');
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347046072', 'rgi_type', 'VARCHAR', '255', '0', null, 'email, fb, gplus');
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347043445', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347047975', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347041753', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347045073', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347046875', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347049978', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347049079', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347047887', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347056377', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346989338, '15184347054667', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184347033887', '15184347057995', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184347033887', '15184347058345', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end



-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15184346991952, '15184347059184', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346991952, '15184347052424', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346991952, '15184347053477', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346991952, '15184347056382', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346991952, '15184347056829', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346991952, '15184347062239', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184347059184', '15184347064979', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184347052424', '15184347061887', 'UNIQUE', null, null);
-- -|END- USER_ROLE table property end



-- -|START- USER_DEVICE_KEY table property started
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347062512', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347062972', 'udkey_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347066637', 'udkey_fcm_token', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347061812', 'udkey_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347067000', 'udkey_android_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347061147', 'udkey_uuid_id', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347069450', 'udkey_app_bundle', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347078851', 'udkey_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347076617', 'udkey_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347078998', 'udkey_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184346982096, '15184347078297', 'udkey_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184347062972', '15184347074808', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_KEY table property end
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
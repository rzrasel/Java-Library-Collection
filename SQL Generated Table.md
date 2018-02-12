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
INSERT INTO tbl_table_property VALUES ('15184362379338', 'app_key_store', 'tbtmp', 'akstor', null);
INSERT INTO tbl_table_property VALUES ('15184362377338', 'app_project', 'tbtmp', 'apjt', null);
INSERT INTO tbl_table_property VALUES ('15184362386325', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15184362382810', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15184362383031', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15184362388228', 'user_device_key', 'tbtmp', 'udivkey', null);
INSERT INTO tbl_table_property VALUES ('15184362386590', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15184362389455', 'user_role', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15184362379338, '15184362389862', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15184362379338, '15184362382702', 'aukey_id', 'BIGINT', '20', '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362382702', '15184362389512', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end



-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362394788', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362399443', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362393355', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362391975', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, 'IP get by other online api');
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362396245', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362392655', 'rgi_traced_ip', 'VARCHAR', '255', '0', null, 'IP traced by php host script');
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362391780', 'rgi_type', 'VARCHAR', '255', '0', null, 'email, fb, gplus');
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362392965', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362391194', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362393541', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362402047', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362405301', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362409595', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362409025', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362409826', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362405075', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362383031, '15184362406876', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362394788', '15184362409130', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184362394788', '15184362401262', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end



-- -|START- USER_DEVICE_KEY table property started
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362402516', 'user_prof_id', 'BIGINT', '20', '1', '1', null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362418583', 'udkey_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362416618', 'udkey_fcm_token', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362414652', 'udkey_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362414659', 'udkey_android_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362412866', 'udkey_uuid_id', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362417501', 'udkey_pkg_bundle', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362418963', 'udkey_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362419654', 'udkey_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362418793', 'udkey_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362388228, '15184362429617', 'udkey_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362418583', '15184362427273', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_KEY table property end



-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15184362389455, '15184362427600', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362389455, '15184362423059', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362389455, '15184362423815', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362389455, '15184362429321', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362389455, '15184362424422', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362389455, '15184362427047', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362423059', '15184362425924', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184362427600', '15184362425665', 'PRIMARY', null, null);
-- -|END- USER_ROLE table property end



-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362435503', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362437061', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362435476', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362434344', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362433133', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362438281', 'ctry_continent', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362435618', 'ctry_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386325, '15184362435421', 'ctry_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362433133', '15184362437954', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184362435503', '15184362437461', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184362434344', '15184362445003', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15184362435476', '15184362441483', 'UNIQUE', null, null);
-- -|END- COUNTRY table property end



-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15184362382810, '15184362446630', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184362382810, '15184362449748', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362382810, '15184362444351', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15184362382810, '15184362448984', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362382810, '15184362449555', 'meta_value', 'TEXT', null, '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362449748', '15184362446624', 'PRIMARY', null, null);
-- -|END- METADATA table property end



-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362442169', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362451393', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362459340', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362458298', 'aproj_type', 'VARCHAR', '255', '0', null, 'APP, GAME ETC');
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362455507', 'aproj_pkg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362454535', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362456717', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362459530', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362456935', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362458742', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362451166', 'aproj_on_published', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362469990', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362461678', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362465690', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362377338, '15184362467781', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15184362442169', '15184362463321', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15184362386590, '15184362466152', 'user_prof_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386590, '15184362469570', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15184362386590, '15184362464925', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184362386590, '15184362462234', 'last_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15184362386590, '15184362462147', 'regi_type', 'VARCHAR', '255', '1', null, 'email, fb, gplus');

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
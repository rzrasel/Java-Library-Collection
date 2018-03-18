### SQL Generated Query
```sql_query
https://github.com/raramuridesign/mysql-country-list
DROP TABLE IF EXISTS apps_countries_detailed;
CREATE TABLE IF NOT EXISTS `apps_countries_detailed` (
	`id` int(5) NOT NULL AUTO_INCREMENT,
	`countryCode` char(2) NOT NULL DEFAULT '',
	`countryName` varchar(45) NOT NULL DEFAULT '',
	`currencyCode` char(3) DEFAULT NULL,
	`fipsCode` char(2) DEFAULT NULL,
	`isoNumeric` char(4) DEFAULT NULL,
	`north` varchar(30) DEFAULT NULL,
	`south` varchar(30) DEFAULT NULL,
	`east` varchar(30) DEFAULT NULL,
	`west` varchar(30) DEFAULT NULL,
	`capital` varchar(30) DEFAULT NULL,
	`continentName` varchar(15) DEFAULT NULL,
	`continent` char(2) DEFAULT NULL,
	`languages` varchar(100) DEFAULT NULL,
	`isoAlpha3` char(3) DEFAULT NULL,
	`geonameId` int(10) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=0;
-- |----|------------------------------------|
word - id, part of speech, spelling
synonym - id, word_id, spelling
definition - id, definition, word_id, example_sentence

language - id, name
word - id, lan_id
translation - word_id_1, word_id_2

-- |----|------------------------------------|
-- |----|------------------------------------|
DROP TABLE IF EXISTS tbtmp_country;
CREATE TABLE IF NOT EXISTS tbtmp_country
(
    cntry_ctry_id                   BIGINT(20)        NOT NULL,
    cntry_ctry_name                 VARCHAR(255)      NOT NULL,
    cntry_ctry_iso2                 VARCHAR(5)        NOT NULL,
    cntry_ctry_iso3                 VARCHAR(5)        NOT NULL,
    cntry_ctry_numeric_code         VARCHAR(10)       NOT NULL,
    cntry_ctry_capital              VARCHAR(255)      NULL,
    cntry_ctry_continent            VARCHAR(255)      NOT NULL,
    cntry_ctry_north                VARCHAR(30)       NOT NULL,
    cntry_ctry_south                VARCHAR(30)       NOT NULL,
    cntry_ctry_east                 VARCHAR(30)       NOT NULL,
    cntry_ctry_west                 VARCHAR(30)       NOT NULL,
    cntry_ctry_create_date          DATETIME          NOT NULL,
    cntry_ctry_modify_date          DATETIME          NOT NULL,
    CONSTRAINT                      uk_count_cntry_ctry_numeric_code UNIQUE (cntry_ctry_numeric_code),
    CONSTRAINT                      pk_count_cntry_ctry_id PRIMARY KEY (cntry_ctry_id),
    CONSTRAINT                      uk_count_cntry_ctry_iso3 UNIQUE (cntry_ctry_iso3),
    CONSTRAINT                      uk_count_cntry_ctry_iso2 UNIQUE (cntry_ctry_iso2)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
-- |----|------------------------------------|
DELETE FROM tbtmp_country;
INSERT INTO tbtmp_country VALUES (id, 'Bangladesh', 'iso2', 'BGD', '050', 'continent', '2018-02-25 12:33:46', '2018-02-25 12:33:46);
INSERT INTO tbtmp_country VALUES (id, 'name', 'iso2', 'iso3', 'ncod', 'continent', 'cdat', 'mdate);
INSERT INTO tbtmp_country VALUES (id, 'name', 'iso2', 'iso3', 'ncod', 'continent', 'cdat', 'mdate);
-- |----|------------------------------------|
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
-- Login Log ->crood, snap shoot, tintin (movie)ulnlog
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15195441306806', 'country', 'tbtmp', 'cntry', null);
INSERT INTO tbl_table_property VALUES ('15195441319233', 'registration', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15195441311295', 'user_login_log', 'tbtmp', 'ulnglog', null);
INSERT INTO tbl_table_property VALUES ('15195441315436', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15195441315095', 'user_role', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15195441315095, '15195441313936', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315095, '15195441315727', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315095, '15195441318106', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315095, '15195441312323', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315095, '15195441311284', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315095, '15195441323673', 'role_modify_date', 'DATETIME', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15195441315727', '15195441328724', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15195441313936', '15195441322139', 'PRIMARY', null, null);
-- -|END- USER_ROLE table property end



-- -|START- USER_LOGIN_LOG table property started
-- -|END- USER_LOGIN_LOG table property end



-- -|START- REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441328549', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441329123', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441329196', 'rgi_password', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441321904', 'rgi_wifi_ip', 'VARCHAR', '255', '0', null, 'IP get by other online api');
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441333906', 'rgi_device_ip', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441333090', 'rgi_traced_ip', 'VARCHAR', '255', '0', null, 'IP traced by php host script');
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441335032', 'rgi_type', 'VARCHAR', '255', '0', null, 'email, fb, gplus');
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441339930', 'rgi_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441335784', 'rgi_tele_device_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441334695', 'rgi_tele_device_serial', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441333077', 'rgi_fcm_id', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441335120', 'rgi_con_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441334202', 'rgi_package', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441349129', 'rgi_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441349512', 'rgi_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441348432', 'rgi_auth_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441319233, '15195441345749', 'rgi_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15195441328549', '15195441344505', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15195441328549', '15195441349100', 'UNIQUE', null, null);
-- -|END- REGISTRATION table property end

-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15195441315436, '15195441359411', 'user_prof_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315436, '15195441359331', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441315436, '15195441357317', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15195441315436, '15195441354644', 'last_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15195441315436, '15195441352769', 'regi_type', 'VARCHAR', '255', '1', null, 'email, fb, gplus');

-- DELETE FROM tbl_constraint_property;
-- -|END- USER_PROFILE table property end

-- -|START- COUNTRY table property started
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441384976', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441382802', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441382350', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441386333', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441396607', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441396416', 'ctry_capital', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441392724', 'ctry_continent', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441394749', 'ctry_north', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441392306', 'ctry_south', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441399030', 'ctry_east', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441398898', 'ctry_west', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441392442', 'ctry_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15195441306806, '15195441398728', 'ctry_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15195441382350', '15195441404189', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15195441396607', '15195441409422', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15195441386333', '15195441404933', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15195441384976', '15195441403412', 'PRIMARY', null, null);
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
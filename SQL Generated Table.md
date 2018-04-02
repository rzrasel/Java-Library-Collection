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
```
```sql_query_insert
-- Forgot, Change,
-- FCM Table -> FCM token, Build Number, package, version, last ip, last country, last city
-- Login Log ->crood, snap shoot, tintin (movie)ulnlog
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15226526266545', 'country', 'tbtmp', 'cntry', null);

-- -|START- COUNTRY table property started
DELETE FROM tbl_column_property;
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526293807', 'ctry_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526296291', 'ctry_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526296815', 'ctry_iso2', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526292415', 'ctry_iso3', 'VARCHAR', '5', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526295160', 'ctry_numeric_code', 'VARCHAR', '10', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526298803', 'ctry_capital', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526303511', 'ctry_continent', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526305241', 'ctry_north', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526301974', 'ctry_south', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526309730', 'ctry_east', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526309099', 'ctry_west', 'VARCHAR', '30', '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526302130', 'ctry_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15226526266545, '15226526302708', 'ctry_modify_date', 'DATETIME', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226526293807', '15226526307171', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226526296815', '15226526304824', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226526292415', '15226526302036', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226526295160', '15226526315811', 'UNIQUE', null, null);
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
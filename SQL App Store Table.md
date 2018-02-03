### SQL App Store Query
```sql_query
DROP TABLE IF EXISTS tbtmp_app_play_store;
CREATE TABLE IF NOT EXISTS tbtmp_app_play_store
(
    apstre_aplstor_id               BIGINT(20)        NOT NULL,
    apstre_aplstor_title            VARCHAR(255)      NOT NULL,
    apstre_aplstor_description      TEXT              NOT NULL,
    apstre_aplstor_slug             VARCHAR(255)      NOT NULL,
    apstre_aplstor_image_url        TEXT              NOT NULL,
    apstre_aplstor_apk_url          TEXT              NOT NULL,
    apstre_aplstor_seo              TEXT              NOT NULL,
    apstre_aplstor_tag              TEXT              NOT NULL,
    apstre_aplstor_is_featured      BOOLEAN           NOT NULL,
    apstre_aplstor_is_own           BOOLEAN           NOT NULL,
    apstre_aplstor_status           BOOLEAN           NOT NULL,
    CONSTRAINT                      pk_apppl_apstre_aplstor_id PRIMARY KEY (apstre_aplstor_id),
    CONSTRAINT                      uk_apppl_apstre_aplstor_slug UNIQUE (apstre_aplstor_slug)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15176828299788', 'app_play_store', 'tbtmp', 'apstre', null);

DELETE FROM tbl_column_property;
-- -|START- APP_PLAY_STORE table property started
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828293660', 'aplstor_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828294724', 'aplstor_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828296395', 'aplstor_description', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828292508', 'aplstor_slug', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828299939', 'aplstor_image_url', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828291488', 'aplstor_apk_url', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828293404', 'aplstor_seo', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828296124', 'aplstor_tag', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828301373', 'aplstor_is_featured', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828307212', 'aplstor_is_own', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15176828299788, '15176828308095', 'aplstor_status', 'BOOLEAN', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15176828292508', '15176828309299', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15176828293660', '15176828305665', 'PRIMARY', null, null);
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
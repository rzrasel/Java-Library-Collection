### SQL App Store Query
```sql_query
DROP TABLE IF EXISTS tbtmp_fb_obscene_dirt;
CREATE TABLE IF NOT EXISTS tbtmp_fb_obscene_dirt
(
    fobsd_dirt_id                   BIGINT(20)        NOT NULL,
    fobsd_dirt_title                VARCHAR(255)      NOT NULL,
    fobsd_dirt_story                TEXT              NOT NULL,
    fobsd_dirt_slug                 VARCHAR(255)      NOT NULL,
    fobsd_dirt_image_url            TEXT              NOT NULL,
    fobsd_dirt_counter              BIGINT(20)        NOT NULL,
    CONSTRAINT                      pk_fbobs_fobsd_dirt_id PRIMARY KEY (fobsd_dirt_id),
    CONSTRAINT                      uk_fbobs_fobsd_dirt_slug UNIQUE (fobsd_dirt_slug)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15180375523834', 'fb_obscene_dirt', 'tbtmp', 'fobsd', null);

DELETE FROM tbl_column_property;
-- -|START- FB_OBSCENE_DIRT table property started
INSERT INTO tbl_column_property VALUES (15180375523834, '15180375522278', 'dirt_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15180375523834, '15180375526501', 'dirt_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15180375523834, '15180375521703', 'dirt_story', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15180375523834, '15180375527511', 'dirt_slug', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15180375523834, '15180375526668', 'dirt_image_url', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15180375523834, '15180375523720', 'dirt_counter', 'BIGINT', '20', '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15180375527511', '15180375527508', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15180375522278', '15180375526062', 'PRIMARY', null, null);
-- -|END- FB_OBSCENE_DIRT table property end
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
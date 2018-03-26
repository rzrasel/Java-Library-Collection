### SQL App Store Query
```sql_query
DROP TABLE IF EXISTS tbl_site_page_meta_store;
CREATE TABLE IF NOT EXISTS tbl_site_page_meta_store
(
    spgmatr_pgmstore_id             BIGINT(20)        NOT NULL,
    spgmatr_pgmstore_name           TEXT              NOT NULL,
    spgmatr_pgmstore_details        TEXT              NOT NULL,
    spgmatr_pgmstore_type           VARCHAR(255)      NOT NULL,
    spgmatr_pgmstore_meta_genre     VARCHAR(255)      NOT NULL,
    CONSTRAINT                      pk_sitep_spgmatr_pgmstore_id PRIMARY KEY (spgmatr_pgmstore_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_meta_data
-- META DATA TABLE - STARTED
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15220838936314', 'site_page_meta_store', 'tbl', 'spgmatr', null);

DELETE FROM tbl_column_property;
-- -|START- SITE_PAGE_META_STORE table property started
INSERT INTO tbl_column_property VALUES (15220838936314, '15220838941543', 'pgmstore_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220838936314, '15220838947609', 'pgmstore_name', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220838936314, '15220838944229', 'pgmstore_details', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220838936314, '15220838943340', 'pgmstore_type', 'VARCHAR', '255', '0', '0', 'Meta key or description');
INSERT INTO tbl_column_property VALUES (15220838936314, '15220838945695', 'pgmstore_meta_genre', 'VARCHAR', '255', '0', '0', 'Normal, adult');

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220838941543', '15220838949447', 'PRIMARY', null, null);
-- -|END- SITE_PAGE_META_STORE table property end
-- VIDEO DETAILS TABLE - ENDED
```


```sql_query_table_data
DELETE FROM tbl_site_page_meta_store;
INSERT INTO tbl_site_page_meta_store VALUES (15220846055371, 'github', 'GitHub is where people build software. More than 27 million people use GitHub to discover, fork, and contribute to over 80 million projects.', 'description', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (15220872829574, 'apple', 'Discover the innovative world of Apple and shop everything iPhone, iPad, Apple Watch, Mac, and Apple TV, plus explore accessories, entertainment, and expert device support.', 'description', 'normal');


INSERT INTO tbl_site_page_meta_store VALUES (15220846055245, 'github', 'php-dynamic-sqlite/dynamic_sqlite.php at master · TangChr/php-dynamic-sqlite · GitHub', 'key', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (15220872823543, 'apple', 'SFX9YPYY9PPXCU9KH', 'key', 'normal');


INSERT INTO tbl_site_page_meta_store VALUES (15220846058083, 'github', 'php-dynamic-sqlite/dynamic_sqlite.php at master · TangChr/php-dynamic-sqlite · GitHub', 'title', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (15220872828734, 'apple', 'Apple', 'title', 'normal');

INSERT INTO tbl_site_page_meta_store VALUES (11212122, 'github', 'Details', 'description', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (11212122, 'github', 'Details', 'key', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (11212122, 'github', 'Details', 'title', 'normal');
-- TABLE DATA END
```

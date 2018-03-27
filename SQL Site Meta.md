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
INSERT INTO tbl_site_page_meta_store VALUES (15221331511905, Prothom Alo, Online Latest Bangla News/Article - Sports, Crime, Entertainment, , Business, Politics, Education, Opinion, Lifestyle, Photo, Video, Travel, National, World, description, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331511547, apple, Discover the innovative world of Apple and shop everything iPhone, iPad, Apple Watch, Mac, and Apple TV, plus explore accessories, entertainment, and expert device support., description, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331519443, github, GitHub is where people build software. More than 27 million people use GitHub to discover, fork, and contribute to over 80 million projects., description, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331519102, name, Details, description, normal);

INSERT INTO tbl_site_page_meta_store VALUES (15221331517876, Prothom Alo, Prothom Alo, bangla news, current News, bangla newspaper, bangladesh newspaper, online paper, bangladeshi newspaper, bangla news paper, bangladesh newspapers, newspaper, all bangla news paper, bd news paper, news paper, bangladesh news paper, daily, bangla newspaper, daily news paper, bangladeshi news paper, bangla paper, all bangla newspaper, bangladesh news, daily newspaper, অনলাইন, পত্রিকা, বাংলাদেশ, আজকের পত্রিকা, আন্তর্জাতিক, অর্থনীতি, খেলা, বিনোদন, ফিচার, বিজ্ঞান ও প্রযুক্তি, চলচ্চিত্র, ঢালিউড, বলিউড, হলিউড, বাংলা গান, মঞ্চ, টেলিভিশন, নকশা, রস+আলো, ছুটির দিনে, অধুনা, স্বপ্ন নিয়ে, আনন্দ, অন্য আলো, সাহিত্য, গোল্লাছুট, প্রজন্ম ডট কম, বন্ধুসভা,কম্পিউটার, মোবাইল ফোন, অটোমোবাইল, মহাকাশ, গেমস, মাল্টিমিডিয়া, রাজনীতি, সরকার, অপরাধ, আইন ও বিচার, পরিবেশ, দুর্ঘটনা, সংসদ, রাজধানী, শেয়ার বাজার, বাণিজ্য, পোশাক শিল্প, ক্রিকেট, ফুটবল, লাইভ স্কোর, key, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331519012, apple, SFX9YPYY9PPXCU9KH, key, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331519279, github, php-dynamic-sqlite/dynamic_sqlite.php at master · TangChr/php-dynamic-sqlite · GitHub, key, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331511910, name, Details, key, normal);

INSERT INTO tbl_site_page_meta_store VALUES (15221331513132, Prothom Alo, Prothom Alo | Latest online bangla world news bd | Sports photo video live, title, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331523654, apple, Apple, title, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331527376, github, php-dynamic-sqlite/dynamic_sqlite.php at master · TangChr/php-dynamic-sqlite · GitHub, title, normal);
INSERT INTO tbl_site_page_meta_store VALUES (15221331525053, name, Details, title, normal);

INSERT INTO tbl_site_page_meta_store VALUES (11212122, 'name', 'Details', 'description', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (11212122, 'name', 'Details', 'key', 'normal');
INSERT INTO tbl_site_page_meta_store VALUES (11212122, 'name', 'Details', 'title', 'normal');
-- TABLE DATA END
```

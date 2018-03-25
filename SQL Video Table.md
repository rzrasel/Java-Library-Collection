### SQL App Store Query
```sql_query
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
DROP TABLE IF EXISTS tbtmp_app_key_store;
CREATE TABLE IF NOT EXISTS tbtmp_app_key_store
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    akstor_aukey_id                 BIGINT(20)        NOT NULL,
    akstor_aukey_sha1_key           TEXT              NOT NULL,
    akstor_aukey_status             BOOLEAN           NOT NULL,
    akstor_aukey_remarks            TEXT              NULL,
    akstor_aukey_create_date        DATETIME          NOT NULL,
    akstor_aukey_modify_date        DATETIME          NOT NULL,
    CONSTRAINT                      pk_appke_akstor_aukey_id PRIMARY KEY (akstor_aukey_id),
    CONSTRAINT                      fk_appke_akstor_apjt_aproj_id FOREIGN KEY (akstor_apjt_aproj_id) REFERENCES tbl_table(akstor_apjt_aproj_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_app_project;
CREATE TABLE IF NOT EXISTS tbtmp_app_project
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    apjt_aproj_name                 VARCHAR(255)      NOT NULL,
    apjt_aproj_details              TEXT              NOT NULL,
    apjt_aproj_type                 VARCHAR(255)      NOT NULL,
    apjt_aproj_pkg_bundle           VARCHAR(255)      NOT NULL,
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
DROP TABLE IF EXISTS tbtmp_user_device_key;
CREATE TABLE IF NOT EXISTS tbtmp_user_device_key
(
    user_prof_id                    BIGINT(20)        NULL,
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivkey_udkey_fcm_token         TEXT              NULL,
    udivkey_udkey_build_id          VARCHAR(255)      NOT NULL,
    udivkey_udkey_android_id        VARCHAR(255)      NOT NULL,
    udivkey_udkey_uuid_id           VARCHAR(255)      NULL,
    udivkey_udkey_app_version       VARCHAR(255)      NOT NULL,
    udivkey_udkey_create_date       DATETIME          NOT NULL,
    udivkey_udkey_modify_date       DATETIME          NOT NULL,
    udivkey_udkey_created_by        BIGINT(20)        NULL,
    udivkey_udkey_modified_by       BIGINT(20)        NULL,
    CONSTRAINT                      pk_userd_udivkey_udkey_id PRIMARY KEY (udivkey_udkey_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_user_device_location;
CREATE TABLE IF NOT EXISTS tbtmp_user_device_location
(
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivloc_loc_id                  BIGINT(20)        NOT NULL,
    udivloc_loc_global_ip           TEXT              NOT NULL,
    udivloc_loc_hardware_ip         TEXT              NOT NULL,
    udivloc_loc_net_lat             TEXT              NOT NULL,
    udivloc_loc_net_lon             TEXT              NOT NULL,
    udivloc_loc_hardware_lat        TEXT              NULL,
    udivloc_loc_hardware_lon        TEXT              NULL,
    udivloc_loc_net_country         TEXT              NOT NULL,
    udivloc_loc_type                TEXT              NOT NULL,
    udivloc_loc_create_date         DATETIME          NOT NULL,
    CONSTRAINT                      pk_userd_udivloc_loc_id PRIMARY KEY (udivloc_loc_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_video_details;
CREATE TABLE IF NOT EXISTS tbtmp_video_details
(
    vidt_video_id                   BIGINT(20)        NOT NULL,
    vidt_video_title                VARCHAR(255)      NOT NULL,
    vidt_video_details              TEXT(255)         NOT NULL,
    vidt_video_store_type           VARCHAR(255)      NOT NULL,
    vidt_video_type                 VARCHAR(255)      NOT NULL,
    vidt_video_path                 TEXT              NOT NULL,
    vidt_video_images               TEXT              NOT NULL,
    vidt_video_can_access           TEXT              NOT NULL,
    vidt_video_language             TEXT              NOT NULL,
    vidt_video_is_default           BOOLEAN           NOT NULL,
    vidt_video_genre                TEXT              NOT NULL,
    vidt_video_status               TEXT              NOT NULL,
    vidt_video_view_counter         BIGINT(20)        NOT NULL,
    vidt_video_force_stop           BOOLEAN           NOT NULL,
    CONSTRAINT                      pk_video_vidt_video_id PRIMARY KEY (vidt_video_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_meta_data
-- META DATA TABLE - STARTED
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15220020483503', 'metadata', 'tbtmp', 'mta', 'Meta data table');

DELETE FROM tbl_column_property;
-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15220020483503, '15220020496589', 'ref_id', 'BIGINT', '20', '1', '0', 'Reference id. Like table id, column id');
INSERT INTO tbl_column_property VALUES (15220020483503, '15220020499405', 'meta_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220020483503, '15220020495267', 'meta_identity', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220020483503, '15220020491386', 'meta_key', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220020483503, '15220020494442', 'meta_value', 'TEXT', null, '1', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220020499405', '15220020496124', 'PRIMARY', null, null);
-- -|END- METADATA table property end
```
```sql_query_app_authentication
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15220024484580', 'app_key_store', 'tbtmp', 'akstor', 'APP key table. Like SHA1, MD5 etc');
INSERT INTO tbl_table_property VALUES ('15220024486122', 'app_project', 'tbtmp', 'apjt', 'APP project information container table');

DELETE FROM tbl_column_property;
-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024486943', 'aproj_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024496822', 'aproj_name', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024491495', 'aproj_details', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024493634', 'aproj_type', 'VARCHAR', '255', '0', '0', 'APP, game etc');
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024494990', 'aproj_pkg_bundle', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024499674', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024499270', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024492232', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024498445', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024491677', 'aproj_status', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024496664', 'aproj_on_published', 'BOOLEAN', null, '0', '0', 'On published false only default data show');
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024501246', 'aproj_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024507554', 'aproj_modify_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024504842', 'aproj_created_by', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024486122, '15220024504688', 'aproj_modified_by', 'BIGINT', '20', '0', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220024486943', '15220024509742', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end

-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024507593', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024501429', 'aukey_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024508914', 'aukey_sha1_key', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024509076', 'aukey_status', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024506859', 'aukey_remarks', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024517118', 'aukey_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220024484580, '15220024514451', 'aukey_modify_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220024507593', '15220024517343', 'FOREIGN', null, null);
INSERT INTO tbl_constraint_property VALUES ('15220024501429', '15220024519254', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end
```
```sql_query_user_device_key
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15220032131419', 'user_device_key', 'tbtmp', 'udivkey', 'Store device key, like build id, uuid etc');
INSERT INTO tbl_table_property VALUES ('15220032133911', 'user_device_location', 'tbtmp', 'udivloc', 'Store device location, ip, country etc');

DELETE FROM tbl_column_property;
-- -|START- USER_DEVICE_LOCATION table property started
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032137287', 'udivkey_udkey_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032141852', 'loc_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032147035', 'loc_global_ip', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032144382', 'loc_hardware_ip', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032148329', 'loc_net_lat', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032149820', 'loc_net_lon', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032148516', 'loc_hardware_lat', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032144935', 'loc_hardware_lon', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032144377', 'loc_net_country', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032146844', 'loc_type', 'TEXT', null, '0', '0', 'registration, last');
INSERT INTO tbl_column_property VALUES (15220032133911, '15220032148672', 'loc_create_date', 'DATETIME', null, '0', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220032141852', '15220032158034', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_LOCATION table property end

-- -|START- USER_DEVICE_KEY table property started
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032154769', 'user_prof_id', 'BIGINT', '20', '1', '1', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032155871', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032157592', 'udkey_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032154535', 'udkey_fcm_token', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032153781', 'udkey_build_id', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032153739', 'udkey_android_id', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032152784', 'udkey_uuid_id', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032156118', 'udkey_app_version', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032154274', 'udkey_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032167646', 'udkey_modify_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032162707', 'udkey_created_by', 'BIGINT', '20', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220032131419, '15220032163486', 'udkey_modified_by', 'BIGINT', '20', '1', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220032157592', '15220032166520', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_KEY table property end
```
```sql_query_video_details
-- VIDEO DETAILS TABLE - STARTED
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15220045257343', 'video_details', 'tbtmp', 'vidt', null);

DELETE FROM tbl_column_property;
-- -|START- VIDEO_DETAILS table property started
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045255247', 'video_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045268869', 'video_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045265386', 'video_details', 'TEXT', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045268522', 'video_store_type', 'VARCHAR', '255', '0', null, 'Youtube or other');
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045263960', 'video_type', 'VARCHAR', '255', '0', null, 'Movie, cartoon etc');
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045269058', 'video_path', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045266896', 'video_images', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045266874', 'video_can_access', 'TEXT', null, '0', null, 'Package bundle name');
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045268471', 'video_language', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045268529', 'video_is_default', 'BOOLEAN', null, '0', null, 'Default videos are only show at play store publishing time');
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045269045', 'video_genre', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045275123', 'video_status', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045276102', 'video_view_counter', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15220045257343, '15220045274132', 'video_force_stop', 'BOOLEAN', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220045255247', '15220045279192', 'PRIMARY', null, null);
-- -|END- VIDEO_DETAILS table property end
-- VIDEO DETAILS TABLE - ENDED
```


```sql_query_table_data
INSERT INTO tbtmp_app_project VALUES (15213090894877, 'Name', 'Details', 'APP', 'package', 'latest_ver_code', 'latest_var_name', 'lowest_valid_code', 'lowest_valid_name', 1, 0, '2018-03-17 10:30:20', '2018-03-17 10:30:20', 15213087909373, 15213087909373);
INSERT INTO tbtmp_app_key_store VALUES (15213090894877, 15213105164209, 'sha1', 1, '2018-03-17 10:30:20', '2018-03-17 10:30:20');

-- TABLE DATA START
DELETE FROM tbtmp_app_project;
INSERT INTO tbtmp_app_project VALUES (15213167755730, 'Gopal Bhar (Tube)', 'Gopal Bhar (YOUTUBE)', 'APP', 'com.rz.usagesexample', '201803001', '201803.00.1', '201803001', '201803.00.1', 1, 0, '2018-03-18 18:26:00', '2018-03-18 18:26:00', 15213087909373, 15213087909373);

DELETE FROM tbtmp_app_key_store;
INSERT INTO tbtmp_app_key_store VALUES (15213167755730, 15213105164209, 'A8:31:C1:5E:DC:30:53:D9:62:37:35:A5:52:13:4D:AC:BE:7A:D9:9F', 1, 'DEBUG KEY (MAC - SM OFFICE)', '2018-03-18 18:26:00', '2018-03-18 18:26:00');
-- TABLE DATA END
```





(
    user_prof_id                    BIGINT(20)        NULL,
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivkey_udkey_fcm_token         TEXT              NULL,
    udivkey_udkey_build_id          VARCHAR(255)      NOT NULL,
    udivkey_udkey_android_id        VARCHAR(255)      NOT NULL,
    udivkey_udkey_uuid_id           VARCHAR(255)      NULL,
    udivkey_udkey_app_version       VARCHAR(255)      NULL,
    udivkey_udkey_create_date       DATETIME          NOT NULL,
    udivkey_udkey_modify_date       DATETIME          NOT NULL,
    udivkey_udkey_created_by        BIGINT(20)        NULL,
    udivkey_udkey_modified_by       BIGINT(20)        NULL,
    CONSTRAINT                      pk_userd_udivkey_udkey_id PRIMARY KEY (udivkey_udkey_id)
)

[ok] device_primary_id - M4B30Z
[ok] device_secondary_id - 3794e4e8ffe7eede
device_global_net_ip - 43.224.119.52
device_net_country - Bangladesh
device_net_longitude - 90.4251
device_net_latitude - 23.726
device_hardware_ip - 192.168.0.19







MAP-KEY-VALUE: device_primary_id - M4B30Z - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_secondary_id - 3794e4e8ffe7eede - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_first_run_date - 2018-03-19 14:01:14 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_is_first_run - true - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_package_name - com.rz.usagesexample - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_version_name - 201803.00.1 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_auth_key - A8:31:C1:5E:DC:30:53:D9:62:37:35:A5:52:13:4D:AC:BE:7A:D9:9F - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_version_code - 201803001 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_initialization_date - 2018-03-19 14:01:14 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118



class Test {
    private int i;
    public Test() { this.i = 0; }
    public void inc(int x) { this.i += x; }
    public int get() { return this.i; }

    public static void main(String[] args) {
        Test t = new Test();
        IntConsumer c = t::inc;
        c.accept(3);
        System.out.println(t.get());
        // prints 3
    }
}

https://www.youtube.com/results?search_query=bot+in+coc
https://www.youtube.com/watch?v=UvZcYIGs9nY
https://www.youtube.com/watch?v=baMJ0VnQDRw

-- http://www.gifsfor.com/masturbation/page/3/
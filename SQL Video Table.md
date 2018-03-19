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
    CONSTRAINT                      pk_appke_akstor_aukey_id PRIMARY KEY (akstor_aukey_id)
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
    udivloc_loc_net_long            TEXT              NOT NULL,
    udivloc_loc_hardware_lat        TEXT              NULL,
    udivloc_loc_hardware_long       TEXT              NULL,
    udivloc_loc_country             TEXT              NOT NULL,
    udivloc_loc_type                TEXT              NOT NULL,
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
INSERT INTO tbl_table_property VALUES ('15202691663007', 'metadata', 'tbtmp', 'mta', null);

DELETE FROM tbl_column_property;
-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15202691663007, '15202691663594', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15202691663007, '15202691664105', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202691663007, '15202691668104', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15202691663007, '15202691665230', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202691663007, '15202691666671', 'meta_value', 'TEXT', null, '1', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15202691664105', '15202691667547', 'PRIMARY', null, null);
-- -|END- METADATA table property end
-- META DATA TABLE - ENDED
```
```sql_query_app_authentication
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15213935826490', 'app_key_store', 'tbtmp', 'akstor', null);
INSERT INTO tbl_table_property VALUES ('15213935825570', 'app_project', 'tbtmp', 'apjt', null);

DELETE FROM tbl_column_property;
-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935839158', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935837312', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935831166', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935839102', 'aproj_type', 'VARCHAR', '255', '0', null, 'APP, GAME ETC');
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935839286', 'aproj_pkg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935837064', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935831806', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935837664', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935844923', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935843208', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935849555', 'aproj_on_published', 'BOOLEAN', null, '0', null, 'On published false only default data show');
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935842252', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935846264', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935845164', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935825570, '15213935847263', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15213935839158', '15213935847387', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end



-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935852730', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935859472', 'aukey_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935858595', 'aukey_sha1_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935854072', 'aukey_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935857583', 'aukey_remarks', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935858056', 'aukey_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15213935826490, '15213935851595', 'aukey_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15213935859472', '15213935853855', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end
```
```sql_query_user_device_key
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15214504305340', 'user_device_key', 'tbtmp', 'udivkey', null);
INSERT INTO tbl_table_property VALUES ('15214504303928', 'user_device_location', 'tbtmp', 'udivloc', null);

DELETE FROM tbl_column_property;
-- -|START- USER_DEVICE_LOCATION table property started
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504301275', 'udivkey_udkey_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504307829', 'loc_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504308459', 'loc_global_ip', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504306118', 'loc_hardware_ip', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504301697', 'loc_net_lat', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504302029', 'loc_net_long', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504308807', 'loc_hardware_lat', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504314489', 'loc_hardware_long', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504317316', 'loc_country', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504303928, '15214504316506', 'loc_type', 'TEXT', null, '0', '0', 'registration, last');

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15214504307829', '15214504315475', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_LOCATION table property end



-- -|START- USER_DEVICE_KEY table property started
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504319778', 'user_prof_id', 'BIGINT', '20', '1', '1', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504314352', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504315305', 'udkey_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504311694', 'udkey_fcm_token', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504311623', 'udkey_build_id', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504321326', 'udkey_android_id', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504322853', 'udkey_uuid_id', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504325268', 'udkey_app_version', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504327778', 'udkey_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504324832', 'udkey_modify_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504327372', 'udkey_created_by', 'BIGINT', '20', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15214504305340, '15214504328455', 'udkey_modified_by', 'BIGINT', '20', '1', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15214504315305', '15214504328683', 'PRIMARY', null, null);
-- -|END- USER_DEVICE_KEY table property end
```
```sql_query_video_details
-- VIDEO DETAILS TABLE - STARTED
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15202694552543', 'video_details', 'tbtmp', 'vidt', null);

DELETE FROM tbl_column_property;
-- -|START- VIDEO_DETAILS table property started
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694558564', 'video_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694565493', 'video_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694564563', 'video_details', 'TEXT', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694562590', 'video_store_type', 'VARCHAR', '255', '0', null, 'Youtube or other');
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694565721', 'video_type', 'VARCHAR', '255', '0', null, 'Movie, cartoon etc');
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694568826', 'video_path', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694563205', 'video_images', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694564149', 'video_can_access', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694564665', 'video_language', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694562348', 'video_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694569652', 'video_genre', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694577367', 'video_status', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694579646', 'video_view_counter', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202694552543, '15202694571388', 'video_force_stop', 'BOOLEAN', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15202694558564', '15202694575215', 'PRIMARY', null, null);
-- -|END- VIDEO_DETAILS table property end
-- VIDEO DETAILS TABLE - ENDED
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



-- http://www.gifsfor.com/masturbation/page/3/
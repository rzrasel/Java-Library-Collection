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
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivkey_udkey_fcm_token         TEXT              NOT NULL,
    udivkey_udkey_build_id          VARCHAR(255)      NOT NULL,
    udivkey_udkey_android_id        VARCHAR(255)      NOT NULL,
    udivkey_udkey_uuid_id           VARCHAR(255)      NULL,
    udivkey_udkey_pkg_bundle        VARCHAR(255)      NULL,
    udivkey_udkey_create_date       DATETIME          NOT NULL,
    udivkey_udkey_modify_date       DATETIME          NOT NULL,
    udivkey_udkey_created_by        BIGINT(20)        NOT NULL,
    udivkey_udkey_modified_by       BIGINT(20)        NOT NULL,
    CONSTRAINT                      pk_userd_udivkey_udkey_id PRIMARY KEY (udivkey_udkey_id)
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
INSERT INTO tbl_table_property VALUES ('15202700386408', 'app_key_store', 'tbtmp', 'akstor', null);
INSERT INTO tbl_table_property VALUES ('15202700386417', 'app_project', 'tbtmp', 'apjt', null);

DELETE FROM tbl_column_property;
-- -|START- APP_KEY_STORE table property started
INSERT INTO tbl_column_property VALUES (15202700386408, '15202700389690', 'apjt_aproj_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15202700386408, '15202700387637', 'aukey_id', 'BIGINT', '20', '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15202700387637', '15202700397816', 'PRIMARY', null, null);
-- -|END- APP_KEY_STORE table property end

-- -|START- APP_PROJECT table property started
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700398692', 'aproj_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700399750', 'aproj_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700393121', 'aproj_details', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700396903', 'aproj_type', 'VARCHAR', '255', '0', null, 'APP, GAME ETC');
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700397007', 'aproj_pkg_bundle', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700398758', 'aproj_latest_ver_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700391524', 'aproj_latest_ver_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700396410', 'aproj_lowest_valid_code', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700405771', 'aproj_lowest_valid_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700409428', 'aproj_status', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700407336', 'aproj_on_published', 'BOOLEAN', null, '0', null, 'On published false only default data show');
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700403812', 'aproj_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700409713', 'aproj_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700401430', 'aproj_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202700386417, '15202700405269', 'aproj_modified_by', 'BIGINT', '20', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15202700398692', '15202700404149', 'PRIMARY', null, null);
-- -|END- APP_PROJECT table property end
```
```sql_query_user_device_key
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15212308034147', 'user_device_key', 'tbtmp', 'udivkey', null);

DELETE FROM tbl_column_property;
-- -|START- USER_DEVICE_KEY table property started
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308038571', 'user_prof_id', 'BIGINT', '20', '1', '1', null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308035493', 'udkey_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308038165', 'udkey_fcm_token', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308035732', 'udkey_build_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308037628', 'udkey_android_id', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308036457', 'udkey_uuid_id', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308039192', 'udkey_pkg_bundle', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308032332', 'udkey_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308034010', 'udkey_modify_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308046369', 'udkey_created_by', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15212308034147, '15212308044571', 'udkey_modified_by', 'BIGINT', '20', '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15212308035493', '15212308047699', 'PRIMARY', null, null);
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

-- http://www.gifsfor.com/masturbation/page/3/
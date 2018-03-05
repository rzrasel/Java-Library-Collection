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
DROP TABLE IF EXISTS tbtmp_video_details;
CREATE TABLE IF NOT EXISTS tbtmp_video_details
(
    vidt_video_id                   BIGINT(20)        NOT NULL,
    vidt_video_title                VARCHAR(255)      NOT NULL,
    vidt_video_store_type           VARCHAR(255)      NOT NULL,
    vidt_video_type                 VARCHAR(255)      NOT NULL,
    vidt_video_path                 TEXT              NOT NULL,
    vidt_video_images               TEXT              NOT NULL,
    vidt_video_can_access           TEXT              NOT NULL,
    CONSTRAINT                      pk_video_vidt_video_id PRIMARY KEY (vidt_video_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15202434717965', 'metadata', 'tbtmp', 'mta', null);
INSERT INTO tbl_table_property VALUES ('15202434717334', 'video_details', 'tbtmp', 'vidt', null);

DELETE FROM tbl_column_property;
-- -|START- VIDEO_DETAILS table property started
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434712296', 'video_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434713280', 'video_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434718775', 'video_store_type', 'VARCHAR', '255', '0', null, 'Youtube or other');
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434714423', 'video_type', 'VARCHAR', '255', '0', null, 'Movie, cartoon etc');
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434727247', 'video_path', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434723250', 'video_images', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202434717334, '15202434721880', 'video_can_access', 'TEXT', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15202434712296', '15202434723169', 'PRIMARY', null, null);
-- -|END- VIDEO_DETAILS table property end

-- -|START- METADATA table property started
INSERT INTO tbl_column_property VALUES (15202434717965, '15202434726666', 'ref_id', 'BIGINT', '20', '1', null, null);
INSERT INTO tbl_column_property VALUES (15202434717965, '15202434725552', 'meta_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15202434717965, '15202434729812', 'meta_identity', 'TEXT', null, '1', null, null);
INSERT INTO tbl_column_property VALUES (15202434717965, '15202434726055', 'meta_key', 'TEXT', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15202434717965, '15202434725498', 'meta_value', 'TEXT', null, '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15202434725552', '15202434731901', 'PRIMARY', null, null);
-- -|END- METADATA table property end
```
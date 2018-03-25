### SQL Raw Query
```sql_raw_table
DROP TABLE IF EXISTS tbl_metadata;
CREATE TABLE IF NOT EXISTS tbl_metadata
(
    tm_meta_ref_id      BIGINT(20) NULL,
    tm_meta_id          BIGINT(20) NOT NULL,
    tm_meta_identity    TEXT NULL,
    tm_meta_key         TEXT NOT NULL,
    tm_meta_value       TEXT NULL,
    CONSTRAINT pk_tm_tm_meta_id PRIMARY KEY (tm_meta_id)
);

DROP TABLE IF EXISTS tbl_table_property;
CREATE TABLE IF NOT EXISTS tbl_table_property
(
    ttpro_id            BIGINT(20) NOT NULL,
    ttpro_tbl_name      TEXT NOT NULL,
    ttpro_tbl_prefix    TEXT NULL,
    ttpro_col_prefix    TEXT NULL,
    ttpro_tbl_comment   TEXT NULL,
    CONSTRAINT pk_ttp_ttpro_id PRIMARY KEY (ttpro_id),
    CONSTRAINT uk_ttp_ttpro_tbl_name UNIQUE (ttpro_tbl_name)
);

DROP TABLE IF EXISTS tbl_column_property;
CREATE TABLE IF NOT EXISTS tbl_column_property
(
    ttpro_id            BIGINT(20) NOT NULL,
    tcpro_id            BIGINT(20) NOT NULL,
    tcpro_col_name      TEXT NOT NULL,
    tcpro_col_dtype     TEXT NOT NULL,
    tcpro_length        TEXT NULL,
    tcpro_is_null       BOOLEAN NOT NULL,
    tcpro_no_prefix     BOOLEAN NULL,
    tcpro_col_comment   TEXT NULL,
    CONSTRAINT pk_tcp_tcpro_id PRIMARY KEY (tcpro_id),
    CONSTRAINT fk_tcp_ttpro_id FOREIGN KEY (ttpro_id) REFERENCES tbl_table_property(ttpro_id)
    -- CONSTRAINT uk_tcp_ttpro_col_name UNIQUE (tcpro_col_name)
);

DROP TABLE IF EXISTS tbl_constraint_property;
CREATE TABLE IF NOT EXISTS tbl_constraint_property
(
    tcpro_id            BIGINT(20) NOT NULL,
    tconp_id            BIGINT(20) NOT NULL,
    tconp_key           TEXT NOT NULL,
    -- tconp_value         TEXT NOT NULL,
    tconp_ref_tbl       TEXT NULL,
    tconp_con_prefix    TEXT NULL,
    CONSTRAINT pk_tconp_tconp_id PRIMARY KEY (tconp_id),
    CONSTRAINT fk_tconp_tcpro_id FOREIGN KEY (tcpro_id) REFERENCES tbl_column_property(tcpro_id)
);
```


DROP TABLE IF EXISTS tbl_column_property;
CREATE TABLE IF NOT EXISTS tbl_column_property
(
    ttpro_tbl_id                    BIGINT(20)        NOT NULL,
    tcpro_col_id                    BIGINT(20)        NOT NULL,
    tcpro_col_name                  TEXT              NOT NULL,
    tcpro_col_dtype                 TEXT              NOT NULL,
    tcpro_col_length                TEXT              NULL,
    tcpro_col_is_null               BOOLEAN           NOT NULL,
    tcpro_col_no_prefix             BOOLEAN           NULL,
    tcpro_col_comment               TEXT              NULL,
    CONSTRAINT                      pk_colum_tcpro_col_id PRIMARY KEY (tcpro_col_id),
    CONSTRAINT                      fk_colum_tcpro_ttpro_tbl_id FOREIGN KEY (ttpro_tbl_id) REFERENCES tbl_table_property(ttpro_tbl_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbl_constraint_property;
CREATE TABLE IF NOT EXISTS tbl_constraint_property
(
    tcpro_col_id                    BIGINT(20)        NOT NULL,
    tconp_cons_id                   BIGINT(20)        NOT NULL,
    tconp_cons_key                  TEXT              NOT NULL,
    tconp_cons_ref_tbl              TEXT              NULL,
    tconp_cons_prefix               TEXT              NULL,
    CONSTRAINT                      fk_const_tconp_tcpro_col_id FOREIGN KEY (tcpro_col_id) REFERENCES tbl_column_property(tcpro_col_id),
    CONSTRAINT                      pk_const_tconp_cons_id PRIMARY KEY (tconp_cons_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbl_table_property;
CREATE TABLE IF NOT EXISTS tbl_table_property
(
    ttpro_tbl_id                    BIGINT(20)        NOT NULL,
    ttpro_tbl_name                  TEXT              NOT NULL,
    ttpro_tbl_prefix                TEXT              NULL,
    ttpro_tbl_col_prefix            TEXT              NULL,
    ttpro_tbl_comment               TEXT              NULL,
    CONSTRAINT                      uk_table_ttpro_tbl_name UNIQUE (ttpro_tbl_name),
    CONSTRAINT                      pk_table_ttpro_tbl_id PRIMARY KEY (ttpro_tbl_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;


DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15220014424942', 'column_property', 'tbl', 'tcpro', 'Contain column name and column property');
INSERT INTO tbl_table_property VALUES ('15220014424999', 'constraint_property', 'tbl', 'tconp', 'Contain constraint property');
INSERT INTO tbl_table_property VALUES ('15220014424873', 'table_property', 'tbl', 'ttpro', 'Contain table name and table property');

DELETE FROM tbl_column_property;
-- -|START- COLUMN_PROPERTY table property started
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014424546', 'ttpro_tbl_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014438758', 'col_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014434367', 'col_name', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014433595', 'col_dtype', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014435619', 'col_length', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014434448', 'col_is_null', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014432945', 'col_no_prefix', 'BOOLEAN', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424942, '15220014431180', 'col_comment', 'TEXT', null, '1', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220014438758', '15220014437656', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15220014424546', '15220014438884', 'FOREIGN', null, null);
-- -|END- COLUMN_PROPERTY table property end

-- -|START- CONSTRAINT_PROPERTY table property started
INSERT INTO tbl_column_property VALUES (15220014424999, '15220014436148', 'tcpro_col_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15220014424999, '15220014449962', 'cons_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424999, '15220014447788', 'cons_key', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424999, '15220014444552', 'cons_ref_tbl', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424999, '15220014447887', 'cons_prefix', 'TEXT', null, '1', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220014449962', '15220014443173', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15220014436148', '15220014443599', 'FOREIGN', null, null);
-- -|END- CONSTRAINT_PROPERTY table property end

-- -|START- TABLE_PROPERTY table property started
INSERT INTO tbl_column_property VALUES (15220014424873, '15220014443624', 'tbl_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424873, '15220014448830', 'tbl_name', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15220014424873, '15220014445151', 'tbl_prefix', 'TEXT', null, '1', '0', 'Table prefix');
INSERT INTO tbl_column_property VALUES (15220014424873, '15220014442227', 'tbl_col_prefix', 'TEXT', null, '1', '0', 'Column prefix');
INSERT INTO tbl_column_property VALUES (15220014424873, '15220014457272', 'tbl_comment', 'TEXT', null, '1', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15220014443624', '15220014458667', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15220014448830', '15220014454575', 'UNIQUE', null, null);
-- -|END- TABLE_PROPERTY table property end
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
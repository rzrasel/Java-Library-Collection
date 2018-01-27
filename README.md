# Java-SQL-Generator
Java SQL Generator


### GIT Command
```git_command
git init
git remote add origin https://github.com/rzrasel/Java-Library-Collection.git
git remote -v
git fetch && git checkout Java-SQL-Generator
git add .
git commit -m "Add Readme & Git Commit File"
git pull
git push --all
```
### SQL QUERY
```sql_main_table
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
```sql_query
DROP TABLE IF EXISTS tbtmp_registration_temp;
CREATE TABLE IF NOT EXISTS tbtmp_registration_temp
(
    regi_rgi_id                     BIGINT(20)        NOT NULL,
    regi_rgi_email                  VARCHAR(255)      NOT NULL,
    regi_rgi_type                   VARCHAR(255)      NOT NULL,
    regi_rgi_password               VARCHAR(255)      NOT NULL,
    regi_rgi_date                   DATETIME          NOT NULL,
    regi_rgi_ip                     DATETIME          NOT NULL,
    CONSTRAINT                      pk_regis_regi_rgi_id PRIMARY KEY (regi_rgi_id),
    CONSTRAINT                      uk_regis_regi_rgi_id UNIQUE (regi_rgi_id)
);
DROP TABLE IF EXISTS tbtmp_user_profile;
CREATE TABLE IF NOT EXISTS tbtmp_user_profile
(
    usrpro_user_id                  BIGINT(20)        NOT NULL,
    usrpro_first_name               VARCHAR(255)      NOT NULL,
    usrpro_mid_name                 VARCHAR(255)      NULL,
    usrpro_last_name                VARCHAR(255)      NULL,
    CONSTRAINT                      pk_userp_usrpro_user_id PRIMARY KEY (usrpro_user_id)
);
DROP TABLE IF EXISTS tbtmp_userrole;
CREATE TABLE IF NOT EXISTS tbtmp_userrole
(
    usrro_role_id                   BIGINT(20)        NULL,
    usrro_role_title                VARCHAR(255)      NULL,
    usrro_role_priority             INT(3)            NULL,
    usrro_role_is_default           BOOLEAN           NULL,
    usrro_role_create_date          DATETIME          NULL,
    usrro_role_modify_date          DATETIME          NULL,
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id),
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title)
);
```
```sql_query_insert
-- Forgot, Change,
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15166153195124', 'registration_temp', 'tbtmp', 'regi', null);
INSERT INTO tbl_table_property VALUES ('15166153191485', 'user_profile', 'tbtmp', 'usrpro', null);
INSERT INTO tbl_table_property VALUES ('15166153197107', 'userrole', 'tbtmp', 'usrro', null);

DELETE FROM tbl_column_property;
-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15166153197107, '15166153192862', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153197107, '15166153194554', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153197107, '15166153198784', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153197107, '15166153192680', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153197107, '15166153197013', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153197107, '15166153207736', 'role_modify_date', 'DATETIME', null, '0', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15166153192862', '15166153206291', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15166153194554', '15166153208167', 'UNIQUE', null, null);
-- -|END- USERROLE table property end



-- -|START- REGISTRATION_TEMP table property started
INSERT INTO tbl_column_property VALUES (15166153195124, '15166153204322', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153195124, '15166153201177', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153195124, '15166153202130', 'rgi_type', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153195124, '15166153208447', 'rgi_password', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153195124, '15166153201294', 'rgi_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153195124, '15166153201591', 'rgi_ip', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15166153204322', '15166153203467', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15166153204322', '15166153207512', 'UNIQUE', null, null);
-- Device Id, Fcm Key, Regi Package, Regi Version, Verify Code
-- ip for device, ip for visiting
-- -|END- REGISTRATION_TEMP table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15166153191485, '15166153209613', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153191485, '15166153204664', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166153191485, '15166153204432', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15166153191485, '15166153209664', 'last_name', 'VARCHAR', '255', '1', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15166153209613', '15166153202688', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end
```
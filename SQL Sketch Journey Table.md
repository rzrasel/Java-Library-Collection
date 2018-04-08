### SQL Raw Query
Validation email and password
```sql_raw_table
DROP TABLE IF EXISTS tbl_user_profile;
CREATE TABLE IF NOT EXISTS tbl_user_profile
(
    usrro_usrrole_id                BIGINT(20)        NOT NULL,
    usrpro_usrprof_id               BIGINT(20)        NOT NULL,
    usrpro_usrprof_first_name       VARCHAR(255)      NULL,
    usrpro_usrprof_mid_name         VARCHAR(255)      NULL,
    usrpro_usrprof_last_name        VARCHAR(255)      NULL,
    usrpro_usrprof_regi_type        VARCHAR(255)      NOT NULL,
    usrpro_usrprof_date_of_birth    DATETIME          NULL,
    usrpro_usrprof_gender           VARCHAR(255)      NULL,
    usrpro_usrprof_status           BOOLEAN           NOT NULL,
    usrpro_usrprof_create_date      DATETIME          NOT NULL,
    usrpro_usrprof_modify_date      DATETIME          NOT NULL,
    CONSTRAINT                      pk_userp_usrpro_usrprof_id PRIMARY KEY (usrpro_usrprof_id),
    CONSTRAINT                      fk_userp_usrro_usrrole_id FOREIGN KEY (usrro_usrrole_id) REFERENCES tbl_user_role(usrro_usrrole_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbl_user_registration;
CREATE TABLE IF NOT EXISTS tbl_user_registration
(
    usrro_usrrole_id                BIGINT(20)        NOT NULL,
    usrpro_usrprof_id               BIGINT(20)        NOT NULL,
    uregi_regi_id                   BIGINT(20)        NOT NULL,
    uregi_regi_email                VARCHAR(255)      NOT NULL,
    uregi_regi_password             TEXT(0)           NOT NULL,
    uregi_regi_wifi_ip              VARCHAR(255)      NOT NULL,
    uregi_regi_device_ip            VARCHAR(255)      NOT NULL,
    uregi_regi_traced_ip            VARCHAR(255)      NOT NULL,
    uregi_regi_type                 VARCHAR(255)      NOT NULL,
    uregi_regi_build_id             VARCHAR(255)      NOT NULL,
    uregi_regi_tele_device_id       VARCHAR(255)      NOT NULL,
    uregi_regi_tele_device_serial   VARCHAR(255)      NOT NULL,
    uregi_regi_fcm_id               TEXT              NOT NULL,
    uregi_regi_con_code             VARCHAR(255)      NOT NULL,
    uregi_regi_package              VARCHAR(255)      NOT NULL,
    uregi_regi_ver_code             VARCHAR(255)      NOT NULL,
    uregi_regi_ver_name             VARCHAR(255)      NOT NULL,
    uregi_regi_auth_key             TEXT              NOT NULL,
    uregi_regi_date                 DATETIME          NOT NULL,
    CONSTRAINT                      fk_userr_usrro_usrrole_id FOREIGN KEY (usrro_usrrole_id) REFERENCES tbl_user_role(usrro_usrrole_id),
    CONSTRAINT                      fk_userr_usrpro_usrprof_id FOREIGN KEY (usrpro_usrprof_id) REFERENCES tbl_user_profile(usrpro_usrprof_id),
    CONSTRAINT                      pk_userr_uregi_regi_id PRIMARY KEY (uregi_regi_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbl_user_role;
CREATE TABLE IF NOT EXISTS tbl_user_role
(
    usrro_usrrole_id                BIGINT(20)        NOT NULL,
    usrro_usrrole_title             VARCHAR(255)      NOT NULL,
    usrro_usrrole_priority          INT(5)            NOT NULL,
    usrro_usrrole_status            BOOLEAN           NOT NULL,
    usrro_usrrole_create_date       DATETIME          NOT NULL,
    usrro_usrrole_modify_date       DATETIME          NOT NULL,
    CONSTRAINT                      pk_userr_usrro_usrrole_id PRIMARY KEY (usrro_usrrole_id),
    CONSTRAINT                      uk_userr_usrro_usrrole_title UNIQUE (usrro_usrrole_title)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;









DROP TABLE IF EXISTS sktjour_user_login_log;
CREATE TABLE IF NOT EXISTS sktjour_user_login_log
(
    usrpro_usrprof_id               BIGINT(20)        NOT NULL,
    usrlolog_usrlog_id              BIGINT(20)        NOT NULL,
    usrlolog_usrlog_auth_token      TEXT              NOT NULL,
    usrlolog_usrlog_net_ip          VARCHAR(255)      NOT NULL,
    usrlolog_usrlog_app_package_bundle TEXT              NULL,
    usrlolog_usrlog_app_version_name VARCHAR(255)      NULL,
    usrlolog_usrlog_date            DATETIME          NOT NULL,
    CONSTRAINT                      fk_userl_usrpro_usrprof_id FOREIGN KEY (usrpro_usrprof_id) REFERENCES sktjour_user_profile(usrpro_usrprof_id),
    CONSTRAINT                      pk_userl_usrlolog_usrlog_id PRIMARY KEY (usrlolog_usrlog_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS sktjour_user_profile;
CREATE TABLE IF NOT EXISTS sktjour_user_profile
(
    usrpro_usrprof_id               BIGINT(20)        NOT NULL,
    usrpro_usrprof_full_name        TEXT              NOT NULL,
    usrpro_usrprof_email            VARCHAR(255)      NOT NULL,
    usrpro_usrprof_password         TEXT              NOT NULL,
    usrpro_usrprof_regi_net_ip      VARCHAR(255)      NOT NULL,
    usrpro_usrprof_regi_type        VARCHAR(255)      NOT NULL,
    usrpro_usrprof_regi_date        DATETIME          NOT NULL,
    usrpro_usrprof_app_package_bundle TEXT              NOT NULL,
    usrpro_usrprof_app_version_name VARCHAR(255)      NOT NULL,
    usrpro_usrprof_status           BOOLEAN           NOT NULL,
    usrpro_usrprof_create_date      DATETIME          NOT NULL,
    usrpro_usrprof_modify_date      DATETIME          NOT NULL,
    CONSTRAINT                      uk_userp_usrpro_usrprof_id UNIQUE (usrpro_usrprof_id),
    CONSTRAINT                      pk_userp_usrpro_usrprof_id PRIMARY KEY (usrpro_usrprof_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS sktjour_user_score;
CREATE TABLE IF NOT EXISTS sktjour_user_score
(
    usrpro_usrprof_id               BIGINT(20)        NOT NULL,
    usrscr_usrscor_id               BIGINT(20)        NOT NULL,
    usrscr_usrscor_level            INT(4)            NOT NULL,
    usrscr_usrscor_meta             VARCHAR(255)      NOT NULL,
    usrscr_usrscor_score            INT(5)            NOT NULL,
    usrscr_usrscor_date             DATETIME          NOT NULL,
    CONSTRAINT                      fk_users_usrpro_usrprof_id FOREIGN KEY (usrpro_usrprof_id) REFERENCES sktjour_user_profile(usrpro_usrprof_id),
    CONSTRAINT                      pk_users_usrscr_usrscor_id PRIMARY KEY (usrscr_usrscor_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_clear_table
DELETE FROM tbl_table_property;
DELETE FROM tbl_column_property;
DELETE FROM tbl_constraint_property;
```
```sql_raw_sql
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15232211952372', 'user_login_log', 'sktjour', 'usrlolog', 'User login log table');
INSERT INTO tbl_table_property VALUES ('15232211954889', 'user_profile', 'sktjour', 'usrpro', 'User profile table');
INSERT INTO tbl_table_property VALUES ('15232211958613', 'user_score', 'sktjour', 'usrscr', 'User login log table');

DELETE FROM tbl_column_property;
-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211953540', 'usrprof_id', 'BIGINT', '20', '0', '0', 'User id in user information table');
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211952882', 'usrprof_full_name', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211959304', 'usrprof_email', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211966730', 'usrprof_password', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211968595', 'usrprof_regi_net_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211968865', 'usrprof_regi_type', 'VARCHAR', '255', '0', '0', 'Registration type normal, gmail, facebook etc');
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211965136', 'usrprof_regi_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211967574', 'usrprof_app_package_bundle', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211964467', 'usrprof_app_version_name', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211962342', 'usrprof_status', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211964387', 'usrprof_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211954889, '15232211967374', 'usrprof_modify_date', 'DATETIME', null, '0', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15232211953540', '15232211963894', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15232211953540', '15232211975436', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end



-- -|START- USER_LOGIN_LOG table property started
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211974196', 'usrpro_usrprof_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211976365', 'usrlog_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211974053', 'usrlog_auth_token', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211973908', 'usrlog_net_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211979434', 'usrlog_app_package_bundle', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211975963', 'usrlog_app_version_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15232211952372, '15232211975146', 'usrlog_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15232211974196', '15232211979988', 'FOREIGN', '15232211954889', null);
INSERT INTO tbl_constraint_property VALUES ('15232211976365', '15232211975982', 'PRIMARY', null, null);
-- -|END- USER_LOGIN_LOG table property end



-- -|START- USER_SCORE table property started
INSERT INTO tbl_column_property VALUES (15232211958613, '15232211985331', 'usrpro_usrprof_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15232211958613, '15232211985891', 'usrscor_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211958613, '15232211985674', 'usrscor_level', 'INT', '4', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211958613, '15232211985749', 'usrscor_meta', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211958613, '15232211985644', 'usrscor_score', 'INT', '5', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232211958613, '15232211987015', 'usrscor_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15232211985331', '15232211981358', 'FOREIGN', '15232211954889', null);
INSERT INTO tbl_constraint_property VALUES ('15232211985891', '15232211989637', 'PRIMARY', null, null);
-- -|END- USER_SCORE table property end
```



### SQL Raw Query
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
```
```sql_raw_sql
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15226985742447', 'user_profile', 'tbl', 'usrpro', 'User profile table');
INSERT INTO tbl_table_property VALUES ('15226985747318', 'user_registration', 'tbl', 'uregi', 'User registration table');
INSERT INTO tbl_table_property VALUES ('15226985746822', 'user_role', 'tbl', 'usrro', 'User role table');

DELETE FROM tbl_column_property;
-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15226985746822, '15226985744212', 'usrrole_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985746822, '15226985748196', 'usrrole_title', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985746822, '15226985758373', 'usrrole_priority', 'INT', '5', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985746822, '15226985753932', 'usrrole_status', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985746822, '15226985758729', 'usrrole_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985746822, '15226985756921', 'usrrole_modify_date', 'DATETIME', null, '0', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226985744212', '15226985751324', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226985748196', '15226985752511', 'UNIQUE', null, null);
-- -|END- USER_ROLE table property end



-- -|START- USER_REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985753029', 'usrro_usrrole_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985753168', 'usrpro_usrprof_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985753885', 'regi_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985766625', 'regi_email', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985764801', 'regi_password', 'TEXT', '0', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985766993', 'regi_wifi_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985763742', 'regi_device_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985764867', 'regi_traced_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985766699', 'regi_type', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985764127', 'regi_build_id', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985768344', 'regi_tele_device_id', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985762574', 'regi_tele_device_serial', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985761887', 'regi_fcm_id', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985771965', 'regi_con_code', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985777266', 'regi_package', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985776286', 'regi_ver_code', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985776098', 'regi_ver_name', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985776012', 'regi_auth_key', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985747318, '15226985771446', 'regi_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226985753029', '15226985778429', 'FOREIGN', '15226923146879', null);
INSERT INTO tbl_constraint_property VALUES ('15226985753885', '15226985774984', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226985753168', '15226985777681', 'FOREIGN', '15226923143693', null);
-- -|END- USER_REGISTRATION table property end



-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985772672', 'usrro_usrrole_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985788624', 'usrprof_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985787208', 'usrprof_first_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985787261', 'usrprof_mid_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985786605', 'usrprof_last_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985781813', 'usrprof_regi_type', 'VARCHAR', '255', '0', '0', 'Registration type normal, gmail, facebook etc');
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985784707', 'usrprof_date_of_birth', 'DATETIME', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985784653', 'usrprof_gender', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985781119', 'usrprof_status', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985786739', 'usrprof_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226985742447, '15226985787072', 'usrprof_modify_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226985788624', '15226985792205', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226985772672', '15226985791691', 'FOREIGN', '15226923146879', null);
-- -|END- USER_PROFILE table property end
```

https://docs.moodle.org/dev/Database_schema_introduction
https://docops.ca.com/ca-identity-manager/12-6-5/EN/configuring/relational-database-management/describe-a-database-in-a-directory-configuration-file/managed-object-descriptions-for-relational-database

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

### SQL Raw Query
```sql_raw_table
DROP TABLE IF EXISTS tbtmp_registration;
CREATE TABLE IF NOT EXISTS tbtmp_registration
(
    regi_rgi_id                     BIGINT(20)        NOT NULL,
    regi_rgi_email                  VARCHAR(255)      NOT NULL,
    regi_rgi_password               TEXT              NOT NULL,
    regi_rgi_wifi_ip                VARCHAR(255)      NOT NULL,
    regi_rgi_device_ip              VARCHAR(255)      NOT NULL,
    regi_rgi_traced_ip              VARCHAR(255)      NOT NULL,
    regi_rgi_type                   VARCHAR(255)      NOT NULL,
    regi_rgi_build_id               VARCHAR(255)      NOT NULL,
    regi_rgi_tele_device_id         VARCHAR(255)      NOT NULL,
    regi_rgi_tele_device_serial     VARCHAR(255)      NOT NULL,
    regi_rgi_fcm_id                 TEXT              NOT NULL,
    regi_rgi_con_code               VARCHAR(255)      NOT NULL,
    regi_rgi_package                VARCHAR(255)      NOT NULL,
    regi_rgi_ver_code               VARCHAR(255)      NOT NULL,
    regi_rgi_ver_name               VARCHAR(255)      NOT NULL,
    regi_rgi_auth_key               TEXT              NOT NULL,
    regi_rgi_date                   DATETIME          NOT NULL,
    CONSTRAINT                      pk_regis_regi_rgi_id PRIMARY KEY (regi_rgi_id),
    CONSTRAINT                      uk_regis_regi_rgi_id UNIQUE (regi_rgi_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_user_login_log;
CREATE TABLE IF NOT EXISTS tbtmp_user_login_log
(
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;,
DROP TABLE IF EXISTS tbtmp_user_profile;,
CREATE TABLE IF NOT EXISTS tbtmp_user_profile,
(
    usrpro_user_prof_id             BIGINT(20)        NOT NULL,
    usrpro_first_name               VARCHAR(255)      NOT NULL,
    usrpro_mid_name                 VARCHAR(255)      NULL,
    usrpro_last_name                VARCHAR(255)      NULL,
    usrpro_regi_type                VARCHAR(255)      NULL
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
DROP TABLE IF EXISTS tbtmp_user_role;
CREATE TABLE IF NOT EXISTS tbtmp_user_role
(
    usrro_role_id                   BIGINT(20)        NOT NULL,
    usrro_role_title                VARCHAR(255)      NOT NULL,
    usrro_role_priority             INT(3)            NOT NULL,
    usrro_role_is_default           BOOLEAN           NOT NULL,
    usrro_role_create_date          DATETIME          NOT NULL,
    usrro_role_modify_date          DATETIME          NOT NULL,
    CONSTRAINT                      uk_userr_usrro_role_title UNIQUE (usrro_role_title),
    CONSTRAINT                      pk_userr_usrro_role_id PRIMARY KEY (usrro_role_id)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
```sql_raw_sql
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15226627042552', 'user_profile', 'tbl', 'usrpro', 'User profile table');
INSERT INTO tbl_table_property VALUES ('15226627053653', 'user_registration', 'tbl', 'uregi', 'User registration table');
INSERT INTO tbl_table_property VALUES ('15226627059880', 'user_role', 'tbl', 'usrro', 'User role table');

DELETE FROM tbl_column_property;
-- -|START- USER_ROLE table property started
INSERT INTO tbl_column_property VALUES (15226627059880, '15226627051155', 'usrrole_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627059880, '15226627059051', 'usrrole_title', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627059880, '15226627058005', 'usrrole_priority', 'INT', '5', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627059880, '15226627057800', 'usrrole_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627059880, '15226627056453', 'usrrole_modify_date', 'DATETIME', null, '0', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226627051155', '15226627056591', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15226627059051', '15226627055869', 'UNIQUE', null, null);
-- -|END- USER_ROLE table property end

-- -|START- USER_REGISTRATION table property started
INSERT INTO tbl_column_property VALUES (15226627053653, '15226627064857', 'usrro_usrrole_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15226627053653, '15226627066834', 'usrpro_usrprof_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15226627053653, '15226627064986', 'regi_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627053653, '15226627062328', 'regi_email', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627053653, '15226627069498', 'regi_password', 'TEXT', '0', '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226627064857', '15226627061155', 'FOREIGN', '15226532039625', null);
INSERT INTO tbl_constraint_property VALUES ('15226627066834', '15226627069691', 'PRIMARY', null, null);
-- -|END- USER_REGISTRATION table property end

-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627065039', 'usrro_usrrole_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627064281', 'usrprof_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627063674', 'usrprof_first_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627077628', 'usrprof_mid_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627071580', 'usrprof_last_name', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627079568', 'usrprof_regi_type', 'VARCHAR', '255', '0', '0', 'Registration type normal, gmail, facebook etc');
INSERT INTO tbl_column_property VALUES (15226627042552, '15226627071725', 'usrprof_date_of_birth', 'DATETIME', null, '1', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15226627065039', '15226627079150', 'FOREIGN', '15226621072626', null);
INSERT INTO tbl_constraint_property VALUES ('15226627064281', '15226627074207', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end
```


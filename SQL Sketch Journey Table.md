### SQL Raw Query
Validation email and password
```sql_raw_table
DROP TABLE IF EXISTS sktjour_user_login_log;
CREATE TABLE IF NOT EXISTS sktjour_user_login_log
(
    usrpro_usrprof_id               BIGINT(20)        NOT NULL,
    usrlolog_usrlog_id              BIGINT(20)        NOT NULL,
    usrlolog_usrlog_auth_token      TEXT              NULL,
    usrlolog_usrlog_net_ip          VARCHAR(255)      NOT NULL,
    usrlolog_usrlog_package         TEXT              NULL,
    usrlolog_usrlog_version         VARCHAR(255)      NULL,
    usrlolog_usrlog_date            DATETIME          NOT NULL,
    CONSTRAINT                      pk_userl_usrlolog_usrlog_id PRIMARY KEY (usrlolog_usrlog_id),
    CONSTRAINT                      fk_userl_usrpro_usrprof_id FOREIGN KEY (usrpro_usrprof_id) REFERENCES sktjour_user_profile(usrpro_usrprof_id)
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
    usrpro_usrprof_valid_token      TEXT              NOT NULL,
    usrpro_usrprof_valid_date       DATETIME          NULL,
    usrpro_usrprof_is_valid         BOOLEAN           NOT NULL,
    usrpro_usrprof_package          TEXT              NOT NULL,
    usrpro_usrprof_version          VARCHAR(255)      NOT NULL,
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
INSERT INTO tbl_table_property VALUES ('15232894458034', 'user_login_log', 'sktjour', 'usrlolog', 'User login log table');
INSERT INTO tbl_table_property VALUES ('15232894457277', 'user_profile', 'sktjour', 'usrpro', 'User profile table');
INSERT INTO tbl_table_property VALUES ('15232894459644', 'user_score', 'sktjour', 'usrscr', 'User login log table');

DELETE FROM tbl_column_property;
-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894459150', 'usrprof_id', 'BIGINT', '20', '0', '0', 'User id in user information table');
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894453634', 'usrprof_full_name', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894454486', 'usrprof_email', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894454824', 'usrprof_password', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894454343', 'usrprof_regi_net_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894452333', 'usrprof_regi_type', 'VARCHAR', '255', '0', '0', 'Registration type normal, gmail, facebook etc');
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894465116', 'usrprof_regi_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894468258', 'usrprof_valid_token', 'TEXT', null, '0', '0', 'Validation token');
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894464457', 'usrprof_valid_date', 'DATETIME', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894466877', 'usrprof_is_valid', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894465224', 'usrprof_package', 'TEXT', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894462529', 'usrprof_version', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894463446', 'usrprof_status', 'BOOLEAN', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894462464', 'usrprof_create_date', 'DATETIME', null, '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894457277, '15232894464846', 'usrprof_modify_date', 'DATETIME', null, '0', '0', null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15232894459150', '15232894461257', 'UNIQUE', null, null);
INSERT INTO tbl_constraint_property VALUES ('15232894459150', '15232894472179', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end



-- -|START- USER_LOGIN_LOG table property started
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894478560', 'usrpro_usrprof_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894478926', 'usrlog_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894476325', 'usrlog_auth_token', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894479796', 'usrlog_net_ip', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894472634', 'usrlog_package', 'TEXT', null, '1', '0', null);
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894473995', 'usrlog_version', 'VARCHAR', '255', '1', '0', null);
INSERT INTO tbl_column_property VALUES (15232894458034, '15232894479103', 'usrlog_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15232894478926', '15232894472407', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15232894478560', '15232894474318', 'FOREIGN', '15232894457277', null);
-- -|END- USER_LOGIN_LOG table property end



-- -|START- USER_SCORE table property started
INSERT INTO tbl_column_property VALUES (15232894459644, '15232894483537', 'usrpro_usrprof_id', 'BIGINT', '20', '0', '1', null);
INSERT INTO tbl_column_property VALUES (15232894459644, '15232894487350', 'usrscor_id', 'BIGINT', '20', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894459644, '15232894481498', 'usrscor_level', 'INT', '4', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894459644, '15232894483676', 'usrscor_meta', 'VARCHAR', '255', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894459644, '15232894488987', 'usrscor_score', 'INT', '5', '0', '0', null);
INSERT INTO tbl_column_property VALUES (15232894459644, '15232894489475', 'usrscor_date', 'DATETIME', null, '0', '0', null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15232894483537', '15232894489884', 'FOREIGN', '15232894457277', null);
INSERT INTO tbl_constraint_property VALUES ('15232894487350', '15232894489649', 'PRIMARY', null, null);
-- -|END- USER_SCORE table property end
```



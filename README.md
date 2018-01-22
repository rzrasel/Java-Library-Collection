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
```sql_query
DROP TABLE IF EXISTS tbl_registration_temp;
CREATE TABLE IF NOT EXISTS tbl_registration_temp
(
);
DROP TABLE IF EXISTS tbl_user_profile;
CREATE TABLE IF NOT EXISTS tbl_user_profile
(
    uspro_user_id                   BIGINT(20)        NOT NULL,
    uspro_first_name                VARCHAR(255)      NOT NULL,
    uspro_mid_name                  VARCHAR(255)      NULL,
    uspro_last_name                 VARCHAR(255)      NULL,
    CONSTRAINT                      pk_userp_uspro_user_id PRIMARY KEY (uspro_user_id)
);
DROP TABLE IF EXISTS tbl_userrole;
CREATE TABLE IF NOT EXISTS tbl_userrole
(
    usrol_role_id                   BIGINT(20)        NULL,
    usrol_role_title                VARCHAR(255)      NULL,
    usrol_role_priority             INT(3)            NULL,
    usrol_role_is_default           BOOLEAN           NULL,
    usrol_role_create_date          DATETIME          NULL,
    usrol_role_modify_date          DATETIME          NULL,
    CONSTRAINT                      pk_userr_usrol_role_id PRIMARY KEY (usrol_role_id),
    CONSTRAINT                      uk_userr_usrol_role_title UNIQUE (usrol_role_title)
);
```
```sql_query_insert
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15166084279248', 'registration_temp', 'fm', '7regtm', null);
INSERT INTO tbl_table_property VALUES ('15166084274113', 'user_profile', 'fm', '7uspro', null);
INSERT INTO tbl_table_property VALUES ('15166084275204', 'userrole', 'fm', '7usrol', null);

DELETE FROM tbl_column_property;
-- -|START- USER_PROFILE table property started
INSERT INTO tbl_column_property VALUES (15166084274113, '15166084279419', 'user_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084274113, '15166084274900', 'first_name', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084274113, '15166084286677', 'mid_name', 'VARCHAR', '255', '1', null, null);
INSERT INTO tbl_column_property VALUES (15166084274113, '15166084282656', 'last_name', 'VARCHAR', '255', '1', null, null);

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15166084279419', '15166084289726', 'PRIMARY', null, null);
-- -|END- USER_PROFILE table property end



-- -|START- REGISTRATION_TEMP table property started
INSERT INTO tbl_column_property VALUES (15166084279248, '15166084289121', 'rgi_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084279248, '15166084283397', 'rgi_email', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084279248, '15166084286979', 'rgi_type', 'VARCHAR', '255', '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15166084289121', '15166084283708', 'PRIMARY', null, null);
-- -|END- REGISTRATION_TEMP table property end



-- -|START- USERROLE table property started
INSERT INTO tbl_column_property VALUES (15166084275204, '15166084287722', 'role_id', 'BIGINT', '20', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084275204, '15166084282021', 'role_title', 'VARCHAR', '255', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084275204, '15166084282157', 'role_priority', 'INT', '3', '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084275204, '15166084288988', 'role_is_default', 'BOOLEAN', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084275204, '15166084288748', 'role_create_date', 'DATETIME', null, '0', null, null);
INSERT INTO tbl_column_property VALUES (15166084275204, '15166084289694', 'role_modify_date', 'DATETIME', null, '0', null, null);

-- DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15166084287722', '15166084287779', 'PRIMARY', null, null);
INSERT INTO tbl_constraint_property VALUES ('15166084282021', '15166084288216', 'UNIQUE', null, null);
-- -|END- USERROLE table property end
```
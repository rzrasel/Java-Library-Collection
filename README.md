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
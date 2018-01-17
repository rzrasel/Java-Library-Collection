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
DROP TABLE IF EXISTS tbl_user_profile;
CREATE TABLE IF NOT EXISTS tbl_user_profile
(
    uspro_user_id           BIGINT(20)        NOT NULL,
    uspro_first_name        VARCHAR(255)      NOT NULL,
    uspro_mid_name          VARCHAR(255)      NULL,
    CONSTRAINT              pk_userp_uspro_user_id PRIMARY KEY (uspro_user_id)
);
```
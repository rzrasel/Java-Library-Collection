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


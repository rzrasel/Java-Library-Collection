# And Cattle shurjoHMS

And Cattle shurjoHMS


### GIT Command
```git_command
git init
git remote add origin https://rzbucket@bitbucket.org/rzbucket/sm-and-cattle-shurjohms.git
git remote -v
git fetch && git checkout master
git add .
git commit -m "Add Readme & Git Commit File"
git pull
git push --all
```

echo time() . "" . rand(1111, 9999);

###Database Table Design
####Language Table
```table_tbl_language
DROP TABLE IF EXISTS tbl_language;
CREATE TABLE IF NOT EXISTS tbl_language
(
    lan_id                          BIGINT(20)        NOT NULL,
    lan_name                        VARCHAR(50)       NOT NULL,
    lan_iso1_code                   CHAR(2)           NOT NULL,
    CONSTRAINT                      pk_tbl_language_lan_id PRIMARY KEY (lan_id),
    CONSTRAINT                      uk_tbl_language_lan_iso1_code UNIQUE (lan_iso1_code)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

####Insert Value In Language Table
```
DELETE FROM tbl_language;
INSERT INTO tbl_language VALUES (15336373323055, 'Bangla', 'bn');
INSERT INTO tbl_language VALUES (15336373779808, 'English', 'en');
```

####Form Label Table
```table_tbl_form_label
DROP TABLE IF EXISTS tbl_form_label;
CREATE TABLE IF NOT EXISTS tbl_form_label
(
    lan_id                          BIGINT(20)        NOT NULL,
    frm_label_id                    BIGINT(20)        NOT NULL,
    frm_label_key                   TEXT              NOT NULL,
    frm_label_value                 TEXT              NULL,
    CONSTRAINT                      pk_tbl_form_label_frm_label_id PRIMARY KEY (frm_label_id),
    --CONSTRAINT                      uk_tbl_form_label_key_value UNIQUE (frm_label_key, frm_label_value)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
```
####Insert Value In Form Label Table
```
SELECT *
FROM tbl_form_label
ORDER BY frm_label_value ASC;

DELETE FROM tbl_form_label;
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897766624, 'hint_text_bolus_id', 'বোলাস আইডি');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897768328, 'hint_text_bolus_id', 'Bolus ID');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897763598, 'hint_text_cow_id', 'গরুর আইডি');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897768117, 'hint_text_cow_id', 'Cow ID');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897761636, 'hint_text_cow_name', 'গরুর নাম');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897775825, 'hint_text_cow_name', 'Cow Name');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897778794, 'hint_text_date_bolused', 'বোলাস লাগানোর তারিখ');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897774155, 'hint_text_date_bolused', 'Date Bolused');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897778193, 'hint_text_password', 'পাসওয়ার্ড');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897775432, 'hint_text_password', 'Password');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897784326, 'hint_text_user_name', 'ব্যবহারকারীর নাম');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897783526, 'hint_text_user_name', 'User Name');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897782852, 'label_age', 'বয়স');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897784923, 'label_age', 'Age');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897788447, 'label_bolused_date', 'বোলাস লাগানোর তারিখ');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897799496, 'label_bolused_date', 'Bolused Date');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897797594, 'label_btn_submit_login', 'লগইন করুন');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897795126, 'label_btn_submit_login', 'Login');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897798300, 'label_cow_id_ear_tag', 'গরুর আইডি/কানে লাগানো ট্যাগ');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897794397, 'label_cow_id_ear_tag', 'Cow Id/Ear Tag');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897805520, 'label_cow_type', 'গরুর ধরন');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897803073, 'label_cow_type', 'Cow Type');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897809882, 'label_drawer_calving_info', 'বাছুর সংক্রান্ত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897804592, 'label_drawer_calving_info', 'Calving Information');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897802205, 'label_drawer_cow_info', 'গাভী/ ষাঁড় সম্পর্কিত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897811772, 'label_drawer_cow_info', 'Cow Info');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897819446, 'label_drawer_dashboard', 'ড্যাশবোর্ড');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897812439, 'label_drawer_dashboard', 'Dashboard');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897819165, 'label_drawer_disease_info', 'রোগ সম্পর্কিত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897816742, 'label_drawer_disease_info', 'Disease Information');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897828623, 'label_drawer_health_and_vaccination_info', 'কৃমিনাশক ও টিকা সম্পর্কিত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897821324, 'label_drawer_health_and_vaccination_info', 'Health and Vaccination Related Information');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897829389, 'label_drawer_insemination', 'বীজ ভরন');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897827137, 'label_drawer_insemination', 'Insemination');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897825907, 'label_drawer_insemination_info', 'বীজ ভরন সংক্রান্ত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897836793, 'label_drawer_insemination_info', 'Insemination Information');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897832353, 'label_drawer_log_out', 'লগ আউট');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897832174, 'label_drawer_log_out', 'Logout');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897838071, 'label_drawer_milk_production_info', 'দুধ উৎপাদন সম্পর্কিত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897832293, 'label_drawer_milk_production_info', 'Milk Production Related Information');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897843621, 'label_drawer_project_info', 'প্রকল্প সম্পর্কিত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897843478, 'label_drawer_project_info', 'Project Information');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897846699, 'label_drawer_registration', 'একটি গাভী/ ষাঁড় নিবন্ধন সিস্টেম');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897841758, 'label_drawer_registration', 'Registration');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897847285, 'label_drawer_reproductive_health_info', 'গাভী/ষাঁড়  প্রজনন স্বাস্থ্য সম্পর্কিত তথ্য');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897852688, 'label_drawer_reproductive_health_info', 'Reproductive health for Cow/Bull');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897854414, 'label_farm_name', 'ফার্মের নাম');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897852621, 'label_farm_name', 'Farm Name');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897859461, 'label_grid_bolus', 'বোলাস');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897856597, 'label_grid_bolus', 'Bolus');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897864410, 'label_grid_bull', 'ষাঁড়');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897869535, 'label_grid_bull', 'Bull');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897868302, 'label_grid_cow', 'গাভী');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897866992, 'label_grid_cow', 'Cow');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897861225, 'label_grid_doctor', 'ডাক্তার');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897877926, 'label_grid_doctor', 'Doctor');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897874244, 'label_grid_farmer', 'খামারি');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897876249, 'label_grid_farmer', 'Farmer');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897878781, 'label_grid_user_manual', 'ব্যবহার বিধি');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897873496, 'label_grid_user_manual', 'User Manual');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897887043, 'label_month', 'মাস');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897887433, 'label_month', 'month');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897885431, 'label_text_intro', 'ডিজিটাল খামার গড়ি \nগবাদি প্রাণীর বীমা করি');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897885990, 'label_text_intro', 'the Cattle Insurance and Well Being Monitoring Platform');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897884881, 'label_tool_bar_title', 'স্বাগতম');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897893787, 'label_tool_bar_title', 'Welcome');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897896304, 'label_year', 'বছর');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897892335, 'label_year', 'Year');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897892047, 'multi_cow_type_holstein_friesian', 'হলস্টিন ফ্রিজিয়ান');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897897387, 'multi_cow_type_holstein_friesian', 'Holstein Friesian');

INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897909282, 'multi_cow_type_jersey', 'জার্সি');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897905376, 'multi_cow_type_jersey', 'Jersey');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897902339, 'multi_cow_type_pabna_breed', 'পাবনাই');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897906844, 'multi_cow_type_pabna_breed', 'Pabna Breed');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897905519, 'multi_cow_type_red_chittagong', 'রেড চিটাগং');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897914594, 'multi_cow_type_red_chittagong', 'Red Chittagong');
INSERT INTO tbl_form_label  VALUES (15336373323055, 15433897914746, 'multi_cow_type_sahiwal', 'শাহিওয়াল');
INSERT INTO tbl_form_label  VALUES (15336373779808, 15433897918734, 'multi_cow_type_sahiwal', 'Sahiwal');






INSERT INTO tbl_form_label  VALUES (15336373323055, 35338121134121, 'label', 'bangla');
INSERT INTO tbl_form_label  VALUES (15336373779808, 35338121131696, 'label', 'english');












```
label_btn_submit_login
<!--https://www.apphp.com/tutorials/index.php?page=multilanguage-database-design-in-mysql-->
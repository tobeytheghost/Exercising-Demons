--Create Workout  Table 

CREATE TABLE chestWorkouts(
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
description VARCHAR(500),
targetMuscles VARCHAR(255),
imageURL VARCHAR(1000));


CREATE TABLE backWorkouts(
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
description VARCHAR(500),
targetMuscles VARCHAR(255),
imageURL VARCHAR(1000));

CREATE TABLE legWorkouts(
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
description VARCHAR(500),
targetMuscles VARCHAR(255),
imageURL VARCHAR(1000));

CREATE TABLE armWorkouts(
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
description VARCHAR(500),
targetMuscles VARCHAR(255),
imageURL VARCHAR(1000));


--List Tables

CREATE TABLE user_list (
listid BIGINT AUTO_INCREMENT PRIMARY KEY,
userId VARCHAR(255) NOT NULL,
id VARCHAR(255) NOT NULL

);

--This table is just to post the names of the list
CREATE TABLE myWorkoutList(
id LONG AUTO_INCREMENT PRIMARY KEY,
listName VARCHAR (255) NOT NULL);


--API TABLE 

CREATE TABLE activity(
id LONG PRIMARY KEY AUTO_INCREMENT,
activityName VARCHAR(255),
caloriesPerHour INTEGER,
duration INTEGER,
totalCalories INTEGER);

--Security Tables

CREATE TABLE sec_user (
  userId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  enabled BIT NOT NULL 
);

CREATE TABLE sec_role(
  roleId   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_uk UNIQUE (userId, roleId);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk1 FOREIGN KEY (userId)
  REFERENCES sec_user (userId);
 
ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk2 FOREIGN KEY (roleId)
  REFERENCES sec_role (roleId);

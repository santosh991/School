
-- Schema Name: schooldb
-- Username: school
-- Password: AllaManO1

-- These tables describe the database of AllamanoBoys

-- Make sure you have created a Postgres user with the above username, password
-- and appropriate permissions. For development environments, you can make the 
-- database user to be a superuser to allow for copying of external files. 

-- Then run the "dbSetup.sh" script in the bin folder of this project.

\c postgres

-- Then execute the following:
DROP DATABASE IF EXISTS schooldb; -- To drop a database you can't be logged into it. Drops if it exists.
CREATE DATABASE schooldb;

-- Connect with the database on the username
\c schooldb school


-- =========================
-- 1.  Suject Management
-- =========================
-- -------------------
-- Table Subject
-- -------------------
CREATE TABLE subject (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    subjectcode text UNIQUE ,
    subjectname text,
    subjectcategory text
);
-- import data from the CSV file for the status table
\COPY subject(uuid,subjectcode,subjectname,subjectcategory) FROM '/tmp/Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE subject OWNER TO school;

-- -------------------
-- Table ClassRoom
-- -------------------
CREATE TABLE classroom (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    roomname text
);
-- import data from the CSV file for the status table
\COPY classroom(uuid,roomname) FROM '/tmp/ClassRoom.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE classroom OWNER TO school;



-- ================================
-- ================================
-- 2. Students Management
-- ================================
-- ================================

-- ----------------
-- Table Students
-- ----------------
CREATE TABLE student(
    id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    firstname text ,
    lastname text ,
    surname text ,
    gender text,
    admno text ,
    year text ,
    dob text,
    bcertno text,
    admissiondate timestamp with time zone 
);

\COPY student(uuid, firstname, lastname,surname,gender,admno,year,dob,bcertno,admissiondate) FROM '/tmp/Students.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student OWNER TO school;


-- -------------------
-- Student_Subject
-- -------------------
CREATE TABLE student_subject (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    subjectUuid text REFERENCES subject(uuid),
    roomnameUuid text REFERENCES classroom(uuid)
);

\COPY student_subject(uuid,studentUuid,subjectUuid,roomnameUuid) FROM '/tmp/Student_Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_subject OWNER TO school;


-- -------------------
-- Student_House
----------------------
CREATE TABLE  student_house (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    housename text
    
);

\COPY student_house(uuid,studentUuid,housename) FROM '/tmp/Student_House.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_subject OWNER TO school;



-- -------------------
-- Table Student_Location
----------------------
CREATE TABLE student_location (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    county text,
    location text,
    sublocation text
);
\COPY student_location(uuid,studentUuid,county,location,sublocation) FROM '/tmp/Student_Location.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_location OWNER TO school;


-- -------------------
-- Table Student_Parent
----------------------
CREATE TABLE student_parent (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    fatherName text ,
    fatherPhone text ,
    fatherOccupation text,
    fatherID text ,
    fatherEmail text,
    motherName text ,
    motherPhone text ,
    motherOccupation text,
    motherEmail text,
    motherID text ,
    relationship text
);
\COPY student_parent(uuid,studentUuid,fatherName,fatherPhone,fatherOccupation,fatherID,fatherEmail,motherName,motherPhone,motherOccupation,motherEmail,motherID,relationship) FROM '/tmp/Student_Parent.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_parent OWNER TO school;




-- -------------------
-- Table Student_Relative
----------------------
CREATE TABLE student_relative (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    relativeName text,
    relativePhone text,
    nationalID text 
);
\COPY student_relative(uuid,studentUuid,relativeName,relativePhone,nationalID) FROM '/tmp/Student_Relative.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_relative OWNER TO school;



-- -------------------
-- Table Student_Sponsor
----------------------
CREATE TABLE student_sponsor (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    Name text,
    Phone text,
    nationalID text,
    Occupation text,
    County text,
    country text
);
\COPY student_sponsor(uuid,studentUuid,Name,Phone,nationalID,Occupation,County,country) FROM '/tmp/Student_Sponsor.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_sponsor OWNER TO school;


-- -------------------
-- Table Student_Activities
----------------------
CREATE TABLE student_activities (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    activity text
);
\COPY student_activities(uuid,studentUuid,activity) FROM '/tmp/Student_Activities.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_activities OWNER TO school;


-- -------------------
-- Table StudentStatus
----------------------
CREATE TABLE studentstatus (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    description text
    
);
\COPY studentstatus(uuid,studentUuid,description) FROM '/tmp/StudentStatus.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE studentstatus OWNER TO school;



-- -------------------
-- Table StudentPrimary
----------------------
CREATE TABLE studentprimary (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    schoolname text,
    index text,
    KcpeYear text ,
    KcpeMarks text
);
\COPY studentprimary(uuid,studentUuid,schoolname,index,KcpeYear,KcpeMarks) FROM '/tmp/StudentPrimary.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE studentprimary OWNER TO school;





-- ======================
-- ======================
-- 3. Exam Management
-- ======================
-- ======================


-- -------------------
-- Table Cat
-- -------------------
 CREATE TABLE  cat (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    catname text
    
   
);

-- import data from the CSV file for the Accounts table
\COPY cat(uuid,catname) FROM '/tmp/Cat.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE cat OWNER TO school;


-- -------------------
-- Table Main
-- -------------------
 CREATE TABLE  main (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    mainname text
    
   
);

-- import data from the CSV file for the Accounts table
\COPY main(uuid,mainname) FROM '/tmp/Main.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE main OWNER TO school;



--------------------
-- Table MainDetails
--------------------

CREATE TABLE  maindetails (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    mainuuid text REFERENCES main(uuid),
    roomnameUuid text REFERENCES classroom(uuid),
    subjectUuid text REFERENCES subject(uuid),
    term text,
    year text,
    outof Integer
   
);

-- import data from the CSV file for the Accounts table
\COPY maindetails(uuid,mainuuid,roomnameUuid,subjectUuid,term,year,outof) FROM '/tmp/MainDetails.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE maindetails OWNER TO school;


--------------------
-- Table CatDetails
--------------------

CREATE TABLE  catdetails (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    catuuid text REFERENCES cat(uuid),
    roomnameUuid text REFERENCES classroom(uuid),
    subjectUuid text REFERENCES subject(uuid),
    term text,
    year text,
    outof Integer
   
);

-- import data from the CSV file for the Accounts table
\COPY catdetails(uuid,catuuid,roomnameUuid,subjectUuid,term,year,outof) FROM '/tmp/CatDetails.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE catdetails OWNER TO school;




-- -------------------
-- Table FinalResult
-- -------------------
 CREATE TABLE  finalresult (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    points float,
    grade text,
    position Integer,
    remarks text
    
   
);

-- import data from the CSV file for the Accounts table
\COPY finalresult(uuid,studentUuid,points,grade,position,remarks) FROM '/tmp/FinalResult.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE finalresult OWNER TO school;



-- -------------------
-- Table FinalMarks
-- -------------------
 CREATE TABLE  finalmarks (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    subjectUuid text REFERENCES subject(uuid),
    marks float,
    grade text
    
   
);

-- import data from the CSV file for the Accounts table
\COPY finalmarks(uuid,studentUuid,subjectUuid,marks,grade) FROM '/tmp/FinalMarks.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE finalmarks OWNER TO school;




-- -------------------
-- Table MainSubjectmark
-- -------------------
CREATE TABLE  mainsubjectmark (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    subjectUuid text REFERENCES subject(uuid),
    marks float, 
    submark float,
    percent float,
    grade text,
    points float, 
    submitdate timestamp with time zone DEFAULT now()
   
   
);

-- import data from the CSV file for the Accounts table
\COPY mainsubjectmark(uuid,studentUuid,subjectUuid,marks,submark,percent,grade,points,submitdate) FROM '/tmp/MainSubjectmark.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE mainsubjectmark OWNER TO school;

-- -------------------
-- Table Mainresult
-- -------------------
 CREATE TABLE  mainresult (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    total float  ,
    points float,
    grade text,
    position Integer,
    remarks text
    
   );

-- import data from the CSV file for the Accounts table
\COPY mainresult(uuid,studentUuid,total,points,grade,position,remarks) FROM '/tmp/Mainresult.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE mainresult OWNER TO school;





-- -------------------
-- Table CatSubjectMark
-- -------------------
CREATE TABLE  catsubjectmark (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    subjectUuid text REFERENCES subject(uuid),
    marks float,
    submark float,
    percent float,
    grade text,
    points float,
    submitdate timestamp with time zone DEFAULT now()
   
   
);

-- import data from the CSV file for the Accounts table
\COPY catsubjectmark(uuid,studentUuid,subjectUuid,marks,submark,percent,grade,points,submitdate) FROM '/tmp/CatSubjectMark.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE catsubjectmark OWNER TO school;

-- -------------------
-- Table Catresult
-- -------------------
 CREATE TABLE  catresult (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    total float,
    points float,
    grade text,
    position Integer,
    remarks text
   
   
   
);

-- import data from the CSV file for the Accounts table
\COPY catresult(uuid,studentUuid,total,points,grade,position,remarks) FROM '/tmp/Catresult.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE catresult OWNER TO school;










-- ==================
-- ==================
-- 4. Staff Management
-- ==================
-- ==================

-- -------------------
-- Table Teacher
-- -------------------
CREATE TABLE teacher (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    firstName text,
    lastName text,
    surname text,
    gender text,
    nhifno text ,
    nssfno text ,
    phone text ,
    dob text,
    nationalID text,
    teacherNumber text,
    county text,
    location text
);
\COPY teacher(uuid,firstName,lastName,surname,gender,nhifno,nssfno,phone,dob,nationalID,teacherNumber,county,location) FROM '/tmp/Teacher.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE teacher OWNER TO school;

-- -------------------
-- Table Teacher_Subject
-- -------------------

CREATE TABLE teacher_subject (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    teacherUuid text REFERENCES Teacher (Uuid),
    sujectUuid text REFERENCES subject (Uuid),
    form text
   
);
\COPY teacher_subject(uuid,teacherUuid,sujectUuid,form) FROM '/tmp/Teacher_Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE teacher_subject OWNER TO school;







--------------------
-- Table Worker
---------------------

CREATE TABLE worker (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    firstName text,
    lastname text,
    surname text,
    gender text,
    nhifno text ,
    nssfno text ,
    dob text,
    phone text,
    nationalID text,
    county text,
    location text,
    sublocation text
);
\COPY worker(uuid,firstName,lastname,surname,gender,nhifno,nssfno,dob,phone,nationalID,county,location,sublocation) FROM '/tmp/Worker.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE worker OWNER TO school;

--------------------
-- Table TeacherPosition
---------------------

CREATE TABLE teacherPosition (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    teacherUuid text REFERENCES teacher(uuid),
    position text,
    salary text
   
);
\COPY teacherPosition(uuid,teacherUuid,position,salary) FROM '/tmp/TeacherPosition.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE teacherPosition OWNER TO school;


--------------------
-- Table NTPosition
---------------------

CREATE TABLE workerPosition (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    workerUuid text REFERENCES worker(uuid),
    position text,
    salary text
   
);
\COPY workerPosition(uuid,workerUuid,position,salary) FROM '/tmp/WorkerPosition.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE workerPosition OWNER TO school;





-- -------------------
-- Table Money
-- -------------------

CREATE TABLE deposit (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    amount float NOT NULL CHECK (amount>=0),
    depositedate timestamp with time zone DEFAULT now()

);
\COPY deposit(uuid,studentUuid,amount,depositedate) FROM '/tmp/Deposit.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE deposit OWNER TO school;

-- -------------------
-- Table  Balance
-- -------------------

CREATE TABLE  withdraw (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    amount float NOT NULL CHECK (amount>=0),
    withdrawdate timestamp with time zone DEFAULT now()

);
\COPY withdraw(uuid,studentUuid,amount,withdrawdate) FROM '/tmp/Withdraw.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE withdraw OWNER TO school;

-- ==================
-- ==================
-- 5. User Management
-- ==================
-- ==================

-- -------------------
-- Table Users
-- -------------------


CREATE TABLE  users (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    userType text,
    username text UNIQUE,
    password text 

);
\COPY users(uuid,userType,username,password) FROM '/tmp/Users.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE users OWNER TO school;

















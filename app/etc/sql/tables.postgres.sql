
-- Schema Name: allamanodb
-- Username: allamano
-- Password: AllaManO1

-- These tables describe the database of AllamanoBoys

-- Make sure you have created a Postgres user with the above username, password
-- and appropriate permissions. For development environments, you can make the 
-- database user to be a superuser to allow for copying of external files. 

-- Then run the "dbSetup.sh" script in the bin folder of this project.

\c postgres

-- Then execute the following:
DROP DATABASE IF EXISTS allamanodb; -- To drop a database you can't be logged into it. Drops if it exists.
CREATE DATABASE allamanodb;

-- Connect with the database on the username
\c allamanodb allamano


-- =========================
-- 1.  Suject Management
-- =========================
-- -------------------
-- Table Subject
-- -------------------
CREATE TABLE subject (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    subjectcode text UNIQUE NOT NULL,
    subjectname text,
    subjectcategory text
);
-- import data from the CSV file for the status table
\COPY subject(uuid,subjectcode,subjectname,subjectcategory) FROM '/tmp/Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE subject OWNER TO allamano;



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
    firstname text NOT NULL,
    lastname text NOT NULL,
    surname text NOT NULL,
    admno text NOT NULL,
    form text ,
    class text ,
    year text ,
    dob text,
    bcertno text,
    admissiondate timestamp with time zone 
);

\COPY student(uuid, firstname, lastname,surname,admno,form,class,year,dob,bcertno,admissiondate) FROM '/tmp/Students.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student OWNER TO allamano;


-- -------------------
-- Student_Subject
-- -------------------
CREATE TABLE student_subject (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    subjectUuid text REFERENCES subject(uuid),
    subjectcode text  REFERENCES subject(subjectcode)
);

\COPY student_subject(uuid,studentUuid,subjectUuid,subjectcode) FROM '/tmp/Student_Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_subject OWNER TO allamano;


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
ALTER TABLE student_subject OWNER TO allamano;



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
ALTER TABLE student_location OWNER TO allamano;


-- -------------------
-- Table Student_Parent
----------------------
CREATE TABLE student_parent (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    fatherName text NOT NULL,
    fatherPhone text NOT NULL,
    fatherOccupation text,
    fatherID Integer UNIQUE NOT NULL,
    fatherEmail text,
    motherName text NOT NULL,
    motherPhone text NOT NULL,
    motherOccupation text,
    motherEmail text,
    motherID Integer UNIQUE NOT NULL,
    relationship text
);
\COPY student_parent(uuid,studentUuid,fatherName,fatherPhone,fatherOccupation,fatherID,fatherEmail,motherName,motherPhone,motherOccupation,motherEmail,motherID,relationship) FROM '/tmp/Student_Parent.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_parent OWNER TO allamano;




-- -------------------
-- Table Student_Relative
----------------------
CREATE TABLE student_relative (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    relativeName text,
    relativePhone text,
    nationalID Integer UNIQUE NOT NULL
);
\COPY student_relative(uuid,studentUuid,relativeName,relativePhone,nationalID) FROM '/tmp/Student_Relative.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_relative OWNER TO allamano;



-- -------------------
-- Table Student_Sponsor
----------------------
CREATE TABLE student_sponsor (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    sponsorName text,
    sponsorPhone text,
    nationalID Integer UNIQUE NOT NULL,
    sponsorOccupation text,
    sponsorCoutry text,
    sponsorCounty text
);
\COPY student_sponsor(uuid,studentUuid,sponsorName,sponsorPhone,nationalID,sponsorOccupation,sponsorCoutry,sponsorCounty) FROM '/tmp/Student_Sponsor.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE student_sponsor OWNER TO allamano;


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
ALTER TABLE student_activities OWNER TO allamano;


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
ALTER TABLE studentstatus OWNER TO allamano;



-- -------------------
-- Table StudentPrimary
----------------------
CREATE TABLE studentprimary (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    schoolname text,
    index text,
    KcpeYear Integer NOT NULL,
    KcpeMarks text
);
\COPY studentprimary(uuid,studentUuid,schoolname,index,KcpeYear,KcpeMarks) FROM '/tmp/StudentPrimary.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE studentprimary OWNER TO allamano;


-- ======================
-- ======================
-- 3. Exam Management
-- ======================
-- ======================
--------------------
-- Table Exam_Type
--------------------

CREATE TABLE  exam_type (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    examtype text,
    term text,
    year text,
    clasz text,
    outof integer,
    description text,
    examno text UNIQUE NOT NULL
   
);

-- import data from the CSV file for the Accounts table
\COPY exam_type(uuid,examtype,term,year,clasz,outof,description,examno) FROM '/tmp/Exam_Type.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE exam_type OWNER TO allamano;

-- -------------------
-- Table Exam_Marks
-- -------------------
CREATE TABLE  exam_marks (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    examtypeUuid text REFERENCES exam_type(uuid),
    subjectUuid text REFERENCES subject(uuid),
    marks Float ,
    submitdate timestamp with time zone
   
   
);

-- import data from the CSV file for the Accounts table
\COPY exam_marks(uuid,studentUuid,examtypeUuid,subjectUuid,marks,submitdate) FROM '/tmp/Exam_Marks.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE exam_marks OWNER TO allamano;

-- -------------------
-- Table Exam_Totals
-- -------------------
 CREATE TABLE  exam_totals (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    subjectUuid text REFERENCES subject(uuid),
    studentUuid text REFERENCES student(uuid),
    total Float  ,
    points Float,
    grade text,
    position integer,
    remarks text,
    submitdate timestamp with time zone
   
   
);

-- import data from the CSV file for the Accounts table
\COPY exam_totals(uuid,subjectUuid,studentUuid,total,points,grade,position,remarks,submitdate) FROM '/tmp/Exam_Results.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE exam_totals OWNER TO allamano;







-- -------------------
-- Table CatMarks
-- -------------------
CREATE TABLE  catmarks (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    examtypeUuid text REFERENCES exam_type(uuid),
    subjectUuid text REFERENCES subject(uuid),
    marks Float,
    submitdate timestamp with time zone
   
   
);

-- import data from the CSV file for the Accounts table
\COPY catmarks(uuid,studentUuid,examtypeUuid,subjectUuid,marks,submitdate) FROM '/tmp/CatMarks.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE catmarks OWNER TO allamano;

-- -------------------
-- Table CatTotals
-- -------------------
 CREATE TABLE  cattotals (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    subjectUuid text REFERENCES subject(uuid),
    studentUuid text REFERENCES student(uuid),
    total Float,
    points Float,
    grade text,
    position integer,
    remarks text, 
    submitdate timestamp with time zone
   
   
);

-- import data from the CSV file for the Accounts table
\COPY cattotals(uuid,subjectUuid,studentUuid,total,points,grade,position,remarks,submitdate) FROM '/tmp/CatResults.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE cattotals OWNER TO allamano;










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
    nhsfno text UNIQUE,
    nssfno text UNIQUE,
    phone text UNIQUE,
    dob text,
    nationalID Integer UNIQUE NOT NULL,
    teacherNumber text UNIQUE,
    county text,
    location text
);
\COPY teacher(uuid,firstName,lastName,surname,nhsfno,nssfno,phone,dob,nationalID,teacherNumber,county,location) FROM '/tmp/Teacher.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE teacher OWNER TO allamano;

-- -------------------
-- Table Teacher_Subject
-- -------------------

CREATE TABLE teacher_subject (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    teacherUuid text REFERENCES Teacher (Uuid),
    sujectUuid text REFERENCES subject (Uuid),
    form text,
    class text
);
\COPY teacher_subject(uuid,teacherUuid,sujectUuid,form,class) FROM '/tmp/Teacher_Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE teacher_subject OWNER TO allamano;







--------------------
-- Table NTStaff
---------------------

CREATE TABLE ntstaff (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    fisrtname text,
    lastname text,
    surname text,
    dob text,
    phone text,
    nationalID Integer UNIQUE NOT NULL,
    county text,
    location text,
    sublocation text
);
\COPY ntstaff(uuid,fisrtname,lastname,surname,dob,phone,nationalID,county,location,sublocation) FROM '/tmp/NTStaff.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ntstaff OWNER TO allamano;

--------------------
-- Table Position
---------------------

CREATE TABLE position (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    teacherUuid text REFERENCES teacher(uuid),
    position text,
    salary text
   
);
\COPY position(uuid,teacherUuid,position,salary) FROM '/tmp/Position.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE position OWNER TO allamano;


--------------------
-- Table NTPosition
---------------------

CREATE TABLE ntposition (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    ntstaffUuid text REFERENCES ntstaff(uuid),
    position text,
    salary text
   
);
\COPY ntposition(uuid,ntstaffUuid,position,salary) FROM '/tmp/NTPosition.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ntposition OWNER TO allamano;





-- -------------------
-- Table Money
-- -------------------

CREATE TABLE money (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    ammount Float NOT NULL CHECK (ammount>=0),
    depositedate timestamp with time zone 

);
\COPY money(uuid,studentUuid,ammount,depositedate) FROM '/tmp/Money.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE money OWNER TO allamano;

-- -------------------
-- Table  Balance
-- -------------------

CREATE TABLE  balance (
    Id SERIAL PRIMARY KEY,
    uuid text UNIQUE NOT NULL,
    studentUuid text REFERENCES student(uuid),
    balance Float NOT NULL CHECK (balance>=0),
    withdrawdate timestamp with time zone 

);
\COPY balance(uuid,studentUuid,balance,withdrawdate) FROM '/tmp/Balance.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE balance OWNER TO allamano;

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
    username text NOT NULL,
    password text NOT NULL

);
\COPY users(uuid,userType,username,password) FROM '/tmp/Users.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE users OWNER TO allamano;

















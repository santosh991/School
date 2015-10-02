
-- Schema Name: schooldb
-- Username: school
-- Password: AllaManO1

-- These tables describe the database a School Management system

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
-- 1.  School Account Management
-- =========================

-- -------------------
-- Table SchoolAccount
-- -------------------


CREATE TABLE  SchoolAccount (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolName text,
    Username text,
    Password text,
    Mobile text, 
    Email text

);
\COPY SchoolAccount(Uuid,SchoolName,Username,Password,Mobile,Email) FROM '/tmp/SchoolAccount.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE SchoolAccount OWNER TO school;




-- =========================
-- 2.  Subject Management
-- =========================
-- -------------------
-- Table Subject
-- -------------------
CREATE TABLE Subject (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    SubjectCode text UNIQUE ,
    SubjectName text,
    SubjectCategory text,
    SysUser text,
    RegDate timestamp with time zone DEFAULT now()
);
-- import data from the CSV file for the status table
\COPY Subject(Uuid,SchoolAccountUuid,SubjectCode,SubjectName,SubjectCategory,SysUser,RegDate) FROM '/tmp/Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Subject OWNER TO school;


-- =========================
-- 3.  ClassRoom Management
-- =========================
-- -------------------
-- Table ClassRoom
-- -------------------
CREATE TABLE ClassRoom (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    Room text,
    RoomName text

);
-- import data from the CSV file for the status table
\COPY ClassRoom(Uuid,SchoolAccountUuid,Room,RoomName) FROM '/tmp/ClassRoom.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ClassRoom OWNER TO school;





-- ================================
-- ================================
-- 4. Students Management
-- ================================
-- ================================

-- ----------------
-- Table Student
-- ----------------
CREATE TABLE Student(
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    FirstName text ,
    LastName text ,
    SurName text ,
    Gender text,
    Admno text ,
    Year text ,
    Dob text,
    BcertNo text,
    SysUser text,
    RegDate timestamp with time zone DEFAULT now()
   
);

\COPY Student(Uuid,SchoolAccountUuid,FirstName, LastName,SurName,Gender,AdmNo,Year,Dob,BcertNo,SysUser,RegDate) FROM '/tmp/Student.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Student OWNER TO school;


-- -------------------
-- StudentSubject
-- -------------------
CREATE TABLE StudentSubject (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    subjectUuid text REFERENCES Subject(Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),
    SysUser text,
    RegDate timestamp with time zone DEFAULT now()

);

\COPY StudentSubject(Uuid,StudentUuid,SubjectUuid,ClassRoomUuid,SysUser,RegDate) FROM '/tmp/StudentSubject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentSubject OWNER TO school;


-- -------------------
-- StudentHouse
----------------------
CREATE TABLE  StudentHouse (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES student(uuid),
    HouseName text
    
);

\COPY StudentHouse(Uuid,StudentUuid,HouseName) FROM '/tmp/StudentHouse.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentHouse OWNER TO school;



-- -------------------
-- Table StudentLocation
----------------------
CREATE TABLE StudentLocation (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    County text,
    Location text,
    SubLocation text
);
\COPY StudentLocation(Uuid,StudentUuid,County,Location,SubLocation) FROM '/tmp/StudentLocation.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentLocation OWNER TO school;


-- -------------------
-- Table StudentParent
----------------------
CREATE TABLE StudentParent (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    FatherName text ,
    FatherPhone text ,
    FatherOccupation text,
    FatherID text ,
    FatherEmail text,
    MotherName text ,
    MotherPhone text ,
    MotherOccupation text,
    MotherEmail text,
    MotherID text ,
    Relationship text
);
\COPY StudentParent(Uuid,StudentUuid,FatherName,FatherPhone,FatherOccupation,FatherID,FatherEmail,MotherName,MotherPhone,MotherOccupation,MotherEmail,MotherID,Relationship) FROM '/tmp/StudentParent.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentParent OWNER TO school;




-- -------------------
-- Table StudentRelative
----------------------
CREATE TABLE StudentRelative (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES student(uuid),
    RelativeName text,
    RelativePhone text,
    NationalID text 
);
\COPY StudentRelative(Uuid,StudentUuid,RelativeName,RelativePhone,NationalID) FROM '/tmp/StudentRelative.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentRelative OWNER TO school;



-- -------------------
-- Table StudentSponsor
----------------------
CREATE TABLE StudentSponsor (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    Name text,
    Phone text,
    NationalID text,
    Occupation text,
    County text,
    Country text
);
\COPY StudentSponsor(Uuid,StudentUuid,Name,Phone,NationalID,Occupation,County,Country) FROM '/tmp/StudentSponsor.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentSponsor OWNER TO school;


-- -------------------
-- Table StudentActivity
----------------------
CREATE TABLE StudentActivity (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    Activity text
);
\COPY StudentActivity(Uuid,StudentUuid,Activity) FROM '/tmp/StudentActivity.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentActivity OWNER TO school;


-- -------------------
-- Table StudentPrimary
----------------------
CREATE TABLE StudentPrimary (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    SchoolName text,
    Index text,
    KcpeYear text ,
    KcpeMark text
);
\COPY StudentPrimary(Uuid,StudentUuid,Schoolname,Index,KcpeYear,KcpeMark) FROM '/tmp/StudentPrimary.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentPrimary OWNER TO school;





-- ======================
-- ======================
-- 5. Exam Management
-- ======================
-- ======================


-- -------------------
-- Table Exam
-- -------------------
 CREATE TABLE  Exam (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    ExamName text 
   
   
);

-- import data from the CSV file for the Accounts table
\COPY Exam(Uuid,ExamName) FROM '/tmp/Exam.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Exam OWNER TO school;


--------------------
-- Table MainExamDetail
--------------------

CREATE TABLE  ExamDetail (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    ExamUuid text REFERENCES Exam(Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid),
    Term text,
    Year text,
    OutOf Integer
   
);

-- import data from the CSV file for the Accounts table
\COPY ExamDetail(Uuid,SchoolAccountUuid,ExamUuid,ClassRoomUuid,SubjectUuid,Term,Year,OutOf) FROM '/tmp/ExamDetail.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ExamDetail OWNER TO school;


-- -------------------
-- Table Perfomance
-- -------------------
 CREATE TABLE  Perfomance (
    Id SERIAL PRIMARY KEY,
    StudentUuid text REFERENCES Student(Uuid),
    ExamDetailUuid text REFERENCES ExamDetail(Uuid),
    Cat1 float,
    Cat2 float,
    Paper1 float,
    Paper2 float,
    Paper3 float,
    SysUser text,
    SubmitDate timestamp with time zone DEFAULT now()

);

-- import data from the CSV file for the Accounts table
\COPY Perfomance(StudentUuid,ExamDetailUuid,Cat1,Cat2,Paper1,Paper2,Paper3,SysUser,SubmitDate) FROM '/tmp/Perfomance.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Perfomance OWNER TO school;



-- -------------------
-- Table Result
-- -------------------
 CREATE TABLE  Result (
    Id SERIAL PRIMARY KEY,
    StudentUuid text REFERENCES student(uuid),
    ExamDetailUuid text REFERENCES ExamDetail(Uuid),
    MPoint float,
    Grade text,
    Remark text,
    Position Integer
    
   
);

-- import data from the CSV file for the Accounts table
\COPY Result(StudentUuid,ExamDetailUuid,MPoint,Grade,Remark,Position) FROM '/tmp/Result.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Result OWNER TO school;




-- -------------------
-- Table CatResult
-- -------------------
 CREATE TABLE  CatResult (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    ExamDetailUuid text REFERENCES ExamDetail(Uuid),
    CatPoint float,
    Grade text,
    Remark text,
    Position Integer
   
   
   
   
);

-- import data from the CSV file for the Accounts table
\COPY CatResult(Uuid,StudentUuid,ExamDetailUuid,CatPoint,Grade,Remark,Position) FROM '/tmp/CatResult.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE CatResult OWNER TO school;




-- ==================
-- ==================
-- .6 Employee Management
-- ==================
-- ==================

--------------------
-- Table Employee
---------------------

CREATE TABLE Employee (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    Category text,
    Position text
);
\COPY Employee(uuid,SchoolAccountUuid,Category,Position) FROM '/tmp/Employee.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Employee OWNER TO school;





-- -------------------
-- Table EmployeeDetail
-- -------------------
CREATE TABLE EmployeeDetail (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    EmployeeUuid text REFERENCES Employee(Uuid),
    EmployeeNo text,
    FirstName text,
    LastName text,
    Surname text,
    Gender text,
    NhifNo text ,
    NssfNo text ,
    Phone text ,
    Dob text,
    NationalID text,
    County text,
    SysUser text,
    RegDate timestamp with time zone DEFAULT now()
);
\COPY EmployeeDetail(Uuid,EmployeeUuid,EmployeeNo,FirstName,LastName,Surname,Gender,NhifNo,NssfNo,Phone,Dob,NationalID,County,SysUser,RegDate) FROM '/tmp/EmployeeDetail.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE EmployeeDetail OWNER TO school;

-- -------------------
-- Table TeacherSubject
-- -------------------

CREATE TABLE TeacherSubject (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    EmployeeUuid text REFERENCES Employee(Uuid),
    SubjectUuid text REFERENCES Subject (Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),
    SysUser text,
    AssignDate timestamp with time zone DEFAULT now()
   
);
\COPY TeacherSubject(Uuid,EmployeeUuid,SubjectUuid,ClassRoomUuid,SysUser,AssignDate) FROM '/tmp/TeacherSubject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE TeacherSubject OWNER TO school;



-- ==================
-- ==================
-- .7 Pocket Money Management
-- ==================
-- ==================

-- -------------------
-- Table Deposit
-- -------------------

CREATE TABLE Deposit (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    Amount float NOT NULL CHECK (Amount>=0),
    SysUser text,
    DepositeDate timestamp with time zone DEFAULT now()

);
\COPY Deposit(Uuid,StudentUuid,Amount,SysUser,DepositeDate) FROM '/tmp/Deposit.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Deposit OWNER TO school;

-- -------------------
-- Table  Withdraw
-- -------------------

CREATE TABLE  Withdraw (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES student(uuid),
    Amount float NOT NULL CHECK (Amount>=0),
    SysUser text,
    WithdrawDate timestamp with time zone DEFAULT now()

);
\COPY Withdraw(Uuid,StudentUuid,Amount,SysUser,WithdrawDate) FROM '/tmp/Withdraw.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Withdraw OWNER TO school;

-- ==================
-- ==================
-- 8. User Management
-- ==================
-- ==================

-- -------------------
-- Table SystemUser
-- -------------------


CREATE TABLE  SystemUser (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    UserType text,
    UserName text UNIQUE,
    Password text 

);
\COPY SystemUser(Uuid,SchoolAccountUuid,UserType,UserName,Password) FROM '/tmp/SystemUser.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE SystemUser OWNER TO school;


















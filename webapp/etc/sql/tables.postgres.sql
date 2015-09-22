
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
CREATE TABLE Subject (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SubjectCode text UNIQUE ,
    SubjectName text,
    SubjectCategory text,
    User text,
    SDate timestamp with time zone DEFAULT now()
);
-- import data from the CSV file for the status table
\COPY Subject(Uuid,SubjectCode,SubjectName,SubjectCategory,User,SDate) FROM '/tmp/Subject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Subject OWNER TO school;

-- -------------------
-- Table ClassRoom
-- -------------------
CREATE TABLE ClassRoom (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    RoomName text

);
-- import data from the CSV file for the status table
\COPY ClassRoom(Uuid,RoomName) FROM '/tmp/ClassRoom.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ClassRoom OWNER TO school;



-- ================================
-- ================================
-- 2. Students Management
-- ================================
-- ================================

-- ----------------
-- Table Student
-- ----------------
CREATE TABLE Student(
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    FirstName text ,
    LastName text ,
    SurName text ,
    Gender text,
    Admno text ,
    Year text ,
    Dob text,
    BcertNo text,
    User text,
    SDate timestamp with time zone DEFAULT now()
   
);

\COPY Student(Uuid, FirstName, LastName,SurName,Gender,AdmNo,Year,Dob,BcerNno,User,SDate) FROM '/tmp/Student.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Student OWNER TO school;


-- -------------------
-- StudentSubject
-- -------------------
CREATE TABLE StudentSubject (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    subjectUuid text REFERENCES Subject(Uuid),
    RoomNameUuid text REFERENCES classroom(Uuid)
    User text,
    SDate timestamp with time zone DEFAULT now()

);

\COPY StudentSubject(Uuid,StudentUuid,SubjectUuid,RoomNameUuid,User,SDate) FROM '/tmp/StudentSubject.csv' WITH DELIMITER AS '|' CSV HEADER
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
\COPY StudentParent(Uuid,StudentUuid,FatherName,FatherPhone,FatherOccupation,FatherID,FatherEmail,
    MotherName,MotherPhone,MotherOccupation,MotherEmail,MotherID,Relationship) FROM '/tmp/StudentParent.csv' WITH DELIMITER AS '|' CSV HEADER
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
-- 3. Exam Management
-- ======================
-- ======================


-- -------------------
-- Table Cat
-- -------------------
 CREATE TABLE  Cat (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    CatName text
   
   
);

-- import data from the CSV file for the Accounts table
\COPY Cat(Uuid,CatName) FROM '/tmp/Cat.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Cat OWNER TO school;


-- -------------------
-- Table MainExam
-- -------------------
 CREATE TABLE  MainExam (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    MainName text
   
   
);

-- import data from the CSV file for the Accounts table
\COPY MainExam(Uuid,MainName) FROM '/tmp/MainExam.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE MainExam OWNER TO school;



--------------------
-- Table MainExamDetail
--------------------

CREATE TABLE  MainExamDetail (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    MainUuid text REFERENCES MainExam(Uuid),
    RoomNameUuid text REFERENCES ClassRoom(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid),
    Term text,
    Year text,
    OutOf Integer
   
);

-- import data from the CSV file for the Accounts table
\COPY maindeMainExamDetailtail(Uuid,MainUuid,RoomNameUuid,SubjectUuid,Term,Year,OutOf) FROM '/tmp/MainExamDetail.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE MainExamDetail OWNER TO school;


--------------------
-- Table CatDetail
--------------------

CREATE TABLE  CatDetail (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    CatUuid text REFERENCES Cat(Uuid),
    RoomNameUuid text REFERENCES ClassRoom(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid),
    Term text,
    Year text,
    OutOf Integer
   
);

-- import data from the CSV file for the Accounts table
\COPY CatDetail(Uuid,CatUuid,RoomNameUuid,SubjectUuid,Term,Year,OutOf) FROM '/tmp/CatDetail.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE CatDetail OWNER TO school;




-- -------------------
-- Table Perfomance
-- -------------------
 CREATE TABLE  Perfomance (
    Id SERIAL PRIMARY KEY,
    StudentUuid text REFERENCES Student(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid),
    RoomNameUuid text REFERENCES ClassRoom(uuid),
    Assignment1 float,
    Assignment2 float,
    Cat1 float,
    Cat2 float,
    Cat3 float,
    Paper1 float,
    Paper2 float,
    Paper3 float,
    User text,
    SDate timestamp with time zone DEFAULT now()

);

-- import data from the CSV file for the Accounts table
\COPY Perfomance(StudentUuid,SubjectUuid,RoomNameUuid,Assignment1,Assignment2,Cat1,Cat2,Cat13,Paper1,Paper2,Paper3,User,SDate) FROM '/tmp/Perfomance.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Perfomance OWNER TO school;



-- -------------------
-- Table Result
-- -------------------
 CREATE TABLE  Result (
    Id SERIAL PRIMARY KEY,
    StudentUuid text REFERENCES student(uuid),
    SubjectUuid text REFERENCES subject(uuid),
    RoomNameUuid text REFERENCES classroom(uuid),
    Mark float,
    MPoint float,
    Grade text,
    Remark text,
    Position Integer
    
   
);

-- import data from the CSV file for the Accounts table
\COPY Result(StudentUuid,SubjectUuid,RoomNameUuid,Mark,MPoint,Grade,Remark,Position) FROM '/tmp/Result.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Result OWNER TO school;




-- -------------------
-- Table Catresult
-- -------------------
 CREATE TABLE  Catresult (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid),
    RoomNameUuid text REFERENCES Classroom(Uuid),
    CatMark float,
    CatPoint float,
    Grade text,
    Remark text,
    Position Integer
   
   
   
   
);

-- import data from the CSV file for the Accounts table
\COPY Catresult(Uuid,StudentUuid,RoomNameUuid,CatMark,CatPoint,Grade,Remark,Position) FROM '/tmp/Catresult.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Catresult OWNER TO school;




-- ==================
-- ==================
-- 4. Employee Management
-- ==================
-- ==================

--------------------
-- Table Employee
---------------------

CREATE TABLE Employee (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    Category text,
    Position text
);
\COPY Employee(uuid,Category,Position) FROM '/tmp/Employee.csv' WITH DELIMITER AS '|' CSV HEADER
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
    User text,
    SDate timestamp with time zone DEFAULT now()
);
\COPY EmployeeDetail(Uuid,EmployeeUuid,EmployeeNo,FirstName,LastName,Surname,Gender,NhifNo,NssfNo,Phone,Dob,NationalID,County,User,SDate) FROM '/tmp/EmployeeDetail.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE EmployeeDetail OWNER TO school;

-- -------------------
-- Table TeacherSubject
-- -------------------

CREATE TABLE TeacherSubject (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    EmployeeUuid text REFERENCES Employee(Uuid),
    SubjectUuid text REFERENCES Subject (Uuid),
    RoomNameUuid text REFERENCES ClassRoom(Uuid)
    User text,
    SDate timestamp with time zone DEFAULT now()
   
);
\COPY TeacherSubject(Uuid,EmployeeUuid,SubjectUuid,RoomNameUuid,User,SDate) FROM '/tmp/TeacherSubject.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE TeacherSubject OWNER TO school;



-- ==================
-- ==================
-- 5. Pocket Money Management
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
    User text,
    DepositeDate timestamp with time zone DEFAULT now()

);
\COPY Deposit(Uuid,StudentUuid,Amount,User,DepositeDate) FROM '/tmp/Deposit.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Deposit OWNER TO school;

-- -------------------
-- Table  Withdraw
-- -------------------

CREATE TABLE  Withdraw (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES student(uuid),
    Amount float NOT NULL CHECK (Amount>=0),
    User text,
    WithdrawDate timestamp with time zone DEFAULT now()

);
\COPY Withdraw(Uuid,StudentUuid,Amount,User,WithdrawDate) FROM '/tmp/Withdraw.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Withdraw OWNER TO school;

-- ==================
-- ==================
-- 6. User Management
-- ==================
-- ==================

-- -------------------
-- Table User
-- -------------------


CREATE TABLE  User (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    UserType text,
    UserName text UNIQUE,
    Password text 

);
\COPY User(Uuid,UserType,UserName,Password) FROM '/tmp/User.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE User OWNER TO school;

















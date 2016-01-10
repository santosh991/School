
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
-- Table Status
-- -------------------


CREATE TABLE  Status (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    Status text
    

);
\COPY Status(Uuid,Status) FROM '/tmp/Status.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Status OWNER TO school;





--------------------
-- Table SchoolAccount
-- -------------------


CREATE TABLE  SchoolAccount (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StatusUuid text REFERENCES Status(Uuid),
    SchoolName text,
    Username text,
    Password text,
    Mobile text, 
    Email text,
    CreationDate timestamp with time zone DEFAULT now()

);
\COPY SchoolAccount(Uuid,StatusUuid,SchoolName,Username,Password,Mobile,Email,CreationDate) FROM '/tmp/SchoolAccount.csv' WITH DELIMITER AS '|' CSV HEADER
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
    RegistrationDate timestamp with time zone DEFAULT now()
);
-- import data from the CSV file for the status table
\COPY Subject(Uuid,SchoolAccountUuid,SubjectCode,SubjectName,SubjectCategory,SysUser,RegistrationDate) FROM '/tmp/Subject.csv' WITH DELIMITER AS '|' CSV HEADER
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
    RoomName text

);
-- import data from the CSV file for the status table
\COPY ClassRoom(Uuid,SchoolAccountUuid,RoomName) FROM '/tmp/ClassRoom.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ClassRoom OWNER TO school;


-- -------------------
-- Table Classes
-- -------------------
CREATE TABLE Classes (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    className text

);
-- import data from the CSV file for the status table
\COPY Classes(Uuid,className) FROM '/tmp/Classes.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Classes OWNER TO school;





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
    StatusUuid text REFERENCES Status(Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),
    AdmNo text ,
    FirstName text ,
    LastName text ,
    SurName text ,
    Gender text,
    DOB text,
    BcertNo text,
    County text,
    SysUser text,
    AdmissionDate timestamp with time zone DEFAULT now()
   
);

\COPY Student(Uuid,SchoolAccountUuid,StatusUuid,ClassRoomUuid,AdmNo,FirstName, LastName,SurName,Gender,DOB,BcertNo,County,SysUser,AdmissionDate) FROM '/tmp/Student.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Student OWNER TO school;


-- -------------------
-- StudentSubClassRoom
-- -------------------
CREATE TABLE StudentSubClassRoom (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),
    SysUser text,
    AllocationDate timestamp with time zone DEFAULT now()

);

\COPY StudentSubClassRoom(Uuid,StudentUuid,SubjectUuid,ClassRoomUuid,SysUser,AllocationDate) FROM '/tmp/StudentSubClassRoom.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentSubClassRoom OWNER TO school;


-- -------------------
-- StudentHouse
----------------------
CREATE TABLE  StudentHouse (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES student(uuid),
    HouseName text,
    SysUser text,
    DateIn timestamp with time zone DEFAULT now()

    
);

\COPY StudentHouse(Uuid,StudentUuid,HouseName,SysUser,DateIn) FROM '/tmp/StudentHouse.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentHouse OWNER TO school;






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
    RelativeName text,
    RelativePhone text


);
\COPY StudentParent(Uuid,StudentUuid,FatherName,FatherPhone,FatherOccupation,FatherID,FatherEmail,MotherName,MotherPhone,MotherOccupation,MotherEmail,MotherID,RelativeName,RelativePhone) FROM '/tmp/StudentParent.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentParent OWNER TO school;






-- -------------------
-- Table StudentSponsor
----------------------
CREATE TABLE StudentSponsor (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    SponsorName text,
    SponsorPhone text,
    SponsorOccupation text,
    SponsorCountry text,
    SponsorCounty text
   
);
\COPY StudentSponsor(Uuid,StudentUuid,SponsorName,SponsorPhone,SponsorOccupation,SponsorCountry,SponsorCounty) FROM '/tmp/StudentSponsor.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentSponsor OWNER TO school;




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
\COPY StudentPrimary(Uuid,StudentUuid,SchoolName,Index,KcpeYear,KcpeMark) FROM '/tmp/StudentPrimary.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentPrimary OWNER TO school;



-- ==================
-- ==================
-- .5 Staff Management
-- ==================
-- ==================

--------------------
-- Table Position
---------------------

CREATE TABLE Position (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    Position text
);
\COPY Position(Uuid,Position) FROM '/tmp/Position.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Position OWNER TO school;



--------------------
-- Table Department
---------------------

CREATE TABLE Department (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    DepartmentName text
   
);
\COPY Department(Uuid,SchoolAccountUuid,DepartmentName) FROM '/tmp/Department.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Department OWNER TO school;



--------------------
-- Table Staff
---------------------

CREATE TABLE Staff (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    Category text,
    PositionUuid text REFERENCES Position(Uuid),
    UserName text,
    Password text
);
\COPY Staff(Uuid,SchoolAccountUuid,Category,PositionUuid,UserName,Password) FROM '/tmp/Staff.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Staff OWNER TO school;





-- -------------------
-- Table StaffDetails
-- -------------------
CREATE TABLE StaffDetails (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StaffUuid text REFERENCES Staff(Uuid),
    EmployeeNo text,
    FirstName text,
    LastName text,
    Surname text,
    Gender text,
    NhifNo text ,
    NssfNo text ,
    Phone text ,
    DOB text,
    NationalID text,
    County text,
    SysUser text,
    RegistrationDate timestamp with time zone DEFAULT now()
);
\COPY StaffDetails(Uuid,StaffUuid,EmployeeNo,FirstName,LastName,Surname,Gender,NhifNo,NssfNo,Phone,Dob,NationalID,County,SysUser,RegistrationDate) FROM '/tmp/StaffDetails.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StaffDetails OWNER TO school;

-- -------------------
-- Table TeacherSubClass
-- -------------------

CREATE TABLE TeacherSubClass (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    TeacherUuid text REFERENCES Staff(Uuid),
    SubjectUuid text REFERENCES Subject (Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),
    SysUser text,
    AllocationDate timestamp with time zone DEFAULT now()
   
);
\COPY TeacherSubClass(Uuid,TeacherUuid,SubjectUuid,ClassRoomUuid,SysUser,AllocationDate) FROM '/tmp/TeacherSubClass.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE TeacherSubClass OWNER TO school;



-- -------------------
-- Table TeacherDepartment
-- -------------------

CREATE TABLE TeacherDepartment (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    TeacherUuid text REFERENCES Staff(Uuid),
    DepartmentUuid text REFERENCES Department (Uuid)
    
   
);
\COPY TeacherDepartment(Uuid,TeacherUuid,DepartmentUuid) FROM '/tmp/TeacherDepartment.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE TeacherDepartment OWNER TO school;




-- -------------------
-- Table Duties
-- -------------------

CREATE TABLE Duties (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    Duty text
        
   
);
\COPY Duties(Uuid,SchoolAccountUuid,Duty) FROM '/tmp/Duties.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Duties OWNER TO school;



-- -------------------
-- Table TeacherDuties
-- -------------------

CREATE TABLE TeacherDuties (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    TeacherUuid text REFERENCES Staff(Uuid),
    DutyUuid text REFERENCES  Duties(Uuid),
    SysUser text,
    AllocationDate timestamp with time zone DEFAULT now()
        
   
);
\COPY TeacherDuties(Uuid,TeacherUuid,DutyUuid,SysUser,AllocationDate) FROM '/tmp/TeacherDuties.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE TeacherDuties OWNER TO school;



-- -------------------
-- Table ClassTeacher
-- -------------------

CREATE TABLE ClassTeacher (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    TeacherUuid text REFERENCES Staff(Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid)
        
   
);
\COPY ClassTeacher(Uuid,TeacherUuid,ClassRoomUuid) FROM '/tmp/ClassTeacher.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE ClassTeacher OWNER TO school;








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
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    ExamName text 
   
   
);

-- import data from the CSV file for the Accounts table
\COPY Exam(Uuid,SchoolAccountUuid,ExamName) FROM '/tmp/Exam.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Exam OWNER TO school;




-- -------------------
-- Table Perfomance
-- -------------------
 CREATE TABLE  Perfomance (
    Id SERIAL PRIMARY KEY,
    SchoolAccountUuid text REFERENCES SchoolAccount(Uuid),
    TeacherUuid text REFERENCES Staff(Uuid),
    StudentUuid text REFERENCES Student(Uuid),
    SubjectUuid text REFERENCES Subject(Uuid), 
    ClassRoomUuid text REFERENCES ClassRoom(Uuid),   
    ClassesUuid text REFERENCES Classes(Uuid),   
    CatOne float,                            
    CatTwo float,
    EndTerm float,
    PaperOne float,
    PaperTwo float,
    PaperThree float,                          
    Term text,
    Year text
 



);

-- import data from the CSV file for the Accounts table
\COPY Perfomance(SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,ClassRoomUuid,ClassesUuid,CatOne,CatTwo,EndTerm,PaperOne,PaperTwo,PaperThree,Term,Year) FROM '/tmp/Perfomance.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Perfomance OWNER TO school;



-- -------------------
-- Table GradingSystem
-- -------------------
 CREATE TABLE  GradingSystem (
     Id SERIAL PRIMARY KEY,
     Uuid text UNIQUE NOT NULL,
     SchoolAccountUuid text REFERENCES SchoolAccount(Uuid),
     GradeAplain Integer,
     GradeAminus Integer,
     GradeBplus Integer,
     GradeBplain Integer,
     GradeBminus Integer,
     GradeCplus Integer,
     GradeCplain Integer,
     GradeCminus Integer,
     GradeDplus Integer,
     GradeDplain Integer,
     GradeDminus Integer,
     GradeE Integer
    
   
);

-- import data from the CSV file for the Accounts table
\COPY GradingSystem(Uuid,SchoolAccountUuid,GradeAplain,GradeAminus,GradeBplus,GradeBplain,GradeBminus,GradeCplus,GradeCplain,GradeCminus,GradeDplus,GradeDplain,GradeDminus,GradeE) FROM '/tmp/GradingSystem.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE GradingSystem OWNER TO school;



-- -------------------
-- Table StudentExam
-- -------------------
 CREATE TABLE  StudentExam (
    Id SERIAL PRIMARY KEY,
    SchoolAccountUuid text REFERENCES SchoolAccount(uuid),
    StudentUuid text REFERENCES Student(Uuid),
    ClassRoomUuid text REFERENCES ClassRoom(Uuid)
   
   
);

-- import data from the CSV file for the Accounts table
\COPY StudentExam(SchoolAccountUuid,StudentUuid,ClassRoomUuid) FROM '/tmp/StudentExam.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE StudentExam OWNER TO school;







-- ==================
-- ==================
-- .7 Pocket Money Management
-- ==================
-- ==================


-- -------------------
-- Table PocketMoney
-- -------------------

CREATE TABLE PocketMoney (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    Balance float NOT NULL CHECK (Balance>=0)
   

);
\COPY PocketMoney(Uuid,StudentUuid,Balance) FROM '/tmp/PocketMoney.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE PocketMoney OWNER TO school;

-- -------------------
-- Table Deposit
-- -------------------

CREATE TABLE Deposit (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES Student(Uuid),
    AmountDeposited float NOT NULL CHECK (AmountDeposited>=0),
    SysUser text,
    DepositeDate timestamp with time zone DEFAULT now()

);
\COPY Deposit(Uuid,StudentUuid,AmountDeposited,SysUser,DepositeDate) FROM '/tmp/Deposit.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Deposit OWNER TO school;

-- -------------------
-- Table  Withdraw
-- -------------------

CREATE TABLE  Withdraw (
    Id SERIAL PRIMARY KEY,
    Uuid text UNIQUE NOT NULL,
    StudentUuid text REFERENCES student(uuid),
    AmountWithdrawn float NOT NULL CHECK (AmountWithdrawn>=0),
    SysUser text,
    WithdrawDate timestamp with time zone DEFAULT now()

);
\COPY Withdraw(Uuid,StudentUuid,AmountWithdrawn,SysUser,WithdrawDate) FROM '/tmp/Withdraw.csv' WITH DELIMITER AS '|' CSV HEADER
ALTER TABLE Withdraw OWNER TO school;
















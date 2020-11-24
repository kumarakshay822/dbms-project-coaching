CREATE TABLE IF NOT EXISTS User (
  userId int NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL UNIQUE,
  password varchar(255) DEFAULT NULL,
  firstName varchar(255) NOT NULL,
  middleName varchar(255) DEFAULT NULL,
  lastName varchar(255) DEFAULT NULL,
  emailAddress varchar(255) NOT NULL UNIQUE,
  dateCreated date NOT NULL,
  isActive boolean DEFAULT false NOT NULL,
  isEmailVerified boolean DEFAULT false NOT NULL,
  lastLoginDate date DEFAULT NULL,
  lastLoginTime time DEFAULT NULL,
  role enum ('ROLE_ADMIN','ROLE_STAFF', 'ROLE_TEACHER', 'ROLE_STUDENT') NOT NULL,
  PRIMARY KEY (userId)
);

DELIMITER $$
CREATE TRIGGER check_insert_isEmailVerified_password BEFORE INSERT ON User
FOR EACH ROW
BEGIN
IF NEW.isEmailVerified = 1 AND NEW.password IS NULL THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = "Email can't be verified for NULL passwords";
END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER check_update_isEmailVerified_password BEFORE UPDATE ON User
FOR EACH ROW
BEGIN
IF NEW.isEmailVerified = 1 AND NEW.password IS NULL THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = "Email can't be verified for NULL passwords";
END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER check_insert_user_emailAddress BEFORE INSERT ON User
FOR EACH ROW
BEGIN
IF (NEW.emailAddress REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_user_emailAddress BEFORE UPDATE ON User
FOR EACH ROW
BEGIN
IF (NEW.emailAddress REGEXP "^[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$") = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS UserToken (
  userId int NOT NULL,
  token varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (userId),
  FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS UserPhoneNumber (
  phoneNumber varchar(255) NOT NULL,
  userId int NOT NULL,
  PRIMARY KEY (phoneNumber, userId),
  FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER $$
CREATE TRIGGER check_insert_user_phone BEFORE INSERT ON UserPhoneNumber
FOR EACH ROW
BEGIN
IF (NEW.phoneNumber REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_user_phone BEFORE UPDATE ON UserPhoneNumber
FOR EACH ROW
BEGIN
IF (NEW.phoneNumber REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;


CREATE TABLE IF NOT EXISTS Student (
  studentId int NOT NULL AUTO_INCREMENT,
  gender enum ('Male','Female', 'Not Specified') DEFAULT 'Not Specified' NOT NULL,
  dateOfBirth date NOT NULL,
  houseNumber varchar(255) DEFAULT NULL,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  schoolAttending varchar(255) NOT NULL,
  userId int NOT NULL,
  PRIMARY KEY (studentId),
  FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Student AUTO_INCREMENT = 100001;


CREATE TABLE IF NOT EXISTS Complaint (
  complaintId int NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  time time NOT NULL,
  subject varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  response varchar(255) DEFAULT NULL,
  isResolved boolean DEFAULT false NOT NULL,
  studentId int NOT NULL,
  PRIMARY KEY (complaintId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Guardian (
  name varchar(255) NOT NULL,
  studentId int NOT NULL,
  occupation varchar(255) DEFAULT NULL,
  address varchar(255) NOT NULL,
  email varchar(255) DEFAULT NULL,
  relationWithStudent enum ('Father', 'Mother', 'Other') NOT NULL,
  PRIMARY KEY (name, studentId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER $$
CREATE TRIGGER check_insert_guardian_email BEFORE INSERT ON Guardian
FOR EACH ROW
BEGIN
IF (NEW.email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_guardian_email BEFORE UPDATE ON Guardian
FOR EACH ROW
BEGIN
IF (NEW.email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = 'Invalid email address';
END IF;
END$$
DELIMITER ;


CREATE TABLE IF NOT EXISTS GuardianPhoneNumber (
  phoneNumber varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  studentId int NOT NULL,
  PRIMARY KEY (phoneNumber, name, studentId),
  FOREIGN KEY (name, studentId) REFERENCES Guardian(name, studentId) ON DELETE CASCADE ON UPDATE CASCADE
);


DELIMITER $$
CREATE TRIGGER check_insert_guardian_phone BEFORE INSERT ON GuardianPhoneNumber
FOR EACH ROW
BEGIN
IF (NEW.phoneNumber REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_guardian_phone BEFORE UPDATE ON GuardianPhoneNumber
FOR EACH ROW
BEGIN
IF (NEW.phoneNumber REGEXP '^[1-9][0-9]{9,9}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong Phone Number Format';
END IF;
END$$
DELIMITER ;


CREATE TABLE IF NOT EXISTS Employee (
  employeeId int NOT NULL AUTO_INCREMENT,
  basicSalary int DEFAULT NULL,
  joinDate date DEFAULT NULL,
  endDate date DEFAULT NULL,
  panNumber varchar(255) NOT NULL,
  accountNumber varchar(255) NOT NULL,
  userId int NOT NULL,
  PRIMARY KEY (employeeId),
  FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Employee AUTO_INCREMENT = 100001;


DELIMITER $$
CREATE TRIGGER check_insert_employee_joinEndDate BEFORE INSERT ON Employee
FOR EACH ROW
BEGIN
IF NEW.endDate < NEW.joinDate THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Date can't be less than join date";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_employee_joinEndDate BEFORE UPDATE ON Employee
FOR EACH ROW
BEGIN
IF NEW.endDate < NEW.joinDate THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Date can't be less than join date";
END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER check_insert_employee_panNumber BEFORE INSERT ON Employee
FOR EACH ROW
BEGIN
IF (NEW.panNumber REGEXP '^[A-Z]{5}[0-9]{4}[A-Z]{1}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong PAN Number Format';
END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER check_update_employee_panNumber BEFORE UPDATE ON Employee
FOR EACH ROW
BEGIN
IF (NEW.panNumber REGEXP '^[A-Z]{5}[0-9]{4}[A-Z]{1}$') = 0 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong PAN Number Format';
END IF;
END$$
DELIMITER ;



CREATE TABLE IF NOT EXISTS Payroll (
  paymentRefNo varchar(255) NOT NULL,
  month varchar(255) NOT NULL,
  year int NOT NULL,
  salaryCredited decimal(10,2) NOT NULL,
  dateCredited date NOT NULL,
  employeeId int NOT NULL,
  PRIMARY KEY (paymentRefNo),
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER $$
CREATE TRIGGER check_insert_payroll_month BEFORE INSERT ON Payroll
FOR EACH ROW
BEGIN
IF NEW.month < 1 OR NEW.month > 12 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Invalid month';
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_payroll_month BEFORE UPDATE ON Payroll
FOR EACH ROW
BEGIN
IF NEW.month < 1 OR NEW.month > 12 THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Invalid month';
END IF;
END$$
DELIMITER ;



CREATE TABLE IF NOT EXISTS Attendance (
  date date NOT NULL,
  employeeId int NOT NULL,
  isPresent boolean DEFAULT true NOT NULL,
  remarks varchar(255) DEFAULT NULL,
  PRIMARY KEY (date, employeeId),
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Staff (
  staffId int NOT NULL AUTO_INCREMENT,
  gender enum ('Male','Female', 'Not Specified') DEFAULT 'Not Specified' NOT NULL,
  dateOfBirth date NOT NULL,
  houseNumber varchar(255) DEFAULT NULL,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  employeeId int NOT NULL,
  PRIMARY KEY (staffId),
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Course (
  courseId varchar(255) NOT NULL,
  courseName varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (courseId)
);


CREATE TABLE IF NOT EXISTS Subject (
  subjectId varchar(255) NOT NULL,
  subjectName varchar(255) NOT NULL UNIQUE,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (subjectId)
);


CREATE TABLE IF NOT EXISTS StudyMaterial (
  materialId varchar(255) NOT NULL,
  subjectId varchar(255) NOT NULL,
  topicName varchar(255) NOT NULL,
  difficulty enum ('Easy','Medium', 'Hard') DEFAULT 'Easy' NOT NULL,
  filename varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (materialId, subjectId),
  FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS CourseSubjectDetails (
  courseId varchar(255) NOT NULL,
  subjectId varchar(255) NOT NULL,
  PRIMARY KEY (courseId, subjectId),
  FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Batch (
  batchId varchar(255) NOT NULL,
  courseId varchar(255) NOT NULL,
  batchName varchar(255) NOT NULL,
  fee int NOT NULL,
  roomNumber int NOT NULL,
  startTime time DEFAULT NULL,
  endTime time DEFAULT NULL,
  PRIMARY KEY (batchId, courseId),
  FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE ON UPDATE CASCADE
);


DELIMITER $$
CREATE TRIGGER check_insert_batch_startEndTime BEFORE INSERT ON Batch
FOR EACH ROW
BEGIN
IF NEW.endTime < NEW.startTime THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Time can't be less than start time";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_batch_startEndTime BEFORE UPDATE ON Batch
FOR EACH ROW
BEGIN
IF NEW.endTime < NEW.startTime THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Time can't be less than start time";
END IF;
END$$
DELIMITER ;


CREATE TABLE IF NOT EXISTS StaffBatchDetails (
  staffId int NOT NULL,
  batchId varchar(255) NOT NULL,
  courseId varchar(255) NOT NULL,
  PRIMARY KEY (staffId, batchId, courseId),
  FOREIGN KEY (staffId) REFERENCES Staff(staffId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (batchId, courseId) REFERENCES Batch(batchId, courseId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Teacher (
  teacherId int NOT NULL AUTO_INCREMENT,
  gender enum ('Male','Female', 'Not Specified') DEFAULT 'Not Specified' NOT NULL,
  dateOfBirth date NOT NULL,
  houseNumber varchar(255) NOT NULL,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  bachelorsDegree varchar(255) DEFAULT NULL,
  mastersDegree varchar(255) DEFAULT NULL,
  doctoralDegree varchar(255) DEFAULT NULL,
  employeeId int NOT NULL,
  subjectId varchar(255) DEFAULT NULL,
  PRIMARY KEY (teacherId),
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE SET NULL ON UPDATE CASCADE
);



CREATE TABLE IF NOT EXISTS Feedback (
  feedbackId int NOT NULL AUTO_INCREMENT,
  studentId int NOT NULL,
  employeeId int NOT NULL,
  date date NOT NULL,
  time time NOT NULL,
  subject varchar(255) NOT NULL,
  message varchar(255) NOT NULL,
  response varchar(255) DEFAULT NULL,
  PRIMARY KEY (feedbackId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS TeacherBatchDetails (
  teacherId int NOT NULL,
  batchId varchar(255) NOT NULL,
  courseId varchar(255) NOT NULL,
  PRIMARY KEY (teacherId, batchId, courseId),
  FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (batchId, courseId) REFERENCES Batch(batchId, courseId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Transaction (
  transactionId int NOT NULL AUTO_INCREMENT,
  amount decimal(10,2) NOT NULL,
  date date NOT NULL,
  time time NOT NULL,
  transactionMode enum ('Offline', 'Online') DEFAULT 'Online' NOT NULL,
  isSuccess boolean DEFAULT false NOT NULL,
  PRIMARY KEY (transactionId)
);


CREATE TABLE IF NOT EXISTS Enrollment (
  enrollmentId int NOT NULL AUTO_INCREMENT,
  studentId int NOT NULL,
  batchId varchar(255) DEFAULT NULL,
  courseId varchar(255) DEFAULT NULL,
  transactionId int NOT NULL,
  joinDate date DEFAULT NULL,
  endDate date DEFAULT NULL,
  PRIMARY KEY (enrollmentId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (batchId, courseId) REFERENCES Batch(batchId, courseId) ON DELETE SET NULL ON UPDATE CASCADE,
  FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId) ON DELETE CASCADE ON UPDATE CASCADE
);


DELIMITER $$
CREATE TRIGGER check_insert_enrollment_joinEndDate BEFORE INSERT ON Enrollment
FOR EACH ROW
BEGIN
IF NEW.endDate < NEW.joinDate THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Date can't be less than join date";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_enrollment_joinEndDate BEFORE UPDATE ON Enrollment
FOR EACH ROW
BEGIN
IF NEW.endDate < NEW.joinDate THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Date can't be less than join date";
END IF;
END$$
DELIMITER ;


CREATE TABLE IF NOT EXISTS Test (
  testId int NOT NULL AUTO_INCREMENT,
  testName varchar(255) NOT NULL,
  roomNumber int DEFAULT NULL,
  testDate date NOT NULL,
  startTime time NOT NULL,
  endTime time NOT NULL,
  maximumMarks int NOT NULL,
  difficulty enum ('Easy','Medium', 'Hard') DEFAULT 'Easy' NOT NULL,
  courseId varchar(255) NOT NULL,
  PRIMARY KEY (testId),
  FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE ON UPDATE CASCADE
);


DELIMITER $$
CREATE TRIGGER check_insert_test_startEndTime BEFORE INSERT ON Test
FOR EACH ROW
BEGIN
IF NEW.endTime < NEW.startTime THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Time can't be less than start time";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_test_startEndTime BEFORE UPDATE ON Test
FOR EACH ROW
BEGIN
IF NEW.endTime < NEW.startTime THEN
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = "End Time can't be less than start time";
END IF;
END$$
DELIMITER ;


CREATE TABLE IF NOT EXISTS Result (
  studentId int NOT NULL AUTO_INCREMENT,
  testId int NOT NULL,
  marksScored int NOT NULL,
  hasAppliedRecheck boolean DEFAULT false NOT NULL,
  isDoneRecheck boolean DEFAULT false NOT NULL,
  recheckComments varchar(255) DEFAULT NULL,
  PRIMARY KEY (studentId, testId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (testId) REFERENCES Test(testId) ON DELETE CASCADE ON UPDATE CASCADE
);

DELIMITER $$
CREATE TRIGGER check_insert_recheck_applied BEFORE INSERT ON Result
FOR EACH ROW
BEGIN
IF NEW.hasAppliedRecheck = 0 AND NEW.isDoneRecheck = 1 IS NULL THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = "Recheck can't be done if it is not applied for";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER check_update_recheck_applied BEFORE UPDATE ON Result
FOR EACH ROW
BEGIN
IF NEW.hasAppliedRecheck = 0 AND NEW.isDoneRecheck = 1 IS NULL THEN
  SIGNAL SQLSTATE '12345'
    SET MESSAGE_TEXT = "Recheck can't be done if it is not applied for";
END IF;
END$$
DELIMITER ;

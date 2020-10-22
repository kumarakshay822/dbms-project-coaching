DROP DATABASE IF EXISTS coaching;
CREATE DATABASE coaching;
USE coaching;


CREATE TABLE IF NOT EXISTS User (
  userId int NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  firstName varchar(255) NOT NULL,
  middleName varchar(255) DEFAULT NULL,
  lastName varchar(255) DEFAULT NULL,
  emailAddress varchar(255) NOT NULL,
  dateCreated date NOT NULL,
  isActive boolean DEFAULT false NOT NULL,
  lastLoginDate date DEFAULT NULL,
  lastLoginTime time DEFAULT NULL,
  role varchar(255) NOT NULL,
  PRIMARY KEY (userId)
);


CREATE TABLE IF NOT EXISTS UserPhoneNumber (
  phoneNumber varchar(255) NOT NULL,
  userId int NOT NULL,
  PRIMARY KEY (phoneNumber, userId),
  FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Student (
  studentId int NOT NULL AUTO_INCREMENT,
  gender enum ('Male','Female', 'Not Specified') DEFAULT 'Not Specified' NOT NULL,
  dateOfBirth date NOT NULL,
  houseNumber varchar(255) DEFAULT NULL,
  street varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  pinCode int NOT NULL,
  schoolAttending varchar(255) NOT NULL,
  pencentage10th decimal(4,2) NOT NULL,
  pencentage12th decimal(4,2) NOT NULL,
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
  emailAddress varchar(255) DEFAULT NULL,
  relationWithStudent enum ('Father', 'Mother', 'Other') NOT NULL,
  PRIMARY KEY (name, studentId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS GuardianPhoneNumber (
  phoneNumber varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  studentId int NOT NULL,
  PRIMARY KEY (phoneNumber, name, studentId),
  FOREIGN KEY (name, studentId) REFERENCES Guardian(name, studentId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Employee (
  employeeId int NOT NULL AUTO_INCREMENT,
  basicSalary int DEFAULT NULL,
  joinDate date DEFAULT NULL,
  endDate date DEFAULT NULL,
  panNumber varchar(255) NOT NULL,
  accountNumber varchar(255) NOT NULL,
  bankName varchar(255) NOT NULL,
  bankBranch varchar(255) NOT NULL,
  ifscCode varchar(255) NOT NULL,
  userId int NOT NULL,
  PRIMARY KEY (employeeId),
  FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Employee AUTO_INCREMENT = 100001;

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
  pinCode int NOT NULL,
  employeeId int NOT NULL,
  PRIMARY KEY (staffId),
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS Course (
  courseId varchar(255) NOT NULL,
  courseName varchar(255) NOT NULL,
  fee int NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (courseId)
);


CREATE TABLE IF NOT EXISTS Subject (
  subjectId varchar(255) NOT NULL,
  subjectName varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (subjectId)
);


CREATE TABLE IF NOT EXISTS StudyMaterial (
  materialId int NOT NULL AUTO_INCREMENT,
  subjectId varchar(255) NOT NULL,
  topicName varchar(255) NOT NULL,
  difficulty enum ('Easy','Medium', 'Hard') DEFAULT 'Easy' NOT NULL,
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
  roomNumber int NOT NULL,
  startTime time DEFAULT NULL,
  endTime time DEFAULT NULL,
  PRIMARY KEY (batchId, courseId),
  FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE ON UPDATE CASCADE
);


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
  pinCode int NOT NULL,
  bachelorsDegree varchar(255) NOT NULL,
  mastersDegree varchar(255) NOT NULL,
  doctoralDegree varchar(255) NOT NULL,
  employeeId int NOT NULL,
  subjectId varchar(255) DEFAULT NULL,
  PRIMARY KEY (teacherId),
  FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (subjectId) REFERENCES Subject(subjectId) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS StudentTeacherFeedback (
  studentId int NOT NULL,
  teacherId int NOT NULL,
  date date NOT NULL,
  time time NOT NULL,
  subject varchar(255) NOT NULL,
  message varchar(255) NOT NULL,
  response varchar(255) DEFAULT NULL,
  PRIMARY KEY (studentId, teacherId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (teacherId) REFERENCES Teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE
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
  accountName varchar(255) NOT NULL,
  amount decimal(10,2) NOT NULL,
  date date NOT NULL,
  time time NOT NULL,
  transactionMode enum ('Offline', 'Online') DEFAULT 'Online' NOT NULL,
  remarks varchar(255) DEFAULT NULL,
  isVerified boolean DEFAULT false NOT NULL,
  PRIMARY KEY (transactionId)
);


CREATE TABLE IF NOT EXISTS Enrollment (
  enrollmentId int NOT NULL AUTO_INCREMENT,
  studentId int NOT NULL,
  batchId varchar(255) DEFAULT NULL,
  courseId varchar(255) DEFAULT NULL,
  transactionId int NOT NULL,
  testScore int DEFAULT NULL,
  percentageScholarship int DEFAULT NULL,
  joinDate date DEFAULT NULL,
  endDate date DEFAULT NULL,
  PRIMARY KEY (enrollmentId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (batchId, courseId) REFERENCES Batch(batchId, courseId) ON DELETE SET NULL ON UPDATE CASCADE,
  FOREIGN KEY (transactionId) REFERENCES Transaction(transactionId) ON DELETE CASCADE ON UPDATE CASCADE
);


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


CREATE TABLE IF NOT EXISTS StudentTestDetails (
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

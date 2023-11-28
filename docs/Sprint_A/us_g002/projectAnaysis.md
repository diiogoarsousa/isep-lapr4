# Project Analysis

## Identify the domain
_The first step in creating a domain-driven model is to identify the domain you are working in. 
This means understanding the problem space, the stakeholders involved, and the business goals that are driving the project._

### Problem space
Creation of a remote learning platform that supports the needs of the 3 major stakeholder (teachers, students and managers) in the learning experience.
The system is abel, among others, to manage users, manage courses, create shared boards, enroll in courses, create and take exams...

### Stakeholders
- teachers
- students
- managers
- Company

### Business goals
- The company intends to **explore** a new innovative software solution for remote learning.
- **Develop**, in an exploratory way, **a new remote learning platform** called
  eCourse, using a minimum viable product (MVP) approach.
- The company set the **duration** of this MVP project as **3 months**.
- Validating the main ideas exposed in this document and
- constitutes, by itself, the pillars of what a possible commercial system could be.


## Define the domain concepts
_Once you have a clear understanding of the domain, you can start defining the key concepts that are relevant to your model. These might be entities, value objects, or aggregates that represent the core business objects you need to work with._

### Entity and value objects

| Concept                | Entity | ValueObject | Comments                                                                       |
|------------------------|:------:|:-----------:|:-------------------------------------------------------------------------------|
| **User**  (abstract)   |   x    |             | ?                                                                              |
| email                  |        |      x      | validation: email format, mandatory, identification in the system              |
| short name             |        |      x      | validation: mandatory                                                          | 
| full name              |        |      x      | validation: mandatory                                                          |  
| password               |        |      x      | validation: mandatory, password rules?                                         |   
| active                 |        |             | enabled/disabled                                                               |
|                        |
| **Manager**            |   x    |             |                                                                                |
| user                   |        |      x      | a manager is a user                                                            |
|                        |
| **Teacher**            |   x    |             |                                                                                |
| user                   |        |      x      | a teacher is a user                                                            |
| birthDate              |        |      x      | possible validation rules                                                      |
| taxNumber              |        |      x      | possible validation rules                                                      |
| acronym                |        |      x      | unique, mandatory, manually assigned, possible validation rules                |
|                        |
| **Student**            |   x    |             |                                                                                |
| user                   |        |      x      | a student is a user                                                            |
| birthDate              |        |      ?      | possible validation rules                                                      |
| taxNumber              |        |      x      | possible validation rules                                                      |
| mecanographicNumber    |        |      x      | creation rules: auto assigned - YYYYSSSSS                                      |
|                        |
| **Course**             |   x    |             | Disciplina (lapr, esoft, ... )                                                 |
| ID (unique)            |        |             | is auto-assigned by DB?                                                        |
| title                  |        |             | validation rules                                                               |
| headTeacher            |        |             | mandatory, a teacher is a object                                               |
| otherTeachers          |        |             | optional, a list of teachers                                                   |
| description            |        |             | mandatory, validation rules                                                    |
| minimumStudents        |        |      x      | validation rules ( 0-no limit, >0-set limit )                                  |
| maximumStudents        |        |      x      | validation rules ( 0-no limit, >0-set limit )                                  |
| state                  |        |      x      | rules on the type of state flow:(open/close/enroll/in progress/closed)         |
|                        |
| **Enrolement**         |   x    |             |                                                                                |
| appliedStudents        |        |             | optional, a list of students                                                   |
| enrolledStudents       |        |             | optional, a list of students                                                   |
| courseId               |        |             |                                                                                |
|                        |
| **CourseClass**        |   x    |             | Aula                                                                           |
| date                   |        |      x      | frequency rules: weekly                                                        |
| duration               |        |      ?      |                                                                                |
| participant            |        |             | a list of participants                                                         |
|                        |
| **Extra Class**        |   x    |             | Aula Extra                                                                     |
| date                   |        |      x      | one time                                                                       |
| duration               |        |      x      |                                                                                |
| participant            |        |      ?      | a list of participants                                                         |
|                        |
| **Meeting**            |   x    |             |                                                                                |
| title (unique)         |        |      x      | validation rules                                                               |
| date                   |        |      x      | has frequency rules                                                            |
| duration               |        |      ?      |                                                                                |
| participant            |        |      ?      | a list of participants                                                         |
|                        |
| **Exam**               |   x    |             |                                                                                |
| title (unique)         |        |      x      |                                                                                |
| description            |        |      x      |                                                                                |
| openDate               |        |      ?      |                                                                                |
| closeDate              |        |      ?      |                                                                                |
| designRules            |        |      x      | a list of rules                                                                |
| executingRules         |        |      x      | a list of rules                                                                |
| evaluationRules        |        |      x      | a list of rules                                                                |
|                        |        |             |                                                                                |
| **Header**             |   x    |             |                                                                                |
| feedbackType           |        |             |                                                                                |
| gradeType              |        |             |                                                                                |
| description            |        |             |                                                                                |
|                        |
| **Section**            |   x    |             |                                                                                |
| questions              |        |      x      | list of questions                                                              |
| description            |        |             |                                                                                |
|                        |
| **ExamTaken**          |   x    |             |                                                                                |
| answers                |        |      x      |                                                                                |
| grade                  |        |      ?      |                                                                                |
|                        |
| **Board***             |   x    |             |                                                                                |
| title (unique)         |        |      x      | validation rules                                                               |
| numberRows             |        |      x      | validation: max number of rows defined by settings in property file (20)       |
| rowTitle               |        |      x      | validation: optional, string or numeric sequential from 1                      |
| numberColumns          |        |      x      | validation: max number of columns defined by settings in property file (10)    |
| columnTitle            |        |      x      | validation: optional, string or numeric sequential from 1                      |
| owner                  |        |      x      | the user who creates the board                                                 |
| allowedUsers           |        |      ?      | optional, list of users that can access the board and permissions (read/write) |
|                        |
| **Board History**      |   x    |             |                                                                                |
|                        |        |             |                                                                                |
|                        |
| **Post It**            |   x    |             |                                                                                |
| content                |
| position               |
|                        |
| **Teacher Assignment** |   x    |             |                                                                                |
| TeacherID              |        |             |                                                                                |      
| CourseID               |        |             |                                                                                | 
|                        |        |             |                                                                                | 




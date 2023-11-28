# US 1010 - Schedule a Course Class


## 1. Context

New feature to be developed.

**Github Issue:** #20
## 2. Requirements

As Teacher, I want to schedule a class


## 2.1 Client Clarifications

***Q:** Boa tarde nos estamos a ter uma duvida sobre qual e a diferença entre meetings e classes

**A:** Os conceitos são bastante próximos mas existem diferenças. Por exemplo, as aulas são regulares e têm participantes pré-definidos. As reuniões são definidas por qualquer utilizador, têm o conceito de convite, podem-se especificar os participantes, etc. Ou seja, existem ainda diferenças significativas.

---
**Q:** Can a meeting and class overlap? If so, should the system notify that there is an overlap and for which user(s)?

**A:** Regarding classes:
- FRC09 - Schedule of Class A teacher schedule a class (always a recurring class, happens every week). System must check if the Teacher is available for the class period

---
**Q:** With the description of the functionalities/requirements of classes and meetings, wouldn't they be the same? Since both are online, in my point of view, it would make sense if a student could accept/reject a class as well, for example, and the same could be applied for the remaining requirements with the exception of creating classes.

**A:** There is no requirement that "says" the student can accept or reject a class. Only meetings have the possibility that participants may accept or reject the meeting. This does not apply to classes.

---
**Q:** Should we allow the users to choose until what date they want to schedule the weekly classes or should the weakly classes be scheduled until the end of the semester and if so what is the duration of the semester

**A:** Only teachers should be able to schedule classes.
I think there is no requirement that refers to the course duration or the concept of term or semester. Therefore, if you do not consider the course to have a pre-established ending date, then you also should not consider the end of the recurrence (in fact, it will happen when the manager closes the course). If you consider that a course must have an ending date, then also consider the end of the recurrence of the classes to the same date.
However, since "nothing" happens at the time of the classes, the option you take will have limited implications, I think, only at the validation of availability of participants.

---
**Q:** When the Manager creates a Course, should there already be an assigned group of Teachers and Students associated with the created Course or should they be added later?

**A:** There are specific requirements for all these functionalities and they may take place after the course creation. For instance, FRC04, refers to setting the teachers of the course and FRC06 refers to the bulk enrolment of students. They both can take place after the creation of the course.
However, I think there is no "problem" if the system supports that the manager do all these settings just "after" creating the course.

---
**Q:** Regarding weekly classes recurrence, should be considered as a recurrence class if it happens more than one day per week? For example, the same class can occur on mondays and wednesdays?

**A:** A class should be considered only one class ("aula"). This one class is always a recurring class. If a course has more than one class each week, then each class will be a recurring class.

---
**Q:** We have a question about a class scheduling. When a teacher wants to schedule a class, do they introduce start date/time and end date/time, or start date/time and duration of the class? And in general, how often will the client want to consult the duration of the classes?

**A:** For the class the teacher should entre the start date/time and duration (see Section 5.1.2).

The duration of the class is important when scheduling "events" (such a classes and meetings) because the system must comply with some scheduling rules, for instance, to avoid scheduling classes that are coincident with other classes of the same course (see Section 5.1.2).

---

## 2.2 Demos



## 3. Analysis

### 3.1 Pre Requirements

- Course must exist


### 3.2 Post Requirements

- Class schedule is registered

### 3.3 Business Rules
- Only the Teachers can schedule a class
- Always a recurring class, happens every week. 
- For the class the teacher should enter a unique title, the start date/time and duration (see Section 5.1.2).
- Impossible to schedule a class that is coincident with other classes of the same course (see Section 5.1.2).
- Warn if one of the participants (teacher or student) in the class has other classes at the same time.

## 4. Design

### 4.1. Realization

#### 4.1.1. UC Realization

- The Teacher starts the class schedule application.
- The system shows a list of courses that the teacher is assigned.
- The Teacher selects a course.
- The system shows a list of already scheduled classes and ask the data of the new schedule.
- The Teacher inputs the data of the new schedule.
- The system validates, update the view of the classes scheduled and ask the Teacher for confirmation.
- The Teacher confirms the schedule of the class.
- The system saves the data.


#### 4.1.2. Sequence Diagram
![sequence_diagram.svg](sequence_diagram.svg)

#### 4.1.3 Class Diagram

![class_diagram.svg](./class_diagram.svg)


## 4.2 Applied Patterns

### 4.2.1 Architectural  Pattern
An **Onion Architectural Pattern** was used in order to achieve a lower coupling of the system since the dependency was from the outer to the inner layer. A better maintainability of the system is another consequence of the chosen design

**presentation -> application -> persistence -> domain**

### 4.2.2 Design Patters

#### Controller
The controller pattern assigns the responsibility of dealing with system events to a non-UI class that represents the overall system or a use case scenario.
In this UseCase **ScheduleCourseClassController** is responsible for organising the use case logic.

#### Factory and Repository
These patterns helped in the persistence, storage and data access.
It is used in the instantiation of the **CourseClassRepository** in order for the system to have access to the students enrollments data and save the approval/rejection of the same.
The repositories are accessed by the RepositoryFactory using the PersistenceContext interface

#### High-Cohesion, Low-Coupling
**Low coupling** is an evaluative pattern that dictates how to assign responsibilities for the following benefits:
* lower dependency between the classes,
* change in one class having a lower impact on other classes,
* higher reuse potential.

**High cohesion** means that the responsibilities of a given set of elements are strongly related and highly focused on a rather specific topic.

In this UseCase we can see in the following examples:

* CourseCLassRepository, is just  a repository of scheduled course classes, were the data related to them are saved and accessed;
* PersistenceContext is an ‘interface’ that recalls the FactoryRepository
* RepositoryFactory is a repository's factory from we can access the CourseClassRepository;

#### Information Expert
This pattern leads to placing the responsibility on the class with the most information required to fulfill it.


## 4.3 Tests
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}


## 5. Implementation

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*


## 6. Integration/Demonstration

*N/A*

## 7. Observations

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*

## 8. To-Do
* [] Review the class overlapping validation
* [] Add the participants overlap warnings



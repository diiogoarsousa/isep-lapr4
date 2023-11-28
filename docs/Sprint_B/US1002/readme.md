# US1002 - Create Courses

## 1. Context

This user story is a new feature to be developed.
As a manager, I want to be able to create courses in the system.

## 2. Requirements

As a manager, I want to be able to create a course with the following information:

- Course name
- Course state
- Start date
- End date
- Minimum enrolments
- Maximum enrolments
- Head teacher
- Description

## 3. Analysis

### 3.1 Pre-Requirements

- Manager must exist

### 3.2 Post-Requirements

- Course must be created

### 3.3 Business Rules

- Any manager can create a course
- A course must have a unique name
- The manager who creates the course is the owner of the course
- The owner of the course can edit the course information
- The owner of the course can delete the course
- The course can have a state of "event create", "event open", "event open enrollments", event close enrollments", or
  "event close"
- The course must have a start date and an end date
- The course must have a minimum and maximum number of enrolments
- The course must have a head teacher assigned to it
- The course can have a description

#### 4.1.1. UC Realization

- The Manager starts the course creation process.
- The System presents the manager with a form to fill with the course information(course name, course state, start date,
  end date, minimum enrolments, maximum enrolments, head teacher, description).
- The Manager fills the form with the course information.
- The System validates the information.
- The System creates the course.
- The System presents the manager with the course.
- The Manager can now edit or delete the course.

#### 4.1.2. Sequence Diagram

![sequence_diagram](/docs/Sprint_B/US1002/us_1002_sequence_diagram.svg)

#### 4.1.3. Class Diagram

![class_diagram](docs/Sprint_B/US1002/us_1002_class_diagram.svg)

## 4. Design

### 4.1. Realization

The course creation process is realized through the following classes:

- Manager
- CreateCourseUi
- CreateCourseController
- CourseBuilder
- Course
- CourseRepository
- JpaCourseRepository
- Database

### 4.2. Domain Model

The domain model for this user story includes the following entities:

- Manager
- Course

### 4.3. Applied Patterns

This user story uses the Model-View-Controller (MVC).

#### Model

The Model represents the domain-specific knowledge and data in an application. In this use case.

#### View

The View renders the model into a form suitable for interaction, typically a user interface element.

#### Controller

The controller pattern assigns the responsibility of dealing with system events to a non-UI class that represents the
overall system or a use case scenario.

#### Builder

The builder pattern separates the construction of a complex object from its representation, allowing the same
construction process to create various representations.

#### Repository

The repository pattern mediates between the domain and data mapping layers, acting like an in-memory domain object
collection.

### 4.4. Tests

The following tests were created to ensure the correct behavior of the course creation process:

#### 4.4.1. Unit Tests

    - Test 1: test equals method if it is correctly implemented
    @Test
    void testEquals() {

        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        Course course1 = new Course(uuid, "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        Course course2 = new Course(uuid, "Course 2", CourseStateEnum.CLOSED, Calendar.getInstance(), Calendar.getInstance(), 5, 15, "Teacher 2", "MyDescription 2");
        Course course3 = new Course(uuid, "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        assertNotEquals(course1, course3);
        assertNotEquals(course1, course2);
    }
    
    - Test 2: test hashCode method if it is correctly implemented
    @Test
    void testHashCode() {
    Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
    Course course2 = new Course(UUID.randomUUID(), "Course 2", CourseStateEnum.CLOSED, Calendar.getInstance(), Calendar.getInstance(), 5, 15, "Teacher 2", "MyDescription 2");
    Course course3 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertNotEquals(course1.hashCode(), course2.hashCode());
        assertNotEquals(course1.hashCode(), course3.hashCode());
    }

    - Test 3: test sameAs method if it is correctly implemented
    @Test
    void sameAs() {
    Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
    Course course2 = new Course(UUID.randomUUID(), "Course 2", CourseStateEnum.CLOSED, Calendar.getInstance(), Calendar.getInstance(), 5, 15, "Teacher 2", "MyDescription 2");

        assertFalse(course1.sameAs(course2));
    }

    - Test 4: test identity method if it is correctly implemented
    @Test
    void identity() {
        UUID uuid = UUID.randomUUID();
        Course course1 = new Course(uuid, "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        Course course2 = course1;

        assertEquals(course2.identity(), course1.identity());
    }
    
    - Test 5: test name method if it is correctly implemented
    @Test
    void name() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertEquals("Course 1", course1.name());
    }

    - Test 6: test isActive method if it is correctly implemented
    @Test
    void isActive() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");

        assertFalse(course1.isActive());
    }

    - Test 7: test toDTO method if it is correctly implemented
    @Test
    void toDTO() {
        Course course1 = new Course(UUID.randomUUID(), "Course 1", CourseStateEnum.OPEN, Calendar.getInstance(), Calendar.getInstance(), 10, 20, "Teacher 1", "MyDescription 1");
        CourseDTO courseDTO = course1.toDTO();

        assertEquals("Course 1", courseDTO.getName());
        assertEquals(CourseStateEnum.CLOSED.toString(), courseDTO.getState());
        assertEquals(10, courseDTO.getMinEnrollments());
        assertEquals(20, courseDTO.getMaxEnrollments());
        assertEquals("Teacher 1", courseDTO.getHeadTeacher());
        assertEquals("MyDescription 1", courseDTO.getDescription());
    }

## 5. Implementation

The implementation of this user story includes the following files:

- `us_1002_sequence_diagram.puml`: Sequence diagram for the course creation process.
- `us_1002_class_diagram.puml`: Class diagram for the course creation process.

## 6. Integration/Demonstration

The course creation process was successfully implemented and tested.

## 7. Observations

N/A
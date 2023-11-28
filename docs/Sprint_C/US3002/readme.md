# US3002 - Create Board

## 1. Context

This user story is a new feature to be developed. As a user, I want to be able to create a board.

## 2. Requirements

As a user, I want to be able to create a board with the following information:

- Title
- Number of rows
- Number of columns

## 3. Analysis

### 3.1 Pre-Requirements

- User must be logged in

### 3.2 Post-Requirements

- Board must be created

### 3.3 Business Rules

- Any user can create a board
- A board must have a unique title
- The user who creates the board is the owner of the board
- The owner of the board can edit the board information
- The owner of the board can delete the board

## 4. Design

### 4.1. Realization

The board creation process is realized through the following classes:

- User
- Board
- BoardRepository
- JpaBoardRepository
- Database

#### 4.1.1. UC Realization

- The User selects the option to create a board.
- The System presents the user with a form to fill with the board information (title, description).
- The User fills the form with the board information.
- The System validates the information.
- The System creates the board.
- The System presents the user with the board.
- The User can now edit or delete the board.

#### 4.1.2. Sequence Diagram

![sequence_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3002/Sequence-Diagram.png)

#### 4.1.3. Class Diagram

![class_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3002/Class-Diagram.png)

### 4.2. Domain Model

The domain model for this user story includes the following entities:

- User
- Board

### 4.3. Applied Patterns

This user story uses the Model-View-Controller (MVC).

#### Model

The Model represents the domain-specific knowledge and data in an application. In this use case, the Model includes the
Board and User entities.

#### View

The View renders the model into a form suitable for interaction, typically a user interface element.

#### Controller

The controller pattern assigns the responsibility of dealing with system events to a non-UI class that represents the
overall system or a use case scenario.

#### Repository

The repository pattern mediates between the domain and data mapping layers, acting like an in-memory domain object
collection.

### 4.4. Tests

The following tests were created to ensure the correct behavior of the board creation process:

#### 4.4.1. Unit Tests

    - Test 1: test equals method if it is correctly implemented
    @Test
    void testEquals() {

        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        Board board1 = new Board(uuid, "Board 1", "MyDescription 1");
        Board board2 = new Board(uuid, "Board 2", "MyDescription 2");
        Board board3 = new Board(uuid, "Board 1", "MyDescription 1");
        assertNotEquals(board1, board3);
        assertNotEquals(board1, board2);
    }
    
    - Test 2: test hashCode method if it is correctly implemented
    @Test
    void testHashCode() {
    Board board1 = new Board(UUID.randomUUID(), "Board 1", "MyDescription 1");
    Board board2 = new Board(UUID.randomUUID(), "Board 2", "MyDescription 2");
    Board board3 = new Board(UUID.randomUUID(), "Board 1", "MyDescription 1");

        assertNotEquals(board1.hashCode(), board2.hashCode());
        assertNotEquals(board1.hashCode(), board3.hashCode());
    }

    - Test 3: test sameAs method if it is correctly implemented
    @Test
    void sameAs() {
    Board board1 = new Board(UUID.randomUUID(), "Board 1", "MyDescription 1");
    Board board2 = new Board(UUID.randomUUID(), "Board 2", "MyDescription 2");

        assertFalse(board1.sameAs(board2));
    }

    - Test 4: test identity method if it is correctly implemented
    @Test
    void identity() {
        UUID uuid = UUID.randomUUID();
        Board board1 = new Board(uuid, "Board 1", "MyDescription 1");
        Board board2 = board1;

        assertEquals(board2.identity(), board1.identity());
    }
    
    - Test 5: test title method if it is correctly implemented
    @Test
    void title() {
        Board board1 = new Board(UUID.randomUUID(), "Board 1", "MyDescription 1");

        assertEquals("Board 1", board1.title());
    }

## Conclusions

This user story was successfully implemented. The board creation process is now available to the user.
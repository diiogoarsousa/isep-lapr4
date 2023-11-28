# US3009 - View the history of updates on a board

## 1. Context

This user story is a new feature to be developed. As a user, I want to view the history of updates on a board.

## 2. Requirements

As a user, I want to be able to view the history of updates on a board:

- Board to view the history of updates on
- List of updates on the board
- Each update contains:
    - User who made the update
    - Date and time of the update

## 3. Analysis

### 3.1 Pre-Requirements

- User must be logged in
- Board must exist

### 3.2 Post-Requirements

- Board history is displayed

### 3.3 Business Rules

- Any user can view the history of updates on a board
- The history of updates on a board is displayed in reverse chronological order
- The history of updates on a board is paginated

## 4. Design

### 4.1. Realization

The board history process is realized through the following classes:

- User
- Board
- BoardRepository
- JpaBoardRepository
- Database

#### 4.1.1. UC Realization

- The User selects the option to view archived boards.
- The System presents the user with a form to fill with the board information (title, description).
- The User fills the form with the board information.
- The System validates the information.
- The System presents the user with the board.
- The User can now view the history of updates on the board.

#### 4.1.2. Sequence Diagram

![sequence_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3009/Sequence-Diagram.png)

#### 4.1.3. Class Diagram

![class_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3009/Class-Diagram.png)

### 4.2. Domain Model

The domain model for this user story includes the following entities:

- User
- Board
- BoardUpdate

### 4.3. Applied Patterns

This user story uses the Model-View-Controller (MVC).

#### Model

The Model represents the domain-specific knowledge and data in an application. In this use case, the Model includes the
Board and BoardUpdate entities.

#### View

The View renders the model into a form suitable for interaction, typically a user interface element.

#### Controller

The controller pattern assigns the responsibility of dealing with system events to a non-UI class that represents the
overall system or a use case scenario.

#### Repository

The repository pattern mediates between the domain and data mapping layers, acting like an in-memory domain object
collection.

### 4.4. Tests

The following tests were created to ensure the correct behavior of the board update history process:

#### 4.4.1. Unit Tests

    - Test 1: test that the board update history is displayed in reverse chronological order
    @Test
    void testBoardUpdateHistoryOrder() {
        Board board = new Board("Board 1");
        board.addUpdate(new BoardUpdate("User 1", LocalDateTime.of(2022, 1, 1, 10, 0)));
        board.addUpdate(new BoardUpdate("User 2", LocalDateTime.of(2022, 1, 2, 10, 0)));
        board.addUpdate(new BoardUpdate("User 3", LocalDateTime.of(2022, 1, 3, 10, 0)));

        List<BoardUpdate> updates = board.getUpdates();
        assertEquals("User 3", updates.get(0).getUser());
        assertEquals("User 2", updates.get(1).getUser());
        assertEquals("User 1", updates.get(2).getUser());
    }

    - Test 2: Test 2: test that the board update history is paginated
    @Test
    void testBoardUpdateHistoryPagination() {
        Board board = new Board("Board 1");
        for (int i = 0; i < 10; i++) {
            board.addUpdate(new BoardUpdate("User " + i, LocalDateTime.of(2022, 1, i + 1, 10, 0)));
        }
        List<BoardUpdate> updates = board.getUpdates(0, 5);
        assertEquals(5, updates.size());
        assertEquals("User 9", updates.get(0).getUser());
        assertEquals("User 8", updates.get(1).getUser());
        assertEquals("User 7", updates.get(2).getUser());
        assertEquals("User 6", updates.get(3).getUser());
        assertEquals("User 5", updates.get(4).getUser());

        updates = board.getUpdates(5, 5);
        assertEquals(5, updates.size());
        assertEquals("User 4", updates.get(0).getUser());
        assertEquals("User 3", updates.get(1).getUser());
        assertEquals("User 2", updates.get(2).getUser());
        assertEquals("User 1", updates.get(3).getUser());
        assertEquals("User 0", updates.get(4).getUser());
    }

## Conclusions

This user story was successfully implemented. The board sharing process is now available to the user.
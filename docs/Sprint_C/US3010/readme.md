# US3010 - Archive Board

## 1. Context

This user story is a new feature to be developed. As User, I want to archive a board I own.

## 2. Requirements

As a user, I want to be able to view the history of updates on a board:

- Board to archive
- Confirmation message after archiving the board

## 3. Analysis

### 3.1 Pre-Requirements

- User must be logged in
- Board must exist

### 3.2 Post-Requirements

- Board is archived
- Confirmation message is displayed

### 3.3 Business Rules

- Only the owner of the board can archive it
- Once a board is archived, it cannot be edited or accessed
- Archiving a board does not delete it permanently, it can be unarchived

## 4. Design

### 4.1. Realization

The board archiving process is realized through the following classes:

- User
- Board
- BoardRepository
- JpaBoardRepository
- Database

#### 4.1.1. UC Realization

- The User selects the option to archive a board.
- The System presents the user with a form to fill with the board information (title, description).
- The User fills the form with the board information.
- The System validates the information.
- The System archives the board.
- The System displays a confirmation message.

#### 4.1.2. Sequence Diagram

![sequence_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3010/Sequence-Diagram.png)

#### 4.1.3. Class Diagram

![class_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3010/Class-Diagram.png)

### 4.2. Domain Model

The domain model for this user story includes the following entities:

- User
- Board

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

The following tests were created to ensure the correct behavior of the board archiving process:

#### 4.4.1. Unit Tests

    - Test 1: test that only the owner of the board can archive
    @Test
    void testBoardArchiving() {
      User owner = new User("Owner");
      User otherUser = new User("Other User");
      Board board = new Board("Board 1", owner);
      assertFalse(board.isArchived());
      board.archive(owner);
      assertTrue(board.isArchived());
      assertThrows(NotAuthorizedException.class, () -> board.archive(otherUser));
    }

    - Test 2: test that a board cannot be edited or accessed after being archived
    @Test
    void testArchivedBoardAccess() {
      User owner = new User("Owner");
      Board board = new Board("Board 1", owner);
      assertFalse(board.isArchived());
      board.archive(owner);
      assertTrue(board.isArchived());
      assertThrows(BoardArchivedException.class, () -> board.addUpdate(new BoardUpdate("User 1", LocalDateTime.now())));
      assertThrows(BoardArchivedException.class, () -> board.getUpdates());
    }

## Conclusions

This user story was successfully implemented. The board archiving process is now available to the user.
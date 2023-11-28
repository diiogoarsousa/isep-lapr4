# US3004 - Share a Board

## 1. Context

This user story is a new feature to be developed. As a user, I want to be able to share a board with other users.

## 2. Requirements

As a user, I want to be able to share a board with the following information:

- Board to be shared
- Users to share the board with

## 3. Analysis

### 3.1 Pre-Requirements

- User must be logged in
- Board must exist

### 3.2 Post-Requirements

- Board must be shared with the specified users

### 3.3 Business Rules

- Any user can share a board they own
- A user can share a board with multiple users
- The users who have access to the shared board can view and edit the board
- The owner of the board can revoke access to the shared board

## 4. Design

### 4.1. Realization

The board sharing process is realized through the following classes:

- User
- Board
- BoardRepository
- JpaBoardRepository
- Database

#### 4.1.1. UC Realization

- The User selects the option to share a board.
- The System presents the user with a form to fill with the board information (title, description).
- The User fills the form with the board information and selects the users to share the board with.
- The System validates the information.
- The system shares the board with the specified users.
- The System presents message to the user confirming the board was shared.
- The User can now share the board with other users.

#### 4.1.2. Sequence Diagram

![sequence_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3004/Sequence-Diagram.png)

#### 4.1.3. Class Diagram

![class_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3004/Class-Diagram.png)

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

The following tests were created to ensure the correct behavior of the board sharing process:

#### 4.4.1. Unit Tests

    - Test 1: test sharing a board with a user
    @Test
    void testShareBoard() {
        User owner = new User("owner@example.com", "Owner");
        User collaborator = new User("collaborator@example.com", "Collaborator");
        Board board = new Board("Board 1", owner);

        board.share(collaborator);

        assertTrue(board.isSharedWith(collaborator));
    }

    - Test 2: test revoking access to a shared board
    @Test
    void testRevokeAccess() {
        User owner = new User("owner@example.com", "Owner");
        User collaborator = new User("collaborator@example.com", "Collaborator");
        Board board = new Board("Board 1", owner);

        board.shareBoard(collaborator);
        board.revokeAccess(collaborator);

        assertFalse(board.isSharedWith(collaborator));
    }

## Conclusions

This user story was successfully implemented. The board sharing process is now available to the user.
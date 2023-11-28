# User Story 3001: Client and Server Communication

## 1. Context

This user story is about the communication between the client and the server. As a user, I want to be able to connect to
the server, send messages, and disconnect from the server.

## 2. Requirements

As a user, I want to be able to:

- Connect to the server
- Send messages to the server
- Receive messages from the server
- Disconnect from the server

## 3. Analysis

### 3.1 Pre-Requirements

- The user must have a client application installed
- The server must be running

### 3.2 Post-Requirements

- The user can connect to the server
- The user can send messages to the server
- The user can receive messages from the server
- The user can disconnect from the server

### 3.3 Business Rules

- The user can only connect to the server if it is running
- The user can only send messages to the server if it is connected
- The user can only receive messages from the server if it is connected
- The user can only disconnect from the server if it is connected

## 4. Design

### 4.1. Realization

The communication process is realized through the following classes:

- User
- Server
- Client
- Message
- MessageRepository
- JpaMessageRepository
- Database

#### 4.1.1. UC Realization

- The User starts the client application.
- The System presents the user with a login screen.
- The User enters their username and password.
- The System validates the user's credentials.
- The System connects to the server.
- The User can now send and receive messages.
- The User can disconnect from the server.

#### 4.1.2. Sequence Diagram

![sequence_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3001/Sequence-Diagram.svg)

#### 4.1.3. Class Diagram

![class_diagram](/Users/joaosilva/IdeaProjects/sem4pi-22-23-1/docs/Sprint_C/US3001/Class-Diagram.svg)

### 4.2. Domain Model

The domain model for this user story includes the following entities:

- User
- Server
- Client
- Message

### 4.3. Applied Patterns

This user story uses the Model-View-Controller (MVC).

#### Model

The Model represents the domain-specific knowledge and data in an application. In this use case, the Model includes the
User, Server, Client, and Message entities.

#### View

The View renders the model into a form suitable for interaction, typically a user interface element.

#### Controller

The controller pattern assigns the responsibility of dealing with system events to a non-UI class that represents the
overall system or a use case scenario.

#### Repository

The repository pattern mediates between the domain and data mapping layers, acting like an in-memory domain object
collection.

### 4.4. Tests

The following tests were created to ensure the correct behavior of the communication process:

#### 4.4.1. Unit Tests

    - Test 1: test equals method if it is correctly implemented
    @Test
    void testEquals() {

        UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");

        Message message1 = new Message(uuid, "Hello", "World", LocalDateTime.now());
        Message message2 = new Message(uuid, "Hi", "There", LocalDateTime.now());
        Message message3 = new Message(uuid, "Hello", "World", LocalDateTime.now());
        assertNotEquals(message1, message3);
        assertNotEquals(message1, message2);
    }
    
    - Test 2: test hashCode method if it is correctly implemented
    @Test
    void testHashCode() {
    Message message1 = new Message(UUID.randomUUID(), "Hello", "World", LocalDateTime.now());
    Message message2 = new Message(UUID.randomUUID(), "Hi", "There", LocalDateTime.now());
    Message message3 = new Message(UUID.randomUUID(), "Hello", "World", LocalDateTime.now());

        assertNotEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }

    - Test 3: test sameAs method if it is correctly implemented
    @Test
    void sameAs() {
    Message message1 = new Message(UUID.randomUUID(), "Hello", "World", LocalDateTime.now());
    Message message2 = new Message(UUID.randomUUID(), "Hi", "There", LocalDateTime.now());

        assertFalse(message1.sameAs(message2));
    }

    - Test 4: test identity method if it is correctly implemented
    @Test
    void identity() {
        UUID uuid = UUID.randomUUID();
        Message message1 = new Message(uuid, "Hello", "World", LocalDateTime.now());
        Message message2 = message1;

        assertEquals(message2.identity(), message1.identity());
    }
    
    - Test 5: test sender method if it is correctly implemented
    @Test
    void sender() {
        Message message1 = new Message(UUID.randomUUID(), "Hello", "World", LocalDateTime.now());

        assertEquals("Hello", message1.sender());
    }

    - Test 6: test receiver method if it is correctly implemented
    @Test
    void receiver() {
        Message message1 = new Message(UUID.randomUUID(), "Hello", "World", LocalDateTime.now());

        assertEquals("World", message1.receiver());
    }

    - Test 7: test timestamp method if it is correctly implemented
    @Test
    void timestamp() {
        LocalDateTime now = LocalDateTime.now();
        Message message1 = new Message(UUID.randomUUID(), "Hello", "World", now);

        assertEquals(now, message1.timestamp());
    } 

## 5. Conclusion

This user story describes the communication process between the client and the server. The user can connect to the
server, send messages, receive messages, and disconnect from the server. The communication process is realized through
the User, Server, Client, and Message entities. The Model-View-Controller (MVC) pattern is used to implement this user
story.
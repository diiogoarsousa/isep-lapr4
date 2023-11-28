# Project Analysis: Shared Board Use Case

## Overview

* The goal of this project is to create a shared board where multiple users can access and modify the same information 
 without overwriting each other's changes. The solution will use a process-based approach with shared memory and 
 semaphores to ensure that only one user can access the shared memory at a time.


## Requirements

### Functional Requirements
    * Users should be able to access the shared board simultaneously without overwriting each other's changes.

    * Users should be able to add, edit, and delete information on the shared board.

    * The shared board should be persistent, so that changes are saved even if the program is closed and reopened.

    * The solution should be scalable, so that it can handle a large number of users and a large amount of data.

### Non-Functional Requirements

    * The solution should be fast and responsive, with minimal latency between user actions and updates to the shared board.

    * The solution should be secure, with appropriate access controls to prevent unauthorized access to the shared board.

    * The solution should be reliable, with appropriate error handling and recovery mechanisms in case of failures.


## Architecture

    * The solution will use a process-based approach with shared memory and semaphores to ensure that only one user can 
        access the shared memory at a time. Each user will run a separate process that communicates with the shared 
        memory using semaphores to ensure mutual exclusion.

    * The shared memory will contain a data structure that represents the shared board, with each element representing a 
        cell (post-it) on the board. Each cell (post-it) will contain a string that represents the contents of the cell.

    * When a user wants to modify the shared board, they will acquire a semaphore that controls access to the shared memory.
        They will then read the current contents of the cell they want to modify, make their changes, and write the 
        updated contents back to the shared memory. Once they are done, they will release the semaphore to allow other 
        users to access the shared memory.

    * To ensure persistence, the shared memory will be backed by a file on disk. When the program starts, it will read the 
        contents of the file into the shared memory. When the program exits, it will write the contents of the shared 
        memory back to the file.

## Implementation
    * The solution will be implemented in C using the POSIX shared memory and semaphore APIs. 
    * The program will consist of a main process that creates the shared memory and semaphores, and creates child processes
        to handle user interactions with the shared board.

    * The shared memory will be implemented using the shm_open() and mmap() functions to create a shared memory region 
        that can be accessed by multiple processes. The shared memory will be backed by a file on the local disk, which 
        will be created using the open() and ftruncate() functions.

    * The semaphores will be implemented using the sem_open() and sem_wait()/sem_post() functions to create and manipulate 
        semaphores that control access to the shared memory.

    * Each child process will handle user interactions with the shared board. When a user wants to modify the shared board,
        the child process will acquire a semaphore that controls access to the shared memory, read the current 
        contents of the cell they want to modify, make their changes, and write the updated contents back to the shared 
        memory. Once they are done, they will release the semaphore to allow other users to access the shared memory.

    * To ensure persistence, the main process will read the contents of the file into the shared memory when the program 
        starts, and write the contents of the shared memory back to the file when the program exits.

## Conclusion
    This solution provides a scalable and reliable approach to implementing a shared board where multiple users can 
    access and modify the same information without overwriting each other's changes. By using a process-based approach 
    with shared memory and semaphores, the solution ensures that only one user can access the shared memory at a time, 
    while also providing fast and responsive performance. The solution is also secure, with appropriate access controls 
    to prevent unauthorized access to the shared board.
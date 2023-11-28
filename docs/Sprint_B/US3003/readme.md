# US 3003


## 1. Context

In this task we wanted to minimize the synchronization issues that the use of shared boards
can cause. The users can be accessing the same board at the same time, and if they are
the changes made by one user can be overwritten by the other. To avoid this we decided to
implement a prototype solution that would allow some users to access the shared memory space 
at the same time, but only one user would be able to write at a time. This would be done by
using semaphores to control the access to the shared memory space.

## 2. Requirements

As Project Manager, I want the team to "explore" the synchronization problems related to the shyncronization
of shared boards and design a conceptual solution based on practical evidence.

## 3. Analysis

[Project Anaysis](projectAnalysis.md)

## 4. Design

### 4.1. Algorithm

![a_algorithm](algorithm.png "Algorithm")

### 4.2. Class Diagram

![a_class_diagram](Class_Diagram.svg "Class Diagram")

### 4.3. Sequence Diagram

![a_sequence_diagram](Sequence_Diagram.svg "Sequence Diagram")


## 5. Implementation

![implementation](1_42.png "lines_of_code")
![implementation](43_85.png "lines_of_code")
![implementation](86_127.png "lines_of_code")
![implementation](128_137.png "lines_of_code")

## 6. Integration/Demonstration

![print_execution](US3003_run.png "run_print")

## 7. Observations

*N/A*
# US2003 - As a Teacher, I want to view a list of all exams in a course.


## 1. Context

A teacher should be able to view a list of all exams in a course.
## 2. Requirements

- The teacher should be able to select an existing course.
- A list of exams from the selected course, should be shown.

**Github Issue:** #13

## 2.1 Client Clarifications


---

## 2.2 Demos



## 3. Analysis

### 3.1 Pre Requirements

- A course must exist

### 3.2 Post Requirements

- If the selected course has exams, a list of exams should be displayed.
- If the selected course does not have exams, the message "No exams" should be displayed.

### 3.3 Business Rules

## 4. Design

### 4.1. Realization

#### 4.1.1. UC Realization

- The Teacher starts to select the "List of exams in a course" option
- The system shows the list of courses.
- The Teacher selects a course.
- The system shows the exams assigned to the selected course.

#### 4.1.2. Sequence Diagram


#### 4.1.3 Class Diagram


## 4.2 Applied Patterns

### 4.2.1 Architectural  Pattern
An **Onion Architectural Pattern** was used in order to achieve a lower coupling of the system since the dependency was from the outer to the inner layer. A better maintainability of the system is another consequence of the chosen design

**presentation -> application -> persistence -> domain**

### 4.2.2 Design Patterns

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





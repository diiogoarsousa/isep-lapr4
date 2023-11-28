#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <semaphore.h>
#include <errno.h>
#include <time.h>

#define SHM_NAME "/shared_memory"
#define SEM_NOME_BASE "/semaphore_"
#define ARRAY_SIZE 20

// Estrutura para a memória partilhada
typedef struct {
    int data[ARRAY_SIZE];
} shared_data;

// Criar n processos filho
int create_children(int n) {
    pid_t pid;
    int i;
    for (i = 0; i < n; i++) {
        pid = fork();
        if (pid < 0) {
            return -1;
        } else if (pid == 0) {
            // Processo filho
            return i + 1;
        }
    }
    return 0;
}

int main(int argc, char* argv[]) {
    const int NUM_FILHOS = 20;
    int i, id, status;
    sem_t *sems[NUM_FILHOS];
    shared_data *data;
    const int DATA_SIZE = sizeof(shared_data);

    // Criação da memória partilhada
    int fd = shm_open(SHM_NAME, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        perror("failed shm_open!");
        exit(EXIT_FAILURE);
    }
    if (ftruncate(fd, sizeof(shared_data)) == -1) {
        perror("failed ftruncate!");
        exit(EXIT_FAILURE);
    }
    // Mapa para a memória partilhada, o offset é 0 (início do ficheiro), o tamanho é o tamanho da estrutura
    data = mmap(NULL, DATA_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (data == MAP_FAILED) {
        perror("failed mmap!");
        exit(EXIT_FAILURE);
    }

    // Criação dos semáforos
    char sem_name[20];
    for (i = 0; i < NUM_FILHOS; i++) {
        snprintf(sem_name, 20, "%s%d", SEM_NOME_BASE, i);
        sems[i] = sem_open(sem_name, O_CREAT | O_EXCL, 0644, 1);
        if (sems[i] == SEM_FAILED) {
            perror("semáforo não criado");
            exit(EXIT_FAILURE);
        }
    }

    // Criação dos processos filhos
    id = create_children(NUM_FILHOS);
    if (id == -1) {
        perror("fork falhou");
        exit(EXIT_FAILURE);
    }

    // Se for um processo filho, escreve na memória partilhada
    if (id > 0) {
        // Processo filho
        sem_wait(sems[id-1]);
        printf("Filho %d a escrever na memória partilhada.\n", id);
        data->data[id-1] = id;
        sem_post(sems[id-1]);
        exit(EXIT_SUCCESS);
    } else {
        // Processo pai
        for (i = 0; i < NUM_FILHOS; i++) {
            if (wait(&status) == -1) {
                perror("wait falhou");
                exit(EXIT_FAILURE);
            }
            if (!WIFEXITED(status) || WEXITSTATUS(status) != EXIT_SUCCESS) {
                perror("processo filho terminado de forma incorreta");
                exit(EXIT_FAILURE);
            }
        }

        // Leitura da memória partilhada
        sem_wait(sems[0]);
        printf("Processo pai a ler da memória partilhada:\n");
        for (i = 0; i < ARRAY_SIZE; i++) {
            printf("%d ", data->data[i]);
        }
        printf("\n");
        sem_post(sems[0]);

        // Limpeza da memória partilhada e dos semáforos
        for (i = 0; i < NUM_FILHOS; i++) {
            if (sem_close(sems[i]) < 0) {
                perror("sem_close()");
                exit(EXIT_FAILURE);
            }
            snprintf(sem_name, 20, "%s%d", SEM_NOME_BASE, i);
            if (sem_unlink(sem_name) == -1) {
                perror("sem_unlink()");
                exit(EXIT_FAILURE);
            }
        }
        if (munmap(data, DATA_SIZE) == -1) {
            perror("failed munmap!");
            exit(EXIT_FAILURE);
        }
        if (close(fd) == -1){
            perror("failed munmap!");
            exit(EXIT_FAILURE);
        }
        if(id == 0) { //Apenas processo pai
            if (shm_unlink(SHM_NAME) == -1) {
                perror("failed shm_unlink!");
                exit(EXIT_FAILURE);
            }
        }
    }

    return 0;
}
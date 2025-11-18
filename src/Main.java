import java.util.Scanner;
// PASSO 1: A classe 'No' (Node)
// Representa cada elemento (nó) da sua lista encadeada.
class No {
    String tarefa; // O dado que o nó armazena
    No proximo;   // A referência para o próximo nó

    // Construtor
    public No(String tarefa) {
        this.tarefa = tarefa;
        this.proximo = null;
    }
}
// PASSO 2: A classe 'ListaDeTarefas'
// Ela gerencia todas as operações da lista (adicionar, remover, mostrar).
class ListaDeTarefas {
    No inicio;
    // Construtor
    public ListaDeTarefas() {
        this.inicio = null;
    }
    // Método para adicionar uma nova tarefa no FINAL da lista
    public void adicionarTarefa(String descricao) {
        No novaTarefa = new No(descricao);
        if (inicio == null) {
            inicio = novaTarefa;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novaTarefa;
        }
        System.out.println("\n>> Tarefa \"" + descricao + "\" adicionada com sucesso!");
    }

    // Método para remover uma tarefa da lista pela descrição
    public boolean removerTarefa(String descricao) {
        if (inicio == null) {
            System.out.println("\n>> A lista de tarefas já está vazia.");
            return false;
        }
        if (inicio.tarefa.equalsIgnoreCase(descricao)) {
            inicio = inicio.proximo;
            System.out.println("\n>> Tarefa \"" + descricao + "\" removida com sucesso!");
            return true;
        }
        No atual = inicio;
        while (atual.proximo != null && !atual.proximo.tarefa.equalsIgnoreCase(descricao)) {
            atual = atual.proximo;
        }
        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
            System.out.println("\n>> Tarefa \"" + descricao + "\" removida com sucesso!");
            return true;
        } else {
            System.out.println("\n>> Tarefa \"" + descricao + "\" não encontrada na lista.");
            return false;
        }
    }

    // Método para mostrar todas as tarefas na lista
    public void mostrarTarefas() {
        if (inicio == null) {
            System.out.println("\n>> Nenhuma tarefa na lista para mostrar.");
            return;
        }
        System.out.println("\n--- Minha Lista de Tarefas ---");
        No atual = inicio;
        int i = 1;
        while (atual != null) {
            System.out.println(i + ". " + atual.tarefa);
            atual = atual.proximo;
            i++;
        }
        System.out.println("----------------------------");
    }
}
// PASSO 3: Classe principal (Main)
// Agora ela exibe um menu e processa a entrada do usuário.
public class Main {
    public static void main(String[] args) {
        // Cria o objeto Scanner para ler a entrada do teclado
        Scanner scanner = new Scanner(System.in);
        // Cria a nossa lista de tarefas
        ListaDeTarefas minhaLista = new ListaDeTarefas();
        int opcao = 0;
        boolean deveLimpar = true;

        // Loop do menu principal
        while (opcao != 4) {
            if(deveLimpar) {
                limparconsole();
            }
            deveLimpar = true;
            exibirMenu();
            // Tratamento para garantir que o usuário digite um número
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                deveLimpar = false;
                System.out.println("\n>> Erro: Por favor, digite um número válido.");
                continue; // Volta para o início do loop
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite a descrição da nova tarefa: ");
                    String descricao = scanner.nextLine();
                    minhaLista.adicionarTarefa(descricao);
                    break;
                case 2:
                    System.out.print("Digite a descrição da tarefa a ser removida: ");
                    String tarefaParaRemover = scanner.nextLine();
                    minhaLista.removerTarefa(tarefaParaRemover);
                    break;
                case 3:
                    deveLimpar = false;
                    minhaLista.mostrarTarefas();
                    break;
                case 4:
                    System.out.println("\nSaindo do programa. Até mais!");
                    break;
                default:
                    deveLimpar = false;
                    System.out.println("\n>> Opção inválida! Por favor, escolha uma opção de 1 a 4.");
            }
        }
        // Fecha o scanner para liberar recursos
        scanner.close();
    }
    //Esse metodo foi criado com o intuito de limpar o console
public static void limparconsole(){
    try {
        final String os = System.getProperty("os.name");
        if(os.contains("Windowws")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
    } catch (final Exception e) {
        for(int i = 0; i < 50; i++){
            System.err.println();
        }
    }
}
    // Método auxiliar para apenas exibir o menu de opções
    public static void exibirMenu() {
        System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Remover Tarefa");
        System.out.println("3. Mostrar Todas as Tarefas");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }
}

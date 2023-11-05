// Referencias:
// Consulta do Lab1c, disponível no moodle da disciplina.
// Consulta do código realizado semestre passado para Apl1.
// Consulta do código do tokenizer, disponivel do enunciado dessa Aplicação.
// Uso do toCharArray(); Disponível em: https://acervolima.com/java-string-tochararray-com-exemplo/.
// Java forEach: como usar o enhanced-for loop. Disponível em: https://blog.betrybe.com/java-foreach/#1.
// Material do moodle sobre Herança. Disponível em: https://graduacao.mackenzie.br/mod/resource/view.php?id=977139.
// Material do moodle sobre árvores binárias. Disponível em: https://graduacao.mackenzie.br/mod/resource/view.php?id=986864.
// Uso do String.charAt(); Disponível em: https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/String/charAt.
// Algoritmos de busca, inserção e remoção de nós em uma BST. Disponíevem em: https://graduacao.mackenzie.br/mod/assign/view.php?id=995146.

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import Operando.BinaryTree;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int selection = 0;
        String expression = "";
        List<String> tokens = new ArrayList<>();
        BinaryTree binaryTree = new BinaryTree();

        while (selection != 5) {
            System.out.println("------------------------------ MENU ------------------------------");
            System.out.println("1. Entrada da expressão aritmética na notação infixa.");
            System.out.println("2. Criação da árvore binária de expressão aritmética.");
            System.out.println("3. Exibição da árvore binária de expressão aritmética.");
            System.out.println("4. Cálculo da expressão (realizando o percurso da árvore).");
            System.out.println("5. Encerramento do programa.");
            System.out.println("Expressão digitada: " + (expression.isEmpty() ? "<nenhuma>" : expression));

            System.out.print("\nDigite uma opção do menu: ");
            try {
                selection = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Seleção inválida!\n");
                sc.nextLine();
                continue;
            }
            if (selection < 1 || selection > 5) {
                System.out.println("Seleção inválida!\n");
                continue;
            }
            if (selection == 1) {
                tokens = new ArrayList<>();
                binaryTree = new BinaryTree();
                // Adicionando a entrada da expressão digitada pelo usuário
                System.out.print("\nDigite a expressão: ");
                expression = sc.nextLine();
                System.out.print("\n");
                try {
                    Metodos tokenizer = new Metodos(expression);
                    tokens = tokenizer.tokenize();
                } catch (Exception e) {
                    System.out.println("Expressão possui caracter inválido!\n");
                    expression = "<nenhuma>";
                    continue;
                }
                // Verificando se o usuário não apertou ENTER sem digitar nenhuma expressão.
                if (tokens.isEmpty()) {
                    System.out.println("A expressão não é válida pois é nula!\n");
                    expression = "<nenhuma>";
                    continue;
                } else if (!Metodos.check(expression)) {
                    // Verificando a ordem, se tiver, dos parenteses na expressão.
                    System.out.println("A expressão não é válida pois apresenta erros nos parenteses!\n");
                    expression = "<nenhuma>";
                    continue;
                } else if (Metodos.checkDivision(tokens)) {
                    // Verificando se tem divisão por 0.
                    System.out.println("A expressão não é válida pois apresenta divisão por 0!\n");
                    expression = "<nenhuma>";
                    continue;
                } else if (Metodos.checkSingleToken(tokens)) {
                    // Verificando se a expressão contém apenas 1 digito.
                    System.out.println("A expressão não é válida pois apresenta um unico caracter!\n");
                    expression = "<nenhuma>";
                    continue;
                } else if (Metodos.firstLastToken(tokens)) {
                    // Verificando se a expressão contém operador no começo ou no fim
                    System.out.println("A expressão não é válida pois começa ou termina com um operador!\n");
                    expression = "<nenhuma>";
                    continue;
                } else if (Metodos.checkUnario(tokens)) {
                    // Verificando se a expressão contém operadores unarios.
                    System.out.println("A expressão não é válida pois possui operador unário!\n");
                    expression = "<nenhuma>";
                    continue;
                } else {
                    System.out.println("A expressão é válida!\n");
                    continue;
                }
            } else if (selection == 2) {
                if (tokens.isEmpty() || expression == "<nenhuma>") {
                    System.out.println("A expressão ainda não foi digitada ou é inválida!\n");
                    continue;
                }
                List<String> postFix = Metodos.infixToPostfix(tokens);

                binaryTree.buildTreeFromExpression(postFix);
                System.out.print("Arvore binária de expressão aritmética criada com sucesso!\n\n");

            } else if (selection == 3) {
                if (binaryTree.isEmpty()) {
                    System.out.println("Você não criou a árvore ainda!\n");
                    continue;
                }
                System.out.print("Pré-Ordem: ");
                binaryTree.preOrderTraversal();
                System.out.print(" Em Ordem: ");
                binaryTree.inOrderTraversal();
                System.out.print("Pós-Ordem: ");
                binaryTree.postOrderTraversal();
                System.out.println();

            } else if (selection == 4) {
                if (binaryTree.isEmpty()) {
                    System.out.println("Você não criou a árvore ainda!\n");
                    continue;
                }
                float resultado = binaryTree.calculateExpression();
                System.out.print("Resultado é: " + resultado);
                System.out.println("\n");
            }
        }
        System.out.println("Fim!");
        sc.close();
    }
}

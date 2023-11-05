import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class Metodos {

    private char[] input;
    private int index;

    public Metodos(String str) {
        input = str.toCharArray();
        index = 0;
    }

    private char getNextChar() {
        if (index >= input.length) {
            return '\0';
        }
        return input[index++];
    }

    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char currChar = getNextChar();

        boolean isTokenizing = true;
        while (isTokenizing) {
            while (Character.isWhitespace(currChar)) {
                currChar = getNextChar();
            }
            if (Character.isDigit(currChar) || currChar == '.') {
                sb.setLength(0);
                while (Character.isDigit(currChar) || currChar == '.') {
                    sb.append(currChar);
                    currChar = getNextChar();
                }
                tokens.add(sb.toString());
            } else if (currChar == '+') {
                tokens.add("+");
                currChar = getNextChar();
            } else if (currChar == '-') {
                tokens.add("-");
                currChar = getNextChar();
            } else if (currChar == '*') {
                tokens.add("*");
                currChar = getNextChar();
            } else if (currChar == '/') {
                tokens.add("/");
                currChar = getNextChar();
            } else if (currChar == '(') {
                tokens.add("(");
                currChar = getNextChar();
            } else if (currChar == ')') {
                tokens.add(")");
                currChar = getNextChar();
            } else if (currChar == '\0') {
                isTokenizing = false;
            } else {
                throw new IllegalArgumentException("Token não reconhecido");
            }
        }
        return tokens;
    }

    public static boolean checkDivision(List<String> tokens) {
        for (int i = 0; i < tokens.size() - 1; i++) {
            String tokenAtual = tokens.get(i);
            String nextToken = tokens.get(i + 1);
            if (tokenAtual.equals("/") && nextToken.equals("0")) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSingleToken(List<String> tokens) {
        if (tokens.size() == 1) {
            return true;
        }
        return false;
    }

    public static boolean firstLastToken(List<String> tokens) {
        if (isOperator(tokens.get(0)) || isOperator(tokens.get(tokens.size() - 1))) {
            return true;
        }
        return false;
    }

    public static boolean checkUnario(List<String> tokens) {
        for (int i = 1; i < tokens.size() - 1; i++) {
            String tokenAtual = tokens.get(i);
            String nextToken = tokens.get(i + 1);
            String lastToken = tokens.get(i - 1);
            if (isOperator(tokenAtual)) {
                if (isOperator(nextToken) || isOperator(lastToken)) {
                    return true;
                }
            } else if (tokenAtual.equals("(") && isOperator(nextToken)) {
                return true;
            } else if (tokenAtual.equals(")") && isOperator(lastToken)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    public static boolean check(String expression) {
        char[] expressionArr = expression.toCharArray();
        Stack<Character> charStack = new Stack<>();

        for (char letter : expressionArr) {
            if (letter == '(') {
                charStack.push('(');
            } else if (letter == ')') {
                if (charStack.isEmpty() || charStack.pop() != '(') {
                    return false;
                }
            }
        }
        return charStack.isEmpty();
    }

    public static List<String> infixToPostfix(List<String> infix) {
        List<String> postfix = new ArrayList<>(); // Lista de strings para armazenar a expressão pós-fixa.
        Stack<Character> stack = new Stack<>(); // Pilha para ajudar na conversão

        for (String token : infix) {
            char c = token.charAt(0); // caractere atual

            if (Character.isDigit(c) || c == '.') {
                // Se o caractere for um dígito ou '.', adicioná-lo diretamente à expressão
                // pós-fixa.
                postfix.add(token);
            } else if (c == '(') {
                // Se o caractere for '(', empilhá-lo na pilha
                stack.push(c);
            } else if (c == ')') {
                // Se o caractere for ')', desempilhar os operadores da pilha até encontrar '('
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.add(String.valueOf(stack.pop()));
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return new ArrayList<>();
                } else {
                    stack.pop(); // Remover o '(' da pilha
                }
            } else {
                // Se o caractere for um operador, manipular precedência
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.add(String.valueOf(stack.pop())); // Desempilhar operadores de alta precedência
                }
                stack.push(c); // Empilhar o operador atual
            }
        }

        // Desempilhar qualquer operador restante na pilha
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return new ArrayList<>(); // Verificar se há parênteses de abertura não correspondidos
            }
            postfix.add(String.valueOf(stack.pop()));
        }

        return postfix; // Retornar a expressão pós-fixa como uma string
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}

public class Converter {

    public static String conversao(String infixa) {
        String posfixa = "";
        Pilha<Character> pilha = new Pilha<>(infixa.length());

        for (int i = 0; i < infixa.length(); i++) {
            char c = infixa.charAt(i);

            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    while (!pilha.vazia() && prioridade(c, pilha.peek())) {
                        posfixa += pilha.desempilhar();
                    }
                    pilha.empilha(c);
                    break;
                case '(':
                    pilha.empilha(c);
                    break;
                case ')':
                    while (!pilha.vazia() && pilha.peek() != '(') {
                        posfixa += pilha.desempilhar();
                    }
                    if (!pilha.vazia() && pilha.peek() == '(') {
                        pilha.desempilhar();
                    }
                    break;
                default:
                    posfixa += c;
                    break;
            }
        }

        while (!pilha.vazia()) {
            posfixa += pilha.desempilhar();
        }

        return posfixa;
    }

    public static boolean prioridade(char operador1, char operador2) {
        if (operador2 == '(' || operador2 == ')') {
            return false;
        }
        if ((operador1 == '*' || operador1 == '/') && (operador2 == '+' || operador2 == '-')) {
            return false;
        }
        if (operador1 == '^' && (operador2 == '+' || operador2 == '-' || operador2 == '*' || operador2 == '/')) {
            return false;
        }
        return true;
    }

    public static void verifica(String expressao) {
        Pilha<Character> pilha = new Pilha<>(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(') {
                pilha.empilha(c);
            } else if (c == ')') {
                if (pilha.vazia() || pilha.peek() != '(') {
                    throw new IllegalArgumentException();
                }
                pilha.desempilhar();
            } else if (!Character.isDigit(c) && !Character.isWhitespace(c) && !confereOperador(c)) {
                throw new IllegalArgumentException();
            }

            if (c == '(' && i + 1 < expressao.length() && confereOperador(expressao.charAt(i + 1))) {
                throw new IllegalArgumentException();
            }
        }

        if (!pilha.vazia()) {
            throw new IllegalArgumentException();
        }

    }

    public static boolean confereOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    public static int resolverExpressao(String posfixa) {
        Pilha<Integer> pilha = new Pilha<>(10);

        for (int i = 0; i < posfixa.length(); i++) {
            char c = posfixa.charAt(i);

            if (Character.isDigit(c)) {
                pilha.empilha(Character.getNumericValue(c));
            } else {
                int num2 = pilha.desempilhar();
                int num1 = pilha.desempilhar();
                int resultado = realizarOperacao(num1, num2, c);
                pilha.empilha(resultado);
            }
        }

        return pilha.desempilhar();
    }

    public static int realizarOperacao(int num1, int num2, char operador) {
        switch (operador) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '^':
                return (int) Math.pow(num1, num2);
            default:
                throw new IllegalArgumentException("Operador inválido: " + operador);
        }
    }
}


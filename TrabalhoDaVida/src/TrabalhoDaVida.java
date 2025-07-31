import java.util.Scanner;

public class TrabalhoDaVida {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Converter auxiliar = new Converter();
        int continuar = 0;

        do {
            System.out.print("Expressão: ");
            String expressao = sc.next();

            try {
                auxiliar.verifica(expressao);

                String posfixa = auxiliar.conversao(expressao);
                System.out.println(posfixa);

                int resultado = auxiliar.resolverExpressao(posfixa);
                System.out.println("Resultado: " + resultado);
            } catch (IllegalArgumentException e) {
                System.out.println("Expressão inválida");
            } catch (Exception e) {
                System.out.println("Expressão inválida");
            }

            System.out.println("Deseja continuar? 1 para sim || outro número para não");
            System.out.print("Digite: ");
            continuar = sc.nextInt();
            System.out.println("");
        } while (continuar == 1);

        sc.close();
    }

}


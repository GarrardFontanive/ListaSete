import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Q5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[] numerosSorteados = gerarNumerosUnicos(6, random);
        Arrays.sort(numerosSorteados);

        System.out.print("Quantas cartelas deseja preencher? ");
        int qtdCartelas = scanner.nextInt();

        int[][] cartelas = new int[qtdCartelas][6];
        for (int i = 0; i < qtdCartelas; i++) {
            cartelas[i] = gerarNumerosUnicos(6, random);
            Arrays.sort(cartelas[i]);
        }

        System.out.println("\n--- NÚMEROS SORTEADOS ---");
        System.out.println(Arrays.toString(numerosSorteados));

        System.out.println("\n--- SUAS CARTELAS E ACERTOS ---");
        for (int i = 0; i < qtdCartelas; i++) {
            int acertos = contarAcertos(cartelas[i], numerosSorteados);
            System.out.printf("Cartela %d: %s -> %d acertos\n",
                    i + 1, Arrays.toString(cartelas[i]), acertos);
        }

        gravarLoteria(numerosSorteados, cartelas, "loteria_jogos.txt");

        scanner.close();
    }

    public static int[] gerarNumerosUnicos(int quantidade, Random random) {
        Set<Integer> numeros = new HashSet<>();
        while (numeros.size() < quantidade) {
            numeros.add(random.nextInt(60) + 1);
        }

        int[] resultado = new int[quantidade];
        int i = 0;
        for(Integer num : numeros){
            resultado[i++] = num;
        }
        return resultado;
    }

    public static int contarAcertos(int[] cartela, int[] sorteados) {
        int cont = 0;
        for (int numCartela : cartela) {
            for (int numSorteado : sorteados) {
                if (numCartela == numSorteado) {
                    cont++;
                }
            }
        }
        return cont;
    }

    public static void gravarLoteria(int[] sorteados, int[][] cartelas, String nomeArquivo){
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            writer.println(Arrays.toString(sorteados));
            for(int i = 0; i < cartelas.length; i++){
                writer.println(Arrays.toString(cartelas[i]));
            }
            System.out.println("\nJogos gravados com sucesso em " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao gravar o arquivo.");
        }
    }
}
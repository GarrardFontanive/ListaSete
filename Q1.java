import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Q1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = null;

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Criar e preencher uma nova matriz");
            System.out.println("2. Ler matriz de um arquivo");
            System.out.println("3. Ver informações da matriz");
            System.out.println("4. Gravar matriz em arquivo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    matriz = criarEPreencherMatriz(scanner);
                    break;
                case 2:
                    matriz = lerMatrizDeArquivo("matriz_dados.txt");
                    break;
                case 3:
                    if (matriz != null) {
                        mostrarMatriz(matriz);
                        mostrarInformacoes(matriz);
                    } else {
                        System.out.println("Nenhuma matriz carregada. Crie ou leia uma primeiro.");
                    }
                    break;
                case 4:
                    if (matriz != null) {
                        gravarMatrizEmArquivo(matriz, "matriz_dados.txt");
                    } else {
                        System.out.println("Nenhuma matriz para gravar.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static int[][] criarEPreencherMatriz(Scanner scanner) {
        int tamanho;
        while (true) {
            System.out.print("Digite um valor inteiro ímpar entre 3 e 11: ");
            tamanho = scanner.nextInt();
            if (tamanho >= 3 && tamanho <= 11 && tamanho % 2 != 0) {
                break;
            } else {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }

        int[][] matriz = new int[tamanho][tamanho];
        System.out.println("Digite os valores da matriz:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.printf("Posição [%d][%d]: ", i, j);
                matriz[i][j] = scanner.nextInt();
            }
        }
        return matriz;
    }

    public static void mostrarMatriz(int[][] matriz) {
        System.out.println("\n--- Matriz ---");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%5d", matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static void mostrarInformacoes(int[][] matriz) {
        int soma = 0;
        int maior = matriz[0][0];
        int menor = matriz[0][0];
        int pares = 0;
        int impares = 0;
        int somaDiagPrincipal = 0;
        int somaDiagSecundaria = 0;
        int tamanho = matriz.length;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int valor = matriz[i][j];
                soma += valor;
                if (valor > maior) maior = valor;
                if (valor < menor) menor = valor;
                if (valor % 2 == 0) pares++;
                else impares++;
                if (i == j) somaDiagPrincipal += valor;
                if (i + j == tamanho - 1) somaDiagSecundaria += valor;
            }
        }

        double media = (double) soma / (tamanho * tamanho);

        System.out.println("\n--- Informações ---");
        System.out.println("Soma dos elementos: " + soma);
        System.out.printf("Média dos elementos: %.2f\n", media);
        System.out.println("Maior valor: " + maior);
        System.out.println("Menor valor: " + menor);
        System.out.println("Quantidade de pares: " + pares);
        System.out.println("Quantidade de ímpares: " + impares);
        System.out.println("Soma da diagonal principal: " + somaDiagPrincipal);
        System.out.println("Soma da diagonal secundária: " + somaDiagSecundaria);
    }

    public static void gravarMatrizEmArquivo(int[][] matriz, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    writer.print(matriz[i][j] + " ");
                }
                writer.println();
            }
            System.out.println("Matriz gravada com sucesso em " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao gravar o arquivo.");
        }
    }

    public static int[][] lerMatrizDeArquivo(String nomeArquivo) {
        try (Scanner fileScanner = new Scanner(new File(nomeArquivo))) {
            Scanner tempScanner = new Scanner(new File(nomeArquivo));
            int linhas = 0;
            int colunas = 0;
            if(tempScanner.hasNextLine()) {
                linhas++;
                colunas = tempScanner.nextLine().trim().split("\\s+").length;
            }
            while(tempScanner.hasNextLine()){
                tempScanner.nextLine();
                linhas++;
            }
            tempScanner.close();

            if (linhas == 0) return null;

            int[][] matriz = new int[linhas][colunas];
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if (fileScanner.hasNextInt()) {
                        matriz[i][j] = fileScanner.nextInt();
                    }
                }
            }
            System.out.println("Matriz lida com sucesso de " + nomeArquivo);
            return matriz;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            return null;
        }
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
        final int N_PRODUTOS = 5;
        final int N_SUPERMERCADOS = 5;
        double[][] precos = new double[N_PRODUTOS][N_SUPERMERCADOS];
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n--- MENU SUPERMERCADO ---");
            System.out.println("1. Digitar preços");
            System.out.println("2. Ler preços de arquivo");
            System.out.println("3. Ver relatório");
            System.out.println("4. Gravar preços em arquivo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    digitarPrecos(precos, scanner);
                    break;
                case 2:
                    precos = lerPrecos("precos_supermercado.txt");
                    break;
                case 3:
                    mostrarRelatorio(precos);
                    break;
                case 4:
                    gravarPrecos(precos, "precos_supermercado.txt");
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

    public static void digitarPrecos(double[][] precos, Scanner scanner) {
        for (int i = 0; i < precos.length; i++) {
            for (int j = 0; j < precos[i].length; j++) {
                System.out.printf("Preço do Produto %d no Supermercado %d: ", i + 1, j + 1);
                precos[i][j] = scanner.nextDouble();
            }
        }
    }

    public static void mostrarRelatorio(double[][] precos) {
        System.out.println("\n--- Tabela de Preços ---");
        System.out.print("Produto   ");
        for (int j = 0; j < precos[0].length; j++) {
            System.out.printf("  Superm. %d", j + 1);
        }
        System.out.println();
        for (int i = 0; i < precos.length; i++) {
            System.out.printf("Produto %d ", i + 1);
            for (int j = 0; j < precos[i].length; j++) {
                System.out.printf("   %8.2f", precos[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n--- Média de Preço por Produto ---");
        for (int i = 0; i < precos.length; i++) {
            double somaProduto = 0;
            for (int j = 0; j < precos[i].length; j++) {
                somaProduto += precos[i][j];
            }
            System.out.printf("Média Produto %d: R$ %.2f\n", i + 1, somaProduto / precos[i].length);
        }

        System.out.println("\n--- Soma de Preços por Supermercado ---");
        double[] somaSupermercado = new double[precos[0].length];
        for (int j = 0; j < precos[0].length; j++) {
            double totalSupermercado = 0;
            for (int i = 0; i < precos.length; i++) {
                totalSupermercado += precos[i][j];
            }
            somaSupermercado[j] = totalSupermercado;
            System.out.printf("Total Supermercado %d: R$ %.2f\n", j + 1, totalSupermercado);
        }

        double maisCaro = somaSupermercado[0];
        double maisBarato = somaSupermercado[0];
        for (int j = 1; j < somaSupermercado.length; j++) {
            if (somaSupermercado[j] > maisCaro) maisCaro = somaSupermercado[j];
            if (somaSupermercado[j] < maisBarato) maisBarato = somaSupermercado[j];
        }

        System.out.println("\n--- Compra Total ---");
        System.out.printf("Valor no supermercado mais barato: R$ %.2f\n", maisBarato);
        System.out.printf("Valor no supermercado mais caro: R$ %.2f\n", maisCaro);
    }

    public static void gravarPrecos(double[][] precos, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            for (int i = 0; i < precos.length; i++) {
                for (int j = 0; j < precos[i].length; j++) {
                    writer.print(precos[i][j] + " ");
                }
                writer.println();
            }
            System.out.println("Preços gravados com sucesso em " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao gravar o arquivo.");
        }
    }

    public static double[][] lerPrecos(String nomeArquivo) {
        double[][] precos = new double[5][5];
        try (Scanner fileScanner = new Scanner(new File(nomeArquivo))) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (fileScanner.hasNextDouble()) {
                        precos[i][j] = fileScanner.nextDouble();
                    }
                }
            }
            System.out.println("Preços lidos com sucesso de " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            return new double[5][5];
        }
        return precos;
    }
}
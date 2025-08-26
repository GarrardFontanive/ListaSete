import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Q3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nomes = null;
        double[][] notas = null;

        System.out.print("Quantos alunos deseja cadastrar? ");
        int n = scanner.nextInt();
        scanner.nextLine();

        nomes = new String[n];
        notas = new double[n][2];

        digitarDadosAlunos(nomes, notas, scanner);
        mostrarRelatorio(nomes, notas);

        gravarDados(nomes, notas, "dados_alunos.txt");
        lerDados("dados_alunos.txt");

        scanner.close();
    }

    public static void digitarDadosAlunos(String[] nomes, double[][] notas, Scanner scanner) {
        for (int i = 0; i < nomes.length; i++) {
            System.out.printf("Digite o nome do aluno %d: ", i + 1);
            nomes[i] = scanner.nextLine();
            System.out.printf("Digite a nota 1 de %s: ", nomes[i]);
            notas[i][0] = scanner.nextDouble();
            System.out.printf("Digite a nota 2 de %s: ", nomes[i]);
            notas[i][1] = scanner.nextDouble();
            scanner.nextLine();
        }
    }

    public static void mostrarRelatorio(String[] nomes, double[][] notas) {
        double[] medias = new double[nomes.length];
        double somaGeral = 0;

        System.out.println("\n--- Boletim da Turma ---");
        for (int i = 0; i < nomes.length; i++) {
            double mediaAluno = (notas[i][0] + notas[i][1]) / 2;
            medias[i] = mediaAluno;
            somaGeral += mediaAluno;
            System.out.printf("Aluno: %-15s | Nota 1: %.2f | Nota 2: %.2f | Média: %.2f\n",
                    nomes[i], notas[i][0], notas[i][1], mediaAluno);
        }

        double maiorNota = notas[0][0];
        String alunoMaiorNota = nomes[0];
        double menorNota = notas[0][0];
        String alunoMenorNota = nomes[0];

        for (int i = 0; i < nomes.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (notas[i][j] > maiorNota) {
                    maiorNota = notas[i][j];
                    alunoMaiorNota = nomes[i];
                }
                if (notas[i][j] < menorNota) {
                    menorNota = notas[i][j];
                    alunoMenorNota = nomes[i];
                }
            }
        }

        double maiorMedia = medias[0];
        String alunoMaiorMedia = nomes[0];
        double menorMedia = medias[0];
        String alunoMenorMedia = nomes[0];

        for(int i = 1; i < medias.length; i++){
            if(medias[i] > maiorMedia){
                maiorMedia = medias[i];
                alunoMaiorMedia = nomes[i];
            }
            if(medias[i] < menorMedia){
                menorMedia = medias[i];
                alunoMenorMedia = nomes[i];
            }
        }

        double mediaGeralTurma = somaGeral / nomes.length;

        System.out.println("\n--- Destaques da Turma ---");
        System.out.printf("Maior nota: %.2f (Aluno: %s)\n", maiorNota, alunoMaiorNota);
        System.out.printf("Menor nota: %.2f (Aluno: %s)\n", menorNota, alunoMenorNota);
        System.out.printf("Maior média: %.2f (Aluno: %s)\n", maiorMedia, alunoMaiorMedia);
        System.out.printf("Menor média: %.2f (Aluno: %s)\n", menorMedia, alunoMenorMedia);
        System.out.printf("\nMédia geral da turma: %.2f\n", mediaGeralTurma);

        System.out.println("\nAlunos com média ACIMA da média geral:");
        for (int i = 0; i < nomes.length; i++) {
            if (medias[i] > mediaGeralTurma) {
                System.out.println("- " + nomes[i]);
            }
        }

        System.out.println("\nAlunos com média ABAIXO da média geral:");
        for (int i = 0; i < nomes.length; i++) {
            if (medias[i] < mediaGeralTurma) {
                System.out.println("- " + nomes[i]);
            }
        }
    }

    public static void gravarDados(String[] nomes, double[][] notas, String nomeArquivo){
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            writer.println(nomes.length);
            for(int i = 0; i < nomes.length; i++){
                writer.printf("%s,%.2f,%.2f\n", nomes[i], notas[i][0], notas[i][1]);
            }
            System.out.println("\nDados gravados com sucesso em " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao gravar dados.");
        }
    }

    public static void lerDados(String nomeArquivo){
        try (Scanner fileScanner = new Scanner(new File(nomeArquivo))) {
            System.out.println("\n--- Lendo dados de " + nomeArquivo + " ---");
            int n = Integer.parseInt(fileScanner.nextLine());
            for(int i = 0; i < n; i++){
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }
}
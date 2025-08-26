import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Q4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantos atletas irão competir? ");
        int nAtletas = scanner.nextInt();
        scanner.nextLine();

        String[] nomes = new String[nAtletas];
        double[][] saltos = new double[nAtletas][5];
        double[] medias = new double[nAtletas];

        for (int i = 0; i < nAtletas; i++) {
            System.out.printf("\n--- Atleta %d ---\n", i + 1);
            System.out.print("Nome do atleta: ");
            nomes[i] = scanner.nextLine();

            double somaSaltos = 0;
            double piorSalto = Double.MAX_VALUE;

            for (int j = 0; j < 5; j++) {
                System.out.printf("Digite o salto %d: ", j + 1);
                saltos[i][j] = scanner.nextDouble();
                somaSaltos += saltos[i][j];
                if (saltos[i][j] < piorSalto) {
                    piorSalto = saltos[i][j];
                }
            }
            scanner.nextLine();

            medias[i] = (somaSaltos - piorSalto) / 4;
            System.out.printf("Média do atleta %s: %.2f\n", nomes[i], medias[i]);
        }

        mostrarResultadosFinais(nomes, saltos, medias);
        gravarResultados(nomes, saltos, "saltos.txt");

        scanner.close();
    }

    public static void mostrarResultadosFinais(String[] nomes, double[][] saltos, double[] medias) {
        System.out.println("\n--- RESULTADO FINAL ---");
        for(int i = 0; i < nomes.length; i++) {
            System.out.printf("Atleta: %s | Saltos: %s | Média: %.2f\n",
                    nomes[i], Arrays.toString(saltos[i]), medias[i]);
        }

        int indiceCampeao = 0;
        int indiceUltimo = 0;
        for (int i = 1; i < medias.length; i++) {
            if (medias[i] > medias[indiceCampeao]) {
                indiceCampeao = i;
            }
            if (medias[i] < medias[indiceUltimo]) {
                indiceUltimo = i;
            }
        }

        System.out.println("\n--- PÓDIO ---");
        System.out.printf("Campeão: %s com média de %.2f\n", nomes[indiceCampeao], medias[indiceCampeao]);
        System.out.printf("Último lugar: %s com média de %.2f\n", nomes[indiceUltimo], medias[indiceUltimo]);
    }

    public static void gravarResultados(String[] nomes, double[][] saltos, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(nomeArquivo)) {
            writer.println(nomes.length);
            for (int i = 0; i < nomes.length; i++) {
                writer.print(nomes[i] + ",");
                for (int j = 0; j < saltos[i].length; j++) {
                    writer.print(saltos[i][j] + (j == saltos[i].length - 1 ? "" : ","));
                }
                writer.println();
            }
            System.out.println("\nResultados gravados com sucesso em " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao gravar o arquivo.");
        }
    }
}
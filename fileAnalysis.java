import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;

public class fileAnalysis {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("FIle name has not been inputted correctly");
            return;
        }
        String fileName = args[0];
        Map<String, Integer> nameIndexMap = new HashMap<>();
        int[][] adjacencyMatrix = MatrixBuilder(fileName, nameIndexMap);

        Task1 task1 = new Task1();
        double density = task1.calculateDensity(adjacencyMatrix);

        Task2 task2 = new Task2();
        int vertexIndex = task2.nodeWithMostInboundEdges(adjacencyMatrix);

        String vertexName = getKeyFromValue (nameIndexMap, vertexIndex);

        System.out.println("%.2f\n", density);
        System.out.println(vertexName);

    }

    private static int[][] MatrixBuilder(String fileName, Map<String, Integer> nameIndexMap){
        List<String[]> edges = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                
            }
    }
}
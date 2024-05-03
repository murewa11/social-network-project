import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class fileAnalysis {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("File name has not been inputted correctly");
            return;
        }
        String fileName = args[0];
        Map<String, Integer> nameIndexMap = new HashMap<>();
        int[][] adjacencyMatrix = MatrixBuilder(fileName, nameIndexMap);

        Task1 task1 = new Task1();
        float density = task1.calculateDensity(adjacencyMatrix);

        Task2 task2 = new Task2();
        int vertexIndex = task2.nodeWithMostInboundEdges(adjacencyMatrix);

        String vertexName = getKeyFromValue (nameIndexMap, vertexIndex);

        System.out.printf("%.4f\n", density);
        System.out.println(vertexName);

    }

    private static int[][] MatrixBuilder(String fileName, Map<String, Integer> nameIndexMap)throws IOException, FileNotFoundException{
       File file = new File(fileName);
       Scanner scanner = new Scanner(file);
       
       int size = determineMatrixSize(scanner, nameIndexMap);
       int [][] matrix = new int[size][size];

       scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] names = line.split(" ");
                int fromIndex = nameIndexMap.get(names[0]);
                for (int i = 1; i < names.length; i++){
                    int toIndex = nameIndexMap.get(names[i]);
                    matrix[fromIndex][toIndex] = 1;
                }
                
            }
            scanner.close();

            return matrix;
    }

    private static int determineMatrixSize(Scanner scanner, Map<String, Integer> nameIndexMap) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] names = line.split(" ");
            for (int i = 0; i < names.length; i++){
                String name = names[i];
                nameIndexMap.putIfAbsent(name, nameIndexMap.size());
            }
        }
        return nameIndexMap.size();
    }
    private static String getKeyFromValue(Map<String, Integer> map, int value ){
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
import java.io.*;
import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class fileAnalysis {
    public static void main(String[] args) throws IOException {
        // Checks if the command line argument for the file name is provided
        if (args.length < 1) {
            System.err.println("File name has not been inputted correctly");
            return;
        }
        String fileName = args[0]; // Stores file name from command
        Map<String, Integer> nameIndexMap = new HashMap<>(); // Creates hash map to track names in file and their indicies
        int[][] adjacencyMatrix = MatrixBuilder(fileName, nameIndexMap); // Declares adjacency matrix

        Task1 task1 = new Task1(); // Creates an instance of Task1
        float density = task1.calculateDensity(adjacencyMatrix); // Calculates the density of the graph

        Task2 task2 = new Task2(); // Creates an instance of Task2
        int vertexIndex = task2.nodeWithMostInboundEdges(adjacencyMatrix); // Finds vertex with the most inbound edges

        String vertexName = getKeyFromValue (nameIndexMap, vertexIndex); // Retrieves the name of the vertex with most inbound edges

        System.out.printf("%.4f\n", density);
        System.out.println(vertexName);

    }

    private static int[][] MatrixBuilder(String fileName, Map<String, Integer> nameIndexMap)throws IOException, FileNotFoundException{
       File file = new File(fileName); // Creates a File object
       Scanner scanner = new Scanner(file); // Initialiases a scanner
       
       //Determines size of the matrix and initialises adjacency matrix
       int size = determineMatrixSize(scanner, nameIndexMap);
       int [][] matrix = new int[size][size];

       scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] names = line.split(" "); // Splits names in same line into elements of an array called 'names'
                // Gets the index for the first name, and index for connected names
                int fromIndex = nameIndexMap.get(names[0]);
                for (int i = 1; i < names.length; i++){
                    int toIndex = nameIndexMap.get(names[i]);
                    matrix[fromIndex][toIndex] = 1; // Updates the matrix to show a directed edge
                }
                
            }
            scanner.close(); 

            return matrix;
    }

    private static int determineMatrixSize(Scanner scanner, Map<String, Integer> nameIndexMap) {
    // Method determines the required size of the matrix by reading through the file and updating a map with unique names
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine(); // Read each line
            String[] names = line.split(" "); // Splits names in same line into elements of an array called 'names'
            for (int i = 0; i < names.length; i++){
                String name = names[i];
                nameIndexMap.putIfAbsent(name, nameIndexMap.size()); // Add unique names to maps
            }
        }
        return nameIndexMap.size(); // return the number of unique names
    }
    private static String getKeyFromValue(Map<String, Integer> map, int value ){
        for (Map.Entry<String, Integer> entry : map.entrySet()){ //Iterates through each entry in map, with each entry consisting of a key-pair value
            if (entry.getValue().equals(value)) {
            // Check if the current entry's value matches with the specified value
                return entry.getKey();
            }
        }
        return null;
    }
}
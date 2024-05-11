import java.io.*;
import java.util.*;

public class Analysis {
    public static void main(String[] args) throws IOException {
        // Checks if the command line argument for the file name is provided
        if (args.length < 1) {
            System.err.println("File name has not been inputted correctly");
            return;
        }
        
        String fileName = args[0]; // Stores file name from command
        Map<String, Integer> nameIndexMap = new HashMap<>(); // Creates hash map to track names in file and their indicies
        int[][] adjacencyMatrix = MatrixBuilder(fileName, nameIndexMap); // Declares adjacency matrix

         // Reverse map from indices to names for Task 3
        Map<Integer, String> indexNameMap = new HashMap<>();
        nameIndexMap.forEach((name, index) -> indexNameMap.put(index, name));
       
        Task1 task1 = new Task1(); // Creates an instance of Task1
        float density = task1.calculateDensity(adjacencyMatrix); // Calculates the density of the graph
        System.out.printf("%.4f\n", density);

        Task2 task2 = new Task2(); // Creates an instance of Task2
        String Task2vertexName = task2.nodeWithMostInboundEdges(adjacencyMatrix, indexNameMap); // Finds vertex with the most inbound edges
        System.out.println(Task2vertexName);

        Task3 task3 = new Task3(); // Creates ab instance of Task3
        String Task3vertexName = task3.nodeWithMostOutboundEdges(adjacencyMatrix, indexNameMap);
        System.out.println(Task3vertexName);

        Task5 task5= new Task5();
        double median = task5.MedianOutboundEdges(adjacencyMatrix);
        System.out.printf("%.2f\n", median);
        
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

}
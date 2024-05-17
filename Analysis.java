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
        List<String> nameList = new ArrayList<>();
        int[][] adjacencyMatrix = MatrixBuilder(fileName, nameIndexMap, nameList); // Declares adjacency matrix
        String[] userNames = nameList.toArray(new String[0]);

        // Reverse map from indices to names for Task 3
        Map<Integer, String> indexNameMap = new HashMap<>();
        nameIndexMap.forEach((name, index) -> indexNameMap.put(index, name));
       
        Task1 task1 = new Task1(); // Creates an instance of Task1
        float density = task1.calculateDensity(adjacencyMatrix); 
        System.out.printf("Calculation density is: %.4f\n", density);

        Task2 task2 = new Task2(); // Creates an instance of Task2
        String Task2vertexName = task2.PersonWithMostFollowers(adjacencyMatrix, indexNameMap); 
        System.out.println("Person with the most followers is:" + Task2vertexName);

        Task3 task3 = new Task3(); // Creates an instance of Task3
        String Task3vertexName = task3.PersonWithMostFollowing(adjacencyMatrix, indexNameMap);
        System.out.println("Person with the highest following is:" + Task3vertexName);

        Task5 task5= new Task5(); // Creates an instance of Task5
        double median = task5.MedianNumberOfFollowers(adjacencyMatrix);
        System.out.printf("Median number of followers is: %.2f\n", median);

        Task6 task6 = new Task6(); // Creates an instance of Task6
        String bestPerson = task6.findBestPersonToEnroll(adjacencyMatrix, userNames);
        System.out.println("Best person to enroll is: " + bestPerson);
        
    }

    private static int[][] MatrixBuilder(String fileName, Map<String, Integer> nameIndexMap, List<String> nameList)throws IOException, FileNotFoundException{
       List<String[]> lines = new ArrayList<>(); // List to store lines of names
       // Reading the file and populating the nameIndexMap and nameList
       try (Scanner scanner = new Scanner(new File(fileName))){
            while (scanner.hasNextLine()) {
                String[] names = scanner.nextLine().split(" "); // Splits names in same line into elements of an array called 'names'
                lines.add(names);// Add the line to the list of lines

                // Iterate over the names to populate nameIndexMap and nameList
                for (String name : names) {
                    if (!nameIndexMap.containsKey(name)) {
                        nameIndexMap.put(name, nameIndexMap.size()); // Assign a new index to the name
                        nameList.add(name); // Add the name to the name list
                    }
                }
            }
        }

       // Initialize the adjacency matrix with zeros 
       int[][] matrix = new int[nameIndexMap.size()][nameIndexMap.size()];
            
       // Populate the adjacency matrix based on the relationships in the lines  
       for (String[] names : lines) {
            int fromIndex = nameIndexMap.get(names[0]); // The first name in the line
            // For each name that the first name follows, set the corresponding matrix entry to 1
            for (int i = 1; i < names.length; i++){
                int toIndex = nameIndexMap.get(names[i]);
                matrix[toIndex][fromIndex] = 1; // Updates the matrix to show a directed edge
            }
       }


        return matrix; // Return the adjacency matrix
    }

}
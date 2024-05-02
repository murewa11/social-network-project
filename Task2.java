public class Task2 {
    
    public int nodeWithMostInboundEdges(int[][] adjacencyMatrix){
        int numVertices = adjacencyMatrix.length;
        // Number of vertices within the graph  
        int[] degree = new int[numVertices];
        // Array stores the degree for each vertex. Each element is initialised to zero

        for (int j = 0; j < numVertices; j ++ ){
            for (int i = 0; i < numVertices; i ++ ){    
                degree[j] += adjacencyMatrix[i][j];
            }
        }
        /* Outer loop iterates through each column, while the inner loop adds the values from each row */

        int LargestDegree = 0;
        int LargestNode = 0;

        for (int j = 0; j < numVertices; j ++ ){
            if (degree[j] > LargestDegree) {
                LargestDegree = degree[j];
                LargestNode = j;
            }
        }
        /* Loop traverses through degree array. If degree value for the node is higher than the initiliased value of
         * the LargestDegree, it replaces the initiliased value.
         * Also, the corresponding node value replaces the initialised value of the LargestNode attribute.
        */

        return LargestNode;
        
    }
}
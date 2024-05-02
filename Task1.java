public class Task1 {
    
    public double calculateDensity(int[][] adjacencyMatrix){
        /* Method made to calculate the density of a directed graph,
        by using an adjacency matrix, where where the entry at row i and column
        j is 1 if there is a directed edge from vertex i to vertex j, and 0 otherwise. */ 
        
        int countEdges = 0;
        // Attribute counts the number of edges within the graph, and is initialised to zero
        int numVertices = adjacencyMatrix.length;
        // Number of vertices within the graph 

        for (int i = 0; i < numVertices; i ++ ){
            for (int j = 0; j < numVertices; j ++ ){    
                if (adjacencyMatrix[i][j] == 1){
                     countEdges ++;
                }
            }
        }
        /*Outer loop iterates over each row of the matrix, representing outgoing edges to vertex i,
        while the inner loop iterates over each column of a given row i, representing an edge to vertex j.
        Each time a 1 is found, this means a directed edge from vertx i to vertex j exists, and edge count increases by 1, 
        tallying the total number of directed edges in the graph */
        
        if (numVertices > 1) {          // Condition to prevent division by zero
            return countEdges / numVertices * (numVertices - 1);
        }
        else{
            return 0;                   // Density is zero if there's only one vertex
        }

    }

}




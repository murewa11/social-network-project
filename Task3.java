public class Task3{

    public int nodeWithMostOutboundEdges(int[][] adjacencyMatrix){
        int numVertices = adjacencyMatrix.length; // Number of vertices within the graph
          
        int[] degree = new int[numVertices]; // Array stores the degree (number of outbound edges)for each vertex. Each element is initialised to zero

        
        for (int i = 0; i < numVertices; i ++ ){ // Calculate the degree for each vertex
            for (int j = 0; j < numVertices; j ++ ){    
                degree[i] += adjacencyMatrix[i][j];
            }
        }

        // Initialise variables to track the mode with the largest number of outbound edges
        int LargestOutboundDegree = 0;
        int LargestOutboundNode = 0;

        // FInd the node with the highest degree
        for (int i = 0; i < numVertices; i ++ ){
            if (degree[i] > LargestOutboundDegree) {
                LargestOutboundDegree = degree[i];
                LargestOutboundNode = i;
            }
        }

        return LargestOutboundNode; // Return the index of the node with the most outbound edges
    }
}
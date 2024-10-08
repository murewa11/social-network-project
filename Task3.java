import java.util.Map;

public class Task3{

    public String PersonWithMostFollowing(int[][] adjacencyMatrix, Map<Integer, String> indexNameMap){
        int numVertices = adjacencyMatrix.length; // Number of vertices within the graph
          
        int[] degree = new int[numVertices]; // Array stores the degree (number of outbound edges)for each vertex. Each element is initialised to zero

        
        for (int i = 0; i < numVertices; i ++ ){ // Calculate the degree for each vertex
            for (int j = 0; j < numVertices; j ++ ){    
                degree[i] += adjacencyMatrix[j][i];
            }
        }

        // Initialise variables to track the mode with the largest number of outbound edges
        int LargestOutboundDegree = 0;
        String LargestOutboundNode = null;

        // Find the node with the highest degree
        for (int i = 0; i < numVertices; i ++ ){
            String currentNodeName = indexNameMap.get(i);
            if (degree[i] > LargestOutboundDegree || (degree[i] == LargestOutboundDegree && (LargestOutboundNode == null || currentNodeName.compareTo(LargestOutboundNode) < 0))) { 
                LargestOutboundDegree = degree [i];
                LargestOutboundNode = currentNodeName;
            }
        }

        return LargestOutboundNode; // Return the index of the node with the most outbound edges
    }
}



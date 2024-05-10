import java.util.Map;

public class Task2 {
    
    public String nodeWithMostInboundEdges(int[][] adjacencyMatrix, Map<Integer, String> indexNameMap){
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

        int LargestInboundDegree = 0;
        String LargestInboundNode = null;

        for (int j = 0; j < numVertices; j ++ ){
            String currentNodeName = indexNameMap.get(j);
            if (degree[j] > LargestInboundDegree || (degree[j] == LargestInboundDegree && (LargestInboundNode == null || currentNodeName.compareTo(LargestInboundNode) < 0))) { 
                LargestInboundDegree = degree [j];
                LargestInboundNode = currentNodeName;
            }
        }
        /* Loop traverses through degree array. If degree value for the node is higher than the initiliased value of
         * the LargestDegree, it replaces the initiliased value.
         * Also, the corresponding node value replaces the initialised value of the LargestNode attribute.
        */

        return LargestInboundNode;
        
    }
}
import java.util.Arrays;

public class Task5 {

    public double MedianOutboundEdges(int[][] adjacencyMatrix){
        
        int numVertices = adjacencyMatrix.length;
        int[] outboundEdges = new int[numVertices];

        for (int i = 0; i < numVertices; i ++ ){ // Sum up the outbound edges for each vertex
            for (int j = 0; j < numVertices; j ++ ){
                outboundEdges[i] += adjacencyMatrix[i][j];
            }
        }
    
        // Sort the array  from smallest to largest element value for median calculation
        Arrays.sort(outboundEdges);
    
        // Calculate the median of the outbound edges
        if (numVertices % 2 == 1) {
            // Odd number of vertices: return the middle element
            return outboundEdges[numVertices / 2];
        }
        else {
            // Even number of vertices: return the average of the two middle elements
            int midIndex = numVertices / 2;
            return (outboundEdges[midIndex - 1] + outboundEdges[midIndex]) / 2.0;
        }

    }
}
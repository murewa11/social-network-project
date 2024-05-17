import java.util.*;

public class Task6 {

    public Set<Integer> MessageSpreader(int[][] adjacencyMatrix, int startPerson) {
        // Set to keep track of visited nodes (users who have received the message)
        Set<Integer> visited = new HashSet<>();
        // Queue for Breadth-First Search to process each user and their followers
        Queue<Integer> queue = new LinkedList<>();
        
        // Start the Breadth-First Search from the startPerson
        queue.offer(startPerson);
        visited.add(startPerson);

         // Continue BFS until all reachable users are processed
        while (!queue.isEmpty()) {
            int current = queue.poll();
            // Check all possible followers of the current user
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                // If there is a direct follow relationship and the user has not been visited
                if (adjacencyMatrix[current][j] == 1 && !visited.contains(j)) {
                    // Add the user to the visited set and queue for further processing
                    visited.add(j);
                    queue.offer(j);
                }
            }
        }

        // Return the set of all users who received the message
        return visited;
    }

    public String findBestPersonToEnroll(int[][] adjacencyMatrix, String[] userNames) {
        int maxReach = 0; // Variable to store the maximum reach found
        String bestPerson = null; // Variable to store the name of the best person to enroll

        // Iterate through each user in the social network
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            // Use MessageSpreader to find all users who receive the message starting from user 'i'
            Set<Integer> recipients = MessageSpreader(adjacencyMatrix, i);
            // Calculate the reach count by subtracting 1 to exclude the origin user
            int reachCount = recipients.size() - 1;

            /* Update the best person if the current user has a greater reach or if there is a tie
             and the current user's name is alphabetically first*/
            if (reachCount > maxReach || (reachCount == maxReach && (bestPerson == null || userNames[i].compareTo(bestPerson) < 0))) {
                maxReach = reachCount;
                bestPerson = userNames[i];
            }
        }

        // Return the name of the best person to enroll
        return bestPerson;
    }

    
}


 


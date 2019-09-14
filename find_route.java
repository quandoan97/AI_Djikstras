import java.io.BufferedReader;
import java.util.*;

import java.io.*;

public class find_route{

    public static class vertexNode{
        //this is the city that we can reach and the cost 
        String cityName;
        int cost;

        public vertexNode(String s, int c){
            this.cityName = s;
            this.cost = c;
        }
    }

    public static void findTheRoute(String source, String endpoint, Map<String, List<vertexNode>> graph, String[] allCities){
        Set<String> visted = new HashSet<>();//this set takes care of the visited nodes 
        PriorityQueue<vertexNode> pq = new PriorityQueue<vertexNode>((x, y) -> (x.cost - y.cost));        
        HashMap<String, String> prevNode = new HashMap<>();//keeps tracks of the previous node and its path
        HashMap<String, Integer> totalCost = new HashMap<>();//keeps track of the total cost to get to that node

        int nodeExpanded = 0; 
        int nodeGenerated = 0;
        int maxNodeMem = 0;

        //Adding all of values to total cost and setting to infinity
        for(String s: allCities){
            totalCost.put(s, Integer.MAX_VALUE);
        }

        vertexNode startNode = new vertexNode(source, 0);
        totalCost.put(startNode.cityName, 0);
        pq.add(startNode);

        prevNode.put(source, null);

        while(!pq.isEmpty()){
            nodeExpanded++;
            maxNodeMem = Math.max(maxNodeMem, pq.size());

            vertexNode newSmallestNode = pq.poll();
            if(newSmallestNode.cityName.equals(endpoint)){
                break;
            }
            visted.add(newSmallestNode.cityName);
            
            List <vertexNode> temp = graph.get(newSmallestNode.cityName);
            //Object[] reachableCities = graph.get(newSmallestNode.cityName).toArray();//convert the list of Nodes to an array to iterate and add to PQ

            for(vertexNode n: temp){
                if(!visted.contains(n.cityName) && (totalCost.get(newSmallestNode.cityName) + n.cost) < (totalCost.get(n.cityName))){
                    pq.offer(n);
                    nodeGenerated++;
                    totalCost.put(n.cityName, totalCost.get(newSmallestNode.cityName) + n.cost);
                    prevNode.put(n.cityName, newSmallestNode.cityName);
                }
            }
        }
        if(totalCost.get(endpoint) == Integer.MAX_VALUE){
            System.out.println("There is no path");
            System.out.printf("Nodes Expanded: %d\nNodes Generated: %d\nMaxNodes in Memory: %d\nTotalDistance: Infinity\n",nodeExpanded, nodeGenerated, maxNodeMem);
            return;
        }
        System.out.printf("Nodes Expanded: %d\nNodes Generated: %d\nMaxNodes in Memory: %d\nTotalDistance: %dkm\n",nodeExpanded, nodeGenerated, maxNodeMem, totalCost.get(endpoint));
        String currCity = endpoint;
        Stack<String> cityStack = new Stack<>();
        while(currCity != null){
            cityStack.push(currCity);
            currCity = prevNode.get(currCity);
        }

        while(cityStack.size() > 1){
            currCity = cityStack.pop();
            List <vertexNode> temp = graph.get(currCity);
            int currCost = 0;
            for(vertexNode n: temp){
                if(n.cityName.equals(cityStack.peek())){
                    currCost = n.cost;
                }
            }
            System.out.printf("%s to %s, %dkm\n",currCity, cityStack.peek(), currCost);
        }
    }   
//-----------------------------------------------------------------------------------------------------------------


    public static void main(String args[])throws Exception {
        String source = args[1];
        String endpoint =  args[2];
        Set <String> allCities = new HashSet<>();

        Map<String, List<vertexNode>> graph = new HashMap<>();
        List<vertexNode> value;
            File file = new File(args[0]);
            BufferedReader buff;
            try{
                buff = new BufferedReader(new FileReader(file));
            }catch(FileNotFoundException e){
                System.out.println("No file found");
                return;
            }
            String line;
            
            //reading in the file and parsing through to see the starting city and the cost to the next city
            //populates the graph
            while( (line = buff.readLine()) != null ){
                String[] tokens = line.split(" ");
                if(tokens[2].equals("INPUT")){
                    break;
                }
                String cityOne = tokens[0];
                String cityTwo = tokens[1];
                int cost = Integer.parseInt(tokens[2]);
                
                allCities.add(cityOne);
                allCities.add(cityTwo);
                
                value = graph.getOrDefault(cityOne, new ArrayList<vertexNode>());
                value.add(new vertexNode(cityTwo, cost));
                graph.put(cityOne, value);

                value = graph.getOrDefault(cityTwo, new ArrayList<vertexNode>());
                value.add(new vertexNode(cityOne, cost));
                graph.put(cityTwo, value);
            }
            buff.close();
            findTheRoute(source, endpoint, graph, allCities.toArray(new String[allCities.size()]));
    }
}
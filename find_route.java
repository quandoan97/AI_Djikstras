import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class find_route{

    public static class vertexNode{
        String cityName;
        int cost;

        public vertexNode(String s, int c){
            this.cityName = s;
            this.cost = c;
        }
    }

    public static void main(String args[])throws Exception {
        String source = args[1];
        String endpoint =  args[2];

        HashMap<String, List<vertexNode>> graph = new HashMap<>();
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
                
                value = graph.getOrDefault(cityOne, new ArrayList<vertexNode>());
                value.add(new vertexNode(cityTwo, cost));
                graph.put(cityOne, value);

                value = graph.getOrDefault(cityTwo, new ArrayList<vertexNode>());
                value.add(new vertexNode(cityOne, cost));
                graph.put(cityTwo, value);
            }
            buff.close();

            for(Map.Entry<String, List<vertexNode>> entry: graph.entrySet()){
                System.out.printf("%s\n", entry.getKey());
                for(vertexNode node: entry.getValue()){
                    System.out.printf("Node: %s %d\n", node.cityName, node.cost);
                }
            }
    }
}
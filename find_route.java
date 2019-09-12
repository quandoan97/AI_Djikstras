import java.io.BufferedReader;
import java.util.*;

public class find_route{

    Node vertex{
        String destination_city;
        int cost;
    }

    public static void main(String args[]){
        String source = args[1];
        String endpoint =  args[2];

        

        File file = new File(args[0]);
        BufferedReader buff = new BufferedReader(new FileReader(file));
        String line;

        //reading in the file and parsing through to see the starting city and the cost to the next city
        while( line = buff.readLine() != null ){
            String[] tokens = line.split(" ");
            if(tokens[2] == "INPUT"){
                break;
            }
            String cityOne = tokens[0];
            String cityTwo = tokens[1];
            int cost = tokens[2];

        }
    }
}
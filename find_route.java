import java.io.BufferedReader;
import java.util.*;

public class find_route{
    public static void main(String args[]){
        String source = args[1];
        String endpoint =  args[2];

        File file = new File(args[0]);
        BufferedReader buff = new BufferedReader(new FileReader(file));
        String line;

        while( line = buff.readLine() != null ){
            String[] token = line.split(" ");
        }
    }
}
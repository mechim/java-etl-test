import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> fetchData(){
        List<String> result = new ArrayList<>();

        try(InputStream inputStream = Utils.class.getResourceAsStream("/sample.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = bufferedReader.readLine())!= null){
                result.add(line);
            }
        } catch (IOException e){
            System.err.println("Error reading file " + e.getMessage());
        }

        return result;
    }


    public static void printResponse(List<List<String>> anagramGroups){
        for (List<String> group : anagramGroups){
            for (String anagram : group){
                System.out.print(anagram + " ");
            }
            System.out.println();
        }
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static void printResponse(List<List<String>> anagramGroups){
        for (List<String> group : anagramGroups){
            for (String anagram : group){
                System.out.print(anagram + " ");
            }
            System.out.println();
        }
    }
}

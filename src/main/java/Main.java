import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args){
        List<List<String>> result = getAnagrams();
        Utils.printResponse(result);
    }

    static final int MAX_CHAR = 26;

    static String getHash(String s){
        StringBuilder hash = new StringBuilder();
        int[] freq = new int[MAX_CHAR];

        for (char ch: s.toCharArray()){
            freq[ch - 'a']++;
        }

        for (int i = 0; i < MAX_CHAR; i++){
            hash.append(freq[i]);
            hash.append("$");
        }

        return hash.toString();
    }

    static List<List<String>> getAnagrams(){
        List<List<String>> anagramGroups = new ArrayList<>();
        Map<String, Integer> mp = new HashMap<>();

        try(InputStream inputStream = Utils.class.getResourceAsStream("/sample.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String currentWord;
            while ((currentWord = bufferedReader.readLine())!= null){
                String key = getHash(currentWord);
                if (!mp.containsKey(key)){
                    mp.put(key, anagramGroups.size());
                    anagramGroups.add(new ArrayList<>());
                }
                anagramGroups.get(mp.get(key)).add(currentWord);
            }
        } catch (IOException e){
            System.err.println("Error reading file " + e.getMessage());
        }




        return anagramGroups;
    }
}


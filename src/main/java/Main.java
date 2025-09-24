import java.util.*;

public class Main {
    public static void main(String[] args){
        List<String> data = Utils.fetchData();
        List<List<String>> result = getAnagrams(data);
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

    static List<List<String>> getAnagrams(List<String> words){
        List<List<String>> anagramGroups = new ArrayList<>();
        Map<String, Integer> mp = new HashMap<>();

        int wordsLength = words.size();
        for (int i = 0; i < wordsLength; i++){
            String currentWord = words.get(i);
            String key = getHash(currentWord);

            if (!mp.containsKey(key)){
                mp.put(key, anagramGroups.size());
                anagramGroups.add(new ArrayList<>());
            }

            anagramGroups.get(mp.get(key)).add(words.get(i));
        }

        return anagramGroups;
    }
}


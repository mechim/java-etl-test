# Anagram Grouper

This program takes a list of words and bundles them into groups of anagrams.

## Description

The program stores words in a hash map based on the hash generated from the frequency of chars in those words. If a key is the same, the words are stored in an array under that key.
Compared to the naive approach of comparing the sorted arrays of chars for each word where the complexity is `O(n * k * log(k))` where k is the lenght of the words and n is the number of words, this one has a complexity of `O(n * k)`. 

## Getting Started

### Dependencies

* The Java Runtime Environment

### Executing program

* Clone the github repository
* To modify the input file, navigate to `src/main/resources/samples.txt`
* In the root repository package the application using the Maven Wrapper
```
./mvnw clean package
```
* In the generated target directory execute the jar package
```
java -jar ./java-etl-test-1.0-SNAPSHOT.jar
```

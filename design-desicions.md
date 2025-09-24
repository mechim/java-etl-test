# Design Decisions

## Approach

The program organizes words into groups of anagrams by computing a **frequency-based hash** for each word. Each character contributes to a 26-length frequency array, which is serialized into a string. Two words with identical hashes are guaranteed to be anagrams, and they are placed into the same group.

This method was selected because it is conceptually straightforward, computationally efficient, and produces consistent results regardless of input order. It avoids sorting characters for each word, which would be more expensive, especially with very large datasets.

## Maintainability

* **Separation of concerns**: File reading, word grouping, and result printing are handled by distinct components (`Utils` for I/O, `Main` for grouping). This modularity makes the code easier to extend or refactor.
* **Resource loading**: Input data is pulled from the `resources/` directory using the classpath, ensuring the program is portable across different environments without modification of absolute file paths.
* **Simplicity**: By relying only on standard Java libraries, the solution avoids introducing unnecessary external dependencies that would complicate setup and maintenance.

## Performance

* **Hashing**: Each word is converted into a frequency hash in O(L) time, where L is the word’s length.
* **Grouping**: Hashes are stored in a `HashMap`, which provides O(1) average-time lookups and insertions.
* **Streaming input**: Instead of loading the entire file into memory before processing, the program reads the file line by line and processes each word immediately. This reduces peak memory consumption and makes the program significantly more scalable compared to the earlier version.

Overall runtime is O(n × k), where n is the number of words, and memory is tied only to the anagram groups themselves rather than duplicating all input data in a separate list.

## Scalability Considerations

### 10 Million Words

* With streaming input, the program can handle tens of millions of words as long as enough memory is available for storing the resulting anagram groups.
* Possible optimizations at this scale include:

  * Using primitive arrays or `StringBuilder` pooling to create less objects.
  * Writing intermediate results to disk or a database if memory becomes a bottleneck.

### 100 Billion Words

* At this magnitude, a single-machine solution is infeasible.
* Necessary changes:

  * **Distributed computing**: Use frameworks like Apache Spark or Hadoop to partition the data and compute anagram groups in parallel.
  * **External storage**: Store intermediate results in distributed databases or file systems (e.g., HDFS, Cassandra).
  * **Streaming + batching**: Process words in batches across nodes and merge hash-based results incrementally rather than holding everything in memory.

## Libraries

* Only the standard Java library is used in the current solution. This keeps the project lightweight and portable.
* For large-scale datasets (hundreds of millions to billions of words), external frameworks such as Spark would be justified, as they provide built-in distributed processing capabilities and efficient handling of massive amounts of data.

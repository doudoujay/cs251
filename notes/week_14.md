# String Sorts

Frequency of operations = key compares.

Key-indexed counting: assumptions about keys

* Keys are integers between 0 and R - 1.
* Can use key as an array index.

### key-indexed counting

Counting sort

```
Count frequencies of each letter using key as index. 
Compute frequency cumulates which specify destinations. 
Access cumulates using key as index to move records.
Copy back into original array.
```
Key-indexed counting uses **8 N + 3 R** array accesses to sort  N records whose keys are integers between 0 and R - 1.

Key-indexed counting uses extra space proportional to **N + R**.

*Stable*? Yes! 

*In-place*? No.

###  LSD string sort

Least-significant-digit-first string sort

* Consider characters from right to left.
* Stably sort using dth character as the key (using key-indexed counting).

LSD sorts fixed-length strings in ascending order.

pf:

* If the characters not yet examined differ, 
it doesn't matter what we do now.
* If the characters not yet examined agree, 
stability ensures later pass won't affect order.

Guarantee: *2WN*<br>
Extra space: *N+R*<br>
Stable: *Yes*<br>
### MSD string sort

* Partition file into R pieces according to first character  (use key-indexed counting).
* Recursively sort all strings that start with each character  (key-indexed counts delineate subarrays to sort).

Variable-length strings

**Treat strings as if they had an extra char at end (smaller than any char).**

#### potential for disastrous performance

Much too slow for small subarrays.

Huge number of small subarrays  because of recursion.

#### performance
Number of characters examined.

* MSD examines just enough characters to sort the keys. 
* Number of characters examined depends on keys.
* Can be sublinear.

*2NW*

Extra space: *N+DR*

stack depth D = length of  longest prefix match

Stable: *Yes*

#### Pros and Cons

Cons for MSD

* Accesses memory "randomly" (cache inefficient). 
* Inner loop has a lot of instructions.
* Extra space for count[].
* Extra space for aux[].

Cons for Quicksort

* Linearithmic number of string compares (not linear). 
* Has to rescan long keys for compares.

**Goal**. Combine advantages of MSD and quicksort.

### 3-way string quicksort

Do 3-way partitioning on the dth character.

* Cheaper than R-way partitioning of MSD string sort.
* Need not examine again characters equal to the partitioning char.

recursively sort subarrays,  excluding first character  for "equal" subarray

#### 3-way string quicksort vs. standard quicksort

Standard quicksort.


* Uses *2N ln N* **string compares** on average.
* Costly for long keys that differ only at the end (and this is a common case!)

3-way string quicksort.

* Uses 2N ln N **character compares** on average for random strings. 
* Avoids recomparing initial parts of the string.
* Adapts to data: uses just "enough" characters to resolve order. 
* Sublinear when strings are long. 

3-way string quicksort is optimal (to within a constant factor);  no sorting algorithm can (asymptotically) examine fewer chars.

#### 3-way string quicksort vs. MSD string sort

MSD string sort.

* Has a long inner loop.
* Is cache-inefficient.
* Too much overhead reinitializing count[] and aux[].

3-way string quicksort.

* Has a short inner loop.
* Is cache-friendly.
* Is in-place.

#### Performance

*1.39 W N lg N*

Extra space: *log N + W*

Stable: *NO*

### suffix arrays

#### Longest repeated substring: a sorting solution

Brute force Analysis. Running time ≤ M N 2 , where M is length of longest match.

Sorting solution

*  input string
*  form suffixes
*  sort suffixes to bring repeated substrings together
*  compute longest prefix between adjacent suffixes

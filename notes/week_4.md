## Mergesort

### Two classic sorting algorithms
* Mergesort.
* Quicksort.

### Mergesort

* Divide array into two halves. 
* **Recursively** sort each half. 
* Merge two halves.

Q: how ti combine two sorted subarrays into a sorted whole?

A: Use an auxiliary array

#### Assertions

* Statement to test assumptions about your program.
* Helps detect logic bugs.
* Documents code.

**Java assert statement. **

Throws an exception unless boolean condition is true.

**Can enable or disable at runtime.** ⇒ No cost in production code.

Use to check internal invariants. Assume assertions will be disabled in production code 

#### empirical analysis

Running time estimates:* Laptop executes 108 compares/second.
* Supercomputer executes 1012 compares/second.

**mergesort (N log N)**

Good algorithms are better than supercomputers.

#### number of compares and array accesses

Mergesort uses at mostN lg N compares and 6 N lg N array accesses to sort any array of size N

#### Divide-and-conquer recurrence
* proof by picture
* proof by expansion
* proof by induction

### Mergesort analysis: memory
Mergesort uses extra space proportional to N.

The array aux[] needs to be of size N for the last merge.

Def: A sorting algorithm is in-place if it uses O(log N) extra memory.<br>
Ex. Insertion sort, selection sort, shellsort

#### practical improvements
**Use insertion sort for small subarrays.*** Mergesort has too much overhead for tiny subarrays. 
* Cutoff to insertion sort for ≈ 7 elements.

**Stop if already sorted.**

* Is biggest element in first half ≤ smallest element in second half?
* Helps for partially-ordered arrays.

**Eliminate the copy to the auxiliary array.** <br>Save time (but not space) by switching the role of the input and auxiliary array in each recursive call.

### bottom-up mergesort
* Pass through array, merging subarrays of size 1. 
* Repeat for subarrays of size 2, 4, 8, 16, ....

Bottom line. **No recursion needed!**

### sorting complexity

**Computational complexity.** Framework to study efficiency of algorithms for solving a particular problem X.

* Model of computation.
* Cost model.
* Upper bound.
* Lower bound. 
* Optimal algorithm.

Example: sorting

```
Model of computation: decision tree. 
Cost model: # compares.Upper bound: ~ N lg N from mergesort.
Lower bound: ~ N lg N ???Optimal algorithm: mergesort ???
```

#### Decision tree (for 3 distinct elements a, b, and c)

**height of tree** =  worst-case number of compares

(at least) one leaf for each possible ordering

#### Compare-based lower bound for sorting

Any compare-based sorting algorithm must use at least  lg ( N ! ) ~ N lg N compares in the worst-case.

**First goal of algorithm design:** optimal algorithms.

#### Complexity results in context

**Other operations?** Mergesort is optimal with respect to number of compares (e.g., but not to number of array accesses).

Lower bound may not hold if the algorithm has information about:

```
The initial order of the input. 
The distribution of key values. 
The representation of the keys.
```

### comparators

#### Natural order

**Comparable interface:**sort uses type’s natural order.

Problem:

* May want to use a non-natural order.
* Desired data type may not come with a “natural” order.

#### Generalized compare

Ex. Sort strings by:

```
import java.text.Collator;

Natural order.
Case insensitive. 
Spanish.British phone book.
```

#### Comparators
Use Java's Comparator interface.Remark. compare() must implement a total order like compareTo().

To support comparators in our sort implementations:

```
Use Object instead of Comparable. 
Pass Comparator to sort() and less().
Use it in less().
```

#### Generalized compare
Comparators enable multiple sorts of a single array (by different keys).

### stability
A typical application. First, sort by name; then sort by section.

A stable sort preserves the relative order of records with equal keys.

#### Is insertion sort stable?
Yes, equal elements never move past each other.

#### Is selection sort stable?
No, long-distance exchange might move left element to the right  of some equal element.

#### Is shellsort stable?
No. Long-distance exchanges.

#### Is merge stable?
Yes, if implemented carefully (take from left subarray if equal).

#### Which sorts are stable ?

**Yes**. Insertion sort, mergesort.<br>**No**. Selection sort, shellsort.

Need to carefully check code ("less than" vs "less than or equal to").
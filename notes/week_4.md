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
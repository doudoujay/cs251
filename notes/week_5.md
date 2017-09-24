### Quicksort

**Basic plan.**

* *Shuffle* the array.* *Partition* so that, for some j

```
element a[j] is in place
no larger element to the left of j
no smaller element to the right of j
```
**Basic plan. How?**

* Scan i from left for an item that belongs on the right.
* Scan j from right for item that belongs on the left. 
* Exchange a[i] and a[j].* Repeat until pointers cross.

#### implementation details

**Partitioning in-place.** Using an extra array makes partitioning easier  (and stable), but is not worth the cost.
**Terminating the loop.** Testing whether the pointers cross is a bit trickier than it might seem.
**Staying in bounds.** The (j == lo) test is redundant (why?),  but the (i == hi) test is not.
**Preserving randomness.** Shuffling is needed for performance guarantee.
**Equal keys.** When duplicates are present, it is (counter-intuitively) better  to stop on elements equal to the partitioning element.

#### empirical analysis

Running time estimates:

Lesson 1. Good algorithms are better than supercomputers. 

Lesson 2. Great algorithms are better than good ones.


#### best-case analysis

Number of compares is ~ N lg N.

#### worst-case analysis
Number of compares is ~ 1⁄2 $N^2$ .


#### average-case analysis
The average number of compares CN to quicksort an array of  N distinct keys is ~ 2N ln N (and the number of exchanges is ~ 1⁄3 N ln N).

**1.39NlgN**

#### summary of performance characteristics

Worst case.

Average case.

Random shuffle.

Caveat emptor.

#### practical improvements

Insertion sort small subarrays.

Median of sample.

Optimize parameters.

### selection

Goal. *Find the kth largest element.*

Min (k = 0), max (k = N - 1), median (k = N / 2).

#### Use theory as a guide.


* Easy O(N log N) upper bound. How?* Easy O(N) upper bound for k = 1, 2, 3. 
* How? Easy Ω(N) lower bound. Why?

#### Quick-select

similar to quick sort.

#### mathematical analysis

Quick-select takes linear time on average.

Intuitively, each partitioning step splits array approximately in half:  N + N / 2 + N / 4 + ... + 1 ~ 2N compares.

Quick-select uses ~ 1⁄2 N 2 compares in the worst case, but (as with quicksort) the random shuffle provides a probabilistic guarantee.

### duplicate keys

Mergesort with duplicate keys. Always between 1⁄2 N lg N and N lg N compares.

Quicksort with duplicate keys.Algorithm goes quadratic unless partitioning stops on equal keys! 1990s C user found this defect in qsort().

* Mistake. Put all keys equal to the partitioning element on one side.
~ 1⁄2 N 2 compares when all keys equal.
* Recommended. Stop scans on keys equal to the partitioning element.
~ N lg N compares when all keys equal.
* Desirable. Put all keys equal to the partitioning element in place.


#### 3-way partitioning
## Elementary Sorts

#### Sample sort client
Goal: Sort any type of data.

#### Callbacks
Callbacks = reference to executable code.

* Client passes array of objects to sort() function.
* The sort() function calls back object's compareTo() method as needed.

#### Implementing callbacks.

```
Java: interfaces.C: function pointers.C++: class-type functors.C#: delegates.Python, Perl, ML, Javascript: first-class functions
```

Required properties. Must ensure a total order.* Reflexive: (v = v)
* Antisymmetric: if(v<w)then(w>v);if(v=w)then(w=v).
* Transitive: if (v ≤ w) and (w ≤ x) then (v ≤ x).

**Built-in comparable types**. String, Double, Integer, Date, File, ... 

#### Two useful sorting abstractions

Helper functions.

* Less. 
* Exchange

Q. If the sorting algorithm passes the test, did it correctly sort its input?<br>


### selection sort

**↑ scans from left to right.**

Invariants.

* Elements to the left of ↑ (including ↑) fixed and in ascending order.
* No element to right of ↑ is smaller than any element to its left

```java
 public class Selection{   public static void sort(Comparable[] a)   {int N = a.length;for (int i = 0; i < N; i++){int min = i; for (int j = i+1; j < N; j++)      if (less(a[j], a[min]))         min = j;   exch(a, i, min);}    }   private static boolean less(Comparable v, Comparable w)   {  /* as before */  }   private static void exch(Comparable[] a, int i, int j)   {  /* as before */  }}
```

#### mathematical analysis
**Proposition.** Selection sort uses (N–1) + (N–2) + ... + 1 + 0 ~ $N^2$ / 2 compares and N exchanges.

**Running time insensitive to input.** Quadratic time, even if input array is sorted. 

**Data movement is minimal.** Linear number of exchanges.

### Insertion sort
↑ scans from left to right.

Invariants.* Elements to the left of ↑ (including ↑) are in ascending order.
* Elements to the right of ↑ have **not yet been seen.**

#### Insertion sort inner loop

* Move the pointer to the right
* Moving from right to left, exchange a[i] with each larger element to its left

```java
 public class Insertion{   public static void sort(Comparable[] a)   {int N = a.length;for (int i = 0; i < N; i++)   for (int j = i; j > 0; j--)      if (less(a[j], a[j-1]))         exch(a, j, j-1);      else break;    }   private static boolean less(Comparable v, Comparable w)   {  /* as before */  }   private static void exch(Comparable[] a, int i, int j)   {  /* as before */  }}
```

#### mathematical analysis
**Proposition.** To sort a randomly-ordered array with distinct keys,  insertion sort uses ~ 1⁄4 $N^2$ compares and ~ 1⁄4 $N^2$ exchanges on average.#### Insertion sort: best and worst case

**Best case.** If the array is in ascending order, insertion sort makes  N - 1 compares and 0 exchanges.

**Worst case.** If the array is in descending order (and no duplicates),  insertion sort makes ~ 1⁄2 $N^2$ compares and ~ 1⁄2 $N^2$ exchanges.#### Insertion sort: partially-sorted arrays

An **inversion** is a pair of keys that are out of order.

An array is partially sorted if the number of inversions is O(N).

**Proposition.** For partially-sorted arrays, insertion sort runs in linear time. 

**Pf.** Number of exchanges equals the number of inversions.

#### sorting challenges

#### Diversion: how to shuffle an array
Knuth shuffle.

* In iteration i, pick integer r between 0 and i uniformly at random.
* Swap a[i] and a[r].

### shellsort

**Idea.** Move elements more than one position at a time by h-sorting the array.

**Shellsort.** [Shell 1959] h-sort the array for decreasing sequence of values of h.

#### h-sort

How to h-sort an array? Insertion sort, with stride length h.

#### Intuition
A g-sorted array remains g-sorted after h-sorting it.#### Shellsort: analysis
**Proposition.** The worst-case number of compares used by shellsort with the 3x+1 increments is O($N^{3/2}$).

**Property.** The number of compares used by shellsort with the 3x+1 increments is at most by a small multiple of N times the # of increments used.
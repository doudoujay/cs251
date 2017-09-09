Arithmetic expression evaluation

Two-stack algorithm

### Analysis of Algorithms

#### Cast of characters

#### Running time

#### Reasons to analyze algorithms

* Predict performance. 
* Compare algorithms.* Provide guarantees. 
* Understand theoretical basis.

#### Discrete Fourier transform.
Break down waveform of N samples into periodic components.

#### N-body simulation.
Simulate gravitational interactions among N bodies.

#### The challenge
Will my program be able to solve a large practical input?<br>
se **scientific method** to understand performance#### Scientific method applied to analysis of algorithms
A framework for predicting performance and comparing algorithms.**Scientific method.**
**Principles.**
Feature of the natural world = computer itself.


### observations

**Example: 3-sum** Given N distinct integers, how many triples sum to exactly zero?

#### Measuring the running time
How to time a program?

```java
public static void main(String[] args){   int[] a = StdArrayIO.readInt1D();   Stopwatch stopwatch = new Stopwatch();   StdOut.println(ThreeSum.count(a));   double time = stopwatch.elapsedTime();}
public class Stopwatch{   private final long start = System.currentTimeMillis();
   public double elapsedTime(){   long now = System.currentTimeMillis();   return (now - start) / 1000.0;}}
```

### Data analysis

**Standard plot.** Plot running time T (N) vs. input size N.

**Log-log plot.** Plot running time vs. input size N using log-log scale.

Useful when you have power low.

### Doubling hypothesis

Quick way to estimate b in a power-law relationship.

$aN^b$

### Experimental algorithmics

System independent effects.(determiunes exponent b)

* Algorithm. 
* Input data.

System dependent effects. (determines constant a)

**The larger N, the more accurate**

### Mathematical models 
#### Mathematical models for running time

Total running time: sum of cost × frequency for all operations.

#### Simplification 1: cost model
Use some basic operation as a proxy for running time.

#### Simplification 2: tilde notation
* Estimate running time (or memory) as a function of input size N.
* Ignore lower order terms.
 - when N is large, terms are negligible
 - when N is small, we don't care

 
Use cost model and tilde notation to simplify frequency counts.

#### Estimating a discrete sum
 Replace the sum with an integral, and use calculus!
 
#### Mathematical models for running time
In principle, accurate mathematical models are available.

####  amortized analysis
Average running time per operation over  a worst-case sequence of operations. [stay tuned]

Often useful to compute average cost per operation over a sequence of ops.

#### order-of-growth classifications

the small set of functions 

**Bottom line.** Need linear or linearithmic alg to keep pace with Moore's law.

#### Binary search
Given a sorted array and a key, find index of the key in the array?

#### Binary search: mathematical analysis
Binary search uses at most 1 + lg N compares to search in a sorted array of size N.

#### An $N^2$ log N algorithm for 3-sum

1. Sort the N numbers. *( $N^2$ with insertion sort)*
2. For each pair of numbers a[i]and a[j], binary search for -(a[i] + a[j]). *($N^2$ log N with binary search.)*

#### An $N^2$ log N algorithm for 3-sum

### dependencies on inputs

#### Types of analyses

* Best case: Lower bound on cost
* Worst case: Upper bound on cost
* Avg case: “Expected” cost

#### Commonly-used notations

* Tilde: provide approximate model
* Big Theta: classify algorithms
* Big Oh: develop upper bounds
* Big Omega: develop lower bounds

#### Tilde notation vs. big-Oh notation
We use tilde notation whenever possible.

### memory

#### Typical memory requirements for primitive types in Java
* Bit
* Byte
* MB
* GB

Array overhead. 16 bytes.

#### Empirical analysis.
Model enables us to **make predictions.**

#### Mathematical analysis.
Model enables us to **explain behavior.**
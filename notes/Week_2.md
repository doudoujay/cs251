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
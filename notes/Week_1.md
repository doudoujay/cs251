username:
cs251-fall17
pw: algofs17

### Intro
---
**Connected components.** Maximal set of objects that are mutually connected.

* **Find query.** Check if two objects are in the same component.* **Union command.** Replace components containing two objects with their union.

Paradoxically, quadratic algorithms get worse with newer equipment.


### Proof Techniques
---

#### Proof by direct derivation

* A and (A ⇒ B) ⇒ B

#### Proof by contraposition

Original: p ⇒ q Contrapositive: ¬ q ⇒ ¬ p

#### Proof by contradiction

Original: p ⇒ q <br>
Contradiction:<br>Use p ∧ ¬q⇒F To show p ⇒ q

### Recursion
---
when one function calls itself directly or indirectly.


#### Recursive definitions
* A set of base cases - simple cases that are defined explicitly* A recursive definition – where additional cases are defined in terms of previous cases.

#### How to write simple recursive programs?

Base case. Reduction step.<br>Trace the execution of a recursive program.<br>
E.g. Factorial function


```java
public static int recursiveFactorial(int n) {if (n == 0) return 1; // base case
else return n * recursiveFactorial(n- 1); // recursive case
}
```

#### Linear recursion

Recur once per method

E.g.

```
public static void ReverseArray(A, i, j): ifi< jthenSwap A[i] and A[ j]ReverseArray(A, i + 1, j - 1)
return
```

#### Defining arguments for recursion
* In creating recursive methods, it is important to define the methods in ways that facilitate recursion.

* This sometimes requires we define additional parameters that are passed to the method.

#### Tail recursion

Tail recursion occurs when a linearly recursive method makes its **recursive call as its last step**.

```java
public static void IterativeReverseArray(A, i, j ): whilei< jdoSwap A[i ] and A[ j ] i =i+1j =j-1return
```

#### Binary recursion
Binary recursion occurs whenever **there are two recursive calls for each non-base case**.

E.g. Problem: add all the numbers in an integer array A

```
public static int BinarySum(A, i, n): 
if n = 1 then	return A[i ]return BinarySum(A, i, n/2) + BinarySum(A, i + n/2, n/2)
```


### Mathematical induction
---

Used to establish that a claim is true for all natural numbers based on creating a one to one correspondence with the integers.
* Show that the first among an infinite sequence of claims is true. 
* Then prove that if any one claim in the sequence is true, the subsequent must also be true.

#### Steps

* Basis: Claim is true for simple cases (e.g., n = 1) 
* Assumption: Claim is true for some integer n ≥ 1.
* Induction: Based on the assumption, show the claim must be true for n + 1.
* Conclusion: Since claim is true for the base case, must be true for all subsequent cases.

#### Weal Vs Strong
when claim is assumed true for all values ≤ n’ it is called weak induction;  when assumed true for only n’ it is called strong induction

### Fibonacci analysis

#### Computing Fibonacci numbers

Let nk be the number of recursive calls by BinaryFib(k)

```
public static int BinaryFib(k): 
if k ≤ 1 then	return k 
else	return BinaryFib(k - 1) + BinaryFib(k - 2)
```
**Note that nk at least doubles every other time**

* That is, $n_{k} > 2^{k/2}$.* This is **exponential** growth, which is even larger than quadratic

#### A better Fibonacci algorithm

Use linear recursion instead

```
public static int[] LinearFibonacci(k): 
if k = 1 then	return [k, 0] 
else	[i, j] = LinearFibonacci(k − 1) 
return [i +j, i]

```

Runtime:
LinearFibonacci makes **k−1** recursive calls

### Stacks and Queues
---

#### Arrays
#### Linked lists

```java
public class Node  {private Object item;     // Instance variableprivate Node next;       // Instance variable/** Creates a node with null references. */public Node(){this(null, null);}/** Creates a node with specified contents. */public Node(Object e, Node n){item = e;next = n;}// Accessor methods:public Object getItem() {  return item; }public Node getNext() {  return next;  }// Modifier methods:public void setItem(Object newItem){item = newItem;}public void setNext(Node newNext){next = newNext;}
```

#### stacks & queues


**Stack: LIFO = "last in first out"**

```java
public class StackOfStrings {    private Node first = null; private class Node{ String item; Node next;  }inner class     public boolean isEmpty()   {  return first == null;  }   public void push(String item){ Node oldfirst = first;  first = new Node();  first.item = item;  first.next = oldfirst; }public String pop() { if (isEmpty()) throw new RuntimeException(); String item = first.item; first = first.next; return item; }  
}
```

**Stack: Linked List Performance**

Uses ~ 16 N bytes to represent a stack with N items

**Stack: Array implementation **
* Use array s[] to store N items on stack. 
* push(): add new item at s[N].* pop(): remove item from s[N-1].

*Defect.* Stack overflows when N exceeds capacity.

#### dynamic resizing
 How to grow and shrink array?
+1 -1:<br>
Cost - ${N^2}/2$

**Ensure that array resizing happens *infrequently*.**

Twice the size:<br>
If array is full, create a new array of twice the size, and copy items.

Cost - **2N**

**How to shrink array?**

Efficient solution.<br>
halve size of s[] when array is *one-quarter* full.

Invariant. Array is between 25% and 100% full.

The final soluition:

* push: double sizeof s[] when array is full.
* halve size of s[] when array is *one-quarter* full.

**Amortized analysis**. Average running time per operation over  a worst-case sequence of operations.

**Memory**

* ~ 4N when full.* ~ 16 N when one-quarter full.

**dynamic array vs. linked List**

#### Queue: 
FIFO = "first in first out"

**Linked list Implementation**

**dynamic array implementation**

* Use array q[] to store items in queue. 
* enqueue(): add new item at q[tail]. 
* dequeue(): removeitemfromq[head]. 
* Update head and tail modulo the capacity. 
* Add dynamic resizing.

#### Doubly-linked lists

A doubly linked list is a data structure consisting of a sequence of 

Strores:

* Item* Link to the next node 
* Link to the previous node

#### Generics

Good solution:

* Avoid casting in client
* Discover type mismatch errors at compile-time instead of run-time.

Can't allocate array of generics

solution: ugly cast
```java
s = (Item[]) new Object[capacity]; }
```

**Generic data types: autoboxing**

Wrapper type.:

Each primitive type has a **wrapper Object type**

#### Iterators

Support iteration over stack items by client,  without revealing the internal representation of the stack.

*Java solution:*<br>
Make stack implement the Iterable interface.

Java supports elegant client code.

What if client modifies the data structure while iterating?<br>
A fail-fast iterator throws a *ConcurrentModificationException*.

**Bag**

When order doesn’t matter
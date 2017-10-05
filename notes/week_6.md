### Priority Queues

Insert and delete items. Which item to delete?

**Priority queue.** Remove the largest (or smallest) item.

#### Priority queue client example

Find the largest M items in a stream of N items.

**Constraint** Not enough memory to store N items.

**solution** Use a min-oriented priority queue.### elementary implementations

#### Priority queue: unordered and ordered array implementation

**challenge** Implement all operations efficiently.

### binary heaps

**Binary tree.** Empty *or* node with links to left and right binary trees.

**Complete tree.** Perfectly balanced, except for bottom level.*Property.*Height of complete tree with N nodes is 1 + ⎣lg N⎦.

#### Binary heap representations

Array representation of a heap-ordered complete binary tree.

**Heap-ordered binary tree.*** Keys in nodes.* No smaller than children’s keys**Array representation.**

* Take nodes in level order. 
* No explicit links needed!



Largest key is a[1], which is root of binary tree

Can use array indices to *move through* tree.

* Parent of node at k is at k/2.* Children of node at k are at 2k and 2k+1.

#### Promotion in a heap

*Scenario.* Node's key becomes larger key than its parent's key.

To eliminate the violation:
* Exchange key in node with key in parent. 
* Repeat until heap order restored.

#### Insertion in a heap

*Insert.* Add node at end, then swim it up. 

*Cost.* At most lg N compares.

#### Demotion in a heap
*Scenario.* Node's key becomes smaller than one (or both) of its children's keys.To eliminate the violation:

* Exchange key in node with key in larger child. 
* Repeat until heap order restored.


#### Delete the maximum in a heap

Exchange root with node at end, then sink it down.

*Cost:* At most 2 lg N compares.


#### Binary heap considerations

Minimum-oriented priority queue.
Dynamic-array resizing.

Immutability of keys.

Other operations.


###  heapsort

* Create max-heap with all N keys. 
* Repeatedly remove the maximum key.#### heap construction
**First pass.**
Build heap using bottom-up method

#### sortdown
**Second pass.**
heap construction* emove the maximum, one at a time.* Leave in array, instead of nulling out.#### mathematical analysis
Heapsort uses at most 2 N lg N compares and exchanges.In-place sorting algorithm with N log N worst-case.

* Mergesort: no, linear extra space.
* Quicksort: no, quadratic time in worst case.
* Heapsort: yes!

Heapsort is optimal for both time and space, but:

* Inner loop longer than quicksort’s
* Makes poor use of cache memory.
* Not stable

### Symbol Tables

Key-value pair abstraction.

* Insert a value with specified key.* Given a key, search for the corresponding value.

**Associative array abstraction.** Associate one value with each key.

#### Conventions
* Values are not null.
* Method get() returns null if key not present
* Method put() overwrites old value with new value.

* Easy to implement contains()
* Can implement lazy version of delete().

### sequential search

### binary search

Maintain an *ordered* array of key-value pairs.

Rank helper function. How many keys < k ?

Binary search uses ~ lg N compares to search any array of size N.

**Problem.**To insert, need to shift all greater keys over.

### ordered operations
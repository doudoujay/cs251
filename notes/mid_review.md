#### Recursive defination
#### Mathmatical Induction
#### Stacks and Queues
#### Tilde notation
Estimate running time as a function of input size N

1 < log N < N < NlogN < N^2 < N^3 < 2^N

#### Binary Search
1+lgN compares to *sorted array*

#### Type of analysis
* Best case
* Worst case
* avg case


#### Commonly used notation

```
Tilde -- approximate
Big Theta -- classify
Big Oh -- upper bound
Big Omega -- lower bound 
```

Use tilde notation whenever possible

#### Sorting

Total order

* reflexive
* antisymmetric
* transitive


#### Selection Sort
$\frac{1}{2}n^2$

left -> right

Select item from right and insert that item to left.

#### Insertion Sort

left -> right

But, instead of selecting the item from right ordered right, it select from the immediate right item

An *inversion* is a pari of keys that are out of order.


And array is *partialluy sorted* -> O(N) yay (best case)

$\frac{1}{4}n^2$

#### Shellsort

h-sorting the array

***stride is the key idea***

$n^{\frac{3}{2}}$ worst case

#### Merge sort
nlogn

Sort each half, then merge(linear time)

need extra space(O(n))

Proof for the time complexity

D(N) = 2D(N/2) + 1 (derived from dicision tree)

stirlings formula: lg(N!) ~ NlgN

* Stable :)
* Not in place :(

Special case handling

* Partially-ordered 
* Duplicate keys
* Digital propertries of keys

#### Quick sort

* Not stable :(
* In place :)

Basic plan:

* shuffle -- why it is not stable
* partition(element a[j] is in place,no larger on lefy, no smaller on right)
* Sort each piece recursivelly 


#### Priority Queue
Remove the largest/smallest item

#### Binary Heap
use to implement priority queue

* parent of node at k is k/2
* children of node at k at 2k or 2k+1

*insert value*
place at end

*Remove*
move to top, remove, sink down

#### Heap sort
* in place
* not stable

#### Symbole tables
key-val

#### BST
```
Inorder left - node- right
Preorder node - children 
Postorder children - node
```

#### Red black(blanced tree)

corresponding to 2-3 tree, by represtenting it as BST


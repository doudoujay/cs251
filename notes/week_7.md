## Binary Search Trees

### Trees

In computer science, a tree is an   abstract model of a hierarchical structure<br>A tree consists of nodes with a parent-child relation

#### Depth and height

Define depth recursivelyDefine height recursively#### Preorder traversal
In a preorder traversal, a node is visited **before** its descendants

#### Postorder traversal
In a postorder traversal, a node is visited **after** its descendants

#### Inorder traversal
In an inorder traversal a node is visited **after** its left subtree and **before** its right subtree

#### Linked structure for binary trees

A node is represented by an object storing* Element* Parent node 
* Left child node 
* Right child node

#### Array-based representation of binary trees

Nodes are stored in an array A

Node v is stored at A[rank(v)]

* rank(root) = 1* if node is the left child of parent(node)  rank(node) = 2 · rank(parent(node))* if node is the right child of parent(node)  rank(node) = 2 · rank(parent(node)) + 1


### Binary search trees
A BST is a binary tree in symmetric order.
s
#### BST insert

**Put**. Associate value with key.

Search for key, then two cases:

* Key in tree ⇒ reset value.* Key not in tree ⇒ add new node.

#### mathematical analysis

If keys are inserted in **random** order, the expected number of compares for a search/insert is ~ 2 ln N

proof: 1-1 correspondence with quicksort partitioning.

If keys are inserted in **random** order, expected height of tree is ~ 4.311 ln N.

But... Worst-case height is N. 

### ordered operations

#### Floor and ceiling

* Floor. Largest key ≤ to a given key. 
* Ceiling. Smallest key ≥ to a given key.

#### Computing the floor

* Case 1. [k equals the key at root] The floor of k is k.
* Case 2. [k is less than the key at root] The floor of k is in the left subtree.
* Case 3. [k is greater than the key at root] The floor of k is in the right subtree (if there is any key ≤ k in right subtree);  otherwise it is the key in the root.

#### Subtree counts

In each node, we store the number of nodes in the subtree rooted at that node.To implement size(), return the count at theC root.

#### Rank

**Rank.** How many keys < k ?

Easy recursive algorithm (4 cases!)

#### Inorder traversal

* Traverse left subtree. 
* Enqueue key.
* Traverse right subtree.

*Property*. Inorder traversal of a BST yields keys in *ascending* order.

### deletion

#### BST deletion: lazy approach
To remove a node with a given key:

* Set its value to null.
* Leave key in tree to guide searches (but don't consider it equal to search key).

*Cost*. 2 ln N' per insert, search, and delete (if keys in random order),  where N' is the number of key-value pairs ever inserted in the BST.

*Unsatisfactory solution*. Tombstone overload.

#### Deleting the minimum

* Go left until finding a node with a null left link.
* Replace that node by its right link.
* Update subtree counts.

#### Hibbard deletion

To delete a node with key k: search for node t containing key k. 

**Case 0**. [0 children] Delete t by setting parent link to null.

**Case 1**. [1 child] Delete t by replacing parent link.

**Case 2**. [2 children]

* Find successor x of t.
* Delete the minimum in t's right subtree.
* Put x in t's spot.

#### Hibbard deletion: analysis

**Unsatisfactory solution**. Not symmetric.

**Surprising consequence**. Trees not random (!) ⇒ sqrt (N) per op.
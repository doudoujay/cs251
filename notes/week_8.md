# Balanced Search Trees

Final Goal:
log N
log N
log N

*Challenge*. Guarantee performance.

### 2-3 search trees

Allow 1 or 2 keys per node.

* 2-node: one key, two children.
* 3-node: two keys, three children.

*Symmetric order*. Inorder traversal yields keys in ascending order. 

*Perfect balance*. Every path from root to null link has same length.

#### Search in a 2-3 tree

* Compare search key against keys in node. 
* Find interval containing search key. 
* Follow associated link (recursively).

#### Insertion in a 2-3 tree

*Case 1*. Insert into a 2-node at bottom.

```
Search for key, as usual. 
Replace 2-node with 3-node.
```

*Case 2*. Insert into a 3-node at bottom.

```
Add new key to 3-node to create temporary 4-node. 
Move middle key in 4-node into parent.
Repeat up the tree, as necessary.
If you reach the root and it's a 4-node, split it into three 2-nodes.
```

**Remark**. Splitting the root increases height by 1.

**All leaf same depth**

### red-black BSTs

#### Left-leaning red-black BSTs (Guibas-Sedgewick 1979 and Sedgewick 2007)

*Transform the 2-3 tree to BST*

* Represent 2–3 tree as a BST.
* Use "internal" left-leaning links as "gllueses" 

#### An equivalent definition

A BST such that:

```
No node has two red links connected to it.
Every path from root to null link has the same number of black links. 
Red links lean left.
```

#### Search implementation for red-black BSTs
search is the same as for elementary BST (ignore color). 

Most other ops (e.g., ceiling, selection, iteration) are also identical.

#### Elementary red-black BST operations

*Left rotation.*
Orient a (temporarily) right-leaning red link to lean left.

*Right rotation.*
Orient a left-leaning red link to (temporarily) lean right

*Color flip.* 
Recolor to split a (temporary) 4-node.

***Invariants***. Maintains symmetric order and perfect black balance.
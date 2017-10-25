# Directed Graphs

### Directed graphs
*Digraph.* Set of vertices connected pairwise by directed edges.

#### digraph API

#### Set-of-edges digraph representation
Store a list of the edges (linked list or array).
#### Adjacency-matrix digraph representation
Maintain a two-dimensional V-by-V boolean array; 

for each edge v → w in the digraph: adj[v][w] = true.

#### Adjacency-list digraph representation

Maintain vertex-indexed array of lists (use Bag abstraction).

### digraph search

#### Depth-first search (in undirected graphs)

Every undirected graph is a digraph (with edges in both directions). 

DFS is a digraph algorithm

#### Reachability application: program control-flow analysis

*Dead-code elimination.* 
Find (and remove) unreachable code.

*Infinite-loop detection.* 
Determine whether exit is unreachable.

#### Reachability application: mark-sweep garbage collector

*Roots.* Objects known to be directly accessible by program (e.g., stack).

*Reachable objects.* Objects indirectly accessible by program (starting at a root and following a chain of pointers).

#### Depth-first search in digraphs summary
***DFS solves reachability problem***

### BFS
#### Breadth-first search in digraphs

*Proposition*. BFS computes shortest paths (fewest number of edges).

#### Breadth-first search in digraphs application: web crawler

### topological sort

#### Precedence scheduling

*Goal*. Given a set of tasks to be completed with precedence constraints,  in which order should we schedule the tasks?

*Graph model*. vertex = task; edge = precedence constraint.

#### Topological sort

*DAG*. Directed acyclic graph.

*Topological sort*. Redraw DAG so all edges point up.

**Solution**. DFS. What else?

#### Depth-first search order

#### Reverse DFS postorder in a DAG

reverse DFS postorder is a topological order!

#### Topological sort in a DAG: correctness proof

*Proposition*. Reverse DFS postorder of a DAG is a topological order.

* **Case 1**: dfs(G, w) has already been called and returned.  Thus, w was done before v.
* **Case 2**: dfs(G, w) has not yet been called. 
It will get called directly or indirectly 
by dfs(G, v) and will finish before dfs(G, v).  Thus, w will be done before v. 

#### Directed cycle detection

*Proposition*. A digraph has a topological order iff no directed cycle

pf:

* If directed cycle, topological order impossible.
* If no directed cycle, DFS-based algorithm finds a topological order.


*Goal*. Given a digraph, find a directed cycle.

### strong components

#### Strongly-connected components

*Def*. Vertices v and w are strongly connected if there is a directed path  from v to w **and** a directed path from w to v.

same as: “there is a directed cycle between v and w”

*Key property*. Strong connectivity is an equivalence relation:

```
v is strongly connected to v.
If v is strongly connected to w, then w is strongly connected to v.
If v is strongly connected to w and w to x, then v is strongly connected to x.
```

*Def*. A strong component is a maximal subset of strongly-connected vertices.

#### Connected components vs. strongly-connected components

v and w are *connected* if there is  a path between v and w

connected component id (easy to compute with DFS)

v and w are *strongly connected* if there is a directed path from v to w and a directed path from w to v

strongly-connected component id (how to compute?)

#### Strong component application: software modules

#### Kosaraju's algorithm: intuition

*Reverse graph.* Strong components in G are same as in $G^R$.

*Kernel DAG.* Contract each strong component into a single vertex.

*Idea.*

* Compute topological order (reverse postorder) in kernel DAG. 
* Run DFS, considering vertices in reverse topological order.

simple (but mysterious) algorithm for computing strong components.


* Run DFS on GR to compute reverse postorder.
* Run DFS on G, considering vertices in order given by first DFS.

#### Kosaraju proof of correctness

*Proposition*. Kosaraju’s algorithm computes strong components.

We show that the vertices marked during the constructor call dfs(G, s)
are the vertices strongly connected to s.
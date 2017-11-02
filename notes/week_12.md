### Minimum Spanning Trees

*Given*. Undirected graph G with positive edge weights (connected).

*Def*. A spanning tree of G is a subgraph T that is connected and acyclic.

*Goal*. Find a min weight spanning tree.

*Brute force*. Try all spanning trees?

#### edge-weighted graph API

#### Edge-weighted graph: adjacency-list representation

Maintain vertex-indexed array of Edge lists (use Bag abstraction).

### greedy algorithm

#### Cut property

*Simplifying assumptions*. Edge weights are distinct; graph is connected.

*Def*. A cut in a graph is a partition of its vertices into two (nonempty) sets. 
A crossing edge connects a vertex in one set with a vertex in the other.

*Cut property*. Given any cut, the crossing edge of min weight is in the MST.

#### Greedy MST algorithm

Proposition. The following algorithm computes the MST:

```
Start with all edges colored gray.
Find a cut with no black crossing edges, and color its min-weight edge black. 
Continue until V - 1 edges are colored black.
```

### Kruskal’s algorithm

Consider edges in ascending order of weight.  Add the next edge to the tree T unless doing so would create a cycle.

**Challenge**. Would adding edge v–w to tree T create a cycle? If not, add it.

**Efficient solution**. Use the union-find data structure.

```
Maintain a set for each connected component in T.
If v and w are in same set, then adding v–w would create a cycle. 
To add v–w to T, merge sets containing v and w.
```

Proposition. Kruskal's algorithm computes MST in ***O(E log E)*** time.

### Prim’s algorithm

Start with vertex 0 and greedily grow tree T. At each step, add to T the min weight edge with exactly one endpoint in T.

*Challenge*. Find the min weight edge with exactly one endpoint in T.

use a priority queue to achieve *O(logE)*

**Proposition**. Prim's algorithm computes the MST.

#### Prim’s algorithm: lazy implementation

Lazy Prim's algorithm computes the MST in time proportional  to *E log E* in the worst case.
# Shortest Paths



Shortest paths in a weighted digraph:

Given an edge-weighted digraph, find the shortest (directed) path from s to t.

**Simplifying assumption.** There exists a shortest path from s to each vertex v.
### edge-weighted digraph API

*Conventions*. Allow self-loops and parallel edges.

#### adjacency-lists representation

Same as EdgeWeightedGraph except replace Graph with Digraph.

#### Single-source shortest paths API

*Goal*. Find the shortest path from s to every other vertex.

### shortest-paths properties

A **shortest path tree** (SPT) solution exists. Why?

Can represent the SPT with two vertex-indexed arrays:

* distTo[v] is length of shortest path from s to v. 
* edgeTo[v] is last edge on shortest path from s to v.

### Generic shortest-paths algorithm
 Generic algorithm computes SPT from s.
 
* Initialize distTo[s] = 0 and distTo[v] = ∞ for all other vertices.
* Repeat until optimality conditions are satisfied: - Relax any edge.

### Dijkstra's algorithm

* Consider vertices in increasing order of distance from s (non-tree vertex with the lowest distTo[] value).
* Add vertex to tree and relax all edges incident from that vertex.

#### Dijkstra's algorithm: which priority queue?

V insert, V delete-min, E decrease-key.

Use min priority queue.
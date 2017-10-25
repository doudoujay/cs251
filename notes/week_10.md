### Undirected Graphs

*Graph.* Set of vertices connected pairwise by edges.


*Why study graph algorithms?*

```
Interesting and broadly useful abstraction.
Challenging branch of computer science and discrete math. 
Hundreds of graph algorithms known.
Thousands of practical applications.
```

#### Graph terminology

*Path.* Sequence of vertices connected by edges.
*Cycle.* Path whose first and last vertices are the same.

Two vertices are **connected** if there is a path between them.

#### Graph representation

*Graph drawing.* Provides intuition about the structure of the graph. 

*Caveat.* Intuition can be misleading.

#### Set-of-edges graph representation

Maintain a list of the edges (linked list or array).

#### Adjacency-matrix graph representation

Maintain a two-dimensional V-by-V boolean array; 

for each edge v-w in graph: adj[v][w] = adj[w][v] = true.

#### Adjacency-list graph representation

Maintain vertex-indexed array of lists.  
(use Bag abstraction)

In practice. Use adjacency-lists representation.

* Algorithms based on iterating over vertices adjacent to v. 
* Real-world graphs tend to be “sparse.”

### depth-first search

#### Maze exploration
Maze graphs.


Vertex = intersection. Edge = passage.

**Goal**. Explore every intersection in the maze.


#### Trémaux maze exploration

```
Unroll a ball of string behind you.
Mark each visited intersection and each visited passage. 
Retrace steps when no unvisited options.
```

#### Depth-first search

*Goal.* Systematically search through a graph. 

*Idea.* Mimic maze exploration.

DFS (to visit a vertex v)

```
Mark v as visited.
Recursively visit all unmarked
vertices w adjacent to v.
```

#### Design pattern for graph processing

*Design pattern.* Decouple graph data type from graph processing.

**Algorithm.**

```
Use recursion (ball of string).
Mark each visited vertex. 
Return (retrace steps) when no  unvisited options.
```
**Data structure.**

boolean[] marked to mark visited vertices.

#### Depth-first search properties

*Proposition.* DFS marks all vertices connected to s in time proportional to the sum of their degrees.                                                 
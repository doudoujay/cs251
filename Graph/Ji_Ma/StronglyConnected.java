import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class StronglyConnected {
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        //    Stdin Workaround
        if (args.length > 1)
            try {
                System.setIn(new FileInputStream(args[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //construct the graph
        int V = StdIn.readInt();
        int E = StdIn.readInt();
        int maxVertex = 0;
        Graph graph = new Graph(V);
        for (int i = 0; i < E; i++) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            graph.addEdge(v, w);
            maxVertex = Math.max(Math.max(v, w), maxVertex);
        }
        Digraph digraph = generateDigraph(graph, maxVertex);
        TarjanSCC scc = new TarjanSCC(digraph);


//        int m = scc.count();
//        StdOut.println(m + " components");
//
//        // compute list of vertices in each strong component
//        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
//        for (int i = 0; i < m; i++) {
//            components[i] = new Queue<Integer>();
//        }
//        for (int v = 0; v < digraph.V(); v++) {
//            components[scc.id(v)].enqueue(v);
//        }
//
//        // print results
//        for (int i = 0; i < m; i++) {
//            for (int v : components[i]) {
//                StdOut.print(v + " ");
//            }
//            StdOut.println();
//        }
        Digraph sccDigraph = generateSCCDigraph(digraph,scc);
        System.out.println(minEdgeNeeded(sccDigraph));
        System.out.printf(stringBuilder.toString());


    }

    public static Digraph generateSCCDigraph(Digraph digraph, TarjanSCC scc) {
        Digraph result = new Digraph(scc.count());
        for (int v = 0; v < digraph.V(); v++) {
            int firstId = scc.id(v);
            for (int w : digraph.adj(v)) {
                int secondId = scc.id(w);
                if(!(firstId==secondId)){
                    //only add edge for different id
                    result.addEdge(firstId,secondId);
                }

            }
        }
        return result;

    }
    public static int minEdgeNeeded(Digraph sccDigraph){
        if (sccDigraph.V() == 1){
            return 0;
        }
        int inCount = 0;
        int outCount = 0;
        for (int v = 0; v < sccDigraph.V(); v++) {
            if(sccDigraph.indegree(v)==0){
                inCount++;
            }
            if (sccDigraph.outdegree(v)==0){
                outCount++;
            }
        }
        return Math.max(inCount,outCount);
    }

    public static Digraph generateDigraph(Graph graph, int maxVertex) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] marked = new boolean[graph.V()];
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<Integer, ArrayList<Integer>>();
        Digraph digraph = new Digraph(graph.V());
        for (int i = 0; i < graph.V(); i++) {
            ArrayList<Integer> list = new ArrayList<>();
            graph.adj(i).iterator().forEachRemaining(list::add);
            Collections.sort(list); //sort the list
            adjList.put(i, list);
        }

        stack.push(maxVertex);
        marked[maxVertex] = true;

        while (true) {
            while (stack.size() != 0 && adjList.get(stack.peek()).size() == 0) {
                //if the top of the stack's adj has zero elements, then pop it
                stack.pop();
            }
            if (stack.size() == 0) break;
            ;
            int valueOfTopStack = stack.peek();
            int firstOfTopStack = adjList.get(stack.peek()).get(0);

            stringBuilder.append(valueOfTopStack + " " + firstOfTopStack + "\n");
            //delete the visited elements in the both lists
            adjList.get(valueOfTopStack).remove(0);
            adjList.get(firstOfTopStack).remove(Integer.valueOf(valueOfTopStack));
            digraph.addEdge(valueOfTopStack, firstOfTopStack);

            if (!marked[firstOfTopStack]) {
                // never pushed marked before
                stack.push(firstOfTopStack);
                marked[firstOfTopStack] = true;
            }


        }
        return digraph;
    }

    public static int getEndNode(DepthFirstPaths dfs, Graph graph, int maxVertex) {
        //        DepthFirstPaths dfs = new DepthFirstPaths(graph, maxVertex);
//        int endNode = getEndNode(dfs,graph,maxVertex);
//        for (int x : dfs.pathTo(endNode)) {
//
//                    if (x == maxVertex) StdOut.print(x);
//                    else StdOut.print("-" + x);
//        }
        int maxCount = 0;
        int endNode = 0;
        for (int v = 0; v < graph.V(); v++) {
            if (dfs.hasPathTo(v)) {

                StdOut.printf("%d to %d:  ", maxVertex, v);
                int count = 0;
                for (int x : dfs.pathTo(v)) {
                    count++;
                    if (x == maxVertex) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
                if (count > maxCount) {
                    maxCount = count;
                    endNode = v;
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d:  not connected\n", maxVertex, v);
            }

        }
        return endNode;
    }

}

package topological_sort.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC_210 {
    List<Integer>[] graph  ;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        buildGraph(numCourses,prerequisites);
        return topoSort(numCourses);
    }
    public int[] topoSort(int numCourses){
        int[] inDegree = new int[graph.length];
        for (int i =0  ; i <graph.length ;i++)
            for (int j : graph[i])
                inDegree[j]++;

        Queue<Integer> readyQueue = new LinkedList<>();
        for (int i =0 ; i<inDegree.length ; i++)
            if(inDegree[i]==0)
                readyQueue.add(i);

        List<Integer> result = new ArrayList<>();
        while (!readyQueue.isEmpty()){
            int current = readyQueue.remove();
            result.add(current);
            for (int i : graph[current])
                if(--inDegree[i]==0)
                    readyQueue.add(i);
        }
        int[]resultArray= new int[result.size()];
        if(result.size()!=graph.length)
            return new int[]{};
        for (int i =0 ; i< result.size(); i++)
            resultArray[i]=result.get(i);
        return resultArray;
    }
    public void buildGraph(int numCourses, int[][] prerequisites){
        graph= new ArrayList[numCourses];
        for (int i =0 ; i<numCourses ; i++)
            graph[i]=new ArrayList<>();
        for (int[] i : prerequisites)
            graph[i[1]].add(i[0]);
    }
}

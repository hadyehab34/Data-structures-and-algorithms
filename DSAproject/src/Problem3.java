import java.util.ArrayList;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int r = Integer.parseInt(s[2]);
        graphList g = new graphList(n);

        for(int i = 0; i < m; i++){
            String[] edges = sc.nextLine().split(" ");
            int u = Integer.parseInt(edges[0]);
            int v = Integer.parseInt(edges[1]);
            g.addEdge(u,v);
        }
        g.topologicalSort(r);
    }
}

class graphList {
    ArrayList<ArrayList<Integer>> adj;
    int[] inDegree;
    int[] outDegree;

    graphList(int vertices)
    {
        adj = new ArrayList<>();
        inDegree = new int[vertices+1];
        outDegree = new int[vertices+1];
        for (int i = 0; i < vertices+1; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int from, int to)
    {
        adj.get(from).add(to);
        inDegree[to]++;
        outDegree[from]++;
    }


    public void topologicalSort (int r) throws NullPointerException
    {
        myStack stack = new myStack();
        int[]tempInDegree = inDegree.clone(); // using a clone just to save the original inDegree array after the sort.
        double[]prob = new double[inDegree.length]; // array to store the probability to reach each node.

        for (int i = 1 ; i < inDegree.length ; i++ )
            if (inDegree[i] == 0 && i != r)
                stack.push(i);

        stack.push(r);
        int n;
        int adjacent;
        prob[r] = 1;
        while (!stack.isEmpty())
        {
            n = stack.pop();
            for (Integer integer : adj.get(n)) {
                adjacent = integer;
                prob[adjacent] += (prob[n]/outDegree[n]);
                // the probability of reaching a node is the probability of reaching one of its parents divided
                // the number of its parent neighbours (outDegree) And the probability is incremented each time
                // we reach a parent of that node.
                tempInDegree[adjacent]--;
                if (tempInDegree[adjacent] == 0)
                    stack.push(adjacent);
            }
        }

        double max = Double.MIN_VALUE;
        // loop to find the max value in the Prob array if the node is a sink node no edges emerge from it.
        for (int i = 1 ; i < prob.length ; i++)
            if (outDegree[i] == 0 && prob[i] > max)
                max = prob[i];

        // loop to find the nodes that you are most likely to get stuck on.
        for(int i = 1 ; i <inDegree.length; i++)
            if(outDegree[i] == 0 && Math.abs(prob[i]-max) == 0)
                System.out.print(i+" ");

    }
}

class myStack {

    StackNode top;

    static class StackNode {
        int data;
        StackNode next;

        StackNode(int d) {
            this.data = d;
        }
    }


    public void push(int data) {
        StackNode newNode = new StackNode(data);

        if (top == null) {
            top = newNode;
        } else {
            StackNode temp = top;
            top = newNode;
            newNode.next = temp;
        }
    }

    public int pop() {
        int popped = 0;
        if (top == null) {
            System.out.println("Stack is Empty");
        } else {
            popped = top.data;
            top = top.next;
        }
        return popped;
    }

    public boolean isEmpty()
    {
        return top == null;
    }
}


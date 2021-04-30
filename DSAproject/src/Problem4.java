import java.util.Scanner;

public class Problem4 {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        String x = input.nextLine();
        String[] Str = x.split(" ", 2);
        int n = Integer.parseInt(Str[0]);
        int m = Integer.parseInt(Str[1]);


        Algorithm Alg = new Algorithm();
        Alg.edgesList = new Edge[m];
        Alg.vertexList = new Vertex[n];
        Alg.MST = new int[n];

        for(int i = 0 ; i < m ; i++)
        {
            String z = input.nextLine();
            String[] arr = z.split(" ", 2);
            int u = Integer.parseInt(arr[0]);
            int v = Integer.parseInt(arr[1]);
            Edge e = new Edge(u, v);
            Alg.edgesList[i] = e;
        }

        for (int j = 0; j < n; j++) {
            Alg.vertexList[j]=new Vertex(j+1);
        }

        Alg.SortEdges();
        Alg.prim();
        
    }
}

class Vertex {
    int name;
    boolean visited;
    public Vertex (int vertexName)
    {
        name = vertexName;
        visited = false;
    }
}

class Edge {
    int from;
    int to;
    int weight;
    boolean status;
    public Edge (int u, int v)
    {
        from = u;
        to = v;
        weight = Math.abs(u-v);
        status = false;
    }
}

class Algorithm {
    Edge [] edgesList ;
    Vertex [] vertexList;
    int [] MST ;

    int count=0;

    public void prim ()
    {
        boolean flag;
        while(count < MST.length-1)
        {
            flag = false;
            for (int i = 0; (i< edgesList.length)&&(!flag); i++)
            {
                if (edgesList[i].status)
                {
                    continue;
                }
                else if (exist(i))
                {
                    continue;
                }
                else
                {
                    vertexList[edgesList[i].to-1].visited=true;
                    MST[count]=edgesList[i].to;
                    count++;
                    edgesList[i].status=true;
                    flag= true;

                }
            }
        }
        int finalCost=GetCost();
        System.out.println(finalCost);

    }


    public boolean exist (int i)   //to check if the edge forms a cycle (if it exists in MST array)
    {
        boolean ex = false;
        int j = 0;
        while ((MST [j]!= 0)&&(!ex))
        {
            if(edgesList[i].to ==MST[j])
            {
                ex= true;
            }
            j++;
        }
        return ex;
    }

    public void swap (int x, int y)
    {
        Edge temp;
        temp= edgesList[x];
        edgesList[x]=edgesList[y];
        edgesList[y]=temp;
    }

    public void SortEdges()      //using bubble sort
    {
        for(int i=0; i<edgesList.length-1; i++)
        {
            for(int j=0; j<edgesList.length-1-i; j++)
            {
                if(edgesList[j].weight< edgesList[j+1].weight)
                {
                    swap(j, j+1);
                }
            }
        }
    }

    public int GetCost ()
    {
        int cost =0;
        for (Edge edge : edgesList) {
            if (!edge.status) {
                cost = cost + edge.weight;
            }
        }
        return cost;
    }

    public boolean checkVertex (int u)
    {
        boolean check= false;
        for (Vertex vertex : vertexList) {
            if (vertex.name == u) {
                check = true;
            }
        }
        return check;
    }

    public int returnempty ()     //returns the index of the first empty place in the index
    {
        int index=-1;
        for (int i = 0; i < vertexList.length; i++) {
            if(vertexList[i].name==0)
            {
                index=i;
            }
        }
        return index;
    }
}



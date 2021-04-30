public class heap {

    int MaxSize = 1000;
    int Heap[];
    int size;

    public heap() {
        size = 0;
        Heap = new int[MaxSize];
        Heap[0] = Integer.MAX_VALUE;
    }

    int parent(int index) {
        return index / 2;
    }

    int leftChild(int index) {
        return 2 * index;
    }

    int rightChild(int index) {
        return 2 * index + 1;
    }

    boolean isLeaf(int index)
    {
        if (leftChild(index) > size) {
            return true;
        }
        return false;
    }

    void swap(int i, int j)
    {
        int tmp;
        tmp = Heap[i];
        Heap[i] = Heap[j];
        Heap[j] = tmp;
    }

    void insertHeap(int element)
    {
        Heap[++size] = element;

        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }


    int deleteMax()
    {
        int p = Heap[1];
        Heap [1] = Heap [size];
        size--;
        maxHeapify(1);
        return p;
    }


    void maxHeapify(int index)
    {
        if (isLeaf(index))
            return;

        if (Heap[index] < Heap[leftChild(index)] || Heap[index] < Heap[rightChild(index)]) {

            if (Heap[leftChild(index)] > Heap[rightChild(index)]) {
                swap(leftChild(index), index);
                maxHeapify(leftChild(index));
            } else {
                swap(rightChild(index), index);
                maxHeapify(rightChild(index));
            }
        }
    }

    int heapSort()
    {
        int n = size;

        for (int i = n; i > 0; i--) // O(n)
        {
            Heap[i] = deleteMax(); // O(log n)
        }
        return n;
    }

    public void print()
    {
        for (int i = 1; i <= size; i++) {
            System.out.print(Heap[i] + " ");
        }
        System.out.println();
    }

    public void printArray(int start, int end)
    {
        for (int i = start; i <= end; i++) {
            System.out.print(Heap[i] + " ");
        }
        System.out.println();
    }
}


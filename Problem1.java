import java.util.Objects;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        HashTable ht = new HashTable();


        for (int i = 0 ;i < s.length() ;i++)
        {
            ht.add(s.charAt(i));
        }

        int tempKey = ht.hashKey(s.charAt(0)); // temp int to store the hashing index of the first character.
        char maxKey = ht.keys[tempKey]; // char storing the key that occurs the maximum number of times.
        int maxValue = ht.values[tempKey]; // The number of occurrence of the character that occurs the most.
        String max = maxKey +" "+ maxValue; // String to print at the end.

        for (int i = 1 ; i < s.length() ;i++)
        {
            // if condition to avoid hashing the index again for the same character.
            if (tempKey != ht.hashKey(s.charAt(i)))
            {
                tempKey = ht.hashKey(s.charAt(i));
            }

            if (maxValue < ht.values[tempKey])
            {
                maxValue = ht.values[tempKey];
                maxKey = ht.keys[tempKey];
                max = maxKey +" "+ maxValue;
            }

            // to output the lower ASCII value
            if (maxValue == ht.values[tempKey])
            {
                if (maxKey < ht.keys[tempKey])
                    max = maxKey +" "+maxValue;
                else
                {
                    maxKey = ht.keys[tempKey];
                    max = maxKey +" "+ maxValue;
                }
            }
        }

        if (maxKey == ' ')
        {
            max = "Space " + maxValue;
            System.out.println(max);
        }else System.out.println(max);

    }
}


// Hash Table using linear probing.
class HashTable {
    int currentSize,maxSize;
    char [] keys; // characters in the given string
    int [] values; // the number of occurrence of the key in the given string.

    HashTable ()
    {
        currentSize = 0;
        maxSize = 1000;
        keys = new char[maxSize];
        values = new int[maxSize];

    }

    boolean isFull(){
        return currentSize == maxSize;
    }

    int hashKey (Character key)
    {
        return key.hashCode() % maxSize;
    }

    void add(char key)
    {
        int tmp = hashKey(key);
        int i = tmp;

        do {
            if (keys[i] == '\0') // null for chars
            {
                keys[i] = key;
                values[i] = 1;
                currentSize++;
                return;
            }
            if (Objects.equals(keys[i], key))
            {
                values[i]++; // incrementing the value of the key if it pops again in the string.
                return;
            }
            i = (i + 1) % maxSize;
        }while (i != tmp);
    }
}
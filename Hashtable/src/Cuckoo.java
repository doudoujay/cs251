import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.util.AbstractMap;
import java.util.ArrayList;

public class Cuckoo<K, V> {
    static final double e = 0.05; // e is epsilon
    private int r = 256; // initial value for r
    private int n = 0; // number of keys stored
    private int limitCount = 0; //limit count for dislodging
    private long a1;
    private long a2;
    private boolean verbose;
    private int Lmax; //upper bound
    private ArrayList<String> soutTemp;
    Entry<K, V>[] table;

    public static void main(String[] args) {
        //    Stdin Workaround
        if (args.length > 1)
            try {
                System.setIn(new FileInputStream(args[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        long a1 = StdIn.readLong(); //read a1
        long a2 = StdIn.readLong(); //read a2
        Cuckoo<Integer, Integer> cuckoo = new Cuckoo<Integer, Integer>(a1, a2);
        cuckoo.setVerbose(true);

        int size = StdIn.readInt(); //read size
        for (int i = 0; i < size; i++) {
            String command = StdIn.readString(); //Read command
            int key;
            int val;
            switch (command) {

                case "put":
                    key = StdIn.readInt();
                    val = StdIn.readInt();
                    cuckoo.put(key, val);
                    break;
                case "get":
                    key = StdIn.readInt();
                    cuckoo.get(key);
                    break;
                case "delete":
                    key = StdIn.readInt();
                    cuckoo.delete(key);
                    break;
                case "contains":
                    key = StdIn.readInt();
                    cuckoo.contains(key);
                    break;
                case "size":
                    cuckoo.size();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command provided!");
            }

        }


    }

    public Cuckoo(long a1, long a2) {
        table = new Entry[r];
        this.a1 = a1;
        this.a2 = a2;
        rehash();
    }

    public void setVerbose(boolean b) {
        this.verbose = b;
    }

    public int hash(K k, boolean isa1) {
        int x = k.hashCode(); // a positive int
        if (isa1) {
            return (int) (((a1 * x) / Math.pow(2, 16)) % r);
        } else {
            return (int) (((a2 * x) / Math.pow(2, 16)) % r);
        }


    }

    public int otherPossibleHash(K key, int index) {
        int hash1 = hash(key, true);
        return (hash1 == index) ? hash(key, false) : hash1;

    }

    public void update_r() {
        this.r = this.r * 2; //doubling
    }

    public void update_Lmax() {
//        logb(n) = loge(n) / loge(b)
        Lmax = (int) Math.ceil((double) 3 * (Math.log(r) / Math.log(1 + e)) + 1);
    }


    public void put(K key, V value) {


        int index_1;
        int index_2;
//        hash h1
        index_1 = hash(key, true);
        index_2 = hash(key, false);
        if (table[index_1] != null && table[index_1].getKey().equals(key)) {
            //Set operation 1
            table[index_1] = new Entry<K, V>(key, value);
            if (verbose) {
                System.out.printf("(%d %d %d)\n", index_1, table[index_1].getKey(), table[index_1].getValue());
            }

        } else if (table[index_2] != null && table[index_2].getKey().equals(key)) {
            //Set operation 2
            table[index_2] = new Entry<K, V>(key, value);
            if (verbose) {
                System.out.printf("(%d %d %d)\n", index_2, table[index_2].getKey(), table[index_2].getValue());
            }
        } else {
            updateAllInternalValues();
            index_1 = hash(key, true);
            index_2 = hash(key, false);

//            does not exit, insert new
            if (table[index_1] == null) {
//        If that cell is empty, insert the key
                table[index_1] = new Entry<K, V>(key, value);
                if (verbose) {
                    System.out.printf("(%d %d %d)\n", index_1, table[index_1].getKey(), table[index_1].getValue());
                }

            } else {
//        if not, second hash function
                if (table[index_2] == null) {
//                If that cell is empty, the new key is inserted there,
                    table[index_2] = new Entry<K, V>(key, value);
                    if (verbose) {
                        System.out.printf("(%d %d %d)\n", index_2, table[index_2].getKey(), table[index_2].getValue());
                    }
                } else {
//                otherwise it is inserted at index i1, thereby dislodging the key previously stored there.
                    dislodging(index_1, new Entry<K, V>(key, value));


                }
            }
            n++;
        }


    }

    public void dislodging(int index, Entry<K, V> currentEntry) {
//        index is the location where exists some temp entry
//        currentEntry is the entry need to be inserted
        if (limitCount >= Lmax) {
//            double r
            update_r(); // size of hash table doubled
//            rehash occurs
            rehash();
            //TODO:  re calculate hash
            table[index] = currentEntry;
            System.out.printf("(%d %d %d)\n", index, currentEntry.getKey(), currentEntry.getValue());
            limitCount = 0; //reset limit
            return;

        }

        limitCount++; //increment limit count for every call
        Entry temp = table[index];
        table[index] = currentEntry;
        if (verbose) {
            System.out.printf("(%d %d %d)\n", index, currentEntry.getKey(), currentEntry.getValue());
        }
        int alternativdeIndex = otherPossibleHash((K) temp.getKey(), index); //calculate dislodging index
        if (table[alternativdeIndex] == null) {
//            Great, found a empty space
            table[alternativdeIndex] = temp;
            limitCount = 0; //reset limit
            if (verbose) {
                System.out.printf("(%d %d %d)\n", alternativdeIndex, temp.getKey(), temp.getValue());
            }
            return;
        } else {
            dislodging(alternativdeIndex, temp);

        }

    }

    public void rehash() {
        Entry<K, V>[] oldTable = table;
        System.out.printf("(hash %d %d %d)\n", a1, a2, r);
        table = new Entry[r];
        n = 0; //clear current size
        //        Reinsert
        for (Entry<K, V> entry : oldTable
                ) {
            if (entry != null) {
                put(entry.getKey(), entry.getValue());
            }
        }


    }

    public V get(K key) {
//        Print the value corresponding to the key
//        If the key is not in the table, print "none" (without quotes).
        int hash_1 = hash(key, true);
        int hash_2 = hash(key, false);
        if (table[hash_1] != null && table[hash_1].getKey().equals(key)) {
            System.out.println(table[hash_1].getValue());
            return table[hash_1].getValue();
        } else if (table[hash_2] != null && table[hash_2].getKey().equals(key)) {
            System.out.println(table[hash_2].getValue());
            return table[hash_2].getValue();
        } else {
            System.out.println("none");
            return null;
        }
    }

    public boolean contains(K key) {
//        Print "yes" if the key is in the table, or "no" otherwise (without quotes).
        int hash_1 = hash(key, true);
        int hash_2 = hash(key, false);
        if (table[hash_1] == null && table[hash_2] == null) {
            System.out.printf("no\n");
            return false;
        }

        if ((table[hash_1] != null && table[hash_1].getKey().equals(key)) || (table[hash_2] != null && (table[hash_2].getKey().equals(key)))) {
            System.out.printf("yes\n");
            return true;
        } else {
            System.out.printf("no\n");
            return false;
        }


    }


    public void delete(K key) {
//        Delete the key from the table.
//        This command does not have any output.
        int hash_1 = hash(key, true);
        int hash_2 = hash(key, false);
        if (table[hash_1] != null && table[hash_1].getKey().equals(key)) {
            table[hash_1] = null;
            n--;
            return;
        } else if (table[hash_2] != null && table[hash_2].getKey().equals(key)) {
            table[hash_2] = null;
            n--;
            return;
        } else {
//            System.out.println("No such key exists");
        }

    }

    public int size() {
        System.out.println(n);
        return n;
    }

    public void updateAllInternalValues() {
        update_Lmax();
        if (r < (2 * (1 + e) * (n + 1))) {
            update_r();
            rehash();
        }
    }


}

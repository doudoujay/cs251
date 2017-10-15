### Hash Tables

#### Hashing: basic plan

Save items in a key-indexed table (index is a function of the key).

*Hash function.* Method for computing array index from key.

*Issues.*

```
• Computing the hash function.
• Equality test: Method for checking whether two keys are equal.
• Collision resolution: Algorithm and data structure 
to handle two keys that hash to the same array index.
```

*Classic space-time tradeoff.*

Space and time limitations: hashing (the real world).

#### hash functions

key -> table index

*Practical challenge.* Need different approach for each key type.

#### Java’s hash code conventions

All Java classes inherit a method hashCode(), which returns a 32-bit int.

*Requirement.*
If x.equals(y), then (x.hashCode()==y.hashCode()).

*Highly desirable.* 
If !x.equals(y), then (x.hashCode() != y.hashCode()).

#### Implementing hash code: user-defined types

for primitive types, use hashCode()  of wrapper type

for reference types, use hashCode()

#### Hash code design

"Standard" recipe for user-defined types.

* Combine each significant field using the 31x + y rule.
* If field is a primitive type, use wrapper type hashCode().
* If field is an array, apply to each element.(Arrays.deepHashCode())
* If field is a reference type, use hashCode()

*In practice.* Recipe works reasonably well; used in Java libraries. 

*In theory.* Need a theorem for each type to ensure reliability.

#### Modular hashing

*Hashcode.* An int between -2^31 and 2^31-1.

*Hashfunction.*An int between 0 and M-1(for use as array index).

#### Uniform hashing assumption
Each key is equally likely to hash to an integer between 0 and M - 1.

*Bins and balls.* Throw balls uniformly at random into M bins.

### separate chaining

#### Collisions
Collision. Two distinct keys hashing to same index.

*Challenge*. Deal with collisions efficiently.

#### Separate chaining ST

Use an array of M < N linked lists. [H. P. Luhn, IBM 1953]

#### Analysis of separate chaining

*Proposition.* Under uniform hashing assumption, probability that the number of keys in a list is within a constant factor of N / M is extremely close to 1.

*Consequence.* Number of probes for search/insert is proportional to N / M.

* M too large ⇒ too many empty chains.
* M too small ⇒ chains too long.
* Typical choice: M ~ N / 5 ⇒ constant-time ops.


### linear probing

#### Collision resolution: open addressing

When a new key collides, find next empty slot, and put it there.

#### Linear probing
Use an array of size M > N.

```
Hash: map key to integer i between 0 and M - 1.
Insert: put at table index i if free; if not try i + 1, i + 2, etc.
Search: search table index i; if occupied but no match, try i + 1, i + 2, etc.
```

#### Clustering

*Cluster.* A contiguous block of items.

*Observation.* New keys likely to hash into middle of big clusters.

#### Knuth's parking problem

*Model.* Cars arrive at one-way street with M parking spaces. 
Each desires a random space i : if space i is taken, try i + 1, i + 2, etc. 

#### Analysis of linear probing

N = α M

```
M too large ⇒ too many empty array entries. 
M too small ⇒ search time blows up.
Typical choice: α = N/M ~ 1⁄2
```

#### Diversion: one-way hash functions

*One-way hash function.* "Hard" to find a key that will hash to a desired value (or two keys that hash to same value).


Ex. MD4, MD5, SHA-0, SHA-1, SHA-2, WHIRLPOOL, RIPEMD-160,

*Applications.* Digital fingerprint, message digest, storing passwords. 

*Caveat*. Too expensive for use in ST implementations.

#### Separate chaining vs. linear probing

**Separate chaining.**

```
Easier to implement delete.
Performance degrades gracefully.
Clustering less sensitive to poorly-designed hash function.
```
**Linear probing.**

```
Less wasted space.
Better cache performan/ce.
```
awk '{
    for (i = 1; i <= NF; i++) {
        if (NR == 1) {
            res[i] = $i;
        } else {
            res[i] = res[i] " " $i;
        }
    }
} END {
    for (i = 1; i <= NF; i++) {
        print res[i];
    }
}' file.txt

: << 'COMMENT'
# Intuition
Transposing a file means turning its rows into columns and columns into rows. Since standard row-based streaming tools like `sed` or `grep` process lines independently, `awk` is perfect here because it natively tracks rows (`NR`), fields/columns (`NF`), and lets us build an array in memory.

# Approach
1. We use `awk` to loop through every field/column (`i`) of every row.
2. `NR == 1`: For the very first row, we initialize our array `res[i]` with the column values.
3. For subsequent rows, we append a space and the current field value (`$i`) to `res[i]`.
4. `END`: After reading the whole file, we iterate through our stored array and print each reconstructed row, which represents the transposed columns.

# Complexity
- Time complexity:
O(N * M)
Where N is the number of rows and M is the number of columns in the file. Every single word must be processed exactly once.

- Space complexity:
O(N * M)
The entire content of the file is stored dynamically inside `awk`'s internal array map to reconstruct the transposed output.
COMMENT

sed -n '10p' file.txt

: << 'COMMENT'
# Intuition
To extract a specific line from a text file, we need a stream editor that can locate line numbers without loading the entire file into memory or processing unnecessary lines. 

# Approach
The `sed` utility allows for efficient line-by-line stream editing. By using the `-n` flag, we suppress automatic printing of every line. Specifying `10p` instructs `sed` to match exactly the 10th line and execute the print command. If the file has fewer than 10 lines, it naturally outputs nothing.

# Complexity
- Time complexity:
O(N)
Where N is the number of lines processed up to the 10th line.

- Space complexity:
O(1)
The file is processed sequentially as a stream, consuming a constant amount of memory.
COMMENT

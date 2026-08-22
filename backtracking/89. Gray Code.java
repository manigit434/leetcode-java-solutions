import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int sequenceLength = 1 << n; // 2^n elements

        // Generate each Gray code sequence element directly
        for (int i = 0; i < sequenceLength; i++) {
            result.add(i ^ (i >> 1));
        }

        return result;
    }
}

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        
        int minLandFinish = Integer.MAX_VALUE;
        int minLandStart = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minLandFinish = Math.min(minLandFinish, landStartTime[i] + landDuration[i]);
            minLandStart = Math.min(minLandStart, landStartTime[i]);
        }
        
        int minWaterFinish = Integer.MAX_VALUE;
        int minWaterStart = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            minWaterFinish = Math.min(minWaterFinish, waterStartTime[j] + waterDuration[j]);
            minWaterStart = Math.min(minWaterStart, waterStartTime[j]);
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (int j = 0; j < m; j++) {
            int finishFirst = minLandFinish;
            int startSecond = Math.max(finishFirst, waterStartTime[j]);
            int totalFinish = startSecond + waterDuration[j];
            ans = Math.min(ans, totalFinish);
        }
        
        for (int i = 0; i < n; i++) {
            int finishFirst = minWaterFinish;
            int startSecond = Math.max(finishFirst, landStartTime[i]);
            int totalFinish = startSecond + landDuration[i];
            ans = Math.min(ans, totalFinish);
        }
        
        return ans;
    }
}

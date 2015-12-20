package leetcode;

public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int len = secret.length();
        int bullsCount = 0;
        int cowsCount = 0;
        int[] ct = new int[256];
        
        for (int i = 0; i < len; ++i) {
            if (guess.charAt(i) == secret.charAt(i)) {
            	bullsCount += 1;
            } else {
            	if (++ct[secret.charAt(i)] <= 0) {
            		cowsCount += 1;
            	}
            	if (--ct[guess.charAt(i)] >= 0) {
            		cowsCount += 1;
            	}
            }
        }
        return bullsCount + "A" + cowsCount + "B";
    }
}

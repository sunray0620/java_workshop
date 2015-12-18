package leetcode;

import java.util.Arrays;

/**************
 Additive number is a string whose digits can form additive sequence.
 A valid additive sequence should contain at least three numbers.
 Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

 For example:
 "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 **************/

public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        // Corner Cases:
        if (num == null || num.length() == 0) {
            return false;
        }

        // Enumerate all possible first two numbers,
        // And validate additive numbers
        char[] numChars = num.toCharArray();
        for (int idx2 = 1; idx2 < numChars.length - 1; ++idx2) {
            for (int idx3 = idx2 + 1; idx3 < numChars.length; ++idx3) {
                if (isAdditivePart(numChars, 0, idx2, idx3)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdditivePart(char[] nc, int idx1, int idx2, int idx3) {
        while (idx3 < nc.length) {
            int len1 = idx2 - idx1;
            int len2 = idx3 - idx2;
            int max_len3 = nc.length - idx3;

            // Early termination case 1: invalid number 1.
            if (len1 > 1 && nc[idx1] == '0') {
                return false;
            }
            // Early termination case 2: invalid number 2.
            if (len2 > 1 && nc[idx2] == '0') {
                return false;
            }
            // Early termination case 3: invalid number 3.
            if (max_len3 > 1 && nc[idx3] == '0') {
                return false;
            }
            // Early termination case 4: remained string not long enough.
            if (Math.max(len1, len2) > max_len3) {
                return false;
            }

            // Compare summation result with string starting from idx3
            char[] r = addString(nc, idx1, idx2, idx3);
            for (int ri = 0, ci = idx3; ri < r.length; ++ri, ++ci) {
                if (ci >= nc.length || r[ri] != nc[ci]) {
                    return false;
                }
            }
            idx1 = idx2;
            idx2 = idx3;
            idx3 += r.length;
        }
        return true;
    }

    private char[] addString(char[] nc, int idx1, int idx2, int idx3) {
        int len1 = idx2 - idx1;
        int len2 = idx3 - idx2;
        int buf_size = Math.max(len1, len2) + 2;
        char[] ret_temp = new char[buf_size];

        // Do summation in buffer.
        int i3 = buf_size - 1;
        for (int i1 = idx2 - 1, i2 = idx3 - 1, carryOn = 0;
             i1 >= idx1 || i2 >= idx2 || carryOn > 0;
             --i1, --i2, --i3) {
            int n1 = (i1 >= idx1)? nc[i1] - '0' : 0;
            int n2 = (i2 >= idx2)? nc[i2] - '0' : 0;
            int n3 = n1 + n2 + carryOn;
            carryOn = n3 / 10;
            n3 %= 10;
            ret_temp[i3] = (char)(n3 + '0');
        }

        return Arrays.copyOfRange(ret_temp, i3 + 1, buf_size);
    }
}
/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int max = 0;
        for (String i : asciis) {
            max = max < i.length() ? i.length() : max;
        }
        String[] ret = new String[asciis.length];
        System.arraycopy(asciis, 0, ret, 0, asciis.length);
        for (int i = 0; i < max; i += 1) {
            sortHelperLSD(ret, max - i - 1);
        }
        return ret;
    }

    /** get the Integer number at a given index */
    private static int getChar(String s, int index) {
        if (s.length() <= index) {
            return -1;
        } else {
            return (int) s.charAt(index);
        }
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] count = new int [257];
        for (String i : asciis) {
            int charNum = getChar(i, index);
            count[charNum + 1] += 1;
        }
        int[] start = new int [257];
        start[0] = 0;
        for (int i = 1; i < 257; i += 1) {
            start[i] = start[i - 1] + count[i - 1];
        }
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < sorted.length; i += 1) {
            int charNum = getChar(asciis[i], index);
            sorted[start[charNum + 1]] = asciis[i];
            start[charNum + 1] += 1;
        }
        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}

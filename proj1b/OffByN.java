public class OffByN implements CharacterComparator {
    private int expetedDiffer;
    public OffByN(int N) {
        expetedDiffer = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (!Character.isAlphabetic(x) || !Character.isAlphabetic(y)) {
            return false;
        }
        int different = Math.abs(x - y);
        return different == expetedDiffer;
    }

}

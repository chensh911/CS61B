public class OffByN implements CharacterComparator {
    private int expetedDiffer;
    public OffByN(int N) {
        expetedDiffer = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int different = Math.abs(x - y);
        return different == expetedDiffer;
    }

}

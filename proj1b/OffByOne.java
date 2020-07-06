public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int different = Math.abs(x - y);
        return different == 1;
    }
}

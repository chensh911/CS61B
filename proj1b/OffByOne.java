public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (!Character.isAlphabetic(x) || !Character.isAlphabetic(y)) {
            return false;
        }
        int different = Math.abs(x - y);
        return different == 1;
    }
}

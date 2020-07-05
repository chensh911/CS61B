public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            ret.addLast(word.charAt(i));
        }
        return ret;
    }
    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> d = palindrome.wordToDeque(word);
        while(d.size() != 0 &&d.size() != 1) {
            Character a = d.removeFirst();
            Character b = d.removeLast();
            if (!a.equals(b)){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {

    }

}

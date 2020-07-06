import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test(timeout = 1000)
    public void TestEqualChars(){
        assertTrue(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'b'));
    }
    /** Uncomment this class once you've created your CharacterComparator interface and OffByOne class. */
}

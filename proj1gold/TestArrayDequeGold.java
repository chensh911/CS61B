import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void testTask() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        for (int i = 0; i < 30; i++) {
            double NumberFromZeroToFour = StdRandom.uniform(4);
            if (NumberFromZeroToFour < 1) {
                //addLast
                Integer NumberToAdd = (Integer) StdRandom.uniform(100);
                student.addLast(NumberToAdd);
                solution.addLast(NumberToAdd);
                assertEquals("addLast(" + NumberToAdd + ")\n",solution.get(solution.size() - 1), student.get(solution.size() - 1));
            } else if (NumberFromZeroToFour < 2) {
                //addFirst
                Integer NumberToAdd = (Integer) StdRandom.uniform(100);
                student.addFirst(NumberToAdd);
                solution.addFirst(NumberToAdd);
                assertEquals("addFirst(" + NumberToAdd + ")\n",solution.get(0), student.get(0));
            } else if (NumberFromZeroToFour < 3) {
                //removeLast
                Integer expected = solution.removeLast();
                Integer actual = student.removeLast();
                assertEquals("removeLast(): " + expected + "\n",expected, actual);
                if (solution.size() != 0 && student.size() != 0) {
                    assertEquals(solution.get(solution.size() - 1), student.get(solution.size() - 1));
                }
                assertEquals(solution.size(), student.size());
            } else {
                //removeFirst
                Integer expected = solution.removeFirst();
                Integer actual = student.removeFirst();
                assertEquals("removeFirst(): " + expected + "\n", expected, actual);
                assertEquals(solution.size(), student.size());
                if (solution.size() != 0 && student.size() != 0) {
                    assertEquals(solution.get(0), student.get(0));
                }
            }
        }
    }
}

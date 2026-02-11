import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests functionality of the DynamicArray class.
 */
public class DynamicArrayTest {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    /**
     * Compares the sizes and elements of a DynamicArray<Character> and a String.
     */
        public void compare(DynamicArray<Character> arr, String s) {
            compareToString(arr, s);
            compareSize(arr, s);
        }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    /**
     * Tests that creating an array with negative capacity results in a RuntimeException.
     */
    @Test (expected = RuntimeException.class)
    public void testConstructorNegative() {
        DynamicArray<Character> array = new DynamicArray<Character>(-1);
    }

    /**
     * Tests that creating an array with positive capacity results in an empty array.
     */
    @Test
    public void testConstructorStandard() {
        DynamicArray<Character> array = new DynamicArray<Character>(3);
        compare(array, "");
        DynamicArray<Character> array2 = new DynamicArray<Character>(1);
        compare(array2, "");
    }

    /**
     * Tests that creating an array with zero capacity results in an empty array.
     */
    @Test
    public void testConstructorZero() {
        DynamicArray<Character> array = new DynamicArray<Character>(0);
        compare(array, "");
    }

    /**
     * Tests that copy constructor creates a deep copy of an empty array.
     */
    @Test
    public void testCopyConstructorEmpty() {
        DynamicArray<Character> copy = new DynamicArray<Character>(empty);
        compare(copy, "");
        empty.add('a');
        compare(copy, "");
    }

    /**
     * Tests that copy constructor creates a deep copy of a nonempty array.
     */
    @Test
    public void testCopyConstructorStandard() {
        DynamicArray<Character> copy = new DynamicArray<Character>(a1);
        compare(copy, "abcdef");
        a1.add('a');
        compare(copy, "abcdef");
    }

    /**
     * Tests that copy constructor creates a deep copy of an array of size 1.
     */
    @Test
    public void testCopyConstructorSingle() {
        DynamicArray<Character> copy = new DynamicArray<Character>(s);
        compare(copy, "s");
        s.add('a');
        compare(copy, "s");
    }

    /**
     * Tests that size() returns 0 on an empty array.
     */
    @Test
    public void testSizeEmpty() {
        assertEquals(empty.size(), 0);
    }

    /**
     * Tests that size() returns 1 on an array with 1 element.
     */
    @Test
    public void testSizeSingle() {
        assertEquals(s.size(), 1);
    }

    /**
     * Tests that size() returns the correct size of a nonempty array.
     */
    @Test
    public void testSizeStandard() {
        assertEquals(a1.size(), 6);
        assertEquals(a2.size(), 4);
    }

    /**
     * Tests that isEmpty() returns true on an empty array.
     */
    @Test
    public void testIsEmptyEmpty() {
        assertTrue(empty.isEmpty());
    }

    /**
     * Tests that isEmpty() returns false on an array of size 1.
     */
    @Test
    public void testIsEmptySingle() {
        assertFalse(s.isEmpty());
    }

    /**
     * Tests that isEmpty() returns false on a nonempty array.
     */
    @Test
    public void testIsEmptyNonempty() {
        assertFalse(a1.isEmpty());
    }

    /**
     * Tests that get() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetNegative() {
        a1.get(-1);
    }

    /**
     * Tests that get() throws an IndexOutOfBoundsException for index = size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetSize() {
        a1.get(6);
    }

    /**
     * Tests that get() throws an IndexOutOfBoundsException on an empty array.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetEmpty() {
        empty.get(0);
    }

    /**
     * Tests that get() accurately accesses index 0.
     */
    @Test
    public void testGetZero() {
        assertSame(a1.get(0), 'a');
        assertSame(a2.get(0), 'w');
        assertSame(s.get(0), 's');
    }

    /**
     * Tests that get() accurately accesses middle indices.
     */
    @Test
    public void testGetStandard() {
        assertSame(a1.get(2), 'c');
        assertSame(a2.get(1), 'x');
    }

    /**
     * Tests that get() accurately accesses the index at (size - 1).
     */
    @Test
    public void testGetEnd() {
        assertSame(a1.get(5), 'f');
        assertSame(a2.get(3), 'z');
    }

    /**
     * Tests that set() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetNegative() {
        a1.set(-1, 'a');
    }

    /**
     * Tests that set() throws an IndexOutOfBoundsException for index = size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetSize() {
        a1.set(6, 'a');
    }

    /**
     * Tests that set() throws an IndexOutOfBoundsException on an empty array.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetEmpty() {
        empty.set(0, 'a');
    }

    /**
     * Tests setting the element at index 0.
     */
    @Test
    public void testSetZero() {
        a1.set(0, 'A');
        compare(a1, "Abcdef");
        s.set(0, 'A');
        compare(s, "A");
    }

    /**
     * Tests setting elements at middle indices.
     */
    @Test
    public void testSetStandard() {
        a1.set(1, 'B');
        a1.set(3, 'D');
        compare(a1, "aBcDef");
    }

    /**
     * Tests setting the element at index = size - 1.
     */
    @Test
    public void testSetEnd() {
        a1.set(5, 'F');
        compare(a1, "abcdeF");
    }

    /**
     * Tests that add() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddNegative() {
        a1.add(-1, 'a');
    }

    /**
     * Tests that add() throws an IndexOutOfBoundsException for index > size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddTooHigh() {
        a1.add(7, 'a');
    }

    /**
     * Tests adding to an empty array.
     */
    @Test
    public void testAddEmpty() {
        empty.add('a');
        compare(empty, "a");
    }

    /**
     * Tests adding at index 0.
     */
    @Test
    public void testAddZero() {
        a1.add(0, '!');
        compare(a1, "!abcdef");
        s.add(0, '!');
        compare(s, "!s");
    }

    /**
     * Tests adding at middle indices.
     */
    @Test 
    public void testAddStandard() {
        a1.add(3, '!');
        compare(a1, "abc!def");
    }

    /**
     * Tests adding at index = size.
     */
    @Test
    public void testAddEnd() {
        a1.add(6,'!');
        a1.add('!');
        compare(a1, "abcdef!!");
        s.add('!');
        compare(s, "s!");
    }

    /**
     * Tests that remove() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveNegative() {
        a1.remove(-1);
    }

    /**
     * Tests that remove() throws an IndexOutOfBoundsException for index = size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveSize() {
        a1.remove(6);
    }

    /**
     * Tests that remove() throws an IndexOutOfBoundsException on an empty array.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveEmpty() {
        empty.remove(0);
    }

    /**
     * Tests removing the element at index 0.
     */
    @Test
    public void testRemoveZero() {
        assertSame(a1.remove(0), 'a');
        compare(a1, "bcdef");
        assertSame(s.remove(0), 's');
        compare(s, "");
    }

    /**
     * Tests removing middle elements.
     */
    @Test
    public void testRemoveStandard() {
        assertSame(a1.remove(2), 'c');
        compare(a1, "abdef");
    }

    /**
     * Tests removing the last element.
     */
    @Test
    public void testRemoveEnd() {
        assertSame(a1.remove(5), 'f');
        compare(a1, "abcde");
    }

    /**
     * Tests that toString() properly prints an empty array.
     */
    @Test
    public void testToStringEmpty() {
        assertEquals(empty.toString(), "[]");
    }

    /**
     * Tests that toString() properly prints an array of size 1.
     */
    @Test
    public void testToStringSingle() {
        assertEquals(s.toString(), "[s]");
    }

    /**
     * Tests that toString() properly prints a nonempty array.
     */
    @Test
    public void testToStringStandard() {
        assertEquals(a1.toString(), "[a, b, c, d, e, f]");
    }

    /**
     * Tests that addAll() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddAllNegative() {
        a1.addAll(-1, a2);
    }

    /**
     * Tests that addAll() throws an IndexOutOfBoundsException for index > size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddAllTooHigh() {
        a1.addAll(7, a2);
    }

    /**
     * Tests that when one array used in addAll() is empty, the result matches the other array.
     */
    @Test
    public void testAddAllEmpty() {
        compare(a1.addAll(2, empty), "abcdef");
        compare(empty.addAll(0, a1), "abcdef");
        compare(empty.addAll(0, empty), "");
    }

    /**
     * Tests that calling addAll() with index 0 results in concatenating the current array 
     * onto the passed array.
     */
    @Test
    public void testAddAllZero() {
        compare(a1.addAll(0, a2), "wxyzabcdef");
        compare(s.addAll(0, a1), "abcdefs");
    }

    /**
     * Tests that passing an array of size 1 into addAll() results in inserting the one 
     * element at the specified index.
     */
    @Test
    public void testAddAllSingle() {
        compare(a1.addAll(3, s), "abcsdef");
        compare(s.addAll(0,s), "ss");
    }

    /**
     * Tests that addAll() with two nonempty arrays results in the current array 
     * with the elements of the passed array inserted.
     */
    @Test
    public void testAddAllStandard() {
        compare(a1.addAll(3, a2), "abcwxyzdef");
    }

    /**
     * Tests that using addAll() to insert a nonempty array into itself results in the current array
     * with an extra copy inserted at the specified index.
     */
    @Test
    public void testAddAllSelf() {
        compare(a1.addAll(2, a1), "ababcdefcdef");
    }

    /**
     * Tests that addAll() leaves the original arrays unchanged.
     */
    @Test
    public void testAddAllFunctionStyle() {
        a1.addAll(3, a2);
        compare(a1, "abcdef");
        compare(a2, "wxyz");
    }

    /**
     * Tests that splitSuffix() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSplitSuffixNegative() {
        a1.splitSuffix(-1);
    }

    /**
     * Tests that splitSuffix() throws an IndexOutOfBoundsException for index > size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSplitSuffixTooHigh() {
        a1.splitSuffix(7);
    }

    /**
     * Tests that splitSuffix() on an empty array returns an empty array.
     */
    @Test
    public void testSplitSuffixEmpty() {
        compare(empty.splitSuffix(0), "");
    }

    /**
     * Tests that splitSuffix() at index 0 returns an array identical to the original.
     */
    @Test
    public void testSplitSuffixZero() {
        compare(a1.splitSuffix(0), "abcdef");
        compare(s.splitSuffix(0), "s");
    }

    /**
     * Tests that splitSuffix() at a middle index returns an array with all elements 
     * starting at the split.
     */
    @Test
    public void testSplitSuffixStandard() {
        compare(a1.splitSuffix(3), "def");
    }

    /**
     * Tests that splitSuffix() at index = size returns an empty array.
     */
    @Test
    public void testSplitSuffixSize() {
        compare(a1.splitSuffix(6), "");
        compare(s.splitSuffix(1), "");
    }

    /**
     * Tests that splitSuffix() leaves the original array unchanged.
     */
    @Test
    public void testSplitSuffixFunctionStyle() {
        a1.splitSuffix(3);
        compare(a1, "abcdef");
    }

    /**
     * Tests that splitPrefix() throws an IndexOutOfBoundsException for negative indices.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSplitPrefixNegative() {
        a1.splitPrefix(-1);
    }

    /**
     * Tests that splitPrefix() throws an IndexOutOfBoundsException for index > size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testSplitPrefixTooHigh() {
        a1.splitPrefix(7);
    }

    /**
     * Tests that splitPrefix() on an empty array returns an empty array.
     */
    @Test
    public void testSplitPrefixEmpty() {
        compare(empty.splitPrefix(0), "");
    }

    /**
     * Tests that splitPrefix() at index 0 returns an empty array.
     */
    @Test
    public void testSplitPrefixZero() {
        compare(a1.splitPrefix(0), "");
        compare(s.splitPrefix(0), "");
    }

    /**
     * Tests that splitPrefix() at a middle index returns an array with all 
     * elements before the split.
     */
    @Test
    public void testSplitPrefixStandard() {
        compare(a1.splitPrefix(3), "abc");
    }

    /**
     * Tests that splitPrefix() at index = size returns an array identical to the original.
     */
    @Test
    public void testSplitPrefixSize() {
        compare(a1.splitPrefix(6), "abcdef");
        compare(s.splitPrefix(1), "s");
    }

    /**
     * Tests that splitPrefix() leaves the original array unchanged.
     */
    @Test
    public void testSplitPrefixFunctionStyle() {
        a1.splitPrefix(3);
        compare(a1, "abcdef");
    }

    /**
     * Tests that delete() throws an IndexOutOfBoundsException for fromIndex < 0.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testDeleteNegative() {
        a1.delete(-1, 1);
    }

    /**
     * Tests that delete() throws an IndexOutOfBoundsException for fromIndex > toIndex.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testDeleteRange() {
        a1.delete(2, 1);
    }

    /**
     * Tests that delete() throws an IndexOutOfBoundsException for toIndex > size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testDeleteTooHigh() {
        a1.delete(2, 7);
    }

    /**
     * Tests that delete() on an empty array returns an empty array.
     */
    @Test
    public void testDeleteEmpty() {
        compare(empty.delete(0, 0), "");
    }

    /**
     * Tests that delete() with toIndex = fromIndex returns an array identical to the original.
     */
    @Test
    public void testDeleteNoRange() {
        compare(a1.delete(3, 3), "abcdef");
        compare(s.delete(1,1), "s");
    }

    /**
     * Tests that deleting from 0 to size returns an empty array.
     */
    @Test
    public void testDeleteFullRange() {
        compare(a1.delete(0, 6), "");
        compare(s.delete(0, 1), "");
    }

    /**
     * Tests deleting a middle range of elements.
     */
    @Test
    public void testDeleteStandard() {
        compare(a1.delete(2, 4), "abef");
    }

    /**
     * Tests that delete() leaves the original array unchanged.
     */
    @Test
    public void testDeleteFunctionStyle() {
        a1.delete(2, 4);
        compare(a1, "abcdef");
    }

    /**
     * Tests that extract() throws an IndexOutOfBoundsException for fromIndex < 0.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testExtractNegative() {
        a1.extract(-1, 1);
    }

    /**
     * Tests that extract() throws an IndexOutOfBoundsException for fromIndex > toIndex.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testExtractRange() {
        a1.extract(2, 1);
    }

    /**
     * Tests that extract() throws an IndexOutOfBoundsException for toIndex > size.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void testExtractTooHigh() {
        a1.extract(2, 7);
    }

    /**
     * Tests that extract() on an empty array returns an empty array.
     */
    @Test
    public void testExtractEmpty() {
        compare(empty.extract(0, 0), "");
    }

    /**
     * Tests that extract() with toIndex = fromIndex returns an empty array.
     */
    @Test
    public void testExtractNoRange() {
        compare(a1.extract(3, 3), "");
        compare(s.extract(0, 0), "");
    }

    /**
     * Tests that extracting from 0 to size returns an array identical to the original.
     */
    @Test
    public void testExtractFullRange() {
        compare(a1.extract(0, 6), "abcdef");
        compare(s.extract(0, 1), "s");
    }

    /**
     * Tests extracting middle ranges.
     */
    @Test
    public void testExtractStandard() {
        compare(a1.extract(2, 4), "cd");
    }

    /**
     * Tests that extract() leaves the original array unchanged.
     */
    @Test
    public void testExtractFunctionStyle() {
        a1.extract(2, 4);
        compare(a1, "abcdef");
    }
}





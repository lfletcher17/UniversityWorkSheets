import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Alexandros Evangelidis and David Mcdonald
 *
 */
public class Ws3PublicTestsEx4 {
    private PGMImage shade, test, shade2;

    @Before
    public void setUp() {
        shade = new PGMImage("shade.pgm");
        test = new PGMImage("test.pgm");
        shade2 = new PGMImage("shade2.pgm");
    }

    // public test
    @Test
    public void test1() {

        int[][] expected = { { 11, 13, 15, 17, 19, 21, 23, 25, 27, 29 },
                             { 51, 53, 55, 57, 59, 61, 63, 65, 67, 69 },
                             { 91, 93, 95, 97, 99, 101, 103, 105, 107, 109 },
                             { 131, 133, 135, 137, 139, 141, 143, 145, 147, 149 } };

        int[][] actual = shade.quarter("shade-quarter.pgm");
        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test2() {
        int[][] expected = { { 160, 140, 120, 100, 80, 60, 40, 20, 0 },
                             { 161, 141, 121, 101, 81, 61, 41, 21, 1 },
                             { 162, 142, 122, 102, 82, 62, 42, 22, 2 },
                             { 163, 143, 123, 103, 83, 63, 43, 23, 3 },
                             { 164, 144, 124, 104, 84, 64, 44, 24, 4 },
                             { 165, 145, 125, 105, 85, 65, 45, 25, 5 },
                             { 166, 146, 126, 106, 86, 66, 46, 26, 6 },
                             { 167, 147, 127, 107, 87, 67, 47, 27, 7 },
                             { 168, 148, 128, 108, 88, 68, 48, 28, 8 },
                             { 169, 149, 129, 109, 89, 69, 49, 29, 9 },
                             { 170, 150, 130, 110, 90, 70, 50, 30, 10 },
                             { 171, 151, 131, 111, 91, 71, 51, 31, 11 },
                             { 172, 152, 132, 112, 92, 72, 52, 32, 12 },
                             { 173, 153, 133, 113, 93, 73, 53, 33, 13 },
                             { 174, 154, 134, 114, 94, 74, 54, 34, 14 },
                             { 175, 155, 135, 115, 95, 75, 55, 35, 15 },
                             { 176, 156, 136, 116, 96, 76, 56, 36, 16 },
                             { 177, 157, 137, 117, 97, 77, 57, 37, 17 },
                             { 178, 158, 138, 118, 98, 78, 58, 38, 18 },
                             { 179, 159, 139, 119, 99, 79, 59, 39, 19 } };

        int[][] actual = shade.rotate("shade-rotate.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test3() {

        int[][] expected = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                             { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                             { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                             { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                             { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                             { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                             { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                             { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                             { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        int[][] actual = shade.bw("shade-bw.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test4() {

        int[][] expected = { { 13, 38 },
                             { 30, 25 } };
        int[][] actual = test.quarter("test-quarter.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test5() {

        int[][] expected = { { 25, 25, 10, 10 },
                             { 45, 25, 10, 20 },
                             { 10, 30, 20, 30 },
                             { 10, 50, 60, 40 } };
        int[][] actual = test.rotate("test-rotate.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test6() {

        int[][] expected = { { 1, 1, 0, 0 },
                             { 1, 1, 1, 0 },
                             { 1, 1, 0, 0 },
                             { 1, 0, 1, 1 } };
        int[][] actual = test.bw("test-bw.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test7() {
        int[][] expected = { { 11, 13, 15, 17, 19, 21, 23, 25, 27 },
                             { 51, 53, 55, 57, 59, 61, 63, 65, 67 },
                             { 91, 93, 95, 97, 99, 101, 103, 105, 107 },
                             { 131, 133, 135, 137, 139, 141, 143, 145, 147 } };

        int[][] actual = shade2.quarter("shade2-quarter.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test8() {
        int[][] expected = { { 160, 140, 120, 100, 80, 60, 40, 20, 0 },
                             { 161, 141, 121, 101, 81, 61, 41, 21, 1 },
                             { 162, 142, 122, 102, 82, 62, 42, 22, 2 },
                             { 163, 143, 123, 103, 83, 63, 43, 23, 3 },
                             { 164, 144, 124, 104, 84, 64, 44, 24, 4},
                             { 165, 145, 125, 105, 85, 65, 45, 25, 5 },
                             { 166, 146, 126, 106, 86, 66, 46, 26, 6 },
                             { 167, 147, 127, 107, 87, 67, 47, 27, 7 },
                             { 168, 148, 128, 108, 88, 68, 48, 28, 8 },
                             { 169, 149, 129, 109, 89, 69, 49, 29, 9 },
                             { 170, 150, 130, 110, 90, 70, 50, 30, 10 },
                             { 171, 151, 131, 111, 91, 71, 51, 31, 11 },
                             { 172, 152, 132, 112, 92, 72, 52, 32, 12 },
                             { 173, 153, 133, 113, 93, 73, 53, 33, 13 },
                             { 174, 154, 134, 114, 94, 74, 54, 34, 14 },
                             { 175, 155, 135, 115, 95, 75, 55, 35, 15 },
                             { 176, 156, 136, 116, 96, 76, 56, 36, 16 },
                             { 177, 157, 137, 117, 97, 77, 57, 37, 17 },
                             { 178, 158, 138, 118, 98, 78, 58, 38, 18 } };

        int[][] actual = shade2.rotate("shade2-rotate.pgm");

        assertArrayEquals(expected, actual);

    }

    // public test
    @Test
    public void test9() {
        int[][] expected = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] actual = shade2.bw("shade2-bw.pgm");

        assertArrayEquals(expected, actual);

    }
	
}
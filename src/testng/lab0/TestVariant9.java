package testng.lab0;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.lab0.Variant9;
public class TestVariant9 {
    public static double EPS = 0.0000001;


    @Test(enabled = false)
    public void loginOld() {

        assertEquals(new Variant9().booleanTask(2, 4), false);

    }

    @Test(dataProvider = "inputProvider")
    public void inputTest(int p1, int p2, double p3) {

        assertEquals(new Variant9().inputOutputTask(p1, p2), p3);
    }

    @DataProvider
    public Object[][] inputProvider() {

        return new Object[][] { { 3, 6, 3 }, { 2, 2, 2 } };
    }


    @Test(expectedExceptions = AssertionError.class)
    public void negativeInputTest() {
        new Variant9().inputOutputTask(-2, -3);
    }

    ////////////////////////////////////////////////

    @Test(dataProvider = "integerProvider")
    public void inputTest(int p1, int p3) {

        assertEquals(new Variant9().integerNumbersTask(p1), p3);
    }

    @DataProvider
    public Object[][] integerProvider() {

        return new Object[][] { { 100, 1 }, { 512, 5 }, { 139, 1 } };
    }


    @Test(expectedExceptions = AssertionError.class)
    public void negativeIntegerTest() {
        new Variant9().integerNumbersTask(-2);
    }



    ////////////////////////////////////////////////

    @Test(dataProvider = "ifProvider")
    public void ifTest(int p1, int p2, Variant9.Change p3) {

        assertEquals(new Variant9().ifTask(p1,p2), p3);
    }

    @DataProvider
    public Object[][] ifProvider() {

        return new Object[][] { { 2, 3, new Variant9.Change(2, 3) }, { 3, 2, new Variant9.Change(3, 2) } };
    }

    //////////////////////////////////////////////////

    @Test(dataProvider = "booleanProvider")
    public void booleanTest(int p1, int p2, boolean p3) {

        assertEquals(new Variant9().booleanTask(p1, p2), p3);
    }

    @DataProvider
    public Object[][] booleanProvider() {
        return new Object[][] { { 5, 2, true }, { 0, 2,  false }, { -3, 4, true }, { 5, 3, true }};
    }

    //////////////////////////////////////////////////

    @Test(dataProvider = "switchProvider")
    public void switchTest(int p1, int p2, String p3) {
        assertEquals(new Variant9().switchTask(p1, p2), p3);

    }

    @DataProvider
    public Object[][] switchProvider() {
        return new Object[][] { { 3, 5, "4	5" }, { 31, 12, "1	1"}, { 30, 4, "1	5"}, {20, 2,"21	2"} };
    }


    @Test(expectedExceptions = AssertionError.class)
    public void switchNegativeTest() {
        new Variant9().switchTask(0,13);
    }

    @Test(expectedExceptions = RuntimeException.class, dataProvider = "switchNegativeProvider")
    public void switchNegativeTest1(int d, int m) {
       new Variant9().switchTask(d, m);
    }
    @DataProvider
    public Object[][] switchNegativeProvider() {
        return new Object[][] {{29, 2}, {30, 2}, {31, 4}};
    }

    ///////////////////////////////////////////////////

    @Test(dataProvider = "forProvider")
    public void forTest(int a, int b, long p2) {
        assertEquals(new Variant9().forTask(a, b), p2);
    }


    @Test(expectedExceptions = AssertionError.class)
    public void forNegativeTest() {
        new Variant9().forTask(-1, -5);
    }


    @DataProvider
    public Object[][] forProvider() {
        return new Object[][] { { 1, 3, 14 }, { 2, 7, 139 }, {9, 12, 446 } };
    }


    ///////////////////////////////////////////////////

    //////////////////////////////////////////

    @Test(dataProvider = "whileProvider")
    public void whileTest(int a, int c) {
        assertEquals(new Variant9().whileTask(a), c);
    }

    @DataProvider
    public Object[][] whileProvider() {
        return new Object[][] { { 10, 3}, { 12, 3}, { 80, 4}, { 250, 6} };
    }


    @Test(expectedExceptions = AssertionError.class, dataProvider = "negativeWhileProvider")
    public void negativeWhileTest(int a) {
        new Variant9().whileTask(a);
    }

    @DataProvider
    public Object[][] negativeWhileProvider() {
        return new Object[][] { { 1}, { -2}, { 0} };
    }

    //////////////////////////////////////////
    @Test(dataProvider = "arrayProvider")
    public void arrayTest(double[] array, double[] value) {
        assertEquals(new Variant9().arrayTask(array), value);
    }

    @DataProvider
    public Object[][] arrayProvider() {
        return new Object[][] { { new double[] { 10, 2, 3 }, new double[]{ 2,10} }, { new double[] { 10, 2, 13, 4, 15 }, new double[]{4,2,10} },
                { new double[] { 4, 3, 5, -4, 9, 2 }, new double[] {2, -4, 4} }, {new double[]{2, 4, 6}, new double[]{6,4,2}} };
    }


    //////////////////////////////////////////

    @Test(dataProvider = "matrixProvider")
    public void twoDimensionArrayTest(int[][] input, int[][] output) {
        assertEquals(new Variant9().twoDimensionArrayTask(input), output);
    }

    @DataProvider
    public Object[][] matrixProvider() {
        int[][] input1 = {{2, 3, 6, 9, -9},
                {34, 98, -9, 2, 1},
                {-4, 2, 1, 6, 1},
                {-98, 8, 1, 5, 3}};
        int[][] output1 = {{-4, 2, 1, 6, 1},};

        int[][] input2 = {{2, 3, 6, 9, -9},
                {-4, 2, 1, 6, 1},
                {34, 98, -9, 2, 1},
                {-98, 8, 1, 5, 3},
                {2, 3, 6, 9, -9}};
        int[][] output2 = {{34, 98, -9, 2, 1},
                           {2, 3, 6, 9, -9}};

        return new Object[][] { {input1, output1}, { input2, output2 } };

    }

}

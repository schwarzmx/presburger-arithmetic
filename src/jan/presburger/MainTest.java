/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jan.presburger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author work
 */
public class MainTest {

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Main.
     */

    @Test
    public void testFormula1() throws Exception {
        System.out.println("FIRST FORMULA!\nmain");
        String[] args = { "res/formula1.txt" };
        Main.main(args);
        assertTrue(true);
    }

    @Test
    public void testFormula2() throws Exception {
        System.out.println("**********************************\nSECOND FORMULA!\nmain");
        String[] args = { "res/formula2.txt" };
        Main.main(args);
        assertTrue(true);
    }

}
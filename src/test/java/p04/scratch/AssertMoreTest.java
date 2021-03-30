package p04.scratch;

import org.junit.*;

public class AssertMoreTest {
   @BeforeClass
   public static void initializeSomethingReallyExpensive() {
      System.out.println("BeforeClass");
   }
   
   @AfterClass
   public static void cleanUpSomethingReallyExpensive() {
      System.out.println("AfterClass");
   }
   
   @Before
   public void createAccount() {
      System.out.println("Before");
   }
   
   @After
   public void closeConnections() {
      System.out.println("After");
   }
   
   @Test
   public void depositIncreasesBalance() {
      System.out.println("Test1");
   }
   
   @Test
   public void hasPositiveBalance() {
      System.out.println("Test2");
   }
}

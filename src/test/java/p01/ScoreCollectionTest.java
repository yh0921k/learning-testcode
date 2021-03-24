package p01;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ScoreCollectionTest {
  @Test
  public void test() {
    //fail("Not yet implemented");
  }

  @Test
  public void answersArithmeticMeanOfTwoNumbers() {
    // Arrange
    ScoreCollection collection = new ScoreCollection();
    collection.add(() -> 5);
    collection.add(() -> 7);

    // Act
    int actualResult = collection.arithmeticMean();

    // Assert
    assertThat(actualResult, equalTo(6));
  }
}
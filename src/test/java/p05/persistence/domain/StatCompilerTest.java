package p05.persistence.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class StatCompilerTest {
  @Test
  public void test() {
    // 내부애 아래와 같은 Question 객체와 QuestionController 객체를 가지고 있음.
    //    static Question q1 = new BooleanQuestion("Tuition reimbursement?");
    //    static Question q2 = new BooleanQuestion("Relocation package?");
    StatCompiler stats = new StatCompiler();

    List<BooleanAnswer> answers = new ArrayList<>();
    answers.add(new BooleanAnswer(1, true));
    answers.add(new BooleanAnswer(1, true));
    answers.add(new BooleanAnswer(1, true));
    answers.add(new BooleanAnswer(1, false));
    answers.add(new BooleanAnswer(2, true));
    answers.add(new BooleanAnswer(2, true));

    // 아래 메서드가 동작하면 내부에서 responses 해시맵을 만들고
    // 넘겨준 answers에 대해 스트림을 순회하며 각 answer에 대해 incrementHistogram 호출
    // 이 때 incrementHistogram의 인자로 각 answer와 만들어둔 responses가 같이 넘어감

    // incrementHistogram에서는 getHistogram()을 호출하며
    // responses와 answer의 questionId를 넘겨 histogram을 생성

    // getHistogram() 에서는 넘어온 questionId가 responses에 포함되어 있으면
    // responses에서 해당 questionId로 histogram을 조회하여 반환하고
    // 포함되어 있지 않으면 createNewHistogram()을 사용하여 새로운 히스토그램을 만들고
    // responsesd에 해당 questionId와 함께 저장하여 리턴

    // 히스토그램을 반환받은 incrementHistogram()은 히스토그램에서 answer의 value로 데이터를 조회한 뒤
    // getAndIncrement()를 사용하여 값을 증가시킴

    // responsesByQuestion() 메서드에서 스트림 순회가 끝나면 최종적으로 만들어진 responses에 대해
    // convertHistogramIdsToText()를 수행하며, responses.keySet()의 스트림을 순회하며
    // 내부 클래스인 QuestionController의 인스턴스를 사용하여 Question 객체를 찾고,
    // <QuestionString, Histogram>의 형태로 textResponses를 반환
    Map<String, Map<Boolean, AtomicInteger>> responses = stats.responsesByQuestion(answers);

    assertThat(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), equalTo(3));
    assertThat(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(), equalTo(1));
    assertThat(responses.get("Relocation package?").get(Boolean.TRUE).get(), equalTo(2));
    assertThat(responses.get("Relocation package?").get(Boolean.FALSE).get(), equalTo(0));
  }
}
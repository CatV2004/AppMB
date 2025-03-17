
import com.nmc.pojo.Choice;
import com.nmc.pojo.Question;
import com.nmc.services.ChoiceService;
import com.nmc.services.QuestionService;
import java.sql.SQLException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author FPTSHOP
 */
public class QuestionTest {
    private static QuestionService questionService;
    private static ChoiceService choiceService;

    @BeforeAll
    public static void setUp() {
        questionService = new QuestionService();
        choiceService = new ChoiceService();
    }

    @Test
    void testQuestionBelongsToCategory() throws SQLException {
        List<Question> questions = questionService.getQuestions("", 10); 
        
//        assertFalse(questions.isEmpty(), "Danh sách câu hỏi không được rỗng!");
        
        for (var q : questions) {
            assertTrue(q.getCategoryId() > 0, "Câu hỏi " + q.getId() + " không thuộc danh mục nào!");
        }
    }

    @Test
    void testQuestionHasExactlyFourChoices() throws SQLException {
        List<Question> questions = questionService.getQuestions("", 10);
        
        assertFalse(questions.isEmpty(), "Danh sách câu hỏi không được rỗng!");
        
        for (Question q : questions) {
            List<Choice> choices = choiceService.getChoices(q.getId());
            assertEquals(4, choices.size(), "Câu hỏi " + q.getId() + " không có đúng 4 câu trả lời!");
        }
    }

    @Test
    void testQuestionHasOnlyOneCorrectChoice() throws SQLException {
        List<Question> questions = questionService.getQuestions("", 10);
        
        assertFalse(questions.isEmpty(), "Danh sách câu hỏi không được rỗng!");
        
        for (Question q : questions) {
            List<Choice> choices = choiceService.getChoices(q.getId());
            
            long correctCount = choices.stream().filter(Choice::isIsCorrect).count();
            assertEquals(1, correctCount, "Câu hỏi " + q.getId() + " phải có đúng 1 phương án đúng!");
        }
    }
}

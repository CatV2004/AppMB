/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmc.services;

import com.nmc.pojo.Choice;
import com.nmc.pojo.Question;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionService {
    public static List<Question> getQuestions(String kw, int num) throws SQLException{
        List<Question> questions = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()){
            CallableStatement stm = conn.prepareCall("{ CALL getQuestions(?, ?) }");
            stm.setString(1, kw);
            stm.setInt(2, num);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Question q = new Question(rs.getString("id"), rs.getString("content"), rs.getInt("categoryId"));
                
                q.setChoices(ChoiceService.getChoices(q.getId()));
                
                questions.add(q);
            }
            
            return questions;
        }
    }
    
//    public static boolean addQuestion(Question question) throws SQLException {
//        try (Connection conn = JdbcUtils.getConn()) {
//            CallableStatement stm = conn.prepareCall("{ CALL addQuestion(?, ?, ?) }");
//            String questionId = question.getId();
//            
//            stm.setString(1, question.getId());
//            stm.setString(2, question.getContent());
//            stm.setInt(3, question.getCategoryId());
//            
//            boolean success = stm.executeUpdate() > 0;
//            
//            if (success) {
//                ResultSet rs = stm.getGeneratedKeys();
//                if (rs.next()) {
//                    // Thêm các lựa chọn vào bảng Choice
//                    for (Choice choice : question.getChoices()) {
//                        choice.setQuestionId(questionId);
//                        ChoiceService.addChoice(choice);
//                    }
//                }
//            }
//            
//            return success;
//        }
//    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//    private static JdbcTemplate t = (JdbcTemplate) ctx.getBean("t");
//    
//    public static List<Question> getQuestions() {
//        return t.query("SELECT * FROM question", (rs, num) -> {
//            Question q = new Question();
//            
//            String id = rs.getString("id");
//            
//            q.setId(id);
//            q.setContent(rs.getString("content"));
//            q.setCategoryId(rs.getInt("categoryId"));
//            q.setChoices(getChoice(id));
//            
//            return q;
//        });
//    }
//    
//    public static List<Choice> getChoice(String questionId){
//        return t.query("SELECT * FROM choice WHERE questionId=?", (rs, number) -> {
//            Choice c = new Choice();
//            c.setId(rs.getString("id"));
//            c.setContent(rs.getString("content"));
//            c.setIsCorrect(rs.getBoolean("isCorrect"));
//            c.setQuestionId(rs.getString("questionId"));
//            
//            return c;
//        }, questionId);
//    }


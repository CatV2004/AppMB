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

/**
 *
 * @author FPTSHOP
 */



//public class CheckChoiceService {
//    public static List<Question> getQuestionCount(int num) throws SQLException {
//        List<Question> questions = new ArrayList<>();
//        
//        try (Connection conn = JdbcUtils.getConn();
//             CallableStatement stm = conn.prepareCall("{ CALL GetQuestionsWithChoices(?) }")) {
//
//            stm.setInt(1, num);
//            ResultSet rs = stm.executeQuery();
//
//            while (rs.next()) {
//                String id = rs.getString("id");
//                String content = rs.getString("content");
//                int choiceCount = rs.getInt("choice_count");
//
//                questions.add(new Question(id, content, choiceCount));
//            }
//        }
//
//        return questions;
//    }
//}

public class CheckChoiceService {
    public static int getQuestionCount(int num) throws SQLException {
        int choiceCount = 0;
        
        try (Connection conn = JdbcUtils.getConn();
             CallableStatement stm = conn.prepareCall("{ CALL GetQuestionsWithChoices(?) }")) {

            stm.setInt(1, num);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                choiceCount = rs.getInt("choice_count");
            }
        }

        return choiceCount;
    }
}



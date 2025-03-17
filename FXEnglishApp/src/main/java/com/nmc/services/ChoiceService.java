/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmc.services;

import com.nmc.pojo.Choice;
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
public class ChoiceService {
        
    public static List<Choice> getChoices(String questionId) throws SQLException{
        List<Choice> choices = new ArrayList<>();
        
        try (Connection conn = JdbcUtils.getConn()){
            CallableStatement stm = conn.prepareCall("{ CALL getChoices(?) }");
            stm.setString(1, questionId);
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Choice c = new Choice(rs.getString("id"), rs.getString("content"), rs.getBoolean("isCorrect"), rs.getString("questionId"));
                choices.add(c);
            }
            
            return choices;
        }
    }
}

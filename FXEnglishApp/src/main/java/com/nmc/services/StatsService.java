/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmc.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author FPTSHOP
 */
public class StatsService {
    public static int getQuestionCount(String name) throws SQLException {
        int count = 0;
        try (Connection conn = JdbcUtils.getConn()) {
            CallableStatement stm = conn.prepareCall("{ CALL getQuestionCountByCategoryName(?) }");
            stm.setString(1, name);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt("question_count");
            }
        }
        return count;
    }
}


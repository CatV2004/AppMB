/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmc.pojo;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author FPTSHOP
 */

//@Setter @Getter

//@AllArgsConstructor
public class Question {
    private String id;
    private String content;
    private int categoryId;
    private List<Choice> choices;

    public Question() {
    }

    public Question(String id, String content, int categoryId) {
        this.id = id;
        this.content = content;
        this.categoryId = categoryId;
        this.choices = new ArrayList<>();
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public List<Choice> getChoices(){
        return choices;
    }
    
    public void setChoices(List<Choice> choices){
        this.choices = choices;
    }
    
    @Override
    public String toString() {
        return this.content;
    }
}

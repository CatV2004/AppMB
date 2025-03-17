package com.nmc.fxenglishapp;

import com.nmc.pojo.Question;
import com.nmc.services.QuestionService;
import com.nmc.services.ChoiceService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {

    @FXML
    private Text txtContent;
    @FXML
    private RadioButton rdoA;
    @FXML
    private RadioButton rdoB;
    @FXML
    private RadioButton rdoC;
    @FXML
    private RadioButton rdoD;
    @FXML
    private Text txtA;
    @FXML
    private Text txtB;
    @FXML
    private Text txtC;
    @FXML
    private Text txtD;
    private List<Question> questions;
    private int currentIdx = 0;

    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void checkHandler(ActionEvent e) throws SQLException {
        Question q = this.questions.get(currentIdx);

        boolean d1 = rdoA.isSelected() && q.getChoices().get(0).isIsCorrect();
        boolean d2 = rdoA.isSelected() && q.getChoices().get(1).isIsCorrect();
        boolean d3 = rdoA.isSelected() && q.getChoices().get(2).isIsCorrect();
        boolean d4 = rdoA.isSelected() && q.getChoices().get(3).isIsCorrect();
        
        if (d1 || d2 || d3 || d4)
            Utils.getAlert("EXACTLY").show();
        else
            Utils.getAlert("WRONG").show();
        

    }

    public void nextHandler(ActionEvent e) {
        if (this.currentIdx < this.questions.size()) {
            this.currentIdx++;
            loadQuestionToUI();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuestionService s = new QuestionService();

        try {
            this.questions = s.getQuestions("", 3);
            loadQuestionToUI();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadQuestionToUI() {
        ChoiceService c = new ChoiceService();
        Question q = this.questions.get(currentIdx);
        txtContent.setText(q.getContent());

        if (q.getChoices() != null) {
            try {
                q.setChoices(c.getChoices(q.getId()));

                txtA.setText(q.getChoices().get(0).toString());
                txtB.setText(q.getChoices().get(1).toString());
                txtC.setText(q.getChoices().get(2).toString());
                txtD.setText(q.getChoices().get(3).toString());

            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

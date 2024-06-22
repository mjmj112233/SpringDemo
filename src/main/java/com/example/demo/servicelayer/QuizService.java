package com.example.demo.servicelayer;

import com.example.demo.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    private List<Question> questions;

    public QuizService() {
        this.questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What country has the highest life expectancy?",
                Arrays.asList("Philippines", "Hong Kong", "USA", "Singapore"), 1));
        questions.add(new Question("How many elements are in the periodic table?",
                Arrays.asList("119", "118", "117", "120"), 3));
        questions.add(new Question("Who was the Ancient Greek God of the Sun?",
                Arrays.asList("Athena", "Hades", "Apollo", "Venus"), 2));
        questions.add(new Question("What country drinks the most coffee per capita?",
                Arrays.asList("Russia", "China", "USA", "Finland"), 3));
        questions.add(new Question("What Renaissance artist is buried in Rome's Pantheon?",
                Arrays.asList("Donatello", "Leonardo", "Michael", "Raphael"), 3));
    }

    public List<Question> getRandomizedQuestions() {
        Collections.shuffle(questions);
        return new ArrayList<>(questions);
    }

    public int calculateScore(Map<Integer, Integer> answers) {
        int score = 0;
        for (Map.Entry<Integer, Integer> entry : answers.entrySet()) {
            int questionIndex = entry.getKey();
            int selectedAnswerIndex = entry.getValue();

            if (questionIndex >= 0 && questionIndex < questions.size()) {
                Question question = questions.get(questionIndex);
                if (question.getCorrectAnswerIndex() == selectedAnswerIndex) {
                    score++;
                }
            }
        }
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}

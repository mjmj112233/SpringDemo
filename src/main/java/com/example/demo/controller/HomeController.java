package com.example.demo.controller;

import com.example.demo.model.Question;
import com.example.demo.model.UserSession;
import com.example.demo.servicelayer.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserSession userSession;

    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        if (userSession.getUsername() == null) return "redirect:/login";

        Map<String, Integer> grades = (Map<String, Integer>) session.getServletContext().getAttribute("grades");
        if (grades == null) {
            grades = new HashMap<>();
            session.getServletContext().setAttribute("grades", grades);
        }

        boolean hasTakenQuiz = grades.containsKey(userSession.getUsername());
        model.addAttribute("hasTakenQuiz", hasTakenQuiz);
        model.addAttribute("username", userSession.getUsername());
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean found = UserSession.userMap.containsKey(username) && UserSession.userMap.get(username).equals(password);

        if (found) {
            userSession.setUsername(username);
            return "redirect:/";
        } else {
            return "redirect:/login?error=Invalid username or password!";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        userSession.setUsername(null);
        return "redirect:/login";
    }

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        if (userSession.getUsername() == null) return "redirect:/login";
        List<Question> questions = quizService.getRandomizedQuestions();
        model.addAttribute("questions", questions);
        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitQuiz(@RequestParam Map<String, String> allParams, Model model, HttpSession session) {
        if (userSession.getUsername() == null) return "redirect:/login";

        Map<Integer, Integer> answers = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("question_")) {
                int questionId = Integer.parseInt(entry.getKey().substring(9));
                int answerIndex = Integer.parseInt(entry.getValue());
                answers.put(questionId, answerIndex);
            }
        }

        int score = quizService.calculateScore(answers);
        Map<String, Integer> grades = (Map<String, Integer>) session.getServletContext().getAttribute("grades");
        if (grades == null) {
            grades = new HashMap<>();
            session.getServletContext().setAttribute("grades", grades);
        }
        grades.put(userSession.getUsername(), score);
        session.getServletContext().setAttribute("grades", grades);

        int totalQuestions = quizService.getTotalQuestions();
        double percentage = ((double) score / totalQuestions) * 100;

        model.addAttribute("score", score);
        model.addAttribute("percentage", percentage);
        model.addAttribute("totalQuestions", totalQuestions);

        return "result";
    }

    @GetMapping("/grade")
    public String showGrades(Model model) {
        if (userSession.getUsername() == null) return "redirect:/login";
        return "grade";
    }
}

package com.jeremy.quizapp_project.controller;

import com.jeremy.quizapp_project.model.Question;
import com.jeremy.quizapp_project.model.QuestionWrapper;
import com.jeremy.quizapp_project.model.Response;
import com.jeremy.quizapp_project.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int num, @RequestParam String title) {

        return quizService.createQuiz(category, num, title);

    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer quizId) {

        return quizService.getQuizQuestions(quizId);

    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<Response> responses) {

        return quizService.calculateResult(quizId, responses);

    }

}

package com.jeremy.quizapp_project.service;

import com.jeremy.quizapp_project.dao.QuestionDao;
import com.jeremy.quizapp_project.dao.QuizDao;
import com.jeremy.quizapp_project.model.Question;
import com.jeremy.quizapp_project.model.QuestionWrapper;
import com.jeremy.quizapp_project.model.Quiz;
import com.jeremy.quizapp_project.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int num, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, num);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId) {

        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();

        for (Question question: questionsFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );
            questionWrapperList.add(questionWrapper);
        }

        return new ResponseEntity<>(questionWrapperList, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<Response> responses) {

        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        int numRight = 0;
        int i = 0;

        for (Response r: responses) {
            if (r.getResponse().equals(questions.get(i).getRightAnswer())) {
                numRight++;
            }
            i++;
        }

        return  new ResponseEntity<>(numRight, HttpStatus.OK);

    }
}

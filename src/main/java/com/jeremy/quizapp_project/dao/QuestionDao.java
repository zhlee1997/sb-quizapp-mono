package com.jeremy.quizapp_project.dao;

import com.jeremy.quizapp_project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    //    If the query is too complex, then have to customise using HQL or JPQL
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int num);
}

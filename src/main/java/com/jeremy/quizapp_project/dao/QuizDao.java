package com.jeremy.quizapp_project.dao;

import com.jeremy.quizapp_project.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {



}

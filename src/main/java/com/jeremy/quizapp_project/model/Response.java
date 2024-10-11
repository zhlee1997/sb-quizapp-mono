package com.jeremy.quizapp_project.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor // this will give parameterized and default constructor
public class Response {

    private Integer id;
    private String response;

}

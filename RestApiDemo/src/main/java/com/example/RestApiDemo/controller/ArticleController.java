package com.example.RestApiDemo.controller;

import com.example.RestApiDemo.dto.ArticleResponse;
import com.example.RestApiDemo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController
{
    private final ArticleService articleService;

    public List<ArticleResponse> getByMember(
            @RequestParam(name="memberId", required = false) Long memberId){

        if(memberId == null){
            return articleService.findAll();
        }else {
            return articleService.findByMemberId(memberId);
        }
    }

}
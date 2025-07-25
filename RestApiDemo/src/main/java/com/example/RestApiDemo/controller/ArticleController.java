package com.example.RestApiDemo.controller;

import com.example.RestApiDemo.dto.ArticleRequest;
import com.example.RestApiDemo.dto.ArticleResponse;
import com.example.RestApiDemo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ArticleResponse get(@PathVariable("id") Long id){
        return articleService.findById(id);
    }

    @PutMapping("/{id}")
    public ArticleResponse put(@PathVariable("id") Long id,
                               @RequestBody ArticleRequest articleRequest){
        return articleService.update(id, articleRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        articleService.delete(id);
    }

}
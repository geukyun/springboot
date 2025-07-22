package com.example.RestApiDemo.service;

import com.example.RestApiDemo.Member;
import com.example.RestApiDemo.dto.ArticleRequest;
import com.example.RestApiDemo.dto.ArticleResponse;
import com.example.RestApiDemo.exception.NotFoundException;
import com.example.RestApiDemo.model.Article;
import com.example.RestApiDemo.repository.ArticleRepository;
import com.example.RestApiDemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    private ArticleResponse mapToArticleResponse(Article article){
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .created(article.getCreated())
                .updated(article.getUpdated())
                .memberId(article.getMember().getId())
                .name(article.getMember().getName())
                .email(article.getMember().getEmail()).build();
    }

    public List<ArticleResponse> findAll(){
        return articleRepository.findAll()
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    public List<ArticleResponse> findByMemberId(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundException::new);
        return articleRepository.findByMember(member)
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    public ArticleResponse create(Long memberId, ArticleRequest articleRequest){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundException::new);
        Article article = Article.builder()
                .title(articleRequest.getTitle())
                .description(articleRequest.getDescription())
                .member(member).build();
        articleRepository.save(article);
        return mapToArticleResponse(article);
    }
}

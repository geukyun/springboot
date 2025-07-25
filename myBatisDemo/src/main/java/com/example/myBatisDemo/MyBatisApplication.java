package com.example.myBatisDemo;

import com.example.myBatisDemo.mapper.Article;
import com.example.myBatisDemo.mapper.ArticleMapper;
import com.example.myBatisDemo.mapper.Member;
import com.example.myBatisDemo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MyBatisApplication implements ApplicationRunner {

    private final MemberMapper memberMapper;
    private final ArticleMapper articleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        int count = memberMapper.selectAllCount();
        System.out.println("count: "+count);

        Member member = memberMapper.selectByEmail("SeojunYoon@hanbit.co.kr").orElseThrow();
        System.out.println("member: "+member);

        Article article = Article.builder()
                .title("Hello, MyBatis")
                .description("MyBatis is an SQL Mapper framework")
                .memberId(member.getId()).build();
        int iserted = articleMapper.insert(article);
        System.out.println("iserted: "+iserted);
    }
}

package com.example.JpaDemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.JpaDemo.repository.MemberRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class JpaApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        memberRepository.save(Member.builder()
                        .name("윤서준")
                        .email("aaaa")
                        .age(10).build());
        memberRepository.save(Member.builder()
                .name("윤광철")
                .email("bbbb")
                .age(43).build());
    }
}

package com.example.jdbcDemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringJdbcApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args){

        // craete
//        Member member = Member.builder()
//                .name("정혁2")
//                .email("HyeokJung2@hanbit.co.kr")
//                .age(10).build();
//        memberRepository.save(member);
//
//        // update
//        member.setAge(11);
//        memberRepository.save(member);

        Iterable<Member> members = memberRepository.findAll();
        log.info("{}", members);
        System.out.println(members);

        members = memberRepository.findTeenAge();
        log.info("{}", members);
        System.out.println(members);


        Optional<Member> member1 = memberRepository.findById(1L);
        log.info("{}", member1);



    }
}

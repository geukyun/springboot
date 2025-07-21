package com.example.RestApiDemo.controller;

import com.example.RestApiDemo.Member;
import com.example.RestApiDemo.dto.MemberRequest;
import com.example.RestApiDemo.dto.MemberResponse;
import com.example.RestApiDemo.repository.MemberRepository;
import com.example.RestApiDemo.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse post(@RequestBody MemberRequest memberRequest){
        return memberService.create(memberRequest);
    }

    @GetMapping
    public List<MemberResponse> getAll(){
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponse get(@PathVariable("id") Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public MemberResponse put(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest){
        return memberService.update(id, memberRequest);
    }

    @PatchMapping("/{id}")
    public MemberResponse patch(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest){
        return memberService.patch(id, memberRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        memberService.deleteById(id);
    }

    @GetMapping("/{id}/articles")
    public void getArticle(@PathVariable("id") Long id,
                           HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException{
            request.getSession()
                    .getServletContext()
                    .getRequestDispatcher("/api/articles?memberId="+id)
                    .forward(request, response);
    }
}

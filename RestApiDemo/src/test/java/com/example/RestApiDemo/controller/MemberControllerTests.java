package com.example.RestApiDemo.controller;

import com.example.RestApiDemo.dto.MemberRequest;
import com.example.RestApiDemo.dto.MemberResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("회원 컨트롤러 테스트")
public class MemberControllerTests
{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("여러명의 회원 생성 테스트")
    public void post() throws Exception {
        // (1) 생성 요청할 회원 정보를 리스트 형식으로 작성
        List<MemberRequest> memberRequests = List.of(
                MemberRequest.builder()
                        .name("윤서준")
                        .email("SeojunYoon@hanbit.co.kr")
                        .age(10).build(),
                MemberRequest.builder()
                        .name("윤광철")
                        .email("KwancheolYoon@hanbit.co.kr")
                        .age(43).build()
        );
        
        // (2) 회원 목록을 HTTP Request Body에 넣기 위해 JSON 스트링으로 변환
        String requestBody = objectMapper.writeValueAsString(memberRequests);

        // (3) MockMvc를 사용해 RESTful API 호출 시 사용할 HTTP Method, URI
        // Content Type, Request Body를 설정한다.
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody);

        // (4) MockMvc를 사용해 POST /api/members를 호출하고
        // 그 결과 status가 2xx 성공이며 반환된 콘텐츠가 JSON인지를 검증
        MvcResult mvcResult = mockMvc
                .perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // (5) 반환된 결과는 다시 ObjectMapper를 사용해 JSON에서 Object로 변환
        List<MemberResponse> memberResponses = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );

        // (6) AssertJ를 사용해 검증
        assertThat(memberResponses.size()).isEqualTo(2L);
        assertThat(memberResponses.get(0).getId()).isNotZero();
        assertThat(memberResponses.get(0).getName()).isEqualTo("윤서준");
        assertThat(memberResponses.get(1).getId()).isNotZero();
        assertThat(memberResponses.get(0).getName()).isEqualTo("윤광철");
    }
}

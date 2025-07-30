package com.example.RestApiDemo;

import com.example.RestApiDemo.model.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)  // 테이블 유니크를 보장하기 위해 ddl-auto 설정시 컬럼 unique 설정
    private String email;

    private Integer age;
    private String password;
    private Boolean enabled;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Article> articles;
}
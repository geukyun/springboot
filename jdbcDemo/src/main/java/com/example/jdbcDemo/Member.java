package com.example.jdbcDemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private Long id;
    private String name;
    private String email;
    private Integer age;

    public String toString(){
        return "Member(id="+id+", name="+name+", email="+email+", age="+age+")";
    }

}

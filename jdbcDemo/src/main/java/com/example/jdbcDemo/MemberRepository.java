package com.example.jdbcDemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    @Query("SELECT * FROM member WHERE age >= 13 AND age <= 19")
    List<Member> findTeenAge();

    @Query("SELECT * FROM member WHERE age >= :min AND age <= :max")
    List<Member> findByAgeRange(int min, int max);

}

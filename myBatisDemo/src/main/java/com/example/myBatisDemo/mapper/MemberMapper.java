package com.example.myBatisDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper
{
    int selectAllCount();
    List<Member> selectAll();
    Optional<Member> selectById(@Param("id") Long id);
    Optional<Member> selectByEmail(@Param("email") String email);
    List<Member> selectAllOrderByAgeAsc();
    List<Member> selectAllOrderBy(@Param("order") String order, @Param("dir") String dir);

}

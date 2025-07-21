package com.example.myBatisDemo.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface ArticleMapper {

    @Results(id="articleResult", value={
            @Result(property = "title", column="title"),
            @Result(property = "description", column="description"),
            @Result(property = "memberId", column="memberId")
    })

    @Insert("""
            INSERT INTO article(title, description, created, updated, member_id)
            VALUES(#{article.title}, #{article.description}, 
                    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, #{article.memberId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("article") Article article);

    @Update("""
            UPDATE article
                SET title=#{title}, description=#{description}, updated=CURRENT_TIMESTAMP
            WHERE id = #{id}
            """)
    int update(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    @Delete("DELETE article WHERE id=#{id}")
    int deleteById(@Param("id") Long id);

    @Delete("DELETE article WHERE member_id=#{memberId}")
    int deleteByMemberId(@Param("memberId") Long memberId);

    @Delete("DELETE article")
    int deleteAll();

    @Select("SELECT * FROM article")
    List<Article> selectAll();

    @Select("SELECT COUNT(*) FROM article")
    int selectAllCount();

    @Select("SELECT * FROM article WHERE id=${id}")
    Optional<Article> selectById(@Param("id") Long id);

    @Select("SELECT * FROM article WHERE member_id=#{memberId}")
    List<Article> selectByMemberId(@Param("memberId") Long memberId);
}

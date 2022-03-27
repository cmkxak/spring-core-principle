package com.kr.ac.jbnu.hispring.repository;

import com.kr.ac.jbnu.hispring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //Optional은 java 8에 들어간 기능. Null 처리
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

package com.kr.ac.jbnu.hispring.service;

import com.kr.ac.jbnu.hispring.domain.Member;
import com.kr.ac.jbnu.hispring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    //MemberRepository를 외부에서 넣어주도록 변경 , 선언하면서 new 연산자 사용 x , 생성자에서 외부에서 받도록 함
    //Dependency Injection 구조

    public MemberService(MemberRepository repository) {
        this.memberRepository = repository;
    }

    /*
    중복 회원 확인, 이름이 같은 경우에는 회원 가입 불가
     */

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 확인 처리.
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent (m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    //가입된 모든 멤버 불러오기.
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    //ID값을 입력받아 해당 아이디를 가진 멤버 반환.
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}

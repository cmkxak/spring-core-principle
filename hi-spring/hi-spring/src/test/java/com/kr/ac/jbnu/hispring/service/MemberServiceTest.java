package com.kr.ac.jbnu.hispring.service;

import com.kr.ac.jbnu.hispring.domain.Member;
import com.kr.ac.jbnu.hispring.repository.MemberRepository;
import com.kr.ac.jbnu.hispring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트 실행 전 호출
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //각 테스트 실행 후 호출
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long savedId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(savedId).get(); //get()메서드로 값을 빼옴(반환값은 Optional Type)
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //when -> 조인을 2번 하게 되면 중복 회원으로 오류 발생

        memberService.join(member1);
        /**
         *
         * try{
         *             memberService.join(member2);
         *             fail();
         *         }catch(IllegalStateException e){
         *          assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
         */

        /**
         * 위의 try-catch 코드 부분을 간결하게 수정.
         * assertThrows 부분 -> join을 실행하면 IllegalStateException이 발생한다 라는 의미.
         */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }


    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}
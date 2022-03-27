package com.kr.ac.jbnu.hispring;

import com.kr.ac.jbnu.hispring.domain.Member;
import com.kr.ac.jbnu.hispring.repository.JdbcMemberRepository;
import com.kr.ac.jbnu.hispring.repository.MemberRepository;
import com.kr.ac.jbnu.hispring.repository.MemoryMemberRepository;
import com.kr.ac.jbnu.hispring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//자바 코드로 직접 스프링 빈 등록하는 법
@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    //핵심.
    @Bean
    public MemberRepository memberRepository(){return new JdbcMemberRepository(dataSource);
    }

}

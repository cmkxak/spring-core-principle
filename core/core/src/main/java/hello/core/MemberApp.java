package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        //Spring Container -> @Bean 이라는 것을 다 관리
        //AppConfig에 있는 환경 설정 정보를 가지고 스프링이 구성 정보를 스프링 컨테이너에 다 집어 넣어서 관리해줌.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("회원가입 한 멤버 = " + member.getName());
        System.out.println("회원가입 후 찾아진 멤버 = " + findMember.getName());
    }
}

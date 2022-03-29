package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 주문 결과 반환을 위해서는 Repo로 부터 회원 등급을 확인하고, 할인을 적용해야 함.
 */
@Component
public class OrderServceImpl implements OrderService{
    //final로 선언이 되있으면 생성자로 주입해줘야 함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId); // 회원 조회

        //할인에 대한건 discountPolicy에게 모두 위임하고, 결과만 달라고 함 --> SRP 원칙을 철저히 지킨 것.
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인 정책에 회원을 던짐

        return new Order(memberId, itemName, itemPrice, discountPrice); //새로운 주문 반환
    }
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}

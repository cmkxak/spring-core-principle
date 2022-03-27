package com.kr.ac.jbnu.hispring.repository;

import com.kr.ac.jbnu.hispring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store  = new HashMap<>();
    //0,1,2 key 값을 생성해주는 변수 sequence
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //반환 값이 Null이면 클라이언트 부분에서 처리해주는 것이 있음 -> Optional...
        //데이터가 null이 올 수 있고 아닐 수도 있는 경우에는 Optional.ofNullable로 생성
        return Optional.ofNullable(store.get(id));
    }
    /*
    * HashMap의 values() 메서드는 map 함수의 values들의 collection들을 만드는데에 사용됨.
    *  */
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //실무에서는 리스트를 많이 사용해요.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

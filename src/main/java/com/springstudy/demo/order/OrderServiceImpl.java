package com.springstudy.demo.order;

import com.springstudy.demo.annotation.MainDiscountPolicy;
import com.springstudy.demo.discount.DiscountPolicy;
import com.springstudy.demo.member.Member;
import com.springstudy.demo.member.MemberRepository;
import jdk.jfr.Unsigned;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(@Qualifier("memoryMemberRepository") MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

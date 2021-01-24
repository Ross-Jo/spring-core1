package com.springstudy.demo;

import com.springstudy.demo.discount.DiscountPolicy;
import com.springstudy.demo.discount.FixDiscountPolicy;
import com.springstudy.demo.discount.RateDiscountPolicy;
import com.springstudy.demo.member.MemberRepository;
import com.springstudy.demo.member.MemberService;
import com.springstudy.demo.member.MemberServiceImpl;
import com.springstudy.demo.member.MemoryMemberRepository;
import com.springstudy.demo.order.OrderService;
import com.springstudy.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                getMemberRepository(),
                getDiscountPolicy());
    }

    @Bean
    public DiscountPolicy getDiscountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

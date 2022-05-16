package com.example.jpademo.web;


import com.example.jpademo.domain.member.MemberJpaRepository;
import com.example.jpademo.domain.member.MemberRepository;
import com.example.jpademo.dto.MemberSearchCondition;
import com.example.jpademo.dto.MemberTeamDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;



    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition){

        logger.info("?");
        return memberJpaRepository.search(condition);
    }


    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition condition, Pageable page){

        return memberRepository.searchPageSimple(condition, page);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCondition condition, Pageable page){

        return memberRepository.searchPageComplex(condition, page);
    }


}

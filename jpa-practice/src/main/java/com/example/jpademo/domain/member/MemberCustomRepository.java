package com.example.jpademo.domain.member;

import com.example.jpademo.dto.MemberSearchCondition;
import com.example.jpademo.dto.MemberTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MemberCustomRepository {


    List<Member> findMemberByNameIsNull();
    List<MemberTeamDto> search(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);


}

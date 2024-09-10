package com.kenny.mapping.section01.entity;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

// MemberRepository를 의존하는 MemberRegistService
@Service    // bean 등록
public class MemberRegistService {

    private MemberRepository memberRepository;

    // 생성자를 통한 의존성 주입(@AutoWired 생략)
    public MemberRegistService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 넘어오는 값은 엔터티(DB와 직접 매핑되는 객체)가 아님.
    // DB와 직접 매핑되는 객체는 서비스 계층에서 생성하여 사용하거나, 조회 시 서비스 계층에서 DTO 클래스 등으로 변경하는 것이 안전.
    // 엔터티는 서비스 계층 바깥에서 사용하지 않음.
    // 엔터티가 아니라 DTO 타입 객체가 넘어온다고 가정
    @Transactional  // 아래 메소드의 호출 시점을 가로채서, 메소드 호출 전에 트랜잭션을 begin 하고, 메소드 종료 시점에 트랜잭션을 commit 한다. 예외 발생 시 rollback 한다. (JPA 기술이 아니라 Spring의 어노테이션)
    public void registMember(MemberRegistDTO newMember) {
        // 컨트롤러 계층에서 memberRegistDTO가 넘어옴

        // 엔터티 형태로 넘겨받아, 엔터티 매니저를 통해 저장(persist) 요청
        Member member = new Member(
                newMember.getMemberId(),
                newMember.getMemberPwd(),
                newMember.getMemberName(),
                newMember.getPhone(),
                newMember.getAddress(),
                newMember.getEnrollDate(),
                newMember.getMemberRole(),
                newMember.getStatus()
        );
        memberRepository.save(member);

    }

    // 저장 후 이름 반환
    /* Spring의 트랜잭션 전파 방식(default : PROPAGATION_REQUIRED)에 따라
    * @Transactional이 붙은 메소드가 다른 @Transactional이 붙은 메소드를 호출하면,
    * 호출된 메소드는 호출한 메소드와 동일한 트랜잭션 컨텍스트를 사용한다. */
    // 이미 실행 중인 트랜잭션이 있으면 그 트랜잭션으로 들어간다.
    // registMember, registMemberAndFindName 둘다 @Transactional을 붙였다.
    // registMemberAndFindName이 findNameById를 호출해도 트랜잭션의 범위는 registMemberAndFindName 메소드의 시작과 끝이다.
    @Transactional
    public String registMemberAndFindName(MemberRegistDTO newMember) {
        registMember(newMember);
        return memberRepository.findNameById(newMember.getMemberId());
    }

}

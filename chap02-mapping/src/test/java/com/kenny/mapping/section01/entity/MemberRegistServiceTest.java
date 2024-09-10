package com.kenny.mapping.section01.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // spring 환경에서 테스트
class MemberRegistServiceTest {

    @Autowired  // 필드 의존성 주입 - 테스트 하고 싶은 대상에 대한 bean 객체를 의존성 주입
    private MemberRegistService memberRegistService;

    private static Stream<Arguments> getMember() {
        return Stream.of(
                Arguments.of(
                        "user01",
                        "pass01",
                        "홍길동",
                        "010-1234-5678",
                        "서울시 종로구",
                        LocalDateTime.now(),
                        "ROLE_MEMBER",
                        "Y"
                ),
                Arguments.of(
                        "user02",
                        "pass02",
                        "유관순",
                        "010-8765-4321",
                        "서울시 서초구",
                        LocalDateTime.now(),
                        "ROLE_ADMIN",
                        "Y"
                )
        );
    }

    @DisplayName("테이블 생성 테스트")
    @ParameterizedTest
    @MethodSource("getMember")
    void testCreateTable(
            String memberId, String memberPwd,
            String memberName, String phone, String address,
            LocalDateTime enrollDate, MemberRole memberRole, String status
    ) {
        //given
        MemberRegistDTO newMember = new MemberRegistDTO(
                memberId,
                memberPwd,
                memberName,
                phone,
                address,
                enrollDate,
                memberRole,
                status
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> memberRegistService.registMember(newMember)
        );
    }

    @DisplayName("프로퍼티 접근 테스트")
    @ParameterizedTest
    @MethodSource("getMember")
    void testAccessProperty(
            String memberId, String memberPwd,
            String memberName, String phone, String address,
            LocalDateTime enrollDate, MemberRole memberRole, String status
    ) {
        //given
        MemberRegistDTO newMember = new MemberRegistDTO(
                memberId,
                memberPwd,
                memberName,
                phone,
                address,
                enrollDate,
                memberRole,
                status
        );

        //when
        String registedName = memberRegistService.registMemberAndFindName(newMember);

        //then
        assertEquals(memberName + "님", registedName);

        // 홍길동 전달 -> 저장 -> memberName 조회(이 때 getMemberName 메소드를 통해 가공) -> 홍길동 님
    }
}
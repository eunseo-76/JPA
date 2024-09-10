package com.kenny.mapping.section01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Member 클래스를 엔터티화
@Entity(name = "entityMember")
@Table(name = "tbl_member")
//@TableGenerator(
//        name = "member_seq_tbl_generator",
//        table = "tbl_my_sequences",
//        pkColumnValue = "my_seq_member_no"
//)
@Access(AccessType.FIELD)   // 클래스 레벨에 설정 가능하며 모든 필드를 대상으로 적용하겠다는 의미 -> 단 FIELD가 default라 영향 x (이 객체를 필드 or 메소드로 접근할지 설정 가능)
public class Member {

    @Id // 필수
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id와 함께 사용할 수 있음
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_tbl_generator")
    // Identity : 기본 키 생성을 DB에 위임(AutoIncrement 속성 부여한다는 의미)
    private int memberNo;

    @Access(AccessType.FIELD)   // 필드 레벨에도 설정 가능하며 해당 필드를 대상으로 적용하겠다는 의미 -> 역시 default라 영향 x
    @Column(
            name = "member_id", unique = true,
            nullable = false, columnDefinition = "varchar(10)"  // columnDefinition 하지 않으면 기본 255
    )
    private String memberId;    // unique 하지 않은 값, null 값이 들어가면 오류 발생,

    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Column(name = "member_name")
    private String memberName;

    @Transient  // DB 매핑에서 제외하. DB에 필요하지 않은 필드
    @Column(name = "phone")
    private String phone;

    @Column(name = "address", length = 900)
    private String address;

    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Column(name = "status", columnDefinition = "char(1) default 'Y'")
    private String status;

    protected Member() {}

    public Member(
            String memberId, String memberPwd, String memberName,
            String phone, String address, LocalDateTime enrollDate,
            MemberRole memberRole, String status
    ) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    // MemberName이 메소드를 통해 다루어지도록 설정
    @Access(AccessType.PROPERTY)
    public String getMemberName() {
        System.out.println("getMemberName 메소드를 통한 Access 확인");

        return memberName + "님";    // 가공이 필요할 때 PROPERTY로 설정
    }

    // getter뿐 아니라 setter도 정의 필요
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
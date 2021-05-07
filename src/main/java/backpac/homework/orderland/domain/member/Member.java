package backpac.homework.orderland.domain.member;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
public class Member {

    // 회원번호(PK, AI)
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long memberNo;

    // 이름(한글, 영문 대소문자만 허용)
    @Column(nullable = false)
    private String name;

    // 별명(영문 소문자만 허용)
    @Column(nullable = false)
    private String nickname;

    // 비밀번호(영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함)
    @Column(nullable = false)
    private String password;

    // 전화번호(숫자)
    @Column(nullable = false)
    private String telNo;

    // 이메일(이메일 형식)
    @Column(nullable = false)
    private String email;

    // 성별
    @Column
    @Enumerated(STRING)
    private Gender gender;

    @Builder
    public Member(String name, String nickname, String password, String telNo, String email, Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.telNo = telNo;
        this.email = email;
        this.gender = gender;
    }
}

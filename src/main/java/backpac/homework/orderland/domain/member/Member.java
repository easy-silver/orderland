package backpac.homework.orderland.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    // 회원번호(PK, AI)
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long memberNo;

    // 이름(한글, 영문 대소문자만 허용)
    @Column(nullable = false, length = 20)
    private String username;

    // 별명(영문 소문자만 허용)
    @Column(nullable = false, length = 30)
    private String nickname;

    // 비밀번호(최소 10자 이상/영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함)
    @Column(nullable = false, length = 100)
    private String password;

    // 전화번호(숫자)
    @Column(nullable = false, length = 20)
    private String telNo;

    // 이메일(이메일 형식)
    @Column(nullable = false, length = 100)
    private String email;

    // 성별
    @Column
    @Enumerated(STRING)
    private Gender gender;

    private Boolean enabled;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "member_no"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Builder
    public Member(String username, String nickname, String password, String telNo,
                  String email, Gender gender, Boolean enabled) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.telNo = telNo;
        this.email = email;
        this.gender = gender;
        this.enabled = enabled;
    }

    public Member addRole(Role role) {
        this.roles.add(role);
        return this;
    }
}


package backpac.homework.orderland.web.dto;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MemberRequestDto {

    private String name;
    private String nickname;
    private String password;
    private String telNo;
    private String email;
    private Gender gender;

    @Builder
    public MemberRequestDto(String name, String nickname, String password,
                            String telNo, String email, Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.telNo = telNo;
        this.email = email;
        this.gender = gender;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .password(password)
                .telNo(telNo)
                .email(email)
                .gender(gender)
                .build();
    }

}

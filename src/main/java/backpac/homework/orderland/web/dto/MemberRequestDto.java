package backpac.homework.orderland.web.dto;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter @Getter
@NoArgsConstructor
public class MemberRequestDto {

    @NotEmpty(message = "이름을 입력해주세요.")
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]*$", message = "한글, 영문 대소문자만 입력해주세요.")
    private String username;

    @NotEmpty(message = "닉네임을 입력해주세요.")
    @Size(max = 30)
    @Pattern(regexp = "^[a-z]*$", message = "영문 소문자만 입력해주세요.")
    private String nickname;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,}",
             message = "비밀번호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함하여 10자 이상으로 입력해주세요.")
    private String password;

    @NotEmpty(message = "전화번호를 입력해주세요.")
    @Size(max = 20)
    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력해주세요.")
    private String telNo;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰주세요.")
    @Size(max = 100)
    private String email;

    private Gender gender;

    @Builder
    public MemberRequestDto(String username, String nickname, String password,
                            String telNo, String email, Gender gender) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.telNo = telNo;
        this.email = email;
        this.gender = gender;
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .telNo(telNo)
                .email(email)
                .gender(gender)
                .enabled(true)
                .build();
    }

}

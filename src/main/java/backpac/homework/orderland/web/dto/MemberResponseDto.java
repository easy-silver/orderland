package backpac.homework.orderland.web.dto;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long memberNo;
    private String name;
    private String nickname;
    private String password;
    private String telNo;
    private String email;
    private Gender gender;

    public MemberResponseDto(Member entity) {
        this.memberNo = entity.getMemberNo();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.password = entity.getPassword();
        this.telNo = entity.getTelNo();
        this.email = entity.getEmail();
        this.gender = entity.getGender();
    }
}
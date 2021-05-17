package backpac.homework.orderland.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MemberSearchRequestDto {

    // 현재 페이지
    private int pageNo = 0;
    // 한 페이지에 보여줄 목록 수
    private int contentCount = 10;
    // 검색 조건
    private String name;
    private String email;

    public MemberSearchRequestDto() {
        this.name = null;
        this.email = null;
    }

    public MemberSearchRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

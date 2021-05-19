package backpac.homework.orderland.web.dto;

import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class ResponseDto<T> {

    // 에러 여부
    private Boolean isError = false;

    // 응답 메시지
    private String responseMessage;

    // 데이터
    private T data;

    // 데이터 건 수
    private int dataCount;

    @Builder
    public ResponseDto(String responseMessage, int dataCount, T data) {
        this.responseMessage = responseMessage;
        this.data = data;
        this.dataCount = dataCount;
    }

    /* 에러 발생 시 */
    public ResponseDto isError() {
        this.isError = true;
        return this;
    }
}

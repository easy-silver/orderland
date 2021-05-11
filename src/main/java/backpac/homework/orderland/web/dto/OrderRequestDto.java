package backpac.homework.orderland.web.dto;

import backpac.homework.orderland.domain.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class OrderRequestDto {

    private String productName;
    private LocalDateTime paymentDate;
    private Long memberNo;

    @Builder
    public OrderRequestDto(String productName, LocalDateTime paymentDate, Long memberNo) {
        this.productName = productName;
        this.paymentDate = paymentDate;
        this.memberNo = memberNo;
    }

    public Order toEntity() {
        return Order.builder()
                .productName(productName)
                .paymentDate(paymentDate)
                .build();
    }
}

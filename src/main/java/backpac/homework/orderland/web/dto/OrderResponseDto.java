package backpac.homework.orderland.web.dto;

import backpac.homework.orderland.domain.order.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseDto {

    private String orderNo;
    private String productName;
    private LocalDateTime paymentDate;
    private Long memberNo;

    public OrderResponseDto(Order entity) {
        this.orderNo = entity.getOrderNo();
        this.productName = entity.getProductName();
        this.paymentDate = entity.getPaymentDate();
        this.memberNo = entity.getMember().getMemberNo();
    }
}

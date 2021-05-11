package backpac.homework.orderland.domain.order;

import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.util.StringUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor
@Table(name = "Orders")
@Entity
public class Order {

    // 주문번호(PK, 중복이 불가능한 임의의 영문 대문자, 숫자 조합)
    @Id
    @Column(nullable = false, length = 12)
    private String orderNo;

    // 제품명(emoji 를 포함한 모든 문자)
    @Column(nullable = false, length = 100)
    private String productName;

    // 결제일시(Timezone 을 고려한 시간 정보)
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    // 회원번호(FK)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Builder
    public Order(String productName, LocalDateTime paymentDate, Member member) {
        this.orderNo = createOrderNo();
        this.productName = productName;
        this.paymentDate = paymentDate;
        this.member = member;
    }

    /**
     * 12자리 주문번호 만들기
     * FIXME : 숫자포함한 고유값으로 변경해야 함
     */
    private String createOrderNo() {
        return StringUtil.randomAlphabet(12);
    }
}

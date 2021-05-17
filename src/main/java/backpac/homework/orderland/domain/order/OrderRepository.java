package backpac.homework.orderland.domain.order;

import backpac.homework.orderland.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    // 회원의 마지막 주문건 조회
    Order findFirstByMemberOrderByPaymentDateDesc(Member member);

    // 회원별 주문 목록 조회
    List<Order> findAllByMember(Member member);

}

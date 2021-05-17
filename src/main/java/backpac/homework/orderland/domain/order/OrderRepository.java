package backpac.homework.orderland.domain.order;

import backpac.homework.orderland.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByMember(Member member);

}

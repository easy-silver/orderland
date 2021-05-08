package backpac.homework.orderland.domain.order;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 주문_저장() {
        //given
        Member member = memberRepository.save(Member.builder()
                .name("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build());

        //when
        Order order = orderRepository.save(Order.builder()
                .orderNo("OD1234567890")
                .productName("새우깡")
                .paymentDate(LocalDateTime.now())
                .member(member)
                .build());

        //then
        Order findOrder = orderRepository.findById(order.getOrderNo()).get();
        assertThat(findOrder.getProductName()).isEqualTo("새우깡");
        assertThat(findOrder.getMember()).isEqualTo(member);
    }
}
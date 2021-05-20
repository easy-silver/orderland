package backpac.homework.orderland.domain.order;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
                .username("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build());

        //when
        Order order = orderRepository.save(Order.builder()
                .productName("새우깡")
                .paymentDate(LocalDateTime.now())
                .member(member)
                .build());

        //then
        Order findOrder = orderRepository.findById(order.getOrderNo()).get();
        assertThat(findOrder.getProductName()).isEqualTo("새우깡");
        assertThat(findOrder.getMember()).isEqualTo(member);
    }

    @Test
    public void 회원별_주문_조회() {
        //given
        Member member1 = memberRepository.save(Member.builder()
                .username("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build());

        orderRepository.save(Order.builder()
                .productName("새우깡")
                .paymentDate(LocalDateTime.now())
                .member(member1)
                .build());

        orderRepository.save(Order.builder()
                .productName("구운감자")
                .paymentDate(LocalDateTime.now())
                .member(member1)
                .build());

        Member member2 = memberRepository.save(Member.builder()
                .username("이지금")
                .nickname("모티")
                .password("!qwerasdf1234")
                .email("moti@mail.com")
                .telNo("01012341235")
                .gender(Gender.MALE)
                .build());

        orderRepository.save(Order.builder()
                .productName("홈런볼")
                .paymentDate(LocalDateTime.now())
                .member(member2)
                .build());

        //when
        List<Order> all = orderRepository.findAll();
        List<Order> allByMember1 = orderRepository.findAllByMember(member1);
        List<Order> allByMember2 = orderRepository.findAllByMember(member2);

        //then
        assertThat(all.size()).isEqualTo(4); // 테스트 데이터 포함
        assertThat(allByMember1.size()).isEqualTo(2);
        assertThat(allByMember2.size()).isEqualTo(1);
    }

    @Test
    public void 회원별_마지막_주문건_조회() {
        //given
        Member member = memberRepository.findByUsername("이지은", PageRequest.of(0, 1))
                .getContent().get(0);

        Order lastOrder = orderRepository.findFirstByMemberOrderByPaymentDateDesc(member);

        //when
        System.out.println("lastOrder.getProductName() = " + lastOrder.getProductName());

        //then
    }
}
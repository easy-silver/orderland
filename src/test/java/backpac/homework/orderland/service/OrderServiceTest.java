package backpac.homework.orderland.service;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import backpac.homework.orderland.domain.order.Order;
import backpac.homework.orderland.domain.order.OrderRepository;
import backpac.homework.orderland.web.dto.OrderRequestDto;
import backpac.homework.orderland.web.dto.OrderResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberRepository memberRepository;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 주문_등록() {
        //given
        Long memberNo = memberRepository.save(Member.builder()
                .name("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build()).getMemberNo();

        OrderRequestDto requestDto = OrderRequestDto.builder()
                .memberNo(memberNo)
                .productName("새우깡")
                .paymentDate(LocalDateTime.now())
                .build();

        //when
        String orderNo = orderService.createOrder(requestDto);
        Order findOrder = orderRepository.findById(orderNo).get();

        //then
        System.out.println("orderNo = " + orderNo);
        assertThat(orderNo).isNotNull();
        assertThat(orderNo).isEqualTo(findOrder.getOrderNo());
    }

    @Test
    public void 회원별_주문_목록_조회() {
        //given
        Long memberNo = memberRepository.save(Member.builder()
                .name("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build()).getMemberNo();

        orderService.createOrder(OrderRequestDto.builder()
                .memberNo(memberNo)
                .productName("새우깡")
                .paymentDate(LocalDateTime.now())
                .build());

        orderService.createOrder(OrderRequestDto.builder()
                .memberNo(memberNo)
                .productName("구운감자")
                .paymentDate(LocalDateTime.now())
                .build());

        //when
        List<OrderResponseDto> orders = orderService.findOrdersByMember(memberNo);

        //then
        assertThat(orders.size()).isEqualTo(2);
        assertThat(orders.get(0).getProductName()).isEqualTo("새우깡");
    }
}
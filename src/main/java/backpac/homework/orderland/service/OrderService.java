package backpac.homework.orderland.service;

import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import backpac.homework.orderland.domain.order.Order;
import backpac.homework.orderland.domain.order.OrderRepository;
import backpac.homework.orderland.web.dto.OrderRequestDto;
import backpac.homework.orderland.web.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    /**
     * 주문 생성
     */
    public String createOrder(OrderRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberNo())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. MEMBER_NO=" + requestDto.getMemberNo()));

        Order order = requestDto.toEntity().setMember(member);

        return orderRepository.save(order).getOrderNo();
    }

    /**
     * 회원별 주문 목록 조회
     */
    public List<OrderResponseDto> findOrdersByMember(Long memberNo) {
        // 회원 조회
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. MEMBER_NO=" + memberNo));

        // 주문 목록 조회
        return orderRepository.findAllByMember(member)
                .stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }
}

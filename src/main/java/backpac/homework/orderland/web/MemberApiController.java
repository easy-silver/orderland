package backpac.homework.orderland.web;

import backpac.homework.orderland.service.MemberService;
import backpac.homework.orderland.service.OrderService;
import backpac.homework.orderland.web.dto.MemberRequestDto;
import backpac.homework.orderland.web.dto.MemberResponseDto;
import backpac.homework.orderland.web.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final OrderService orderService;

    /* FIXME : List로 반환하는 것이 아닌 Map 형태로 반환할 것 */

    /**
     * 회원 가입
     */
    @PostMapping("/members")
    public Long join(@RequestBody MemberRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    /**
     * 모든 회원 조회
     */
    @GetMapping("/members")
    public List<MemberResponseDto> findAllMembers() {
        return memberService.findAllMembers();
    }

    /**
     * 단일 회원 조회
     */
    @GetMapping("/members/{memberNo}")
    public MemberResponseDto findMember(@PathVariable Long memberNo) {
        return memberService.findMember(memberNo);
    }

    /**
     * 단일 회원의 주문 목록 조회
     */
    @GetMapping("/members/{memberNo}/orders")
    public List<OrderResponseDto> findOrdersByMember(@PathVariable Long memberNo) {
        return orderService.findOrdersByMember(memberNo);
    }

}

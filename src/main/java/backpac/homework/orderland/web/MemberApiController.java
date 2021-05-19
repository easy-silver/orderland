package backpac.homework.orderland.web;

import backpac.homework.orderland.service.MemberService;
import backpac.homework.orderland.service.OrderService;
import backpac.homework.orderland.web.dto.MemberRequestDto;
import backpac.homework.orderland.web.dto.MemberResponseDto;
import backpac.homework.orderland.web.dto.MemberSearchRequestDto;
import backpac.homework.orderland.web.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final OrderService orderService;

    /* FIXME : List로 반환하는 것이 아닌 Map 형태로 반환할 것 */

    /**
     * 회원 가입
     */
    @PostMapping("/members")
    public Long join(@RequestBody @Valid MemberRequestDto requestDto, BindingResult result) throws BindException{

        // 입력값 검증
        if (result.hasErrors()) {
            throw new BindException(result);
        }

        return memberService.join(requestDto);
    }

    /**
     * 모든 회원 조회
     */
    @GetMapping("/members")
    public List<MemberResponseDto> findAllMembers(MemberSearchRequestDto requestDto) {
        return memberService.findMembers(requestDto);
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

package backpac.homework.orderland.web;

import backpac.homework.orderland.service.MemberService;
import backpac.homework.orderland.service.OrderService;
import backpac.homework.orderland.web.dto.*;
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

    /**
     * 회원 가입
     */
    @PostMapping("/members")
    public ResponseDto join(@RequestBody @Valid MemberRequestDto requestDto, BindingResult result) throws BindException{

        ResponseDto<Object> responseDto = ResponseDto.builder().build();

        // 입력값 검증
        try {
            if (result.hasErrors()) {
                throw new BindException(result);
            }

            responseDto.setData(memberService.join(requestDto));
            responseDto.setDataCount(1);

        } catch (BindException e) {

            responseDto.isError();
            responseDto.setResponseMessage("입력값이 유효하지 않습니다.");
            responseDto.setData(result.getAllErrors());

        } finally {
            return responseDto;
        }
    }

    /**
     * 모든 회원 조회
     */
    @GetMapping("/members")
    public ResponseDto findAllMembers(MemberSearchRequestDto requestDto) {
        List<MemberResponseDto> members = memberService.findMembers(requestDto);

        return ResponseDto.builder()
                .data(members)
                .dataCount(members.size())
                .build();
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
    public ResponseDto findOrdersByMember(@PathVariable Long memberNo) {
        List<OrderResponseDto> orderList = orderService.findOrdersByMember(memberNo);

        return ResponseDto.builder()
                .data(orderList)
                .dataCount(orderList.size())
                .build();
    }

}

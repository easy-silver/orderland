package backpac.homework.orderland.web;

import backpac.homework.orderland.service.MemberService;
import backpac.homework.orderland.web.dto.MemberRequestDto;
import backpac.homework.orderland.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService service;

    /**
     * 회원 가입
     */
    @PostMapping("/members")
    public Long join(@RequestBody MemberRequestDto requestDto) {
        return service.join(requestDto);
    }

    /**
     * 단일 회원 조회
     */
    @GetMapping("/members/{memberNo}")
    public MemberResponseDto findMember(@PathVariable Long memberNo) {
        return service.findMember(memberNo);
    }

    /**
     * 모든 회원 조회
     */
    @GetMapping("/members")
    public List<MemberResponseDto> findAllMembers() {
        return service.findAllMembers();
    }

}

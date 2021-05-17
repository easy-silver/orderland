package backpac.homework.orderland.service;

import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import backpac.homework.orderland.web.dto.MemberRequestDto;
import backpac.homework.orderland.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;

    /**
     * 회원 가입
     */
    public Long join(MemberRequestDto requestDto) {
        return repository.save(requestDto.toEntity()).getMemberNo();
    }

    /**
     * 단일 회원 조회
     */
    public MemberResponseDto findMember(Long memberNo) {
        Member member = repository.findById(memberNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. MEMBER_NO=" + memberNo));

        return new MemberResponseDto(member);
    }

    /**
     * 모든 회원 조회
     */
    public List<MemberResponseDto> findAllMembers(int pageNo) {
        // 회원번호 내림차순으로 정렬
        Pageable pageable = PageRequest.of(pageNo,10,
                Sort.by(Sort.Direction.DESC, "memberNo"));

        return repository.findAll(pageable)
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }
}

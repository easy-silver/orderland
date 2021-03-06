package backpac.homework.orderland.service;

import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import backpac.homework.orderland.domain.member.Role;
import backpac.homework.orderland.web.dto.LoginDto;
import backpac.homework.orderland.web.dto.MemberRequestDto;
import backpac.homework.orderland.web.dto.MemberResponseDto;
import backpac.homework.orderland.web.dto.MemberSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityService securityService;

    /**
     * 회원 가입
     */
    public Long join(MemberRequestDto requestDto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(encodedPassword);

        // 권한 추가
        Role role = new Role();
        role.setId(1L);
        Member member = requestDto.toEntity();
        member.addRole(role);

        return repository.save(member).getMemberNo();
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
    public List<MemberResponseDto> findMembers(MemberSearchRequestDto requestDto) {
        // 회원번호 내림차순으로 정렬
        Pageable pageable = PageRequest.of(requestDto.getPageNo(),requestDto.getContentCount(),
                Sort.by(Sort.Direction.DESC, "memberNo"));

        String name = requestDto.getName();
        String email = requestDto.getEmail();

        List<Member> members;

        // 검색 조건에 따른 조회
        if (name != null && !name.isEmpty()) {
            members = repository.findByUsername(name, pageable).getContent();

        }else if (email != null && !email.isEmpty()) {
            members = repository.findByEmail(email, pageable).getContent();

        }else {
            members = repository.findAll(pageable).getContent();
        }

        return members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    public boolean login(LoginDto loginDto) {
        return this.securityService.login(loginDto.getEmail(), loginDto.getPassword());
    }

    public MemberResponseDto findByEmail(String email) {
        Member member = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. EMAIL=" + email));

        return new MemberResponseDto(member);
    }
}

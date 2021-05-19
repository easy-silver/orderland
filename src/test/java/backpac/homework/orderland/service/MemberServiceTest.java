package backpac.homework.orderland.service;

import backpac.homework.orderland.domain.member.Gender;
import backpac.homework.orderland.domain.member.Member;
import backpac.homework.orderland.domain.member.MemberRepository;
import backpac.homework.orderland.web.dto.MemberRequestDto;
import backpac.homework.orderland.web.dto.MemberResponseDto;
import backpac.homework.orderland.web.dto.MemberSearchRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;

    @Test
    public void 회원_가입() {
        //given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .username("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build();

        //when
        Long memberNo = service.join(requestDto);

        //then
        Member findMember = repository.findById(memberNo).get();
        assertThat(requestDto.getUsername()).isEqualTo(findMember.getUsername());
    }

    @Test
    public void 회원_조회() {
        //given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .username("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build();

        Long memberNo = service.join(requestDto);

        //when
        MemberResponseDto responseDto = service.findMember(memberNo);

        //then
        assertThat(responseDto.getMemberNo()).isEqualTo(memberNo);
        assertThat(responseDto.getName()).isEqualTo("이지은");
        assertThat(responseDto.getNickname()).isEqualTo("티모");
    }

    @Test
    public void 모든_회원_조회() {
        //given
        service.join(MemberRequestDto.builder()
                .username("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build());

        service.join(MemberRequestDto.builder()
                .username("이지금")
                .nickname("모티")
                .password("!qwerasdf1234")
                .email("moti@mail.com")
                .telNo("01012341235")
                .gender(Gender.MALE)
                .build());

        //when
        List<MemberResponseDto> members = service.findMembers(new MemberSearchRequestDto());

        //then
        assertThat(members.size()).isGreaterThan(2);
    }

    @Test
    public void 이름으로_조회() {
        //given
        String name = "이지은";

        //when
        List<MemberResponseDto> members = service.findMembers(new MemberSearchRequestDto(name, null));

        //then
        assertThat(members.size()).isGreaterThan(0);
        assertThat(members.get(0).getNickname()).isEqualTo("티모");
    }
}
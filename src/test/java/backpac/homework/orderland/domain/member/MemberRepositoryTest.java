package backpac.homework.orderland.domain.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    public void 회원_저장() {
        //given
        Member member = Member.builder()
                .name("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build();

        //when
        Member savedMember = repository.save(member);

        //then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    public void 단일_회원_조회() {
        //given
        Member member = repository.save(Member.builder()
                .name("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build());

        //when
        Member findMember = repository.findById(member.getMemberNo()).get();

        //then
        assertThat(findMember.getName()).isEqualTo("이지은");
    }

    @Test
    public void 여러_회원_조회() {
        //given
        repository.save(Member.builder()
                .name("이지은")
                .nickname("티모")
                .password("!qwerasdf1234")
                .email("timo@mail.com")
                .telNo("01012341234")
                .gender(Gender.FEMALE)
                .build());

        repository.save(Member.builder()
                .name("이지금")
                .nickname("모티")
                .password("!qwerasdf1234")
                .email("moti@mail.com")
                .telNo("01012341235")
                .gender(Gender.MALE)
                .build());

        //when
        List<Member> members = repository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void 페이징_사용() {
        //given
        Pageable pageable = PageRequest.of(0,10,
                Sort.by(DESC, "memberNo"));

        //when
        Page<Member> result = repository.findAll(pageable);
        List<Member> members = result.getContent();
        int totalPages = result.getTotalPages();
        int totalCount = result.getNumberOfElements();
        boolean hasNextPage = result.hasNext();

        //then
        assertThat(hasNextPage).isFalse();
        assertThat(totalPages).isGreaterThan(0);
        assertThat(members.get(0).getName()).isEqualTo("이라떼");
        assertThat(totalCount).isEqualTo(2);
    }

    @Test
    public void 이름으로_회원조회() {
        //given
        String name = "이지은";
        Pageable pageable = PageRequest.of(0, 10, Sort.by(DESC, "memberNo"));

        //when
        Page<Member> result = repository.findByName(name, pageable);
        List<Member> members = result.getContent();

        //then
        assertThat(members.size()).isGreaterThan(0);
        assertThat(members.get(0).getGender()).isEqualTo(Gender.FEMALE);
    }

}
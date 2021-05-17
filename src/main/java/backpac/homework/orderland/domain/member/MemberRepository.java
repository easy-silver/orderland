package backpac.homework.orderland.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이름으로 검색
    Page<Member> findByName(String name, Pageable pageable);

    // 이메일로 검색
    Page<Member> findByEmail(String email, Pageable pageable);
}

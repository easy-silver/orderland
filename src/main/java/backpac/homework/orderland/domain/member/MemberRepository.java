package backpac.homework.orderland.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이름으로 검색
    Page<Member> findByUsername(String name, Pageable pageable);

    // 이메일로 검색
    Page<Member> findByEmail(String email, Pageable pageable);

    Optional<Member> findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.email =:email")
    Member findUserByEmail(@Param("email") String email);
}

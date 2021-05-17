package backpac.homework.orderland.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //List<Member> findAll(Pageable pageable);
}

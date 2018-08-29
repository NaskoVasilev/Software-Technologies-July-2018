package softuniBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}

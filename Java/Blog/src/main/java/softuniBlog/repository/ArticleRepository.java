package softuniBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
    List<Article> findAllByAuthorId(Integer id);
}

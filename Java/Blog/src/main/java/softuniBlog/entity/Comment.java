package softuniBlog.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    private Integer id;
    private String content;
    private Article article;
    private User author;

    public Comment(String content, Article article, User author) {
        this.content = content;
        this.article = article;
        this.author = author;
    }

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne()
    @JoinColumn(name="authorId")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne()
    @JoinColumn(name="articleId")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

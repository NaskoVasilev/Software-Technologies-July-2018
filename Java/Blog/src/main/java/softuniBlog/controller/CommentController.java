package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuniBlog.bindingModel.CommentBindingModel;
import softuniBlog.entity.Article;
import softuniBlog.entity.Comment;
import softuniBlog.entity.User;
import softuniBlog.repository.ArticleRepository;
import softuniBlog.repository.CommentRepository;
import softuniBlog.repository.UserRepository;

@Controller
public class CommentController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/article/{id}/comment/add")
    @PreAuthorize("isAuthenticated()")
    public String addComment(@PathVariable Integer id, CommentBindingModel commentBindingModel) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepository.findByEmail(userDetails.getUsername());

        Article article = this.articleRepository.findOne(id);

        Comment comment = new Comment(
                commentBindingModel.getContent()
                , article, user);

        this.commentRepository.saveAndFlush(comment);

        return "redirect:/article/"+id;
    }

    @GetMapping("/comment/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteComment (Model model ,@PathVariable Integer id ){
        Comment comment =this.commentRepository.findOne(id);
        User author=comment.getAuthor();

        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User userEntity = this.userRepository.findByEmail(user.getUsername());

        if(author.getId()!=userEntity.getId())
        {
            //return "redirect:/";
            return "redirect:/article/"+comment.getArticle().getId();
        }
        model.addAttribute("comment",comment);
        model.addAttribute("view","comment/delete");
        return "base-layout";
    }
    @PostMapping("/comment/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteComment (@PathVariable Integer id,CommentRepository commentRepository){
        Comment comment =this.commentRepository.findOne(id);
        Integer articleId=comment.getArticle().getId();
        this.commentRepository.delete(comment);

        return "redirect:/article/"+articleId;
    }
}

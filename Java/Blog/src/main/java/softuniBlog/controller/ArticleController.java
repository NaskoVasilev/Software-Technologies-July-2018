package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuniBlog.bindingModel.ArticleBindingModel;
import softuniBlog.entity.Article;
import softuniBlog.entity.User;
import softuniBlog.repository.ArticleRepository;
import softuniBlog.repository.UserRepository;

import javax.jws.WebParam;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/article/create")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model) {
        model.addAttribute("view", "article/create");
        return "base-layout";
    }

    @PostMapping("/article/create")
    @PreAuthorize("isAuthenticated()")
    public String createProcess(ArticleBindingModel articleBindingModel) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User userEntity = this.userRepository.findByEmail(user.getUsername());

        Article articleEntity = new Article(
                articleBindingModel.getTitle(),
                articleBindingModel.getContent(),
                userEntity,
                articleBindingModel.getImageUrl()
        );

        this.articleRepository.saveAndFlush(articleEntity);

        return "redirect:/";
    }

    @GetMapping("/article/{id}")
    public String details(Model model, @PathVariable Integer id) {

        Authentication authentication=SecurityContextHolder
                .getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            User user=this.userRepository.findByEmail(userDetails.getUsername());
            model.addAttribute("user",user);
        }


        if (!this.articleRepository.exists(id)) {
            return "redirect:/";
        }
        Article article = this.articleRepository.findOne(id);

        model.addAttribute("article", article);
        model.addAttribute("view", "article/details");

        return "base-layout";
    }

    @GetMapping("/article/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(Model model, @PathVariable Integer id) {
        if (!this.articleRepository.exists(id)) {
            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!isAdminOrAuthor(article)){
            return "redirect:/";
        }

        model.addAttribute("article", article);
        model.addAttribute("view", "article/edit");
        return "base-layout";
    }

    @PostMapping("/article/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editProcess(@PathVariable Integer id, ArticleBindingModel articleBindingModel) {
        if (!this.articleRepository.exists(id)) {
            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!isAdminOrAuthor(article)){
            return "redirect:/";
        }

        article.setContent(articleBindingModel.getContent());
        article.setTitle(articleBindingModel.getTitle());
        article.setImageUrl(articleBindingModel.getImageUrl());

        this.articleRepository.saveAndFlush(article);

        return "redirect:/article/" + article.getId();

    }

    @GetMapping("/article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(Model model, @PathVariable Integer id) {
        if (!this.articleRepository.exists(id)) {
            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!isAdminOrAuthor(article)){
            return "redirect:/";
        }

        model.addAttribute("article", article);
        model.addAttribute("view", "article/delete");
        return "base-layout";
    }

    @PostMapping("article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteProcess(@PathVariable Integer id){
        if(!this.articleRepository.exists(id)){
            return "redirect:/";
        }

        Article article=this.articleRepository.findOne(id);

        if(!isAdminOrAuthor(article)){
            return "redirect:/";
        }

        this.articleRepository.delete(article);

        return  "redirect:/";
    }

    public boolean isAdminOrAuthor(Article article){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user=this.userRepository.findByEmail(userDetails.getUsername());

        return user.isAdmin() || user.isAuthor(article);
    }

}



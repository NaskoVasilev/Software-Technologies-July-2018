package issuetracker.controller;

import issuetracker.entity.Issue;
import org.aspectj.weaver.tools.ISupportsMessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import issuetracker.bindingModel.IssueBindingModel;
import issuetracker.repository.IssueRepository;

import java.util.List;

@Controller
public class IssueController {
    private final IssueRepository issueRepository;

    @Autowired
    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Issue> issues = this.issueRepository.findAll();

        model.addAttribute("view", "issue/index");
        model.addAttribute("issues", issues);

        return "base-layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("view", "issue/create");

        return "base-layout";
    }

    @PostMapping("/create")
    public String createProcess(Model model, IssueBindingModel issueBindingModel) {
        Issue issue = new Issue
                (
                        issueBindingModel.getTitle(),
                        issueBindingModel.getContent(),
                        issueBindingModel.getPriority()
                );

        this.issueRepository.saveAndFlush(issue);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Issue issue=this.issueRepository.findOne(id);
        model.addAttribute("view","issue/edit");
        model.addAttribute("issue",issue);
        return "base-layout";
    }

    @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable int id, Model model, IssueBindingModel issueBindingModel) {
        Issue issue=this.issueRepository.findOne(id);
        issue.setTitle(issueBindingModel.getTitle());
        issue.setContent(issueBindingModel.getContent());
        issue.setPriority(issueBindingModel.getPriority());

        this.issueRepository.saveAndFlush(issue);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Issue issue=this.issueRepository.findOne(id);
        model.addAttribute("view","issue/delete");
        model.addAttribute("issue",issue);
        return "base-layout";
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable int id, IssueBindingModel issueBindingModel) {
        Issue issue=this.issueRepository.findOne(id);
        this.issueRepository.delete(issue);
        return "redirect:/";
    }
}

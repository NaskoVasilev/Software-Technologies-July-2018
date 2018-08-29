namespace IssueTracker.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using IssueTracker.Models;
    using System.Linq;
    using System.Collections.Generic;

    public class IssueController : Controller
    {
        private readonly IssueDbContext context;

        public IssueController(IssueDbContext context)
        {
            this.context = context;
        }

        [HttpGet]
        [Route("")]
        public ActionResult Index()
        {
            List<Issue> issues = this.context.Issues.ToList();
            return View(issues);
        }

        [HttpGet]
        [Route("create")]
        public ActionResult Create()
        {
            return View();
            
        }

        [HttpPost]
        [Route("create")]
        public ActionResult Create(Issue issue)
        {
            this.context.Issues.Add(issue);
            this.context.SaveChanges();
            return RedirectToAction(nameof(Index));
        }

        [HttpGet]
        [Route("edit/{id}")]
        public ActionResult Edit(int id)
        {
            var issue = this.context.Issues.Find(id);
            return View(issue);
        }

        [HttpPost]
        [Route("edit/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult EditConfirm(int id, Issue issueModel)
        {
            var issue = this.context.Issues.Find(id);
            issue.Title = issueModel.Title;
            issue.Content = issueModel.Content;
            issue.Priority = issueModel.Priority;
            this.context.Issues.Update(issue);
            this.context.SaveChanges();
            return RedirectToAction(nameof(Index));
        }

        [HttpGet]
        [Route("delete/{id}")]
        public ActionResult Delete(int id)
        {
            var issue = this.context.Issues.Find(id);
            return View(issue);
        }

        [HttpPost]
        [Route("delete/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirm(int id, Issue issueModel)
        {
            var issue = this.context.Issues.Find(id);
            this.context.Issues.Remove(issue);
            this.context.SaveChanges();
            return RedirectToAction(nameof(Index));
        }
    }
}

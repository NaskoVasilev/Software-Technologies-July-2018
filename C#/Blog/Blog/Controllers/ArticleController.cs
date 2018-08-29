using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Blog.Data;
using Blog.Models;
using Microsoft.AspNetCore.Authorization;

namespace Blog.Controllers
{
    public class ArticleController : Controller
    {
        private readonly ApplicationDbContext db;

        public ArticleController(ApplicationDbContext dbContext)
        {
            this.db = dbContext;
        }

        [HttpGet]
        public ActionResult Index()
        {
            return RedirectToAction("List");
        }

        [HttpGet]
        public ActionResult List()
        {
            List<Article> articles = db.Articles
                .Include(a => a.Author)
                .OrderByDescending(x => x.DateAdded)
                .ToList();

            return View(articles);
        }

        [HttpGet]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return StatusCode(500);
            }

            Article article = db.Articles
                .Include(a => a.Author)
                .FirstOrDefault(a => a.Id == id);

            if (article == null)
            {
                return StatusCode(500);
            }

            return View(article);
        }

        [Authorize]
        [HttpGet]
        public ActionResult Create()
        {
            return View();
        }

        [Authorize]
        [HttpPost]
        public ActionResult Create(Article article)
        {

            if (ModelState.IsValid)
            {
                string authorId = db.Users
                    .Where(u => u.UserName == this.User.Identity.Name)
                    .First()
                    .Id;
                article.AuthorId = authorId;

                db.Articles.Add(article);
                db.SaveChanges();

                return RedirectToAction("Index");
            }

            return View(article);
        }

        [Authorize]
        [HttpGet]
        public ActionResult Delete(int? id)
        {

            if (id == null)
            {
                return NotFound();
            }

            Article article = db.Articles.Find(id);
            if (article == null)
            {
                return StatusCode(500);
            }

            if (IsUserAuthorizedToEdit(article) == false)
            {
                return Forbid();
            }


            return View(article);
        }

        [Authorize]
        [HttpPost]
        public ActionResult Delete(Article article)
        {

            if (article == null)
            {
                return NotFound();
            }

            db.Articles.Remove(article);
            db.SaveChanges();

            return RedirectToAction("Index");
        }

        [Authorize]
        [HttpGet]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            Article article = db.Articles.Find(id);

            if (article == null)
            {
                return NotFound();
            }

            if (IsUserAuthorizedToEdit(article) == false)
            {
                return Forbid();
            }

            ArticleViewModel articleModel = new ArticleViewModel(article.Id, article.Title, article.Content);

            return View(articleModel);
        }

        [Authorize]
        [HttpPost]
        public ActionResult Edit(ArticleViewModel articleModel)
        {
            if (ModelState.IsValid)
            {
                Article article = db.Articles
                .FirstOrDefault(a => a.Id == articleModel.Id);

                article.Title = articleModel.Title;
                article.Content = articleModel.Content;

                db.Articles.Update(article);
                db.SaveChanges();

                return RedirectToAction("Index");

            }
            return View(articleModel);
        }

        public bool IsUserAuthorizedToEdit(Article article)
        {
            string authorId = db.Users.
                Where(u => u.UserName == this.User.Identity.Name)
                .First()
                .Id;
            bool isAdmin = this.User.IsInRole("Admin");
            bool isAuthor = article.AuthorId == authorId;

            return isAdmin || isAuthor;
        }

        [HttpGet]
        public ActionResult MyArticles()
        {
            string authorId = db.Users
                    .Where(u => u.UserName == this.User.Identity.Name)
                    .First()
                    .Id;

            List<Article> articles = db.Articles
                .Where(a => a.AuthorId == authorId)
                .OrderByDescending(x => x.DateAdded)
                .ToList();

            return View(articles);
        }

    }
}

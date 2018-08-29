using Microsoft.AspNetCore.Mvc;
using Library.Models;
using Library.Data;
using Microsoft.AspNetCore.Http;
using System.Security.Claims;
using Microsoft.EntityFrameworkCore;
using System.Linq;

namespace Library.Controllers
{
    public class BookController : Controller
    {
        private readonly ApplicationDbContext context;
        private readonly IHttpContextAccessor httpContext;


        public BookController(ApplicationDbContext context, IHttpContextAccessor httpContextAccessor)
        {
            this.context = context;
            this.httpContext = httpContextAccessor;
        }

        [HttpGet]
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Book book)
        {
            var userId = httpContext.HttpContext.User.FindFirst(ClaimTypes.NameIdentifier).Value;
            book.UserId = userId;
            context.Books.Add(book);
            context.SaveChanges();

            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult Details(int id)
        {
            var book = context.Books
                .Include(b => b.User)
                .FirstOrDefault(b=>b.Id==id);

            return View(book);
        }

        [HttpGet]
        public IActionResult Edit(int id)
        {
            var book = context.Books
               .Find(id);

            return View(book);
        }

        [HttpPost]
        public IActionResult Edit(Book book)
        {
            var currentBook = context.Books.Find(book.Id);
            currentBook.Title = book.Title;
            currentBook.Discription = book.Discription;
            context.Books.Update(currentBook);
            context.SaveChanges();

            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult Delete(int id)
        {
            var book = context.Books
               .Find(id);

            return View(book);
        }

        [HttpPost]
        public IActionResult Delete(Book book)
        {
            context.Books.Remove(book);
            context.SaveChanges();

            return RedirectToAction("Index", "Home");
        }
    }
}
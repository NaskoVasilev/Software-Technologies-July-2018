using Microsoft.AspNetCore.Mvc;
using Library.Data;
using System.Linq;
using Microsoft.EntityFrameworkCore;

namespace Library.Controllers
{
    public class HomeController : Controller
    {
        private readonly ApplicationDbContext context;

        public HomeController(ApplicationDbContext context)
        {
            this.context = context;
        }

        [HttpGet]
        public IActionResult Index()
        {
            var books = context.Books
                .Include(b=>b.User)
                .ToList();

            return View(books);
        }

        

    }
}
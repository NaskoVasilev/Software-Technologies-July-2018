namespace IMDB.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using System.Collections.Generic;
    using System.Linq;
    using Models;

    public class FilmController : Controller
    {
        private readonly IMDBDbContext dbContext;

        public FilmController(IMDBDbContext dbContext)
        {
            this.dbContext = dbContext;
        }

        [HttpGet]
        [Route("")]
        public IActionResult Index()
        {
            List<Film> films = dbContext.Films.ToList();
            return View(films);
        }

        [HttpGet]
        [Route("/create")]
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [Route("/create")]
        public IActionResult Create(Film film)
        {
            if (!ModelState.IsValid)
            {
                return View();
            }
            this.dbContext.Films.Add(film);
            this.dbContext.SaveChanges();
            return RedirectToAction(nameof(Index));
        }

        [HttpGet]
        [Route("/edit/{id}")]
        public IActionResult Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            Film film = dbContext.Films.Find(id);

            if (film==null)
            {
                return NotFound();
            }

            return View(film);
        }

        [HttpPost]
        [Route("/edit/{id}")]
        public IActionResult Edit(Film film)
        {
            if (ModelState.IsValid)
            {
                this.dbContext.Films.Update(film);
                this.dbContext.SaveChanges();

                return RedirectToAction(nameof(Index));
            }

            return View(film);
        }

        [HttpGet]
        [Route("/delete/{id}")]
        public IActionResult Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            Film film = dbContext.Films.Find(id);

            if (film == null)
            {
                return NotFound();
            }

            return View(film);
        }

        [HttpPost]
        [Route("/delete/{id}")]
        public IActionResult Delete(Film film)
        {
            this.dbContext.Films.Remove(film);
            this.dbContext.SaveChanges();
            return RedirectToAction(nameof(Index));
        }
    }
}

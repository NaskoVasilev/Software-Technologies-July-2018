namespace CatShop.Controllers
{
    using Microsoft.AspNetCore.Mvc;
    using CatShop.Models;
    using System.Linq;
    using System.Collections.Generic;

    public class CatController : Controller
    {
        private readonly CatDbContext context;

        public CatController(CatDbContext context)
        {
            this.context = context;
        }

        [HttpGet]
        [Route("")]
        public ActionResult Index()
        {
            List<Cat> cats = context.Cats.ToList();

            return View(cats);
        }

        [HttpGet]
        [Route("create")]
        public ActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [Route("create")]
        public ActionResult Create(Cat cat)
        {
            context.Cats.Add(cat);
            context.SaveChanges();
            return RedirectToAction("Index");
        }

        [HttpGet]
        [Route("edit/{id}")]
        public ActionResult Edit(int id)
        {
            Cat cat = context.Cats.Find(id);
            return View(cat);
        }

        [HttpPost]
        [Route("edit/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult EditConfirm(int id, Cat catModel)
        {
            Cat cat = context.Cats.Find(id);
            cat.Name = catModel.Name;
            cat.Nickname = catModel.Nickname;
            cat.Price = catModel.Price;
            context.Cats.Update(cat);
            context.SaveChanges();

            return RedirectToAction("Index");
        }

        [HttpGet]
        [Route("delete/{id}")]
        public ActionResult Delete(int id)
        {
            Cat cat = context.Cats.Find(id);
            return View(cat);
        }

        [HttpPost]
        [Route("delete/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirm(int id, Cat catModel)
        {
            Cat cat = context.Cats.Find(id);
            context.Cats.Remove(cat);
            context.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}

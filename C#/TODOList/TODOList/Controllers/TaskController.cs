using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using TODOList.Models;
using Task = System.Threading.Tasks.Task;

namespace TODOList.Controllers
{
    public class TaskController : Controller
    {
        [HttpPost]
        public IActionResult Create(Models.Task task)
        {
            if (task == null)
            {
                return RedirectToAction("Index", "Home");
            }

            using (var db = new TaskDbContext())
            {
                db.Tasks.Add(task);
                db.SaveChanges();
            }
            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult Delete(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("Index", "Home");
            }

            using (var db=new TaskDbContext())
            {
                var task = db.Tasks.Find(id);
                if (task == null)
                {
                    return RedirectToAction("Index", "Home");
                }
                db.Tasks.Remove(task);
                db.SaveChanges();
            }
            return RedirectToAction("Index", "Home");

        }

        [HttpPost]
        public IActionResult Edit(Models.Task task ,int? id)
        {
            if (task == null||id==null)
            {
                return RedirectToAction("Index", "Home");
            }

            using (var db = new TaskDbContext())
            {
                var taskToEdit = db.Tasks.Find(id);
                if (taskToEdit == null)
                {
                    return RedirectToAction("Index", "Home");
                }
                taskToEdit.Title = task.Title;
                db.SaveChanges();
            }
            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult Edit(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("Index", "Home");
            }

            using(var db = new TaskDbContext())
            {
                var task = db.Tasks.Find(id);

                if (task == null)
                {
                    return RedirectToAction("Index", "Home");
                }
                return View(task);


            }
        }

        [HttpGet]
        public IActionResult Details(int? id)
        {
            if (id == null)
            {
                return RedirectToAction("Index", "Home");
            }

            using (var db=new TaskDbContext())
            {
                var task = db.Tasks.Find(id);
                if (task == null)
                {
                    return RedirectToAction("Index", "Home");
                }
                return View(task);
            }
        }

    }
}
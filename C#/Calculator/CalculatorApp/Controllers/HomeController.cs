using CalculatorApp.Models;
using Microsoft.AspNetCore.Mvc;
using System;

namespace CalculatorApp.Controllers
{
    public class HomeController : Controller
    {
        [HttpGet]
        public IActionResult Index(Calculator calculator)
        {
            return View(calculator);
        }

        [HttpPost]
        public IActionResult Calculate(Calculator calculator)
        {
            if (calculator.RightOperand == 0 && calculator.Operator == "/")
            {
                return RedirectToAction("Index","Home");
            }
            calculator.CalculateResult();
            calculator.Result = Math.Round(calculator.Result, 3);

            return RedirectToAction("Index", calculator);
        }
        

    }
}

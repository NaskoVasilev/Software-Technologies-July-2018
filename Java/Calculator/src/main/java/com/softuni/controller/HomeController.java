package com.softuni.controller;

import com.softuni.entity.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("operator", "+");
        model.addAttribute("view", "views/calculatorForm");
        return "base-layout";
    }

    @PostMapping("/")
    public String calculate(@RequestParam String leftOperand,
                            @RequestParam String operator,
                            @RequestParam String rightOperand,
                            Model model
    ) {
        double firstNum;
        double secondNum;
        firstNum = parseInput(leftOperand);
        secondNum = parseInput(rightOperand);
        Calculator calculator = new Calculator(firstNum, operator, secondNum);
        String result = String.format("%.2f", calculator.calculateResult());


        if ((calculator.getOperator().equals("/")||calculator.getOperator().equals("%"))
                && calculator.getRightOperand() == 0) {
            result = "Cannot divide by zero";
        }

        model.addAttribute("view", "views/calculatorForm");
        model.addAttribute("leftOperand", calculator.getLeftOperand());
        model.addAttribute("operator", calculator.getOperator());
        model.addAttribute("rightOperand", calculator.getRightOperand());
        model.addAttribute("result", result);

        return "base-layout";

    }


    public static double parseInput(String input) {
        double number = 0;
        try {
            number = Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            number = 0;
        }
        return number;
    }
}

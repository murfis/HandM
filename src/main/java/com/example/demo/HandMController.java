package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HandMController {

    @RequestMapping("/init")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        ArrayList<Execution> executions = new ArrayList<Execution>();
        executions.add(new Execution("asd", "asd", "dsa"));
        model.addAttribute("previousExecutions",executions );
        return "init";
    }

}
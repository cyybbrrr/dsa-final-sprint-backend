package com.example.bst.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    /** Optional: keep compatibility if someone hits /enter-numbers directly */
    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "redirect:/enter-numbers.html";
    }
}

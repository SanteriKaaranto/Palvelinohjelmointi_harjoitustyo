package com.example.harjoitustyo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HarjoitustyoController {

    @Autowired
    private NoteRepository noteRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("notes", this.noteRepository.findAll());
        return "index";
    }

/*     private String message;
    // 'content' pitää vastaa HTML:n name='' osiota
    @PostMapping("/")
    public String posting(@RequestParam String content) { 
        this.message = content;
        System.out.println(message);
        return "redirect:/";
    } */
}


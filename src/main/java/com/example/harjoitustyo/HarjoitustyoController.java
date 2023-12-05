package com.example.harjoitustyo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HarjoitustyoController {

    @Autowired
    private NoteRepository noteRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("notes", this.noteRepository.findAll());
        return "index";
    }

    @PostMapping("/saveNote")
    public String saveNote(@RequestParam String note) {
        noteRepository.save(new Notes(null, note));
        return "redirect:/";
    }
}


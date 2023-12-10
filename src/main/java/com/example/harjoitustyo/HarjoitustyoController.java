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

    // Get
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("notes", this.noteRepository.findAll());
        return "index";
    }

    // Post
    @PostMapping("/saveNote")
    public String saveNote(@RequestParam String note) {
        noteRepository.save(new Notes(null, note));
        return "redirect:/";
    }

    // Delete
    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam Long id) {
        noteRepository.deleteById(id);
        return "redirect:/";
    }

    // Update

    // Update get
    @GetMapping("/updateNote")
    public String updateNoteForm(@RequestParam Long id, Model model) {
        Notes existingNote = noteRepository.findById(id).orElse(null);
        model.addAttribute("note", existingNote);
        return "updateNote";
    }

    // Update post
    @PostMapping("/updateNote")
    public String updateNote(@RequestParam Long id, @RequestParam String updatedNote) {
        Notes existingNote = noteRepository.findById(id).orElse(null);
        if (existingNote != null) {
            existingNote.setNote(updatedNote);
            noteRepository.save(existingNote);
        }
        return "redirect:/";
    }
}
package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.repository.CategoriaRepository;

import org.springframework.ui.Model;



@Controller
@RequestMapping("/plataformas")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("plataformas", plataformaRepository.findAll());
        return "plataformas/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        return "plataformas/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        // Create a new plataforma object
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);

        // Save the plataforma to the database
        plataformaRepository.save(plataforma);

        // Redirect to the list page
        return "redirect:/plataformas/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {
        // Find the plataforma by ID
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);

        // If the plataforma is found, add it to the model and return the update page
        if (plataforma.isPresent()) {
            ui.addAttribute("plataforma", plataforma.get());
            return "plataformas/update";
        }

        // If the plataforma is not found, redirect to the list page
        return "redirect:/plataformas/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id, @RequestParam("nome") String nome) {
        // Find the plataforma by ID
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);

        // If the plataforma is found, update its name and save it to the database
        if (plataforma.isPresent()) {
            plataforma.get().setNome(nome);
            plataformaRepository.save(plataforma.get());
        }

        // Redirect to the list page
        return "redirect:/plataformas/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        // Find the plataforma by ID
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);

        // If the plataforma is found, delete it from the database
        if (plataforma.isPresent()) {
            plataformaRepository.delete(plataforma.get());
            return "plataformas/delete";
        }

        // If the plataforma is not found, redirect to the list page
        return "redirect:/plataformas/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        // Delete the plataforma by ID
        plataformaRepository.deleteById(id);

        // Redirect to the list page
        return "redirect:/plataformas/list";
    }
}
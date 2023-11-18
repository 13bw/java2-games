package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import application.model.Plataforma;
import application.repository.PlataformaRepository;

@Controller
@RequestMapping("/plataforma")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("plataformas", plataformaRepository.findAll());
        return "plataforma/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        return "plataforma/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(nome);

        plataformaRepository.save(plataforma);

        return "redirect:/plataforma/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);

        if (plataforma.isPresent()) {
            ui.addAttribute("plataforma", plataforma.get());
            return "plataforma/update";
        }

        return "redirect:/plataforma/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id, @RequestParam("nome") String nome) {
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);

        if (plataforma.isPresent()) {
            plataforma.get().setNome(nome);
            plataformaRepository.save(plataforma.get());
        }

        return "redirect:/plataforma/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Plataforma> plataforma = plataformaRepository.findById(id);

        if (plataforma.isPresent()) {
            ui.addAttribute("plataforma", plataforma.get());
            return "plataforma/delete";
        }

        return "redirect:/plataforma/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        plataformaRepository.deleteById(id);

        return "redirect:/plataforma/list";
    }
}

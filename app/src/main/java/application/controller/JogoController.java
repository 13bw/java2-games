package application.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import application.model.Jogo;
import application.model.Plataforma;
import application.repository.CategoriaRepository;
import application.repository.JogoRepository;
import application.repository.PlataformaRepository;

import java.util.Set;

@Controller
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("jogos", jogoRepository.findAll());
        return "jogo/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("plataformas", plataformaRepository.findAll());
        ui.addAttribute("categorias", categoriaRepository.findAll());
        return "jogo/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
            @RequestParam("titulo") String titulo,
            @RequestParam("plataformas") Long[] idsPlataformas,
            @RequestParam("categoria") Long categoria_id) {

        Jogo jogo = new Jogo();
        jogo.setTitulo(titulo);
        jogo.setCategoria(categoriaRepository.findById(categoria_id).get());

        for (long p : idsPlataformas) {
            Optional<Plataforma> plataforma = plataformaRepository.findById(p);

            if (plataforma.isPresent()) {
                jogo.getPlataformas().add(plataforma.get());
            }
        }
        jogoRepository.save(jogo);
        return "redirect:/jogo/list";

    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {

        Optional<Jogo> jogo = jogoRepository.findById(id);
        if (jogo.isPresent()) {
            ui.addAttribute("jogo", jogoRepository.findById(id).get());
            ui.addAttribute("plataformas", plataformaRepository.findAll());
            ui.addAttribute("categorias", categoriaRepository.findAll());
            return "jogo/update";
        }
        return "redirect:/jogo/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam("id") Long id,
            @RequestParam("plataformas") Long[] idsPlataformas,
            @RequestParam("categoria") Long categoria_id,
            @RequestParam("titulo") String titulo) {

        Optional<Jogo> jogo = jogoRepository.findById(id);

        if (jogo.isPresent()) {
            jogo.get().setTitulo(titulo);
            jogo.get().setCategoria(categoriaRepository.findById(categoria_id).get());

            Set<Plataforma> updatePlataforma = new HashSet<>();

            for (long p : idsPlataformas) {
                Optional<Plataforma> plataforma = plataformaRepository.findById(p);
                if (plataforma.isPresent()) {
                    updatePlataforma.add(plataforma.get());
                }
            }
            jogo.get().setPlataformas(updatePlataforma);
            jogoRepository.save(jogo.get());
        }

        return "redirect:/jogo/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Jogo> jogo = jogoRepository.findById(id);

        if (jogo.isPresent()) {
            ui.addAttribute("jogo", jogo.get());
            return "jogo/delete";
        }

        return "redirect:/jogo/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        jogoRepository.deleteById(id);
        return "redirect:/jogo/list";
    }

}

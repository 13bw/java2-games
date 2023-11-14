package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.repository.CategoriaRepository;

import org.springframework.ui.Model;

import application.model.Categoria;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("categorias", categoriaRepository.findAll());
        return "categorias/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        return "categorias/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoriaRepository.save(categoria);
        return "redirect:/categorias/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            ui.addAttribute("categoria", categoriaRepository.findById(id));
            return "categorias/update";
        }

        return "redirect:/categorias/list";

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id, @RequestParam("nome") String nome) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isPresent()) {
            categoria.get().setNome(nome);
            categoriaRepository.save(categoria.get());
        }

        return "redirect:/categorias/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isPresent()) {
            categoriaRepository.delete(categoria.get());
            return "categorias/delete";
        }
        
        return "redirect:/categorias/list";
    }

    @RequestMapping(value="delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        categoriaRepository.deleteById(id);
        return "redirect:/categorias/list";
    }
}

package com.adibamine.exam_jee.controller;


import com.adibamine.exam_jee.model.Medecin;
import com.adibamine.exam_jee.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class MedecinsController {
    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping(path = "/medecin")
    public String test(Model model,
                       @RequestParam(name="motCle",defaultValue="") String mc,
                       @RequestParam(name="page",defaultValue="0") int page,
                       @RequestParam(name="size",defaultValue="5") int size)
    {
        Page<Medecin> medecinsPage = medecinRepository.findByNomContains(mc, PageRequest.of(page,size));
        List<Medecin> medecins = medecinsPage.getContent();
        model.addAttribute("listeMedecins", medecins);
        model.addAttribute("motCle", mc);
        model.addAttribute("nbrPage",medecinsPage.getTotalPages());
        model.addAttribute("pages",new int[medecinsPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        return "medecin";
    }
    @GetMapping(path="/addMedecin")
    public String addMedecin(Model model){
        model.addAttribute("medecin",new Medecin());
        return  "formMedecin";
    }
    @PostMapping("saveMedecin")
    public String saveMedecin(@ModelAttribute("medecin") @Valid Medecin medecin, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "formMedecin";
        medecinRepository.save(medecin);
        System.out.println(medecin);
        return "redirect:/medecin";
    }
    @GetMapping(path="/updateMedecin")
    public String updateMedecin(@RequestParam(name = "id") Long id, Model model){
        Optional<Medecin> medecin = medecinRepository.findById(id);
        if (medecin.isEmpty())
            return "redirect:/medecin";
        model.addAttribute("medecin", medecin.get());
        return  "update";
    }
    @PostMapping("/updateMedecin")
    public String updateMedecinForm(@RequestParam(name = "id") Long id, @ModelAttribute("medecin") @Valid Medecin medecin, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "updateMedecin";
        Optional<Medecin> oMedecin = medecinRepository.findById(id);
        if (oMedecin.isEmpty())
            return "redirect:/medecin";
        Medecin p = oMedecin.get();
        p.setNom(medecin.getNom());
        p.setSpecialite(medecin.getSpecialite());
        p.setRendezVousSet(medecin.getRendezVousSet());
        medecinRepository.save(p);
        return "redirect:/medecin";
    }
    @GetMapping(path="/deleteMedecin")
    public String delete(Long id,String motCle,int page,int size) {
        medecinRepository.deleteById(id);
        return "redirect:/medecin?page="+page+"&motCle="+motCle+"&size="+size;
    }
}

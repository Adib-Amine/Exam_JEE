package com.adibamine.exam_jee.controller;


import com.adibamine.exam_jee.model.RendezVous;
import com.adibamine.exam_jee.repository.RendezVousRepository;
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
public class RendezVousController {
    @Autowired
    private RendezVousRepository rendez_vousRepository;

    @GetMapping(path = "/rendez_vous")
    public String test(Model model,
                       @RequestParam(name="motCle",defaultValue="") String mc,
                       @RequestParam(name="page",defaultValue="0") int page,
                       @RequestParam(name="size",defaultValue="5") int size)
    {
        Page<RendezVous> rendez_voussPage = rendez_vousRepository.findAll( PageRequest.of(page,size));
        List<RendezVous> rendez_vouss = rendez_voussPage.getContent();
        model.addAttribute("listeRendezVouss", rendez_vouss);
        model.addAttribute("motCle", mc);
        model.addAttribute("nbrPage",rendez_voussPage.getTotalPages());
        model.addAttribute("pages",new int[rendez_voussPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        return "rendez_vous";
    }
    @GetMapping(path="/addRendezVous")
    public String addRendezVous(Model model){
        model.addAttribute("rendez_vous",new RendezVous());
        return  "formRendezVous";
    }
    @PostMapping("saveRendezVous")
    public String saveRendezVous(@ModelAttribute("rendez_vous") @Valid RendezVous rendez_vous, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "formRendezVous";
        rendez_vousRepository.save(rendez_vous);
        System.out.println(rendez_vous);
        return "redirect:/rendez_vous";
    }
    @GetMapping(path="/updateRendezVous")
    public String updateRendezVous(@RequestParam(name = "id") Long id, Model model){
        Optional<RendezVous> rendez_vous = rendez_vousRepository.findById(id);
        if (rendez_vous.isEmpty())
            return "redirect:/rendez_vous";
        model.addAttribute("rendez_vous", rendez_vous.get());
        return  "update";
    }
//    @PostMapping("/updateRendezVous")
//    public String updateRendezVousForm(@RequestParam(name = "id") Long id, @ModelAttribute("rendez_vous") @Valid RendezVous rendez_vous, BindingResult bindingResult){
//        if (bindingResult.hasErrors())
//            return "updateRendezVous";
//        Optional<RendezVous> oRendezVous = rendez_vousRepository.findById(id);
//        if (oRendezVous.isEmpty())
//            return "redirect:/rendez_vous";
//        RendezVous p = oRendezVous.get();
//        p.setNom(rendez_vous.getNom());
//        p.setSpecialite(rendez_vous.getSpecialite());
//        p.setRendezVousSet(rendez_vous.getRendezVousSet());
//        rendez_vousRepository.save(p);
//        return "redirect:/rendez_vous";
//    }
    @GetMapping(path="/deleteRendezVous")
    public String delete(Long id,String motCle,int page,int size) {
        rendez_vousRepository.deleteById(id);
        return "redirect:/rendez_vous?page="+page+"&motCle="+motCle+"&size="+size;
    }
}

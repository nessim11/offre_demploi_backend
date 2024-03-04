package com.site.SDE.Service;

import com.site.SDE.Entite.Formateur;
import com.site.SDE.Entite.Offre;
import com.site.SDE.Entite.Postulation;
import com.site.SDE.Entite.PostulationRQ;
import com.site.SDE.Repository.FormateurRepository;
import com.site.SDE.Repository.OffreRepository;
import com.site.SDE.Repository.PostulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostulationServiceImpl implements PostulationService {

    @Autowired
    PostulationRepository postulationRepository;
    @Autowired
    FormateurRepository formateurService;
    @Autowired
    OffreRepository offreRepository;
    @Override
   public Postulation ajouterPostulation(PostulationRQ postulation) {
        Optional<Formateur>forma=formateurService.findById(postulation.getIdFormateur());
        Optional<Offre>off=offreRepository.findById(postulation.getIdOffre());
        if(forma.isPresent() && off.isPresent())
        {
           if(!this.postulationRepository.existsByFormateurIdAndOffreId(postulation.getIdFormateur(),postulation.getIdOffre()))
               return postulationRepository.save(new Postulation(off.get(),forma.get(),postulation.getCv()));
           else return null;
        }
        else
           return null;
    }

    @Override
    public List<Postulation> listPostulationByFormateurId(long id) {
        return postulationRepository.findByFormateurId(id);
    }

    @Override
    public List<Postulation> listPostulationByEntrepriseId(long id) {
        return postulationRepository.findByOffreId(id);
    }
}

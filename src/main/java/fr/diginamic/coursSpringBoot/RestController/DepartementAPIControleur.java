package fr.diginamic.coursSpringBoot.RestController;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.dto.DepartementDto;
import fr.diginamic.coursSpringBoot.dto.VilleDto;
import fr.diginamic.coursSpringBoot.mapper.DepartementMapper;
import fr.diginamic.coursSpringBoot.mapper.VilleMapper;
import fr.diginamic.coursSpringBoot.service.DepartementServices;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementAPIControleur {

    @Autowired
    public DepartementServices departementServices;

    private DepartementMapper departementMapper = new DepartementMapper();
    private VilleMapper villeMapper = new VilleMapper();

    @Autowired
    private VilleServices villeServices;

    @GetMapping()
    public List<Departement> afficherDepartements() {
        return departementServices.afficherDepartements();
    }

    @GetMapping("/code/{code}")
    public DepartementDto getDepartement(@PathVariable String code) {
        return departementMapper.toDto(departementServices.getDepartement(code));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> ajoutDepartment(@RequestBody DepartementDto departementDto) {
        Departement departement = departementMapper.fromDto(departementDto);
        Departement resultat = departementServices.getDepartement(departement.getCode());
        if (resultat == null) {
            departementServices.insertDepartement(departement);
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Impossible de créer le département", HttpStatus.BAD_REQUEST);
        }
    }

    
    @PutMapping
    @Transactional
    public ResponseEntity<String> modifierDepartement(@Valid @RequestBody Departement departement, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());

        }
        departementServices.modifierDepartement(departement);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity<String> supprimerDepartement(@Valid @RequestBody Long id, BindingResult result) {
        departementServices.supprimerDepartement(id);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/infoDepartement/{code}")
    public void infoDepartement(@PathVariable("code") String code,
                                                  HttpServletResponse response) throws IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=infodpt.pdf");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        document.addTitle("Information détaillée sur un département");
        document.newPage();
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
        Departement departement=  departementServices.getDepartement(code);
        String codeRegion = departementServices.getRegion(code);
        DepartementDto departementDto=  departementMapper.toDto(departement);
        List <VilleDto> villeDtos = villeMapper.toDtos(villeServices.extractVilles(departement));
        Font font = new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80));
        document.add(new Phrase("Information sur le département"+"\n\n" , font));
        document.add(new Phrase("Code du département : "+ departementDto.getDepartementCode() + " Nom du département : "+ departementDto.getDepartementNom()+"\n"));
        document.add(new Phrase("Nombre d'habitant : "+ departementDto.getNbHabitants() + " Code région : "+ codeRegion+"\n\n\n"));
        for (VilleDto t :villeDtos) {
            Phrase phrase = new Phrase("Ville de "+ t.getNom() + " : " + t.getNbHabitant() + " habitants \n");
            document.add(phrase);
        }
        document.close();
        response.flushBuffer();
    }
}

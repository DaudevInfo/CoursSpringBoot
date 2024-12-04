package fr.diginamic.coursSpringBoot.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.coursSpringBoot.bo.Departement;
import fr.diginamic.coursSpringBoot.bo.Ville;
import fr.diginamic.coursSpringBoot.dto.VilleDto;
import fr.diginamic.coursSpringBoot.mapper.VilleMapper;
import fr.diginamic.coursSpringBoot.service.DepartementServices;
import fr.diginamic.coursSpringBoot.service.VilleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleAPIControleur {


    @Autowired
    public VilleServices villeServices;

    @Autowired
    private DepartementServices departementServices;

    private VilleMapper villeMapper = new VilleMapper();

    @GetMapping("/API")
    public Iterable<VilleDto> APIafficherVilles() {
        return villeMapper.toDtos(villeServices.extractVilles());
    }

    @GetMapping(path = "/API/id/{id}")
    public VilleDto APIafficherVille(@PathVariable Long id) {
        return villeMapper.toDto(villeServices.extractVille(id));
    }

    @GetMapping(path = "/API/nom/{nom}")
    public VilleDto APIafficherVille(@PathVariable String nom) {
        Ville ville = villeServices.extractVille(nom);
        return villeMapper.toDto(ville);
    }

    @GetMapping(path = "/API/debute/{nom}")
    public List<VilleDto> APIlisteVilleCommencePar(@PathVariable String nom) {
        return villeMapper.toDtos(villeServices.listeVilleCommencePar(nom));
    }

    @GetMapping(path = "/API/popmin/{nbrMin}")
    public List<VilleDto> APIlisteVillePopulationSuperieurA(@PathVariable int nbrMin) {
        return villeMapper.toDtos(villeServices.listeVillePopulationSuperieurA(nbrMin));
    }

    @GetMapping(path = "/API/popmin/{nbrMin}/popmax/{nbrMax}")
    public List<VilleDto> APIlisteVillePopulationEntre(@PathVariable int nbrMin, int nbrMax) {
        return villeMapper.toDtos(villeServices.listeVillePopulationEntre(nbrMin, nbrMax));
    }

    @GetMapping(path = "/API/dpt/{dpt}/popmin/{nbrMin}")
    public List<VilleDto> APIlisteVillePopulationSuperieurPourUnDepartement(@PathVariable String dpt, int nbrMin) {
        return villeMapper.toDtos(villeServices.listeVillePopulationSuperieurPourUnDepartement(nbrMin, dpt));
    }

    @GetMapping(path = "/API/dpt/{dpt}/popmin/{nbrMin}/popmax/{nbrMax}")
    public List<VilleDto> APIlisteVillePopulationEntrePourUnDepartement(@PathVariable String dpt, int nbrMin, int nbrMax) {
        return villeMapper.toDtos(villeServices.listeVillePopulationEntrePourUnDepartement(nbrMin, nbrMax, dpt));
    }

    @Operation(summary = "Création d'une nouvelle ville")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ajoute une nouvelle ville",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VilleDto.class))}),
            @ApiResponse(responseCode = "400", description = "Si une règle métier n'est pas respectée.",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<String> APIinsertVille(@RequestBody VilleDto villeDto) {
        Ville ville = villeMapper.fromDto(villeDto);
        Departement departement = departementServices.getDepartement(villeDto.getDepartementCode());
        if (departement == null) {
            return new ResponseEntity<String>("Impossible de créer la ville, pas de département " + villeDto.getDepartementCode(), HttpStatus.BAD_REQUEST);
        }
        ville.setDepartement(departement);
        if (villeServices.insertVille(ville)) {
            return new ResponseEntity<String>("Succès !", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Impossible de créer la ville, elle existe déjà ou il manque le département : " + ville.toString(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping
    public ResponseEntity<String> APImodifierVille(@Valid @RequestBody Ville ville, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());

        }
        villeServices.modifierVille(ville);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    public ResponseEntity<String> APIsupprimerVille(@Valid @RequestBody Long id, BindingResult result) {
        villeServices.supprimerVille(id);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/API/listeVillePopulationSuperieurA/{min}/csv")
    public void APIlisteVillePopulationSuperieurACsv(@PathVariable Integer min,
                                                    HttpServletResponse response) throws IOException, DocumentException {
        response.setHeader("Content-Disposition", "attachment; filename=ville.csv");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        document.newPage();
        document.add(new Phrase("Nom de la Ville, Habitants, Code department , Nom du department"));
        List<VilleDto> villeDtos =  villeMapper.toDtos(villeServices.listeVillePopulationSuperieurA(min));

        for (VilleDto t :villeDtos) {
            Phrase phrase = new Phrase(t.getNom() + "," + t.getNbHabitant() + "," + t.getDepartementCode() + "," + t.getDepartementNom());
            document.add(phrase);
        }

        document.close();
        response.flushBuffer();
    }

}

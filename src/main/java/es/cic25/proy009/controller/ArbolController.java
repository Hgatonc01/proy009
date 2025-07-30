package es.cic25.proy009.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic25.proy009.model.Arbol;
import es.cic25.proy009.service.ArbolService;

@RestController
@RequestMapping("/arbol")
public class ArbolController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArbolController.class);

    @Autowired
    private ArbolService arbolService;

    @GetMapping
    public List<Arbol> getAll() {
        LOGGER.info("Consiguiendo lista de todos los árboles");
        return arbolService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Arbol> getArbol(@PathVariable Long id) {
        LOGGER.info(String.format("Buscando el árbol con id %d", id));
        return arbolService.getArbol(id);
    }

    @PostMapping
    public Arbol createArbol(@RequestBody Arbol arbol) {
        LOGGER.info("Creando un árbol");
        if(arbol.getId() != null){
            throw new ModificacionSecurityException();
        }
        return arbolService.createArbol(arbol);
    }

    @PutMapping
    public Arbol updateArbol(@RequestBody Arbol arbol) {
        LOGGER.info(String.format("actualizando el árbol con id %d", arbol.getId()));
        if(arbol.getId() == null){
            throw new CreacionSecurityException();
        }
        return arbolService.createArbol(arbol);
    }

    @DeleteMapping("/{id}")
    public void deleteArbol(@PathVariable Long id) {
        LOGGER.info(String.format("Borrando el árbol con id %d", id));
        arbolService.deleteArbol(id);
    }
}
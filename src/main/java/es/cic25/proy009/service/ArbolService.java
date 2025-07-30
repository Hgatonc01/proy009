package es.cic25.proy009.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic25.proy009.model.Arbol;
import es.cic25.proy009.repository.ArbolRepository;

@Service
@Transactional
public class ArbolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArbolService.class);

    @Autowired
    private ArbolRepository arbolRepository;

    @Transactional(readOnly = true)
    public Optional<Arbol> getArbol(Long id) {
        LOGGER.info(String.format("Buscando el árbol con id %d", id));
        return arbolRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Arbol> getAll() {
        LOGGER.info("Consiguiendo lista de todos los árboles");
        return arbolRepository.findAll();
    }

    public Arbol createArbol(Arbol arbol) {
        LOGGER.info("Creando un árbol");
        return arbolRepository.save(arbol);
    }

    public Arbol updateArbol(Arbol arbol) {
        LOGGER.info(String.format("actualizando el árbol con id %d", arbol.getId()));
        return arbolRepository.save(arbol);
    }

    public void deleteArbol(Long id) {
        LOGGER.info(String.format("Borrando el árbol con id %d", id));
        arbolRepository.deleteById(id);
    }
}
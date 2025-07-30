package es.cic25.proy009.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic25.proy009.model.Arbol;
import es.cic25.proy009.model.Rama;
import es.cic25.proy009.repository.ArbolRepository;
import es.cic25.proy009.repository.RamaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ArbolControllerIntegrationTest {

    @Autowired
    private ArbolRepository arbolRepository;

    @Autowired
    private RamaRepository ramaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateArbol() throws Exception {

        // Creamos un arbol
        Arbol arbol = new Arbol();
        arbol.setColor("Verde");
        arbol.setEdad(57);
        arbol.setEspecie("Manzano");
        arbol.setFruto("Manzana");
        arbol.setPerenne(false);
        arbol.setPeso(50.7);

        // creamos las ramas y las añadimos a la lista
        Rama rama1 = new Rama();
        rama1.setArbol(arbol);
        rama1.setGrosor(5);
        rama1.setHojaCompuesta(false);
        rama1.setLongitud(20);
        rama1.setNumHojas(7);

        Rama rama2 = new Rama();
        rama2.setArbol(arbol);
        rama2.setGrosor(7);
        rama2.setHojaCompuesta(false);
        rama2.setLongitud(15);
        rama2.setNumHojas(9);

        arbol.getRamas().add(rama1);
        arbol.getRamas().add(rama2);

        // Convertimos el objeto de tipo arbol en json con ObjectMapper
        String arbolJson = objectMapper.writeValueAsString(arbol);

        // Con MockMvc simulamos la petición HTTP para crear un arbol
        MvcResult result = mockMvc.perform(post("/arbol")
                .contentType("application/json")
                .content(arbolJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Arbol arbolGenerado = objectMapper.readValue(result.getResponse().getContentAsString(), Arbol.class);

        assertTrue(arbolGenerado.getRamas().size() >= 2);

    }

    @Test
    void testDeleteArbol() throws Exception {

        // Creamos un arbol
        Arbol arbol = new Arbol();
        arbol.setColor("Verde");
        arbol.setEdad(57);
        arbol.setEspecie("Manzano");
        arbol.setFruto("Manzana");
        arbol.setPerenne(false);
        arbol.setPeso(50.7);

        // creamos las ramas y las añadimos a la lista
        Rama rama1 = new Rama();
        rama1.setArbol(arbol);
        rama1.setGrosor(5);
        rama1.setHojaCompuesta(false);
        rama1.setLongitud(20);
        rama1.setNumHojas(7);

        Rama rama2 = new Rama();
        rama2.setArbol(arbol);
        rama2.setGrosor(7);
        rama2.setHojaCompuesta(false);
        rama2.setLongitud(15);
        rama2.setNumHojas(9);

        arbol.getRamas().add(rama1);
        arbol.getRamas().add(rama2);

        Long idGenerado = arbolRepository.save(arbol).getId();

        //borramos el arbol con el id que se ha generado
        mockMvc.perform(delete("/arbol/" + idGenerado))
        .andDo(print())
        .andExpect(status().isOk());

        //comprobamos tambien que no existe
        assertTrue(arbolRepository.findById(idGenerado).isEmpty());
        //comprobamos si se han borrado las ramas
        assertTrue(ramaRepository.findAll().size() == 0);

    }

    @Test
    void testGetAll() throws Exception {

    }

    @Test
    void testGetArbol() throws Exception {

        // Creamos un arbol
        Arbol arbol = new Arbol();
        arbol.setColor("Verde");
        arbol.setEdad(57);
        arbol.setEspecie("Manzano");
        arbol.setFruto("Manzana");
        arbol.setPerenne(false);
        arbol.setPeso(50.7);

        // creamos las ramas y las añadimos a la lista
        Rama rama1 = new Rama();
        rama1.setArbol(arbol);
        rama1.setGrosor(5);
        rama1.setHojaCompuesta(false);
        rama1.setLongitud(20);
        rama1.setNumHojas(7);

        Rama rama2 = new Rama();
        rama2.setArbol(arbol);
        rama2.setGrosor(7);
        rama2.setHojaCompuesta(false);
        rama2.setLongitud(15);
        rama2.setNumHojas(9);

        arbol.getRamas().add(rama1);
        arbol.getRamas().add(rama2);

        Long idGenerado = arbolRepository.save(arbol).getId();

        mockMvc.perform(get("/arbol/" + idGenerado))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idGenerado))
                .andExpect(jsonPath("$.color").value(arbol.getColor()));

    }

    @Test
    void testUpdateArbol() throws Exception {

    }
}

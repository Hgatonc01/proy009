package es.cic25.proy009.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic25.proy009.model.Arbol;
import es.cic25.proy009.model.Rama;

@SpringBootTest
@AutoConfigureMockMvc
public class ArbolControllerIntegrationTest {

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

        // Creamos una lista para las ramas
        List<Rama> ramas = new ArrayList<>();

        // creamos las ramas y las añadimos a la lista
        Rama rama1 = new Rama();
        rama1.setArbol(arbol);
        rama1.setGrosor(5);
        rama1.setHojaCompuesta(false);
        rama1.setLongitud(20);
        rama1.setNumHojas(7);
        ramas.add(rama1);

        Rama rama2 = new Rama();
        rama2.setArbol(arbol);
        rama2.setGrosor(7);
        rama2.setHojaCompuesta(false);
        rama2.setLongitud(15);
        rama2.setNumHojas(9);
        ramas.add(rama2);

        // Asignamos la lista de ramas al arbol
        arbol.setRamas(ramas);

        // Convertimos el objeto de tipo arbol en json con ObjectMapper
        String arbolJson = objectMapper.writeValueAsString(arbol);

        // Con MockMvc simulamos la petición HTTP para crear un arbol
        mockMvc.perform(post("/arbol")
        .contentType("application/json")
        .content(arbolJson))
        .andExpect(status().isOk())
        .andExpect( arbolResult ->{
            assertTrue(
                objectMapper.readValue(
                    arbolResult.getResponse().getContentAsString(), Arbol.class).getId()
                    > 0, "Si el id es 0 sgnifica que no se ha creado el arbol");
        });

    }

    @Test
    void testDeleteArbol() throws Exception {

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

        // Creamos una lista para las ramas
        List<Rama> ramas = new ArrayList<>();

        // creamos las ramas y las añadimos a la lista
        Rama rama1 = new Rama();
        rama1.setArbol(arbol);
        rama1.setGrosor(5);
        rama1.setHojaCompuesta(false);
        rama1.setLongitud(20);
        rama1.setNumHojas(7);
        ramas.add(rama1);

        Rama rama2 = new Rama();
        rama2.setArbol(arbol);
        rama2.setGrosor(7);
        rama2.setHojaCompuesta(false);
        rama2.setLongitud(15);
        rama2.setNumHojas(9);
        ramas.add(rama2);

        // Asignamos la lista de ramas al arbol
        arbol.setRamas(ramas);

        // Convertimos el objeto de tipo arbol en json con ObjectMapper
        String arbolJson = objectMapper.writeValueAsString(arbol);

        MvcResult mvcResult = mockMvc.perform(post("/arbol")
                .contentType("application/json")
                .content(arbolJson))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Arbol.class).getId();

        mockMvc.perform(get("/arbol/" + id))
                .andDo(print())// Imprime los valores en consola
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(objectMapper.readValue(result.getResponse().getContentAsString(), Arbol.class).getId(),
                            id);
                });

    }

    @Test
    void testUpdateArbol() throws Exception {

    }
}

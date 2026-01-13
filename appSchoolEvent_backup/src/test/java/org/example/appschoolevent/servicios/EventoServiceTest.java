package org.example.appschoolevent.servicios;


import jakarta.transaction.Transactional;
import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.enums.TipoCategoria;
import org.example.appschoolevent.exceptions.ElementosNoEncontrados;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.repositorio.EventoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class EventoServiceTest {

    @Autowired
    private EventoService servicio;

    @Autowired
    private EventoRepository repositorio;

    @BeforeEach
    void cargarDatos(){
        repositorio.deleteAll();

        Evento u = new Evento();
        u.setNombre("admin");
        u.setRequisitos("NINGUNO");
        u.setConsiste("Comida");
        u.setInscripciones(new HashSet<>());
        u.setFecha(LocalDate.now());
        u.setCategoria(TipoCategoria.Deportivo);
        u.setLugar("Casa");

        Evento u2 = new Evento();
        u2.setNombre("admin");
        u2.setRequisitos("NINGUNO");
        u2.setConsiste("Comida");
        u2.setInscripciones(new HashSet<>());
        u2.setFecha(LocalDate.now());
        u2.setCategoria(TipoCategoria.Deportivo);
        u2.setLugar("Casa");

        repositorio.save(u);
        repositorio.save(u2);

    }

    @Test
    @DisplayName("Servicio 1 -> Caso Positivo")
    public void obtenerEventoPorId(){

        //Given (Previo al test)



        //Then (Ejecución de la prueba)
        EventoDTO dto = servicio.obtenerEventoPorId(1);


        //When (Comprobaciones)
        assertNotNull(dto, "El evento no puede ser encontrado");
    }


    @Test
    @DisplayName("Servicio 1 -> Caso Negativo")
    public void obtenerEventoPorIdNegativo(){

        //Given (Previo al test)

        //Then (Ejecución de la prueba)


        //When (Comprobaciones)
        assertThrows(ElementosNoEncontrados.class,()-> servicio.obtenerEventoPorId(5));

    }

}

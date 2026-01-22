package org.example.appschoolevent.servicios;


import jakarta.transaction.Transactional;
import org.example.appschoolevent.DTO.EventoDTO;
import org.example.appschoolevent.enums.TipoCategoria;
import org.example.appschoolevent.exceptions.ElementosNoEncontrados;
import org.example.appschoolevent.modelo.Evento;
import org.example.appschoolevent.modelo.Inscripcion;
import org.example.appschoolevent.modelo.Usuario;
import org.example.appschoolevent.repositorio.EventoRepository;
import org.example.appschoolevent.repositorio.InscripcionRepository;
import org.example.appschoolevent.repositorio.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class EventoServiceTest {

    @Autowired
    private EventoService servicio;

    @Autowired
    private EventoRepository repositorio;

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private InscripcionRepository inscripcionRepositorio;

    private Usuario usuarioPrincipal;
    private Evento eventoPrincipal;

    @BeforeEach
    void cargarDatos(){
        inscripcionRepositorio.deleteAll();
        repositorio.deleteAll();
        usuarioRepositorio.deleteAll();

        // Usuario
        usuarioPrincipal = new Usuario();
        usuarioPrincipal.setNombre("Luis");
        usuarioPrincipal.setApellido("Cabello");
        usuarioPrincipal.setContrasena("pelones");
        usuarioPrincipal.setFecha_de_nacimiento(LocalDate.of(2006,12,13));
        usuarioPrincipal.setCorreo("lcabellocabanillas@safareyes.es");
        usuarioPrincipal.setTipo(Usuario.TipoUsuario.User);
        usuarioRepositorio.save(usuarioPrincipal);

        // Evento
        eventoPrincipal = new Evento();
        eventoPrincipal.setNombre("Evento Test");
        eventoPrincipal.setRequisitos("Ninguno");
        eventoPrincipal.setConsiste("Prueba");
        eventoPrincipal.setFecha(LocalDate.now());
        eventoPrincipal.setCategoria(TipoCategoria.Deportivo);
        eventoPrincipal.setLugar("Aula 1");
        eventoPrincipal.setInscripciones(new HashSet<>());
        repositorio.save(eventoPrincipal);

        // Inscripción inicial
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuarioPrincipal);
        inscripcion.setEvento(eventoPrincipal);
        inscripcionRepositorio.save(inscripcion);
    }

    @Test
    @DisplayName("Servicio 2 -> Caso Positivo")
    void crearEvento(){

        //Given (Previo al test)
        EventoDTO dto = new EventoDTO();
        dto.setNombre("Evento Prueba");
        dto.setRequisitos("Ninguno");
        dto.setConsiste("Charla Motivacional");
        dto.setFecha(LocalDate.now().toString());
        dto.setCategoria(TipoCategoria.Cultural.toString());
        dto.setLugar("Auditorio Principal");


        //Then (Ejecución de la prueba)
        EventoDTO resultado = servicio.crearEvento(dto);


        //When (Comprobaciones)
        assertNotNull(resultado);
        assertEquals("Evento Prueba", resultado.getNombre());

    }


    @Test
    @DisplayName("Servicio 2 -> Caso Negativo")
    void crearEventoNegativo(){

        //Given (Previo al test)

        //Then (Ejecución de la prueba)
        EventoDTO dto = new EventoDTO();

        //When (Comprobaciones)
        assertThrows(RuntimeException.class, () -> servicio.crearEvento(dto) );

    }

    @Test
    @DisplayName("Servicio 3 -> Caso Positivo")
    void filtarEventos(){

        EventoDTO dto = servicio.obtenerEventoPorId(eventoPrincipal.getId());
        assertNotNull(dto);
        assertEquals(eventoPrincipal.getNombre(), dto.getNombre());

    }

    @Test
    @DisplayName("Servicio 3 -> Caso Negativo")
    void filtarEventosNegativo(){

        assertThrows(ElementosNoEncontrados.class, () -> servicio.filtarEventos(LocalDate.of(2000,1,1), TipoCategoria.Cultural) );

    }

    @Test
    @DisplayName("Servicio 4 -> Caso Positivo")
    void detallesEventos(){

    }

    @Test
    @DisplayName("Servicio 4 -> Caso Negativo")
    void detallesEventosNegativo(){

    }

    @Test
    @DisplayName("Servicio 5 -> Caso Positivo")
    void actualizarEvento(){

    }

    @Test
    @DisplayName("Servicio 5 -> Caso Negativo")
    void actualizarEventoNegativo(){

    }

    @Test
    @DisplayName("Servicio 6 -> Caso Positivo")
    void InscribirUsuario(){

    }

    @Test
    @DisplayName("Servicio 6 -> Caso Negativo")
    void InscribirUsuarioNegativo(){

    }

    @Test
    @DisplayName("Servicio 7 -> Caso Positivo")
    void subirFoto(){

    }

    @Test
    @DisplayName("Servicio 7 -> Caso Negativo")
    void subirFotoNegativo(){

    }
}

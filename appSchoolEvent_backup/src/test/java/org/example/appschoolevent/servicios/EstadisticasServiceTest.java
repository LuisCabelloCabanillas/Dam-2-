package org.example.appschoolevent.servicios;

import jakarta.transaction.Transactional;
import org.example.appschoolevent.enums.TipoCategoria;
import org.example.appschoolevent.modelo.Evento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootTest
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class EstadisticasServiceTest {


//    @Test
//    @DisplayName("Servicio 9 -> Caso Negativo")
//    void top5Eventos(){
//        for (int i = 1; i <= 5; i++) {
//            Evento dto = new Evento();
//            dto.setNombre("Evento" + 1);
//            dto.setRequisitos("Ninguno");
//            dto.setConsiste("Charla Motivacional");
//            dto.setFecha(LocalDate.parse(LocalDate.now().toString()));
//            dto.setCategoria(TipoCategoria.valueOf("Deportivo"));
//            dto.setLugar("Clase" + i);
//            dto.setInscripciones(new HashSet<>());
//            repositorio.save(dto);
//        }
//
//        var top = servicio.
//
//    }
//
//    @Test
//    @DisplayName("Servicio 9 -> Caso Negativo")
//    void top5EventosNegativo(){
//
//    }
}

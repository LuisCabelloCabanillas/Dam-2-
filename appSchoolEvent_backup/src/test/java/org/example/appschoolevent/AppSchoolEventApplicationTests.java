package org.example.appschoolevent;

import org.example.appschoolevent.modelo.Tienda;
import org.example.appschoolevent.repositorio.ITiendaRepository;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AppSchoolEventApplicationTests {

    @Autowired
    private ITiendaRepository tiendaRepository;

    @Test
    void consultarTienda() {

        List<Tienda> tiendas = tiendaRepository.findAll();

        for (Tienda t : tiendas) {

            System.out.println(t.getNombre());
        }

    }

}

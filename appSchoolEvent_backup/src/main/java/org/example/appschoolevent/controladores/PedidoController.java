package org.example.appschoolevent.controladores;


import lombok.AllArgsConstructor;
import org.example.appschoolevent.modelo.Pedido;
import org.example.appschoolevent.servicios.PedidoServicios;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor

public class PedidoController {

    private PedidoServicios Servicios;


    @GetMapping("/all")
    public List<Pedido> buscarTodos(){
        return Servicios.obtenerTodosPedidos();
    }
}

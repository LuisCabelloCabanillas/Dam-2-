package org.example.appschoolevent.servicios;


import lombok.AllArgsConstructor;
import org.example.appschoolevent.DTO.PedidoDTO;
import org.example.appschoolevent.DTO.ProductoDTO;
import org.example.appschoolevent.modelo.LineaPedido;
import org.example.appschoolevent.modelo.Pedido;
import org.example.appschoolevent.repositorio.IPedidoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class PedidoServicios {



    private IPedidoRepositorio pedidoRepositorio;


    public List<Pedido> obtenerTodosPedidos()
    {
        List<Pedido> pedidos = pedidoRepositorio.findAll();
        List<PedidoODT> dtos = new ArrayList<>();

        for (Pedido pedido : pedidos) {

            PedidoDTO dto = new PedidoDTO();
            dto.setCodigoPedido(pedido.getId());
            dto.setFechaPedido(pedido.getFecha().toString());


            List<PedidoDTO> productos = new ArrayList<>();

            for (LineaPedido I: pedido.getLineas()){
                ProductoDTO pDTO = new ProductoDTO();

                pDTO.setNombre(I.getProducto().getNombre());
                productos.add(pDTO);
            }

            dto.setProductos(productos);

            dtos.add(dto);
        }
        return dtos;
    }
}

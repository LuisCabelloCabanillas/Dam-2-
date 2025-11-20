package org.example.appschoolevent.DTO;

import lombok.Data;
import org.example.appschoolevent.modelo.Pedido;

import java.util.List;

@Data
public class PedidoDTO {
    private String codigoPedido;
    private String fechaPedido;
    private List<ProductoDTO> productos;
}

package br.api.notebook.controller;

import br.api.notebook.dto.OrderDTO;
import br.api.notebook.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/order")
    public List<OrderDTO> getOrder(){
        return orderService.getOrder();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/order/save")
    public ResponseEntity<String> saveOrder(){
        return ResponseEntity.ok().body(orderService.saveOrder());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Pedido cancelado.");
    }
}

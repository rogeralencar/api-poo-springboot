package br.api.notebook.service;

import br.api.notebook.dto.OrderDTO;
import br.api.notebook.model.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrder();
    String saveOrder();
    void deleteOrder(Long id);
}

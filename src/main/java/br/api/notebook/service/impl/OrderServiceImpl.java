package br.api.notebook.service.impl;

import br.api.notebook.dto.OrderDTO;
import br.api.notebook.model.OrderEntity;
import br.api.notebook.model.OrderEntityBuilder;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.OrderRepository;
import br.api.notebook.service.AddressService;
import br.api.notebook.service.CartService;
import br.api.notebook.service.OrderService;
import br.api.notebook.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final AddressService addressService;
    private final UserService userService;
    private final CartService cartService;

    public OrderServiceImpl(OrderRepository orderRepo, AddressService addressService,
                            UserService userService, CartService cartService) {
        this.orderRepo = orderRepo;
        this.addressService = addressService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public List<OrderDTO> getOrder() {
        return orderRepo.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String saveOrder() {
        UserEntity user = userService.findByEmail();
        OrderEntityBuilder builder = new OrderEntityBuilder();
        if(user.getAddress() == null){
            return "Você não pode confirmar o pedido sem ter adicionado um endereço para entrega.";
        } else if(user.getPaymentMethod() == null){
            return "Você não pode confirmar o pedido sem ter selecionado um método de pagamento.";
        }
        OrderEntity order = builder.withUser(user)
                        .withAddress(user.getAddress())
                        .withPayment(user.getPaymentMethod())
                        .build();
        orderRepo.save(order);
        return "Pedido confirmado com sucesso.";
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    private OrderDTO convertEntityToDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        UserEntity user = userService.findByEmail();
        orderDTO.setUser(userService.getUserDto(user));
        orderDTO.setAddress(addressService.getAddressDto(orderEntity.getAddress()));
        orderDTO.setCart(cartService.listItems(user));
        orderDTO.setPay(user.getPaymentMethod());
        return orderDTO;
    }
}

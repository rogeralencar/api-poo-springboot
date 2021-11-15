package br.api.notebook.service.impl;

import br.api.notebook.dto.CartItemDTO;
import br.api.notebook.model.PayWithBankSlipEntity;
import br.api.notebook.model.PayWithPixEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.PaymentRepository;
import br.api.notebook.service.CartService;
import br.api.notebook.service.PaymentService;
import br.api.notebook.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository payRepo;
    private final UserService userService;
    private final CartService cartService;

    public PaymentServiceImpl(PaymentRepository payRepo, UserService userService, CartService cartService) {
        this.payRepo = payRepo;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public String savePaymentPix() {
        PayWithPixEntity payment = new PayWithPixEntity();
        UserEntity user = userService.findByEmail();
        payment.addKey();
        user.setPaymentMethod(null);
        user.setPaymentMethod(payment);
        payRepo.save(payment);
        CartItemDTO cart = cartService.listItems(user);
        StringBuffer msg = new StringBuffer();
        if (cart.getTotalCost() == 0) {
            return msg.append("Não há itens no seu carrinho.").toString();
        }
        cart.getCartItems().forEach(item -> {
            if (item.getQuantity() > 1) {
                msg.append("- Você adicionou ").append(item.getQuantity()).append(" notebooks ao carrinho.\n");
            } else {
                msg.append("- Você adicionou ").append(item.getQuantity()).append(" notebook ao carrinho.\n");
            }
        });
        msg.append("- A chave PIX para o pagamento é: ").append(payment.getKeyPix()).append(".\n");
        msg.append("- O total a ser pago é:  R$").append(cart.getTotalCost());
        return msg.toString();
    }

    @Override
    public String savePaymentBankSlip() {
        PayWithBankSlipEntity payment = new PayWithBankSlipEntity();
        UserEntity user = userService.findByEmail();
        payment.addKey();
        user.setPaymentMethod(null);
        user.setPaymentMethod(payment);
        payRepo.save(payment);
        CartItemDTO cart = cartService.listItems(user);
        StringBuffer msg = new StringBuffer();
        if (cart.getTotalCost() == 0) {
            return msg.append("Não há itens no seu carrinho.").toString();
        }
        cart.getCartItems().forEach(item -> {
            if (item.getQuantity() > 1) {
                msg.append("- Você adicionou ").append(item.getQuantity()).append(" notebooks ao carrinho.\n");
            } else {
                msg.append("- Você adicionou ").append(item.getQuantity()).append(" notebook ao carrinho.\n");
            }
        });
        msg.append("- A linha digitável para o pagamento é: ").append(payment.getBankSlipCode()).append(".\n");
        msg.append("- Data de vencimento do boleto: ").append(payment.getDate()).append(".\n");
        msg.append("- O total a ser pago é:  R$").append(cart.getTotalCost());
        return msg.toString();
    }
}

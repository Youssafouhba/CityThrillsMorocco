package com.CityThrillsMorocco.payment.controller;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.Cart.Service.CartService;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.email.model.EmailDetails;
import com.CityThrillsMorocco.email.service.EmailSender;
import com.CityThrillsMorocco.order.model.Order;
import com.CityThrillsMorocco.order.service.OrderService;
import com.CityThrillsMorocco.payment.dto.PaymentRequestDto;
import com.CityThrillsMorocco.payment.model.Payment;
import com.CityThrillsMorocco.payment.service.PaymentService;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import com.CityThrillsMorocco.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequestMapping("/CityThrillsMorocco/api/payment")
public class PaymmentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ActivityService activityService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/charge/{userId}")
    public boolean chargeCard(@RequestBody PaymentRequestDto paymentRequest ,@PathVariable("userId") Long userId) throws Exception {

        Cart cart=cartService.getCartByUserId(userId);
        User user=mapper.map(userService.getUserById(userId),User.class);
        String token = paymentRequest.getToken();
        String firstName = paymentRequest.getFirstName();
        String lastName = paymentRequest.getLastName();
        String email = paymentRequest.getEmail();
        String phone = paymentRequest.getPhone();

        boolean paymentSuccess = this.paymentService.chargeCreditCard(cart.getTotal_amount(), token, firstName, lastName, email, phone);
        if(paymentSuccess){
            for(CartElement cartElement: cart.getCartElements()){
                activityService.decrementActivityCapacity(cartElement.getActivity(),cartElement.getNbrOfPerson());
            }
            Order order=orderService.createOrderFromCart(userId);
            Payment payment = new Payment();
            payment.setAmount(cart.getTotal_amount());
            payment.setFirstName(firstName);
            payment.setLastName(lastName);
            payment.setEmail(email);
            payment.setOrder(order);
            payment.setUser(user);
            payment.setCreationDate(new Date());
            this.paymentService.savePayment(payment);
            this.paymentService.sendOrderConfirmation(payment);
        }
        System.out.println(paymentSuccess);
        return paymentSuccess;
    }

    @GetMapping("/{userId}")
    public Payment getPayment(@PathVariable("userId") Long userId){
        return paymentService.getLastPaymentByUserId(userId);

    }

}
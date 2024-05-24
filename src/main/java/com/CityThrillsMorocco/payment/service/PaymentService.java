package com.CityThrillsMorocco.payment.service;

import com.CityThrillsMorocco.email.model.EmailDetails;
import com.CityThrillsMorocco.email.service.EmailSender;
import com.CityThrillsMorocco.orderElement.model.OrderElement;
import com.CityThrillsMorocco.payment.model.Payment;
import com.CityThrillsMorocco.payment.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class PaymentService {
    @Value("${stripe.apikey}")
    private String key;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmailSender emailService;

    public boolean chargeCreditCard(Double amount ,String token,String firstName,String lastName,String email,String phone) throws Exception {
        Stripe.apiKey=key;
        try {
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("name",firstName+" "+lastName);
            customerParams.put("email",email);
            customerParams.put("phone",phone);
            customerParams.put("source",token);
            Customer customer= Customer.create(customerParams);
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount",  (int) (amount * 100) );
            chargeParams.put("currency", "mad");
            chargeParams.put("customer", customer.getId());
            Charge charge = Charge.create(chargeParams);
            return true;
        }
        catch (CardException e) {
        String declineCode = e.getCode();
            System.out.println("Le paiement a été refusé. Code de déclinaison : " + declineCode);
            return false;
        }
        catch (InvalidRequestException e) {
            System.out.println("Le paiement a été refusé. Informations de carte incorrectes.");
            return false;
        }
        catch (Exception e) {
            System.out.println("Une erreur est survenue lors du traitement du paiement : " + e.getMessage());
            return false;
    }
    }


    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    public Payment getLastPaymentByUserId(Long userId) {
        List<Payment> payments = paymentRepository.findAllByUserIdOrderedByCreatiOrderByCreationDate(userId);
        if (!payments.isEmpty()) {
            return payments.get(0);
        }
        return null;
    }

    public void sendOrderConfirmation(Payment payment){
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setEmail(payment.getEmail());
        emailDetails.setFirstName(payment.getFirstName());
        emailDetails.setLastName(payment.getLastName());
        emailDetails.setTotal_amount(payment.getAmount());
        Map<String, Double> orderElements=new HashMap<String, Double>();
        for (OrderElement element: payment.getOrder().getOrderItems()) {
            orderElements.put(element.getActivity().getDesignation(),element.getSub_total());
        }
        emailDetails.setActivities(orderElements);
        this.emailService.sendHtmlEmail(emailDetails);

    }
}

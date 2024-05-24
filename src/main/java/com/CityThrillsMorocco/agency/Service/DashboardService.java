package com.CityThrillsMorocco.agency.Service;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.agency.Repository.AgenceRepository;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.cart_element.repository.CartElementRepository;
import com.CityThrillsMorocco.user.model.Admin;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    private final CartElementRepository cartElementRepository;
    private final ActivityRepo activityRepo;
    private final AgenceRepository agenceRepository;
    private final ModelMapper mapper;
    private final UserService userService;

    public DashboardService(CartElementRepository cartElementRepository, ActivityRepo activityRepo, AgenceRepository agenceRepository, ModelMapper mapper, UserService userService) {
        this.cartElementRepository = cartElementRepository;
        this.activityRepo = activityRepo;
        this.agenceRepository = agenceRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public Long AgencyClientsCount(Long id) {
        List<Activity> allAgenceActivities = AllAgenceActivities(id);
        System.out.println(allAgenceActivities.size());
        Long total = 0L;
        for (Activity activity : allAgenceActivities) {
            List<CartElement> cartElementList = cartElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (CartElement cartElement : cartElementList) {
                total += cartElement.getNbrOfPerson();
            }
        }
        return  total;
    }

    public double AgencyEarnings(Long id) {
        List<Activity> allAgenceActivities = AllAgenceActivities(id);
        System.out.println(allAgenceActivities.size());
        double total = 0L;
        for (Activity activity : allAgenceActivities) {
            List<CartElement> cartElementList = cartElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (CartElement cartElement : cartElementList) {
                total +=cartElement.getCart().getTotal_amount();
            }
        }
        return  total;
    }

    public double ActivityEarnings(Long id,Long activityId){
        List<Activity> allAgenceActivities = AllAgenceActivities(id);
        List<CartElement> cartElementList;
        double total = 0L;
        for (Activity activity : allAgenceActivities) {
            if (activityId==activity.getId()){
                cartElementList = cartElementRepository.getCartElementsByActivity_Id(activity.getId());
                for (CartElement cartElement : cartElementList) {
                    total +=cartElement.getCart().getTotal_amount();
                }
            }
        }
        return total;
    }

    public double ActivityClients(Long id,Long activityId){
        List<Activity> allAgenceActivities = AllAgenceActivities(id);
        List<CartElement> cartElementList;
        double total = 0L;
        for (Activity activity : allAgenceActivities) {
            if (activityId==activity.getId()){
                cartElementList = cartElementRepository.getCartElementsByActivity_Id(activity.getId());
                for (CartElement cartElement : cartElementList) {
                    total +=cartElement.getNbrOfPerson();
                }
            }
        }
        return total;
    }

    public List<Activity> AllAgenceActivities(Long id){
        return activityRepo.findActivitiesByAgence_Id(
                getAgenceByUser(id).getId()
        );
    }

    public Agence getAgenceByUser(Long id) {
        Admin admin = mapper.map(userService.getUserById(id), Admin.class);

        List<Agence> agences = agenceRepository.findByAdmin(admin);

        return agences.get(0);
    }
    /*
        public Map<Agence, AgenceService.AgenceInfo> getAgenceInfo() {
        Map<Agence, AgenceService.AgenceInfo> agenceInfoMap = new HashMap<>();

        List<Agence> agences = agenceRepository.findAll();

        for (Agence agence : agences) {
            int numClients = cartRepository.countByUser(agence.getAdmin());
            int numActivities = activityRepository.countByAgence(agence);
            double earnings = 0.0;

            List<Cart> carts = cartRepository.getCartsByUser(agence.getAdmin());
            for (Cart cart : carts) {
                earnings += paymentRepository.sumAmountByUser(cart.getUser());
            }

            AgenceService.AgenceInfo agenceInfo = new AgenceService.AgenceInfo(numClients, numActivities, earnings);
            agenceInfoMap.put(agence, agenceInfo);
        }

        return agenceInfoMap;
    }
    */

    public static class AgenceInfo {
        private Long numClients;
        private int numActivities;
        private double earnings;

        public AgenceInfo(Long numClients, int numActivities, double earnings) {
            this.numClients = numClients;
            this.numActivities = numActivities;
            this.earnings = earnings;
        }

        public Long getNumClients() {
            return numClients;
        }

        public int getNumActivities() {
            return numActivities;
        }

        public double getEarnings() {
            return earnings;
        }
    }

    public AgenceInfo getAgenceInfo(Long id) {
        return new AgenceInfo(AgencyClientsCount(id),AllAgenceActivities(id).size(), AgencyEarnings(id));
    }

}

package com.CityThrillsMorocco.agency.Service;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.agency.Repository.AgenceRepository;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.orderElement.model.OrderElement;
import com.CityThrillsMorocco.orderElement.repository.OrderElementRepository;
import com.CityThrillsMorocco.user.model.Admin;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final OrderElementRepository orderElementRepository;
    private final ActivityRepo activityRepo;
    private final AgenceRepository agenceRepository;
    private final ModelMapper mapper;
    private final UserService userService;

    public DashboardService(OrderElementRepository cartElementRepository, ActivityRepo activityRepo, AgenceRepository agenceRepository, ModelMapper mapper, UserService userService) {
        this.orderElementRepository = cartElementRepository;
        this.activityRepo = activityRepo;
        this.agenceRepository = agenceRepository;
        this.mapper = mapper;
        this.userService = userService;
    }


    public Long AgencyClientsCount(Long id) {
        List<Activity> allAgenceActivities = AllAgenceActivities(id);

        Long total = 0L;
        for (Activity activity : allAgenceActivities) {
            List<OrderElement> cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (OrderElement cartElement : cartElementList) {
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
            List<OrderElement> cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (OrderElement cartElement : cartElementList) {
                total +=cartElement.getOrder().getTotalAmount();
            }
        }
        return  total;
    }
    public List<ActivityEarningsDTO> getAgencyActivityEarnings(Long id) {
        List<Activity> allAgencyActivities = AllAgenceActivities(id);
        List<ActivityEarningsDTO> earnings = new ArrayList<>();

        for (Activity activity : allAgencyActivities) {
            double total = 0;
            List<OrderElement> cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (OrderElement cartElement : cartElementList) {
                total += cartElement.getOrder().getTotalAmount();
            }
            earnings.add(new ActivityEarningsDTO(activity.getDesignation(), total));
        }
        return earnings;
    }

    public List<Double> getAgencyActivityEarningslist(Long id) {
        List<Activity> allAgencyActivities = AllAgenceActivities(id);
        List<Double> earnings = new ArrayList<>();

        for (Activity activity : allAgencyActivities) {
            double total = 0;
            List<OrderElement> cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (OrderElement cartElement : cartElementList) {
                total += cartElement.getOrder().getTotalAmount();
            }
            earnings.add(total);
        }
        return earnings;
    }

    public List<ActivityClientsDTO> getAgencyActivityClients(Long id) {
        List<Activity> allAgencyActivities = AllAgenceActivities(id);
        List<ActivityClientsDTO> clients = new ArrayList<>();

        for (Activity activity : allAgencyActivities) {
            double total = 0;
            List<OrderElement> cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
            for (OrderElement cartElement : cartElementList) {
                total += cartElement.getNbrOfPerson();
            }
            clients.add(new ActivityClientsDTO(activity.getDesignation(), total));
        }
        return clients;
    }

    @Data
    public class ActivityEarningsDTO {
        private String activityName;
        private double earnings;

        public ActivityEarningsDTO(String activityName, double earnings) {
            this.activityName = activityName;
            this.earnings = earnings;
        }
    }

    @Data
    public class ActivityClientsDTO {
        private String activityName;
        private double clients;

        public ActivityClientsDTO(String activityName, double clients) {
            this.activityName = activityName;
            this.clients = clients;
        }
    }

    public double ActivityEarnings(Long id,Long activityId){
        List<Activity> allAgenceActivities = AllAgenceActivities(id);
        List<OrderElement> cartElementList;
        double total = 0L;
        for (Activity activity : allAgenceActivities) {
            if (activityId==activity.getId()){
                cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
                for (OrderElement cartElement : cartElementList) {
                    total +=cartElement.getSub_total();
                }
            }
        }
        return total;
    }

    public Map<String, Long> getAgencyActivitiesByCategory(Long id) {
        List<Activity> allActivities = AllAgenceActivities(id);

        Map<String, Long> categoryCount = allActivities.stream()
                .collect(Collectors.groupingBy(
                        Activity::getCategory,
                        Collectors.counting()
                ));

        return categoryCount;
    }

    public double ActivityClients(Long id,Long activityId){
        List<Activity> allAgenceActivities = AllAgenceActivities(id);
        List<OrderElement> cartElementList;
        double total = 0L;
        for (Activity activity : allAgenceActivities) {
            if (activityId==activity.getId()){
                cartElementList = orderElementRepository.getCartElementsByActivity_Id(activity.getId());
                for (OrderElement cartElement : cartElementList) {
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
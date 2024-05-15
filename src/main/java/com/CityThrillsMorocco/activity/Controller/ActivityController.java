package com.CityThrillsMorocco.activity.Controller;


import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.activity.WebSocket.ActivityWebSocketHandler;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static com.CityThrillsMorocco.activity.WebSocket.ActivityWebSocketHandler.userid;


@RestController
@RequestMapping("/CityThrillsMorocco/Admin/activities")
@CrossOrigin("http://localhost:4200/")
public class ActivityController {
    private final ActivityWebSocketHandler activityWebSocketHandler;
    private final ActivityService activityService;
    public static List<Activity> activities  = new ArrayList<>();
    public static List<Activity> activities$  = new ArrayList<>();

    public ActivityController(ActivityWebSocketHandler activityWebSocketHandler, ActivityService activityService){
        this.activityWebSocketHandler = activityWebSocketHandler;
        activities=activityService.getAllActivities();
        this.activityService = activityService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public ResponseEntity<List<Activity>> allUsers() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    @GetMapping("/agence/{agence_id}")
    public ResponseEntity<List<Activity>> AllAgenceActivities(@PathVariable ("agence_id") Long id) throws IOException {
        activities = activityService.getAllActivities();
        activities$=activityService.AllAgenceActivities(userid);
        System.out.println(userid);
        activityWebSocketHandler.sendUpdateToClients(activities$);
        return new ResponseEntity<>(activityService.AllAgenceActivities(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id){
        return new ResponseEntity<>(activityService.getActivityById(id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public void deleteActivity(@PathVariable("id") Long id) throws IOException {
        activityService.deleteActivity(id);
        activities$=activityService.AllAgenceActivities(userid);
        activityWebSocketHandler.sendUpdateToClients(activities$);
    }

    @PostMapping("/{agence_id}")
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public ResponseEntity<?> registerUser(@RequestBody Activity activity,@PathVariable("agence_id") Long agence_id) throws  IOException {
        activityService.addActivity(activity,agence_id);
        activities$=activityService.AllAgenceActivities(userid);
        activityWebSocketHandler.sendUpdateToClients(activities$);
        return ResponseEntity.status(HttpStatus.CREATED).body("Activité ajoutée avec succès");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public ResponseEntity<?> updateActivity(
            @RequestBody Activity activity,
            @PathVariable("id")Long id) throws IOException {
        activityService.updateActivity(activity,id);
        activities$=activityService.AllAgenceActivities(userid);
        activityWebSocketHandler.sendUpdateToClients(activities$);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Message créé avec succès");
    }


    private static final String UPLOAD_FOLDER = "C:/CityThrillsMorocco-admin_brache/src/main/java/com/CityThrillsMorocco/uploads/";

    @PostMapping("/upload")
    public String handleFileUpload(@RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            return "Aucun fichier n'a été téléchargé.";
        }

        try {
            // Génère un nom de fichier unique pour éviter les collisions
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Chemin complet du fichier de destination
            Path source = (Path) file.getInputStream();
            Path destination = Paths.get(UPLOAD_FOLDER + fileName);

            // Déplace le fichier vers le dossier de destination
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

            return "Fichier déplacé avec succès.";
        } catch (Exception e) {
            return "Erreur lors du déplacement du fichier : " + e.getMessage();
        }
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            // Chemin complet de l'image
            Path imagePath = Paths.get(UPLOAD_FOLDER + imageName);

            // Vérifie si le fichier existe
            if (!Files.exists(imagePath)) {
                return ResponseEntity.notFound().build();
            }

            // Lit les données de l'image en tant que ressource
            Resource resource = new UrlResource(imagePath.toUri());

            // Renvoie la réponse avec le type de contenu approprié
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // ou MediaType.IMAGE_PNG pour les images PNG
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

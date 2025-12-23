package com.example.startspring;

import com.example.startspring.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SettingsController {

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping(
            value = "/save-settings",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> saveSettings(
            @RequestParam("userId") String userId,
            @RequestParam("gmail") String gmail,
            @RequestParam("appPassword") String appPassword
    ) {
        try {
            System.out.println("SAVE SETTINGS HIT");
            System.out.println("UserId: " + userId);
            System.out.println("Gmail: " + gmail);

            String cleanPassword = appPassword.replaceAll("\\s+", "");

            String encryptedPassword = encryptionService.encrypt(cleanPassword);

            // TODO: save encryptedPassword + gmail using FirebaseService
            System.out.println("Encrypted password: " + encryptedPassword);

            return ResponseEntity.ok("Settings saved securely");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

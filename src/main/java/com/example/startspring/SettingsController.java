package com.example.startspring;

import com.example.startspring.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/settings")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://mailx-backend.onrender.com"
})
public class SettingsController {

    @Autowired
    private EncryptionService encryptionService;

    // SAVE SETTINGS
    @PostMapping("/save")
    public Map<String, String> saveSettings(@RequestBody Map<String, String> request) throws Exception {

        String email = request.get("email");
        String appPassword = request.get("password");

        if (email == null || appPassword == null) {
            return Map.of("status", "error", "message", "Missing email or password");
        }

        // Encrypt and save (your logic here)
        String encryptedEmail = encryptionService.encrypt(email);
        String encryptedPassword = encryptionService.encrypt(appPassword);

        // TODO: Store encrypted values properly (DB, file, Firebase, etc.)
        System.out.println("Encrypted Email: " + encryptedEmail);
        System.out.println("Encrypted Password: " + encryptedPassword);

        return Map.of("status", "success", "message", "Settings saved securely");
    }

    // FETCH SAVED SETTINGS (OPTIONAL)
    @GetMapping("/get")
    public Map<String, String> getSettings() {

        // TODO: fetch saved encrypted values
        String encryptedEmail = "demo-email";
        String encryptedPassword = "demo-password";

        return Map.of(
                "email", encryptedEmail,
                "password", encryptedPassword
        );
    }
}

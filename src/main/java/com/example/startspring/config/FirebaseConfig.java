package com.example.startspring.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @PostConstruct
    public void initialize() {
        try {
            System.out.println("🔥 FirebaseConfig initialize() CALLED");

            // Prevent double initialization
            if (!FirebaseApp.getApps().isEmpty()) {
                logger.info("Firebase already initialized");
                return;
            }

            // ✅ Render Secret File (case-sensitive)
            String firebaseKeyPath = "/etc/secrets/FIREBASE_SERVICE_ACCOUNT.json";

            InputStream serviceAccount = new FileInputStream(firebaseKeyPath);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("mail-fire-99b18")
                    .setStorageBucket("mail-fire-99b18.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);

            logger.info("✅ Firebase Admin SDK initialized successfully");

        } catch (Exception e) {
            logger.error("❌ Firebase initialization failed", e);
        }
    }
}

package com.example.startspring.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @PostConstruct
    public void initialize() {
        try {
            // 🔹 Prevent multiple initializations
            if (!FirebaseApp.getApps().isEmpty()) {
                logger.info("ℹ️ Firebase already initialized.");
                return;
            }

            // 🔹 Read Firebase service account JSON from ENV variable (Render-safe)
            String firebaseJson = System.getenv("FIREBASE_SERVICE_ACCOUNT");

            if (firebaseJson == null || firebaseJson.isEmpty()) {
                throw new RuntimeException(
                        "FIREBASE_SERVICE_ACCOUNT environment variable is not set"
                );
            }

            InputStream serviceAccount =
                    new ByteArrayInputStream(firebaseJson.getBytes(StandardCharsets.UTF_8));

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("mail-fire-99b18") // ✅ your Firebase project ID
                    .setStorageBucket("mail-fire-99b18.appspot.com") // ✅ correct bucket
                    .build();

            FirebaseApp.initializeApp(options);

            logger.info("✅ Firebase Admin SDK initialized successfully.");

        } catch (Exception e) {
            logger.error("❌ Failed to initialize Firebase Admin SDK", e);
        }
    }
}

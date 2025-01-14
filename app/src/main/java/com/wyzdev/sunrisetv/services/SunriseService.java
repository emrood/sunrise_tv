package com.wyzdev.sunrisetv.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class SunriseService extends Service {
    public SunriseService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Nettoyer les ressources si nécessaire
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Crée une notification pour le service en avant-plan
        Notification notification = new NotificationCompat.Builder(this, "SUNRISE_CHANNEL")
                .setContentTitle("Service en avant-plan")
                .setContentText("Votre service fonctionne en arrière-plan.")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();

        // Démarrer le service en avant-plan avec l'ID de notification
        startForeground(1, notification);

        // Ajouter ici le code pour les tâches à exécuter en arrière-plan

        // Si vous voulez que le service continue de fonctionner jusqu'à ce qu'il soit explicitement arrêté
        return START_STICKY;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    "SUNRISE_CHANNEL",
                    "Service En Avant-Plan",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }
}
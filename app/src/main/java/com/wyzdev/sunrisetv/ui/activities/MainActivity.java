package com.wyzdev.sunrisetv.ui.activities;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.viewpager2.widget.ViewPager2;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.wyzdev.sunrisetv.databinding.ActivityMainBinding;
import com.wyzdev.sunrisetv.R;
import com.wyzdev.sunrisetv.interfaces.APIInterface;
import com.wyzdev.sunrisetv.models.Tracking;
import com.wyzdev.sunrisetv.models.TruckStation;
import com.wyzdev.sunrisetv.receivers.SunriseAdminReceiver;
import com.wyzdev.sunrisetv.tools.APIClient;
import com.wyzdev.sunrisetv.ui.adapters.TruckStationAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    View view;
    private GestureDetector detector;
    public Animation.AnimationListener mAnimationListener;
    int sales_by_fliper = 1;

    private Handler sliderHandler = new Handler(Looper.getMainLooper());


    List<Integer> flippers = Arrays.asList(R.id.view_flipper1, R.id.view_flipper2, R.id.view_flipper3, R.id.view_flipper3, R.id.view_flipper4, R.id.view_flipper5, R.id.view_flipper6);


    HashMap<Integer, List<Tracking>> list_by_flipper;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler(Looper.myLooper());
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar
            if (Build.VERSION.SDK_INT >= 30) {
                mContentView.getWindowInsetsController().hide(
                        WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
            } else {
                // Note that some of these constants are new as of API 16 (Jelly Bean)
                // and API 19 (KitKat). It is safe to use them, as they are inlined
                // at compile-time and do nothing on earlier devices.
                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    private ActivityMainBinding binding;

    private List<Tracking> trackings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVisible = true;
        mControlsView = binding.fullscreenContentControls;
        mContentView = binding.fullscreenContent;

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        binding.dummyButton.setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    @Override
    protected void onResume() {
        super.onResume();

        ComponentName componentName = new ComponentName(this, SunriseAdminReceiver.class);
        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        if (!dpm.isAdminActive(componentName)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Sunrise");
            startActivityForResult(intent, 205);
        }

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.banner_video);
        binding.videoView.setVideoURI(videoUri);
        binding.videoView.start();
        binding.videoView.setVisibility(View.VISIBLE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startRefreshTask();
            }
        }, 30000);

    }


    public void startRefreshTask(){
        Handler handler = new Handler();
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                getPackageStatus();
                getPrices();
                handler.postDelayed(this, 1800000);
            }
        };
        handler.post(runnableCode);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        if (Build.VERSION.SDK_INT >= 30) {
            mContentView.getWindowInsetsController().show(
                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        } else {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void getPackageStatus(){
        try{
            AsyncTask.execute(() -> APIClient.getClient().create(APIInterface.class).getFlightPackages(null, null, null, null, null)
                    .enqueue(new Callback<List<Tracking>>() {
                        @Override
                        public void onResponse(Call<List<Tracking>> call, Response<List<Tracking>> response) {
                            if(response.isSuccessful()){
                                runOnUiThread(() -> showPackages(response.body()));
                            }else{
                                runOnUiThread(() -> binding.trackingContainer.setVisibility(View.GONE));
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Tracking>> call, Throwable t) {
                            runOnUiThread(() -> binding.trackingContainer.setVisibility(View.GONE));
                        }
                    }));

        }catch (Exception e){

        }
    }

    public void showPackages(List<Tracking> trackingList){

        if(trackingList.isEmpty()){
            binding.trackingContainer.setVisibility(View.GONE);

        }else{

            binding.trackingContainer.setVisibility(View.VISIBLE);

            list_by_flipper = new HashMap<>();
            for(int i = 0 ; i < flippers.size(); i++){
                list_by_flipper.put(i, new ArrayList<>());
            }

            int flipper = -1;
            for(Tracking sale : trackingList){
                if(flipper < (flippers.size() - 1)){
                    flipper++;
                }else{
                    flipper = 0;
                }

                List<Tracking> temp_list = list_by_flipper.get(flipper);
                temp_list.add(sale);
                list_by_flipper.put(flipper, temp_list);
            }

            flipper = 0;

            for(Integer id : flippers){
                ViewFlipper viewFlipper = findViewById(id);
                viewFlipper.removeAllViews();
                viewFlipper.setAutoStart(true);
                viewFlipper.setFlipInterval(10000);
                viewFlipper.startFlipping();
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up_out));

                if(list_by_flipper.containsKey(flipper)){
                    for(Tracking sale : list_by_flipper.get(flipper)){
                        setFlipperItem(viewFlipper, sale);
                    }
                }

                flipper++;
            }




            for(Integer id : flippers){
                ViewFlipper viewFlipper = findViewById(id);
                viewFlipper.setDisplayedChild(0);
            }
        }



    }

    private void setFlipperItem(ViewFlipper flipper, Tracking tracking) {

        TextView tvBarcode;

        view = LayoutInflater.from(this).inflate(R.layout.item_tracking, null, false);
        tvBarcode = view.findViewById(R.id.tv_barcode);

        try{
            tvBarcode.setText(tracking.product.barcode);
        }catch (Exception e){

        }

        flipper.addView(view);
    }


    public void getPrices(){
        try{
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    APIClient.getClient().create(APIInterface.class).getTruckStations(null, "truck,station", 1L)
                            .enqueue(new Callback<List<TruckStation>>() {
                                @Override
                                public void onResponse(Call<List<TruckStation>> call, Response<List<TruckStation>> response) {
                                    if (response.isSuccessful() && !response.body().isEmpty()){
                                        showPrices(response.body());
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<TruckStation>> call, Throwable t) {

                                }
                            });
                }
            });
        }catch (Exception e){

        }

    }



    public void showPrices(List<TruckStation> truckStations){
        try{

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        TruckStationAdapter adapter = new TruckStationAdapter(truckStations);
                        binding.viewPager.setAdapter(adapter);
                        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        autoScrollViewPager(truckStations.size());
                    }catch (Exception e){

                    }
                }
            });

        }catch (Exception e){

        }
    }

    public void autoScrollViewPager(int itemCount){
        try{
            sliderHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int current = binding.viewPager.getCurrentItem();
                    int next = (current + 1) % itemCount;
                    binding.viewPager.setCurrentItem(next, true);
                    sliderHandler.postDelayed(this, 3000);
                }
            }, 3000);
        }catch (Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sliderHandler.removeCallbacksAndMessages(null);
    }
}
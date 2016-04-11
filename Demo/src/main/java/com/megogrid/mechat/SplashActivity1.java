//package com.megogrid.mechat;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.megogrid.activities.MegoUserData;
//import com.megogrid.megochat.MegoChat;
//import com.megogrid.megochat.MegoChat;
//import com.megogrid.megochat.OnTaskFinishListener;
//import com.megogrid.megochat.exceptions.InValidAppIDException;
//import com.megogrid.megochat.exceptions.InvalidContactException;
//import com.megogrid.megouser.MegoUser;
//
//import mego.auth.MegoAuthorizer;
//import mego.rest.AuthController;
//
///**
// * Created by akkash on 6/2/16.
// */
//public class SplashActivity extends Activity {
//
//    private int splashOutTime = 3000;
//    Animation animation;
//    private ImageView logo;
//    private TextView title2_txt;
//    MegoChat chatApiController;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        setContentView(R.layout.activity_splash);
//
//        logo = (ImageView) findViewById(R.id.logo_img);
//        title2_txt = (TextView) findViewById(R.id.pro_txt);
//
//        initAuth();
//        initMeuser();
//
//        chatApiController = MegoChat.getInstance(SplashActivity.this);
//        chatApiController.setChatHeaderName("Chat Demo");
//        chatApiController.initConfiguration();
//        SplashActivity activity = new SplashActivity();
//
//        if (savedInstanceState == null) {
//            flyIn();
//        }
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                endSplash();
//            }
//        }, splashOutTime);
//
//    }
//
//    private void initAuth() {
//        AuthController.setHostMode(false);
//        MegoAuthorizer authorizer = new MegoAuthorizer(SplashActivity.this);
//        authorizer.intializeSDK();
//
//    }
//
//    private void initMeuser() {
//
//        MegoUser.getInstance(SplashActivity.this).initSDK();
//    }
//
//
//    private void flyIn() {
//        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
//        logo.startAnimation(animation);
//
//        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation);
//        title2_txt.startAnimation(animation);
//    }
//
//    private void endSplash() {
//        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_back);
//        logo.startAnimation(animation);
//
//        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation_back);
//        title2_txt.startAnimation(animation);
//
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationEnd(Animation arg0) {
//                if (!MegoUser.isLoggedIn(SplashActivity.this)) {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    String number[] = MegoUserData.getInstance(SplashActivity.this).getUserContac().split(":");
//                    System.out.println("Number is " + number[0] + " " + number[1]);
//                    try {
//                        chatApiController.setContactInfo(number[0], number[1]);
//                        chatApiController.startChat(new OnTaskFinishListener() {
//                            @Override
//                            public void onFinishTask() {
//                                SplashActivity.this.finish();
//                            }
//                        });
//
//                    } catch (InvalidContactException e1) {
//                        e1.printStackTrace();
//                        System.out.println("InvalidContactException = " + e1);
//                        Toast.makeText(SplashActivity.this, e1.getMessage(),Toast.LENGTH_LONG).show();
//                    } catch (InValidAppIDException e1) {
//                        e1.printStackTrace();
//                        System.out.println("InValidAppIDException = " + e1);
//                        Toast.makeText(SplashActivity.this, e1.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation arg0) {
//
//            }
//
//            @Override
//            public void onAnimationStart(Animation arg0) {
//
//            }
//        });
//
//    }
//
//
//}

package com.megogrid.mechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.megogrid.activities.RegistrationHandler;
import com.megogrid.megoauth.MegoAuthorizer;
import com.megogrid.megochat.MegoChat;
import com.megogrid.megochat.OnTaskFinishListener;
import com.megogrid.megochat.exceptions.MegoChatException;
import com.megogrid.megouser.MegoUser;
import com.megogrid.megouser.MegoUserConfig;
import com.megogrid.megouser.MegoUserContact;
import com.megogrid.megouser.MegoUserException;
import com.megogrid.megouser.sdkinterfaces.IResponseHandler;


public class SplashActivity extends Activity {

    private MegoUserContact megoUserContact;
    private int splashOutTime = 3000;
    Animation animation;
    private ImageView logo;
    private TextView title2_txt;
    private MegoChat chatApiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_splash);

        logo = (ImageView) findViewById(R.id.logo_img);
        title2_txt = (TextView) findViewById(R.id.pro_txt);

        flyIn();
        MegoAuthorizer megoAuthorizer = new MegoAuthorizer(SplashActivity.this);
        megoAuthorizer.intializeSDK();

        new MegoUserConfig.ConfigBuilder(SplashActivity.this).setAppBackground(MegoUserConfig.ImageFormat.JPG,R.drawable.background).build();
        chatApiController=MegoChat.getInstance(SplashActivity.this);
        chatApiController.initConfiguration();
//        chatApiController.setChatHeaderName("Chat Demo");
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                endSplash();
            }
        }, splashOutTime);
    }


    private void flyIn() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        logo.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation);
        title2_txt.startAnimation(animation);
    }

    private void startMegoChat() {
        try {
            megoUserContact = MegoUser.getInstance(SplashActivity.this).getUserContact();
            chatApiController = MegoChat.getInstance(SplashActivity.this);
            chatApiController.setContactInfo(megoUserContact.country_code, megoUserContact.contact_number);
            chatApiController.startChat(new OnTaskFinishListener() {
                @Override
                public void onFinishTask() {
                    SplashActivity.this.finish();
                }
            });
        } catch (MegoUserException e) {
            e.printStackTrace();
        } catch (MegoChatException e) {
            e.printStackTrace();
        }
    }

    private void endSplash() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_back);
        logo.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation_back);
        title2_txt.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                if (MegoUser.isLoggedIn(SplashActivity.this)) {
                    startMegoChat();
                } else {
                    MegoUser.getInstance(SplashActivity.this).initiateRegistration(new IResponseHandler() {
                        @Override
                        public void onRegisterCallBack(MegoUserException meUserException) {

                        }

                        @Override
                        public void onLoginCallBack(MegoUserException meUserException) {
                            if (meUserException == null)
                                startMegoChat();
                        }

                        @Override
                        public void onRegisterPromptInvocation(MegoUserException meUserException) {

                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });
    }


}

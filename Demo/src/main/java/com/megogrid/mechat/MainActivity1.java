//package com.megogrid.mechat;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.megogrid.activities.MegoUserData;
//import com.megogrid.activities.RegistrationHandler;
//import com.megogrid.megochat.MegoChat;
//import com.megogrid.megochat.OnTaskFinishListener;
//import com.megogrid.megochat.exceptions.InvalidContactException;
//import com.megogrid.megouser.MegoUser;
//import com.megogrid.megouser.MegoUserConfig;
//import com.megogrid.megouser.MegoUserException;
////import com.mig.meuser.MeUser;
////import com.mig.meuser.MeUserConfig;
////import com.mig.meuser.MeUserException;
////import com.mig.migreg.MeUserData;
////import com.mig.migreg.RegistrationHandler;
//
//
//public class MainActivity1 extends Activity {
//    private static String DT_GOOGLE_ID = "798441167501-p8sds84jof5m1gagf8t7gqpjb8mgmd7r.apps.googleusercontent.com";
//
//    MegoChat chatApiController;
//    MegoUserData countryCode;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        chatApiController = MegoChat.getInstance(this);
//        chatApiController.setChatHeaderName("Chat Demo");
//        chatApiController.initConfiguration();
//        chatApiController.setNotificationIcon(R.drawable.ic_launcher);
//
//        try {
//            countryCode = MegoUserData.getInstance(MainActivity.this);
//        } catch (Exception e) {
//            System.out.println("<<<<checking" + e.getMessage());
//        }
//        startMeuserConfig();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println("onResume ");
//        if (MegoUser.isLoggedIn(MainActivity.this)) {
//            String number[] = countryCode.getUserContac().split(":");
//            System.out.println("Number is " + number[0] + " " + number[1]);
//            try {
//                chatApiController.setContactInfo(number[0], number[1]);
//                chatApiController.startChat(new OnTaskFinishListener() {
//                    @Override
//                    public void onFinishTask() {
//                        MainActivity.this.finish();
//                    }
//                });
//
//            } catch (InvalidContactException e1) {
//                e1.printStackTrace();
//                System.out.println("InvalidContactException = " + e1);
//                Toast.makeText(MainActivity.this, e1.getMessage(), Toast.LENGTH_LONG).show();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//                System.out.println("InValidAppIDException = " + e1);
//                Toast.makeText(MainActivity.this, e1.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    private void startMeuserConfig() {
//        new MegoUserConfig.ConfigBuilder(MainActivity.this).setAppBackground(R.drawable.background).build();
//        MegoUser.getInstance(MainActivity.this).initiateRegistration(new RegistrationHandler.IResponseHandler() {
//            @Override
//            public void onRegisterCallBack(MegoUserException e) {
//                System.out.println("<<<checking onRegisterCallBack ");
//            }
//
//            @Override
//            public void onLoginCallBack(MegoUserException e) {
//                System.out.println("<<<checking onLoginCallBack ");
//            }
//
//            @Override
//            public void onRegisterPromptInvocation(MegoUserException e) {
//                System.out.println("<<<checking onRegisterPromptInvocation ");
//            }
//        });
//    }
//
//
//}

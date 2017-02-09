package com.pex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pex.R;
import com.pex.controller.ControllerJson;
import com.pex.interfaces.TaskCompleteListener;
import com.pex.pref.PEXPref;
import com.pex.utils.AESHelper;
import com.pex.utils.ConstantClass;
import com.pex.utils.CustomToastClass;
import com.pex.utils.Font;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by avikaljain on 26/12/16.
 */
public class MPinLoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare the Objects & Variable
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView0;

    private TextView textViewAbc;
    private TextView textViewDef;
    private TextView textViewGhi;
    private TextView textViewJkl;
    private TextView textViewMno;
    private TextView textViewPqrs;
    private TextView textViewTuv;
    private TextView textViewWxyz;
    private TextView textViewStar;
    private TextView textViewHash;

    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private RelativeLayout relativeLayout3;
    private RelativeLayout relativeLayout4;
    private RelativeLayout relativeLayout5;
    private RelativeLayout relativeLayout6;
    private RelativeLayout relativeLayout7;
    private RelativeLayout relativeLayout8;
    private RelativeLayout relativeLayout9;
    private RelativeLayout relativeLayout0;
    private RelativeLayout relativeLayoutHash;

    private TextView textViewType1;
    private TextView textViewType2;
    private TextView textViewType3;
    private TextView textViewType4;

    TextView[] pinBoxArray;

    private String previous;
    private String current;
    private String appendStr;


    private String tempCurrent;
    private String tempAppendStr = "";
    private ImageView cancel;


    private Toolbar toolbar;
    private TextView toolbarTitle;

    private String state = "";
    private String mPin = "";
    private String currentmPIN = "";
    private String userId = "";
    private Bundle bundle;

    private TextView textViewForgotPin;
    final int PIN_LENGTH = 4;

    private JSONObject post_mPin;
    int keyHint;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin_login);
        keyHint = AESHelper.generateEncryptionHint();// Generate the random hint key
        initViews();
        clickEvent();
    }

    /**
     * Method for initialize view & variable
     * Also set fonts
     */
    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar.setNavigationIcon(R.drawable.back_arrow);
        textView1 = (TextView) findViewById(R.id.text_1);
        textView2 = (TextView) findViewById(R.id.text_2);
        textView3 = (TextView) findViewById(R.id.text_3);
        textView4 = (TextView) findViewById(R.id.text_4);
        textView5 = (TextView) findViewById(R.id.text_5);
        textView6 = (TextView) findViewById(R.id.text_6);
        textView7 = (TextView) findViewById(R.id.text_7);
        textView8 = (TextView) findViewById(R.id.text_8);
        textView9 = (TextView) findViewById(R.id.text_9);
        textView0 = (TextView) findViewById(R.id.text_0);
        textViewAbc = (TextView) findViewById(R.id.text_abc);
        textViewDef = (TextView) findViewById(R.id.text_def);
        textViewGhi = (TextView) findViewById(R.id.text_ghi);
        textViewJkl = (TextView) findViewById(R.id.text_JKL);
        textViewMno = (TextView) findViewById(R.id.text_mno);
        textViewPqrs = (TextView) findViewById(R.id.text_pqr);
        textViewTuv = (TextView) findViewById(R.id.text_tuv);
        textViewWxyz = (TextView) findViewById(R.id.text_xyz);
        textViewStar = (TextView) findViewById(R.id.text_star);
        textViewHash = (TextView) findViewById(R.id.text_hash);
        relativeLayout0 = (RelativeLayout) findViewById(R.id.relative_0);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.relative_1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relative_2);
        relativeLayout3 = (RelativeLayout) findViewById(R.id.relative_3);
        relativeLayout4 = (RelativeLayout) findViewById(R.id.relative_4);
        relativeLayout5 = (RelativeLayout) findViewById(R.id.relative_5);
        relativeLayout6 = (RelativeLayout) findViewById(R.id.relative_6);
        relativeLayout7 = (RelativeLayout) findViewById(R.id.relative_7);
        relativeLayout8 = (RelativeLayout) findViewById(R.id.relative_8);
        relativeLayout9 = (RelativeLayout) findViewById(R.id.relative_9);
        relativeLayoutHash = (RelativeLayout) findViewById(R.id.relative_hash);
        textViewType1 = (TextView) findViewById(R.id.text_type1);
        textViewType2 = (TextView) findViewById(R.id.text_type2);
        textViewType3 = (TextView) findViewById(R.id.text_type3);
        textViewType4 = (TextView) findViewById(R.id.text_type4);
        pinBoxArray = new TextView[PIN_LENGTH];

        textViewForgotPin = (TextView) findViewById(R.id.forgat_mpin);
        cancel = (ImageView) findViewById(R.id.cancel_btn);
        textViewForgotPin.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansRegular));
        toolbarTitle.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansRegular));
        textView1.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView2.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView3.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView4.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView5.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView6.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView7.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView8.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView9.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textView0.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewAbc.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewDef.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewGhi.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewJkl.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewMno.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewPqrs.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewTuv.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewWxyz.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewStar.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        textViewHash.setTypeface(new Font(this).getTypeface(Font.AssetTypefaces.OpenSansSemiBold));
        bundle = getIntent().getExtras();
        if (bundle != null) {
            state = bundle.getString(ConstantClass.STATE);

            actionType();
        }

    }

    private void clickEvent() {
        relativeLayout0.setOnClickListener(this);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);
        relativeLayout4.setOnClickListener(this);
        relativeLayout5.setOnClickListener(this);
        relativeLayout6.setOnClickListener(this);
        relativeLayout7.setOnClickListener(this);
        relativeLayout8.setOnClickListener(this);
        relativeLayout9.setOnClickListener(this);
        relativeLayoutHash.setOnClickListener(this);

        cancel.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

   /* @Override
    public void onBackPressed() {
        Intent intent = new Intent(MPinLoginActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }*/

    /**
     * Custome keyboard click event
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgat_mpin:
//                Intent register = new Intent(MPinLoginActivity.this, RegistrationActivity.class);
//                startActivity(register);
//                overridePendingTransition(R.anim.slide_right_in,
//                        R.anim.slide_left_out);
                break;
            case R.id.relative_0:
                setCardNumber("0");
                break;
            case R.id.relative_1:
                setCardNumber("1");
                break;
            case R.id.relative_2:
                setCardNumber("2");
                break;
            case R.id.relative_3:
                setCardNumber("3");
                break;
            case R.id.relative_4:
                setCardNumber("4");
                break;
            case R.id.relative_5:
                setCardNumber("5");
                break;
            case R.id.relative_6:
                setCardNumber("6");
                break;
            case R.id.relative_7:
                setCardNumber("7");
                break;
            case R.id.relative_8:
                setCardNumber("8");
                break;
            case R.id.relative_9:
                setCardNumber("9");
                break;
            case R.id.cancel_btn:
                tempAppendStr = "";
                textViewType1.setText("");
                textViewType2.setText("");
                textViewType3.setText("");
                textViewType4.setText("");
                cancel.setVisibility(View.GONE);
                break;
            case R.id.relative_hash:
                if (tempAppendStr.length() < 0 || tempAppendStr.equals("")) {
                    cancel.setVisibility(View.GONE);
                } else {
                    cancel.setVisibility(View.VISIBLE);
                    if (tempAppendStr.length() == 4) {
                        StringBuilder builder = new StringBuilder(tempAppendStr);
                        builder.deleteCharAt(tempAppendStr.length() - 1);
                        tempAppendStr = builder.toString();
                        textViewType4.setText("");
                    } else if (tempAppendStr.length() == 3) {
                        StringBuilder builder = new StringBuilder(tempAppendStr);
                        builder.deleteCharAt(tempAppendStr.length() - 1);
                        tempAppendStr = builder.toString();
                        textViewType3.setText("");
                    } else if (tempAppendStr.length() == 2) {
                        StringBuilder builder = new StringBuilder(tempAppendStr);
                        builder.deleteCharAt(tempAppendStr.length() - 1);
                        tempAppendStr = builder.toString();
                        textViewType2.setText("");
                    } else if (tempAppendStr.length() == 1) {
                        StringBuilder builder = new StringBuilder(tempAppendStr);
                        builder.deleteCharAt(tempAppendStr.length() - 1);
                        tempAppendStr = builder.toString();
                        textViewType1.setText("");
                        cancel.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }

    /**
     * Method for set the toolbar title
     * Perform action according the previous screen
     */
    private void actionType() {
        if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.LOGIN_STATE)) {
            toolbarTitle.setText(R.string.mPinLogin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setOnClickListener(this);
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.OWN_STATE)) {
            toolbarTitle.setText(R.string.enter_mpin);
            textViewForgotPin.setVisibility(View.GONE);
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.VISA_STATE)) {
            toolbarTitle.setText(R.string.enter_mpin);
            textViewForgotPin.setVisibility(View.GONE);
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CHANGE_PIN)) {
            toolbarTitle.setText(R.string.change_new_pin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Enter Pin");
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.RE_CHANGE_PIN)) {
            toolbarTitle.setText(R.string.change_new_pin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Re Enter Pin");
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CURRENT_mPIN)) {
            toolbarTitle.setText(R.string.change_mpin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Enter Current mPIN");

        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CHANGE_mPIN)) {
            toolbarTitle.setText(R.string.change_mpin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Enter New mPIN");
            mPin = bundle.getString("cardNumber");
            currentmPIN = mPin;
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.RE_CHANGE_mPIN)) {
            toolbarTitle.setText(R.string.change_mpin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Re Enter New mPIN");
            currentmPIN = bundle.getString("oldmPin");
            mPin = bundle.getString("cardNumber");
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CUSTOMER_LOGIN_STATE)) {
            toolbarTitle.setText(R.string.set_mPin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Enter New mPIN");
        } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.RE_CUSTOMER_LOGIN_STATE)) {
            toolbarTitle.setText(R.string.set_mPin);
            textViewForgotPin.setVisibility(View.VISIBLE);
            textViewForgotPin.setText("Re-Enter New mPIN");
            mPin = bundle.getString("cardNumber");
            Log.d("Card Number MPin", mPin);
        }


    }

    /**
     * Set digit or mPin to the box and perform operation
     *
     * @param digit
     */
    private void setCardNumber(String digit) {
        if (!validation()) {
            tempCurrent = digit;
            current = "*";
            tempAppendStr = tempAppendStr + tempCurrent;
            if (tempAppendStr.length() < 5) {
                if (tempAppendStr.length() == 1) {
                    textViewType1.setText("*");
                } else if (tempAppendStr.length() == 2) {
                    textViewType2.setText("*");
                } else if (tempAppendStr.length() == 3) {
                    textViewType3.setText("*");
                } else if (tempAppendStr.length() == 4) {
                    textViewType4.setText("*");
                }
                Log.d("Card Number", tempAppendStr);
                appendStr = previous + current;
                if (tempAppendStr.length() < 0) {
                    cancel.setVisibility(View.GONE);
                } else {
                    cancel.setVisibility(View.VISIBLE);
                }
            }

        } else {
            CustomToastClass.ValidationToast(MPinLoginActivity.this, getResources().getString(R.string.error_msg_enter_4_digit));
        }
    }

    /**
     * Action bar menu to login in the application
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setpassword_menu, menu);
        TypefaceSpan face = new TypefaceSpan("fonts/opensans_regular.ttf");
        SpannableStringBuilder title = new SpannableStringBuilder(getString(R.string.done));
        title.setSpan(face, 0, title.length(), 0);
        MenuItem menuItem = menu.findItem(R.id.action_done); // OR THIS

        menuItem.setTitle(title);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_done:
                if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.LOGIN_STATE)) {
                    actionDashBoard();
                } else {
                    actionTrasnferSuccess();
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to login
     */
    private void actionDashBoard() {
        if (validation()) {
            loginViamPIN();
        } else {
            CustomToastClass.ValidationToast(MPinLoginActivity.this, getResources().getString(R.string.error_msg_enter_4_digit));
        }
    }

    /**
     * Method for going to next screen and perform the operation
     */
    private void actionTrasnferSuccess() {
        if (validation()) {
            if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.OWN_STATE)) {
                Intent intent = new Intent(MPinLoginActivity.this, TransferSuccessNew.class);
                intent.putExtra(ConstantClass.STATE, ConstantClass.OWN_STATE);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.VISA_STATE)) {
                Intent intent = new Intent(MPinLoginActivity.this, TransferSuccessNew.class);
                intent.putExtra(ConstantClass.STATE, ConstantClass.VISA_STATE);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CURRENT_mPIN)) {
                Intent intent = new Intent(MPinLoginActivity.this, MPinLoginActivity.class);
                intent.putExtra("cardNumber", tempAppendStr);
                intent.putExtra(ConstantClass.STATE, ConstantClass.CHANGE_mPIN);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CHANGE_PIN)) {
                Intent intent = new Intent(MPinLoginActivity.this, MPinLoginActivity.class);
                intent.putExtra(ConstantClass.STATE, ConstantClass.RE_CHANGE_PIN);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.RE_CHANGE_PIN)) {
                Intent intent = new Intent(MPinLoginActivity.this, TransferSuccessNew.class);
                intent.putExtra(ConstantClass.STATE, ConstantClass.RE_CHANGE_PIN);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CHANGE_mPIN)) {
                Intent intent = new Intent(MPinLoginActivity.this, MPinLoginActivity.class);
                intent.putExtra("oldmPin", currentmPIN);
                intent.putExtra("cardNumber", tempAppendStr);
                intent.putExtra(ConstantClass.STATE, ConstantClass.RE_CHANGE_mPIN);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.RE_CHANGE_mPIN)) {
                changemPin();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.CUSTOMER_LOGIN_STATE)) {
                Intent intent = new Intent(MPinLoginActivity.this, MPinLoginActivity.class);
                intent.putExtra("cardNumber", tempAppendStr);
                intent.putExtra(ConstantClass.STATE, ConstantClass.RE_CUSTOMER_LOGIN_STATE);
                startActivity(intent);
                finish();
            } else if (!state.isEmpty() && state.equalsIgnoreCase(ConstantClass.RE_CUSTOMER_LOGIN_STATE)) {
                setmPIN();
            }
        } else {
            CustomToastClass.ValidationToast(MPinLoginActivity.this, getResources().getString(R.string.error_msg_enter_4_digit));
        }
    }

    /**
     * Check the validation where we enter the mPin is right way or not
     *
     * @return
     */
    private boolean validation() {
        boolean check = false;
        if (!isPasswordValid(tempAppendStr)) {
            check = true;
        }
        return check;
    }

    /**
     * Method for login via mPin
     * Hit the api and get response to the server
     */
    private void loginViamPIN() {
        post_mPin = new JSONObject();
        try {
            String passPhrase = AESHelper.generateMessageEncryptionKey(keyHint);// Encrypt the key hint
            String encryptDeviceID = AESHelper.encrypt(ConstantClass.getDeviceId(this), passPhrase);// Encrypt the device id
            String encryptTempStr = AESHelper.encrypt(tempAppendStr, passPhrase);// Encrypt the mPin
            post_mPin.put("DeviceId", encryptDeviceID);
            post_mPin.put("MPinPassword", encryptTempStr);
            post_mPin.put("ResponseKey", String.valueOf(keyHint));
            Log.d("Mpim", encryptTempStr);
            Log.d("DeviceID", encryptDeviceID);
            Log.d("ResppnseKey", String.valueOf(keyHint));
            // Hit the api and AsyncTask and get result
            new ControllerJson(MPinLoginActivity.this, ConstantClass.LOGINviamPINRequest, String.valueOf(post_mPin), true, new TaskCompleteListener() {
                @Override
                public void onTaskComplete(Object obj) throws JSONException {
                    JSONObject json = new JSONObject(obj.toString());
                    Log.d("Response", json.toString());
                    int success = json.getInt("ResponseCode");
                    if (success == 1) {// After successful login
                        JSONObject jsonData = new JSONObject(json.getJSONObject("ResponseData").toString());
                        String userID = jsonData.getString("UserId");
                        int responseKey = Integer.parseInt(json.getString("ResponseKey"));
                        String pass_Phrase = AESHelper.generateMessageEncryptionKey(responseKey);
                        userID = AESHelper.decrypt(userID, pass_Phrase);
                        PEXPref.getInstance(MPinLoginActivity.this).writePrefs(ConstantClass.USER_ID_PREF, userID);
                        Intent intent = new Intent(MPinLoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        tempAppendStr = "";
                        textViewType1.setText("");
                        textViewType2.setText("");
                        textViewType3.setText("");
                        textViewType4.setText("");
                        cancel.setVisibility(View.GONE);
                        CustomToastClass.ValidationToast(MPinLoginActivity.this, json.getString("ResponseMessage"));
                    }
                }
            }).executeTask();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method call when we set our mPin
     * Hit the api and set mPin  to the server
     */
    private void setmPIN() {
        if (!mPin.isEmpty() && mPin.equalsIgnoreCase(tempAppendStr)) {
            post_mPin = new JSONObject();
            try {
                String passPhrase = AESHelper.generateMessageEncryptionKey(keyHint);// encrypt the hint key
                String encryptDeviceID = AESHelper.encrypt(ConstantClass.getDeviceId(this), passPhrase);// Encrypt the device id
                String encryptTempStr = AESHelper.encrypt(tempAppendStr, passPhrase);// Encrypt the mPin
                String userID = AESHelper.encrypt(PEXPref.getInstance(this).readPrefs(ConstantClass.USER_ID_PREF), passPhrase);// Encrypt the user Id
                post_mPin.put("DeviceId", encryptDeviceID);
                post_mPin.put("MPinPassword", encryptTempStr);
                post_mPin.put("ResponseKey", String.valueOf(keyHint));
                post_mPin.put("UserId", userID);
                // Hit the api and get response
                new ControllerJson(MPinLoginActivity.this, ConstantClass.SETmPINRequest, String.valueOf(post_mPin), true, new TaskCompleteListener() {
                    @Override
                    public void onTaskComplete(Object obj) throws JSONException {
                        JSONObject json = new JSONObject(obj.toString());
                        int success = json.getInt("ResponseCode");
                        if (success == 1) {
                            Intent intent = new Intent(MPinLoginActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MPinLoginActivity.this, "" + json.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                        }
                    }

                }).executeTask();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            CustomToastClass.ValidationToast(this, getResources().getString(R.string.error_msg_confirm_msg));
        }
    }

    /**
     * Method for change the mPin and set new mpin
     * Hit the api of change mPin
     */
    private void changemPin() {
        if (!mPin.isEmpty() && mPin.equalsIgnoreCase(tempAppendStr)) {
            if (!currentmPIN.isEmpty()) {
                post_mPin = new JSONObject();
                try {
                    String passPhrase = AESHelper.generateMessageEncryptionKey(keyHint);// Encrypt the key Hint
                    String encryptDeviceID = AESHelper.encrypt(ConstantClass.getDeviceId(this), passPhrase);// Encrypt the device id
                    String encryptTempStr = AESHelper.encrypt(tempAppendStr, passPhrase);// Encrypt the Old mpin
                    String encryptOldStr = AESHelper.encrypt(currentmPIN, passPhrase);// Encrypt the current mPin
                    String encryptUserId = AESHelper.encrypt(PEXPref.getInstance(this).readPrefs(ConstantClass.USER_ID_PREF), passPhrase);//Encrypt the User id which is store in mobile
                    post_mPin.put("DeviceId", encryptDeviceID);
                    post_mPin.put("OldMPinPassword", encryptOldStr);
                    post_mPin.put("NewMPinPassword", encryptTempStr);
                    post_mPin.put("UserId", encryptUserId);
                    post_mPin.put("ResponseKey", String.valueOf(keyHint));
                    Log.d("DeviceId", encryptDeviceID);
                    Log.d("OldMPinPassword", encryptOldStr);
                    Log.d("NewMPinPassword", encryptTempStr);
                    Log.d("UserId", encryptUserId);
                    Log.d("ResponseKey", String.valueOf(keyHint));
                    // Hit the api for change mPin & get response
                    new ControllerJson(MPinLoginActivity.this, ConstantClass.CHANGEmPINRequest, String.valueOf(post_mPin), true, new TaskCompleteListener() {
                        @Override
                        public void onTaskComplete(Object obj) throws JSONException {
                            JSONObject json = new JSONObject(obj.toString());
                            int success = json.getInt("ResponseCode");
                            if (success == 1) {
                                Intent intent = new Intent(MPinLoginActivity.this, TransferSuccessNew.class);
                                intent.putExtra(ConstantClass.STATE, ConstantClass.RE_CHANGE_mPIN);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(MPinLoginActivity.this, "" + json.getString("ResponseMessage"), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }).executeTask();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            CustomToastClass.ValidationToast(this, getResources().getString(R.string.error_msg_confirm_msg));
        }
    }

    /**
     * Check password length which is enter by the user
     *
     * @param passwordStr
     * @return
     */
    public boolean isPasswordValid(String passwordStr) {
        return passwordStr.length() < 4;
    }


}


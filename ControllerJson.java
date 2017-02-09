package com.pex.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.pex.R;
import com.pex.interfaces.TaskCompleteListener;
import com.pex.utils.ConnectivityReceiver;
import com.pex.utils.ConstantClass;
import com.pex.utils.HTTPURLConnection;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by avikaljain on 3/2/17.
 */

public class ControllerJson {

    private boolean isShowLoader = false;
    private ProgressDialog dialog;
    private String mRequestBody;
    private TaskCompleteListener mListener = null;
    private String mUrl;
    private Context mContext;
    private String response = "";
    private HTTPURLConnection service;
    JSONObject json;

    public ControllerJson(Context context, int method, String requestBody,
                          boolean mIsShowLoader, TaskCompleteListener listener) {

        mContext = context;
        if (ConnectivityReceiver.isConnected()) {
            this.mListener = listener;
            this.mUrl = getMethodName(method);
            this.isShowLoader = mIsShowLoader;
            this.mRequestBody = requestBody;
            service = new HTTPURLConnection();
        } else {
            Toast.makeText(context, "Internet Required", Toast.LENGTH_SHORT).show();
        }
    }


    public void executeTask() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            BackgroundTask task = new BackgroundTask(mContext);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mRequestBody);
        } else {
            BackgroundTask task = new BackgroundTask(mContext);
            task.execute(mRequestBody);
        }
    }

    private class BackgroundTask extends AsyncTask<String, String, String> {

        Context mContext;

        public BackgroundTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (isShowLoader) {
                dialog = new ProgressDialog(mContext);
                dialog.setMessage(mContext.getResources().getString(R.string.please_wait));
                dialog.setCancelable(false);
                dialog.show();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            String JsonDATA = params[0];
            response = service.ServerData(mUrl, JsonDATA);
            try {
                json = new JSONObject(response);
                System.out.println("success=" + json.get("ResponseMessage"));

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    mListener.onTaskComplete(errorMsg(e.getMessage()));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(final String result) {
            super.onPostExecute(result);

            try {
                dismissDialog();

                mListener.onTaskComplete(result);
            } catch (Exception e) {
                try {
                    mListener.onTaskComplete(errorMsg(e.getMessage()));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private String errorMsg(String msg) {
        return "{\"message\":\"" + msg + "\",\"result_code\":\"0\"}";
    }

    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

   /* private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            copy.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }*/

    private String getMethodName(int methodType) {

        String methodName = "";

        switch (methodType) {
            case ConstantClass.LOGINRequest:
                methodName = ConstantClass.LOGIN_METHOD;
                break;
            case ConstantClass.SETmPINRequest:
                methodName = ConstantClass.SetmPIN_METHOD;
                break;
            case ConstantClass.LOGINviamPINRequest:
                methodName = ConstantClass.LOGINviaMPIN_METHOD;
                break;
            case ConstantClass.CHANGEmPINRequest:
                methodName = ConstantClass.CHANGEmPIN_METHOD;
                break;
        }

        methodName = ConstantClass.BASEURL + methodName;
        return methodName;
    }

}
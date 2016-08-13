package net;

import org.json.JSONException;
import org.json.JSONObject;

import secret.Config;

/**
 * 获取验证码的通信类
 * Created by QQQ on 2016/8/13.
 */
public class GetCode {

    public GetCode(String phone, final SuccessCallBack successCallback, final FailCallback failCallback) {

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    switch (jsonObj.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback != null) {
                                successCallback.onSuccess();
                            }
                            break;
                        default:
                            if (failCallback != null) {
                                failCallback.onFail();
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                    if (failCallback != null) {
                        failCallback.onFail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback != null) {
                    failCallback.onFail();
                }
            }
        }, Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUM, phone);
    }

    public interface SuccessCallBack{
        void onSuccess();
    }

    public interface FailCallback{
        void onFail();
    }
}

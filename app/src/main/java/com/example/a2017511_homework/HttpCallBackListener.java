package com.example.a2017511_homework;

/**
 * Created by lenovo on 2017/5/11.
 */
/**定义一个借接口**/
public interface HttpCallBackListener {

    void onFinish(String reponse);

    void onError(Exception e);
}

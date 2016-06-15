package com.goobole.houselabel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class TotalUtils{
    private static TotalUtils mTotalUtils;
    private static Context context;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    //滚动图片的地址
    public static final String[] viewPagerImageUrl={
            "http://192.168.155.1:8080/ZhangShangTao/Images/cc.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/title1.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/title3.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/cc.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/title1.jpg"
    };

    //图片地址
    public static final String[] imagesUrl={
            "http://192.168.155.1:8080/ZhangShangTao/Images/phone.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/computer.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/peijian.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/camera.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/clothes.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/book.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/lifeuser.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/exercise.jpg",
            "http://192.168.155.1:8080/ZhangShangTao/Images/zhu.jpg"
    };

    private TotalUtils() {
    }

    /**
     * 获取实例
     */
    public static TotalUtils getInstances(Context ctx){
        context=ctx;
        if(mTotalUtils==null){
            synchronized (TotalUtils.class){
                if(mTotalUtils==null){
                    mTotalUtils=new TotalUtils();
                    preferences = context.getSharedPreferences("ZhangShangTao", context.MODE_PRIVATE);
                    editor = preferences.edit();
                }
            }
        }
        return mTotalUtils;
    }

    /**
     * 获取裁剪后的圆形图片
     *
     * @param radius
     *            半径
     */
    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        //伸缩后的位图
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        //位图宽度和高度
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        //圆形位图宽度和高度
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        if (squareBitmap.getWidth() != diameter
                || squareBitmap.getHeight() != diameter) {
            //伸缩图片以适应环境
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);
        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());
        //抗锯齿
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        //画圆形位图
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        return output;
    }

    //返回圆形图片
    public Bitmap getRoundImage(Bitmap b,int radius){
        //8位 可修改
        Bitmap bitmap =b.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
        return roundBitmap;
    }

    //判断是否是第一次进来(读)
    public boolean isFirstComing(){
        SharedPreferences preferences =
                context.getSharedPreferences("ZhangShangTao", context.MODE_PRIVATE);
        return preferences.getBoolean("isFirstIn", true);
    }
    public void changeComingState(){
        SharedPreferences preferences =
                context.getSharedPreferences("ZhangShangTao", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
    }

    //判断是否登录
    public boolean isLogin(){
        SharedPreferences preferences =
                context.getSharedPreferences("ZhangShangTao", context.MODE_PRIVATE);
        return preferences.getBoolean("isLogin", false);
    }

    //取消登录状态
    public void cancelLoginState(){
        editor.putBoolean("isLogin", false);
        editor.remove("userID");
        editor.remove("userName");
        editor.remove("userPassWd");
        editor.remove("userGrade");
        editor.remove("userImage");
        editor.remove("userCallNumber");
        editor.remove("userPeriod");
        editor.remove("userAcademy");
        editor.remove("userSign");
        editor.commit();
    }

    //存取图片地址的操作
    public void saveImageUrl(String imageUrl){
        editor.putString("userImage", imageUrl);
        editor.commit();
    }

    public String getImageUrl(){
        return preferences.getString("userImage","");
    }

    //判断是否联网
    public boolean internetIsAccess(){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        /**
         * null:当前没有已激活的网络连接（表示用户关闭了数据流量服务，也没有开启WiFi等别的数据服务）
         * isAvailable():有已激活的连接，但是否可用
         */
        if(networkInfo==null || !networkInfo.isAvailable()||!connectivityManager.getBackgroundDataSetting()){
            Toast.makeText(context, "请开启网络", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    /**
     * 保存返回的json信息,备注：数据库userImage为空，因此JSON默认不显示，也就是没有这一项
     * @param result
     */
    public void writeUser(String result){
        try {
            JSONObject jsonObject=new JSONObject(result);
            editor.putBoolean("isLogin", true);
            editor.putInt("userID", jsonObject.getInt("userID"));
            editor.putString("userName", jsonObject.getString("userName"));
            editor.putString("userPassWd", jsonObject.getString("userPassWd"));
            editor.putString("userGrade", jsonObject.getString("userGrade"));
            editor.putString("userImage", jsonObject.getString("userImage"));
            editor.putString("userCallNumber",jsonObject.getString("userCallNumber"));
            editor.putString("userPeriod",jsonObject.getString("userPeriod"));
            editor.putString("userAcademy",jsonObject.getString("userAcademy"));
            editor.putString("userSign", jsonObject.getString("userSign"));
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取用户信息
     * @return
     */
//    public User readerUser(){
//        SharedPreferences preferences =
//                context.getSharedPreferences("ZhangShangTao", context.MODE_PRIVATE);
//        User user=new User(preferences.getInt("userID",-1),preferences.getString("userName", ""),
//                preferences.getString("userPassWd", ""),preferences.getString("userGrade", ""),
//                preferences.getString("userImage", ""),preferences.getString("userCallNumber","")
//        ,preferences.getString("userPeriod",""),preferences.getString("userAcademy",""),
//                preferences.getString("userSign",""));
//        return user;
//    }

}

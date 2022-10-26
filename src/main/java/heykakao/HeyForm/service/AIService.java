package heykakao.HeyForm.service;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class AIService {

    public String Category_recommend(String target, String[] categories) throws Exception
    {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        for( int i =0; i< categories.length; i ++){
            stringBuilder.append("\""+categories[i]+"\""+",");
        }
        String json = stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
        try{
            String tmp = "{\"title\":\""+target+"\","+"\"categories\":{"+json+"}}";
            OkHttpClient client = new OkHttpClient();
            System.out.println(tmp);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), tmp);
            System.out.println(requestBody+ " + " +requestBody.toString());
            Request.Builder builder = new Request.Builder().url("http://210.109.61.98:8000/api/ai/category")
                    .post(requestBody);
            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()){
                ResponseBody body = response.body();
                if (body != null){
                    System.out.println("Response:" + body.string());
                }
                else
                    System.out.println("Error");
                }
            else{
                System.out.println("fail");
            }

        }catch (Exception e){e.printStackTrace();}
        return result;
    }
}

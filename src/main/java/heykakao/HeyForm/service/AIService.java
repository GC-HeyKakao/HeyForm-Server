//package heykakao.HeyForm.service;
//
//import io.sentry.protocol.Request;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Response;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.io.IOException;
//import java.util.List;
//
//public class AIService {
//
//    public String Category_recommend(String target, String[] categories) throws Exception
//    {
//        System.out.println("dddd");
//        String result = "";
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//
//        MultipartBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("param",  "{\"Title\":\""+target+"\","+categories)
//                .build();
//        System.out.println("body"+body);
//        okhttp3.Request request = new okhttp3.Request.Builder().url("http://210.109.61.98:8000/api/ai/category").post(body).build();
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println("give" + response);
//            try {
//                result = response.body().string();
//                System.out.println(result);
//            }
//            catch(Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        catch(IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return result;
//    }
//}

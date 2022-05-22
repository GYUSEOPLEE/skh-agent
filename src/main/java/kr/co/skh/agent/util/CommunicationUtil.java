package kr.co.skh.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommunicationUtil {
    @Value("${server.Ip}") private String serverIp;
    @Value("${server.url}") private String url;

    public boolean request(Helmet helmet) throws Exception {
        System.out.println(serverIp);
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(helmet);
//        RequestBody requestBody = RequestBody.create(json, JSON);
//
//        Request request = new Request.Builder()
//                .url("http://" + serverIp + url)
//                .post(requestBody)
//                .build();
//
//        Response response = okHttpClient.newCall(request).execute();
//        ResponseBody responseBody = response.body();
//
//        assert responseBody != null;
//        JSONObject statusResponse = new JSONObject(responseBody.string());
//
//        return "200".equals(statusResponse.getString("code"));
        return true;
    }

    public boolean request(HelmetWear helmetWear) {
        return false;
    }

    public boolean request(HelmetLocation helmetLocation) {
        return false;
    }
}

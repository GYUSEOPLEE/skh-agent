package kr.co.skh.agent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.json.*;

import okhttp3.*;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.util.Properties;

@Log4j2
@Component
public class CommunicationUtil {
    @Value("${hlm-protocol}") private String protocol;
    @Value("${hlm-url-info}") private String infoUrl;
    @Value("${hlm-url-wear}") private String wearUrl;
    @Value("${hlm-url-location}") private String locationUrl;

    static Properties info = new Properties();

    // 헬멧 정보 전송
    public boolean request(Helmet helmet) throws IOException, JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(helmet);

        return "200".equals(createRequest(json, infoUrl).getString("code"));
    }

    // 헬멧 착용 여부 전송
    public boolean request(HelmetWear helmetWear) throws IOException, JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(helmetWear);

        return "200".equals(createRequest(json, wearUrl).getString("code"));
    }

    // 헬멧 위치 정보 전송
    public boolean request(HelmetLocation helmetLocation) throws IOException, JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(helmetLocation);

        return "200".equals(createRequest(json, locationUrl).getString("code"));

    }

    // 송신 중복 코드 생성 메소드
    public JSONObject createRequest(String json, String url) throws IOException, JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(protocol + info.getProperty("systemIp") + url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();

        assert responseBody != null;
        return new JSONObject(responseBody.string());
    }
}

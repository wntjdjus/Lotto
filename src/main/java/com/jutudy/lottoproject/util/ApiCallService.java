package com.jutudy.lottoproject.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class ApiCallService {

    public JSONObject apiCall(String url, String method, Map<String, String> header, JSONObject body) {

        JSONObject response = null;

        try {
            URL targetUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
            conn.setRequestMethod(method);
            if (header != null) {

                for (Map.Entry<String, String> entry : header.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (body != null) {

                conn.setDoOutput(true);

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                osw.write(body.toString());
                osw.flush();
                osw.close();
            } else {

                conn.setDoOutput(false);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            if (conn.getResponseCode() != 200) {
                System.out.println("Fail : " + conn.getResponseCode());
                throw new RuntimeException("Fail : " + conn.getResponseCode());
            } else {
                System.out.println("성공");
            }

            JSONParser parser = new JSONParser();
            response = (JSONObject) parser.parse(br);
            br.close();
            conn.disconnect();
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return response;
    }
}

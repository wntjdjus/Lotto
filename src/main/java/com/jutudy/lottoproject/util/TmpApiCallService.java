package com.jutudy.lottoproject.util;

import org.json.simple.JSONArray;
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
import java.util.HashMap;
import java.util.Map;

@Service
public class TmpApiCallService {

    private final String baseUrl = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";

    public JSONObject apiCall(String url, String method, Map<String, String> header, JSONObject body) {

        JSONObject response = null;

        try {
            URL targetUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
            conn.setRequestMethod(method);
            for (Map.Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
                System.out.println(entry.getKey() + " " + entry.getValue());
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

//            String line = null;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
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

    public JSONObject solve() {

        /* start */
        System.out.println("[Call start]");
        String url = baseUrl + "/start";
        Map<String, String> header = new HashMap<>();
        header.put("X-Auth-Token", "5c1a8899b1908fb91158db55e8b2bb26");
        header.put("Content-Type", "application/json");

        JSONObject body = new JSONObject();
        body.put("problem", 1);

        JSONObject response = this.apiCall(url, "POST", header, body);
        System.out.println(response.toString());

        String auth_key = response.get("auth_key").toString();

        /* location */
        System.out.println("[Call location]");
        url = baseUrl + "/locations";
        header = new HashMap<>();
        header.put("Authorization", auth_key);
        header.put("Content-Type", "application/json");

        response = this.apiCall(url, "GET", header, null);
        JSONArray locations = (JSONArray) response.get("locations");
        int lSize = locations.size();
        for (int i = 0; i < lSize; i++) {
            System.out.println(locations.get(i));
        }

        /* trucks */
        System.out.println("[Call trucks]");
        url = baseUrl + "/trucks";

        response = this.apiCall(url, "GET", header, null);
        JSONArray trucks = (JSONArray) response.get("trucks");
        int tSize = trucks.size();
        for (int i = 0; i < tSize; i++) {
            System.out.println(trucks.get(i));
        }

        /* simulate */
        while(true){

            System.out.println("[Call simulate]");
            url = baseUrl + "/simulate";
            body = new JSONObject();
            JSONArray truckCommands = new JSONArray();

            for(int i=0;i<tSize;i++){
                JSONObject truckCommand = new JSONObject();
                JSONObject truck = (JSONObject) trucks.get(i);
                truckCommand.put("truck_id", truck.get("id"));

                JSONArray command = new JSONArray();
                for(int j=0;j<5;j++){
                    command.add(j);
                }
                truckCommand.put("command", command);

                truckCommands.add(truckCommand);
            }
            body.put("commands", truckCommands);

            System.out.println(body.get("commands").toString());

            response = this.apiCall(url, "PUT", header, body);

            System.out.println(response.toString());

            if(response.get("status").equals("finished")){
                break;
            }
        }

        /* score */
        url = baseUrl + "/score";

        response = this.apiCall(url, "GET", header, null);

        System.out.println(response.get("score"));

        return response;
    }
}

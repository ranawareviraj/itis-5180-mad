package com.example.okhttp;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createContact("Tango Charlie", "tc@tc.com", "111-222-3333", "CELL");
    }

    void createContact(String name, String email, String phone, String type) {
        HttpUrl url = HttpUrl.parse("https://www.theappsdr.com")
                .newBuilder()
                .addPathSegment("contact")
                .addPathSegment("json")
                .addPathSegment("create")
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("type", type)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Log.d("API", "createContact: " + request);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d("API", "onResponse: " + body);

                    //Updating UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println();
                        }
                    });

                } else {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d("API", "onResponse: " + body);

//                    Json Parsing
//                    try {
//                        JSONObject jsonObject = new JSONObject(body);
//                        Log.d("API", "onResponse: " + jsonObject);
//                        JSONArray jsonArray  = jsonObject.getJSONArray(" ");
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }

                    //Json Parsing with GSON
                    Gson gson = new Gson();
                    Person person = gson.fromJson(responseBody.charStream(), com.example.okhttp.Person.class);
                }
            }
        });
    }
}

/*
//        HttpUrl url = HttpUrl.parse("https://www.theappsdr.com").newBuilder()
//                .addPathSegment("contacts")
//                .addPathSegment("json")
//                .build();

        HttpUrl.Builder builder = new HttpUrl.Builder();
        HttpUrl url = builder.scheme("https")
                .host("www.theappsdr.com")
                .addPathSegment("contacts")
                .addPathSegment("json")
                .build();

        Request request = new Request.Builder()
//                .url("https://publicobject.com/helloworld.txt")
                .url(url)
                .build();

                client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    assert responseBody != null;
                    String body = responseBody.string();
                    Log.d("API", "onResponse: " + body);
                }
            }
        });
 */
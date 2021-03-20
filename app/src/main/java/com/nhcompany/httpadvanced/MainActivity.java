package com.nhcompany.httpadvanced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button getData , showData , retrofit ;
    RecyclerView recyclerView ;

    String name , profileImage;
    int likes ;
    UserAdapter adapter;
    RetrofitAdapter adapter2;
    ArrayList<UserDetails> users;

    String link = "http://pastebin.com/raw/wgkJgazE";
    HttpURLConnection httpURLConnection;
    URL url ;
    InputStream inputStream;
    String result ;
    //StringBuffer buffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData = findViewById(R.id.getData);
        showData = findViewById(R.id.showData);
        recyclerView = findViewById(R.id.recycler);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // backgroundThread or workerThread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL(link);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        try {
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setReadTimeout(5000);
                            httpURLConnection.setConnectTimeout(5000);
                            httpURLConnection.setRequestMethod("GET");

                            inputStream = httpURLConnection.getInputStream();

                            int c = 0;
                            StringBuffer stringBuffer = new StringBuffer();

                            int statusCode = httpURLConnection.getResponseCode();
                            if (statusCode == HttpURLConnection.HTTP_OK) {
                                while ((c = inputStream.read()) != -1) {
                                    stringBuffer.append((char) c);
                                }
                            }
                            result = stringBuffer.toString();
                            users = new ArrayList<>();

                            // manual Parcing !!
                            JSONArray array = new JSONArray(result);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);
                                likes = object.getInt("likes");

                                JSONObject object1 = object.getJSONObject("user");
                                name = object1.getString("name");

                                JSONObject object2 = object1.getJSONObject("profile_image");
                                profileImage = object2.getString("medium");

                                users.add(new UserDetails(name , likes , profileImage));
                            }

                            inputStream.close();
                            httpURLConnection.disconnect();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finally {
                            httpURLConnection.disconnect();
                        }
                    }
                }).start();

                showData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter = new UserAdapter(MainActivity.this , users);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                });

                retrofit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Retrofit retrofit1 = new Retrofit.Builder().baseUrl("http://pastebin.com/raw/")
                                .addConverterFactory(GsonConverterFactory.create()).build();
                        RetrofitService retrofitService = retrofit1.create(RetrofitService.class);
                        retrofitService.getPosts().enqueue(new Callback<ArrayList<UserData>>() {
                            @Override
                            public void onResponse(Call<ArrayList<UserData>> call, Response<ArrayList<UserData>> response) {
                                RetrofitAdapter retrofitAdapter = new RetrofitAdapter(MainActivity.this , response.body());
                                recyclerView.setAdapter(retrofitAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            }

                            @Override
                            public void onFailure(Call<ArrayList<UserData>> call, Throwable t) {

                            }
                        });


                    }
                });
            }


        });
    }
}

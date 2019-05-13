package com.appsmontreal.guessthecelebrity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.ImageContent;
import Controller.WebContent;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String WEBSOURCE = "http://www.posh24.se/kandisar";
    public String imageSource;
    WebContent webContent;
    ImageContent imageContent;
    Bitmap myImage;
    ArrayList<String> celebritiesNames;
    ArrayList<Bitmap> celebritiesPhotos;
    FirebaseUser user;
    TextView userTextView;
    TextView scoreTextView;
    int score;
    String result;
    Button buttons[] = new Button[6];
    int buttonWidgets[] = {R.id.nameButton1,R.id.nameButton2,R.id.nameButton3,R.id.nameButton4,R.id.restartButton,R.id.exitButton};



//    public class DownloadTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//
//            String result = "";
//            URL url;
//            HttpURLConnection urlConnection = null;
//
//            try {
//                url = new URL(urls[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream in = urlConnection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in);
//                int data = reader.read();
//
//                while (data != -1) {
//                    char current = (char) data;
//
//                    result += current;
//
//                    data = reader.read();
//                }
//
//                return result;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//                return "Failed";
//            }
//        }
//    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();

//        DownloadTask task = new DownloadTask();
//        String result = null;
//        try {
//            result = task.execute("http://www.posh24.se/kandisar").get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Log.i("Result",result);
    }

    private void initialize() {
        celebritiesNames = new ArrayList<>();
        downloadWebContent();
        getImages();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userTextView = findViewById(R.id.userTextView);
        userTextView.setText(user.getDisplayName());
        scoreTextView = findViewById(R.id.scoreTextView);
        resetScore();

        for (int b = 0; b < buttons.length; b++){
            buttons[b] = findViewById(buttonWidgets[b]);
            buttons[b].setOnClickListener(this);
        }
    }


    public void downloadWebContent(){
        webContent = new WebContent();
        result = null;
        try {
            result = webContent.execute("http://www.posh24.se/kandisar").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("Result================>",result);




//        DownloadTask task = new DownloadTask();
//        String result = null;
//        try {
//            result = task.execute("http://www.posh24.se/kandisar").get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Log.i("Result",result);
    }


    private void getImages() {
        Log.i("url ================>","we're here");
        imageContent = new ImageContent();
        Pattern p = Pattern.compile("<img src=\"(.*?)\"");//////////////////////////////
        Matcher m = p.matcher(result);
        try {
            while (m.find()) {
                Log.i("url ================>",m.group(1));
                imageSource = m.group(1);//get image rul
//                myImage = imageContent.execute(imageSource).get();//send image Url
//                celebritiesPhotos.add(myImage);//add new image to array
            }
            Log.i("================>","finish");
            p = Pattern.compile("alt=\"(.*?)\"");//////////////////////////////

            m = p.matcher(result);
            while (m.find()) {
                Log.i("name ================>",m.group(1));
                imageSource = m.group(1);//get image rul
//                myImage = imageContent.execute(imageSource).get();//send image Url
//                celebritiesPhotos.add(myImage);//add new image to array
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }


    public void resetScore(){
        score = 0;
        scoreTextView = findViewById(R.id.scoreTextView);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exitButton:
                finish();
                break;

            case R.id.restartButton:
                resetScore();

        }

    }
}

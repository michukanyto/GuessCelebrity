package com.appsmontreal.guessthecelebrity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
    ArrayList<String> celebritiesPhotos;
    FirebaseUser user;
    TextView userTextView;
    TextView scoreTextView;
    ImageView celebrityImageView;
    int score;
    String result;
    Button buttons[] = new Button[6];
    int buttonWidgets[] = {R.id.nameButton1,R.id.nameButton2,R.id.nameButton3,R.id.nameButton4,R.id.restartButton,R.id.exitButton};
    String[] splitResult;
    Random random;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {
        celebritiesNames = new ArrayList<String>();
        celebritiesPhotos = new ArrayList<String>();
        downloadWebContent();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userTextView = findViewById(R.id.userTextView);
        userTextView.setText(user.getDisplayName());
        celebrityImageView = findViewById(R.id.celebrityImageView);
        scoreTextView = findViewById(R.id.scoreTextView);
        random  = new Random();
        resetScore();

        for (int b = 0; b < buttons.length; b++){
            buttons[b] = findViewById(buttonWidgets[b]);
            buttons[b].setOnClickListener(this);
        }
        nextQuestion();
    }


    public void downloadWebContent(){
        webContent = new WebContent();
        result = null;
        try {
            result = webContent.execute(WEBSOURCE).get();
            splitResult = result.split("<div clss=\"listedArticles\">");

        Log.i("Result================>",result);

        Pattern p = Pattern.compile("<img src=\"(.*?)\"");//////////////////////////////
        Matcher m = p.matcher(splitResult[0]);

            while (m.find()) {
                celebritiesPhotos.add(m.group(1));
                imageSource = m.group(1);//get image rul
            }

            Log.i("url ================>",celebritiesPhotos.toString());
            Log.i("================>","finish");
            p = Pattern.compile("\" alt=\"(.*?)\"/>");//////////////////////////////

            m = p.matcher(splitResult[0]);
//            m = p.matcher(result);
            while (m.find()) {
                celebritiesNames.add(m.group(1));
                imageSource = m.group(1);//get image rul
            }
            Log.i("Names ================>",celebritiesNames.toString());
            int tmp = celebritiesNames.size();
            Log.i("size ================>",String.valueOf(tmp));
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    private void nextQuestion(){
        setImages();
        celebrityImageView.setImageBitmap(myImage);
    }


    private void setImages() {
        Log.i("url ================>","we're here");
        imageContent = new ImageContent();

        try {
            myImage = imageContent.execute(celebritiesPhotos.get(random.nextInt(88))).get();
        } catch (Exception e) {
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

            case R.id.nameButton1:
                nextQuestion();
                break;

            case R.id.nameButton2:
                nextQuestion();
                break;

            case R.id.nameButton3:
                nextQuestion();
                break;

            case R.id.nameButton4:
                nextQuestion();
                break;
        }
    }
}

package com.appsmontreal.guessthecelebrity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseUser user;
    TextView userTextView;
    TextView scoreTextView;
    int score;
    Button buttons[] = new Button[6];
    int buttonWidgets[] = {R.id.nameButton1,R.id.nameButton2,R.id.nameButton3,R.id.nameButton4,R.id.restartButton,R.id.exitButton};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialize();
    }

    private void initialize() {
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

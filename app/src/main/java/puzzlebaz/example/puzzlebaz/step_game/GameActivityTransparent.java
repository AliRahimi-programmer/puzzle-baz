package puzzlebaz.example.puzzlebaz.step_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.main.MainActivity;

public class GameActivityTransparent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_transparent);
        Intent intent = new Intent(this, gameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GameActivityTransparent.this, MainActivity.class));
    }
}
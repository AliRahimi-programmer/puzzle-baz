package puzzlebaz.example.puzzlebaz.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import puzzlebaz.example.puzzlebaz.CustomClasses.service;
import com.example.puzzlebaz.R;

public class LoadingPage extends AppCompatActivity {

    private int progress_count = 0;
    private ProgressBar progressBar;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        timer = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long l) {
                progress_count += 10;
                progressBar.setProgress(progress_count);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(LoadingPage.this, MainActivity.class);
                startActivity(intent);

            }
        }.start();

        if (getIntent().getBooleanExtra("EXIT", false)) {
            this.stopService(new Intent(LoadingPage.this, service.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
    }
}
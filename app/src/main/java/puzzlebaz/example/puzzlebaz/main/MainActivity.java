package puzzlebaz.example.puzzlebaz.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import puzzlebaz.example.puzzlebaz.BaseClass.model;
import puzzlebaz.example.puzzlebaz.CustomClasses.ExitPanel;
import puzzlebaz.example.puzzlebaz.CustomClasses.SettingPanel;
import puzzlebaz.example.puzzlebaz.CustomClasses.service;
import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.adapter.Adapter;
import puzzlebaz.example.puzzlebaz.database.DataBaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    Adapter adapter;
    List<model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    MediaPlayer mediaPlayer;
    ImageButton setting;
    DataBaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.button_sound);


        myDB = new DataBaseHandler(this);
        int state = myDB.GetState(1);

        if (state == 1){
            startService(new Intent(this, service.class));

        }

        setting = (ImageButton)findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.start();

                SettingPanel panel = new SettingPanel();
                panel.showDialog(MainActivity.this, mediaPlayer);

            }
        });



        models = new ArrayList<>();
        models.add(new model(R.drawable.puzzle));
        models.add(new model(R.drawable.coming_soon));
        models.add(new model(R.drawable.coming_soon));
        models.add(new model(R.drawable.coming_soon));



        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(230, 0, 230, 0);

        Integer[] color_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = color_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void onBackPressed(){

        ExitPanel panel = new ExitPanel();
        panel.showDialog(this);


    }


}






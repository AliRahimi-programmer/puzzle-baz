package puzzlebaz.example.puzzlebaz.step_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.adapter.CustomAdapter;
import puzzlebaz.example.puzzlebaz.database.DataBaseHandler;

import java.util.ArrayList;

public class gameActivity extends AppCompatActivity implements GridViewAdapter.OnItemListener {

    ArrayList<Integer> imageList = new ArrayList<>();
    ArrayList<Integer> status_ratingg = new ArrayList<>();
    GridView gridView;

    DataBaseHandler myDB;

    int ID[] = {
            1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10 ,
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30
    };
    int status[] = {0,1,2,3,4,5};

    TextView textView;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        initialize();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_game);

        initialize();
    }

    private void initialize() {



        gridView = (GridView)findViewById(R.id.gridView);
        textView = (TextView)findViewById(R.id.score);

        myDB = new DataBaseHandler(this);

        //insert initial data to database
        myDB.insertData(ID, status);

        Cursor result = myDB.getAllData();

            while (result.moveToNext()){
                switch (result.getInt(0)){
                    case 1:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level1);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 2:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level2 );
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 3:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level3);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 4:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level4);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 5:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level5);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 6:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level6);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 7:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level7);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 8:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level8);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 9:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level9);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 10:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level10);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 11:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level11);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 12:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level12);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 13:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level13);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 14:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level14);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 15:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level15);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 16:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level16);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 17:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level17);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 18:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level18);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 19:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level19);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 20:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level20);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 21:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level21);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 22:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level22);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 23:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level23);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 24:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level24);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 25:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level25);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 26:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level26);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 27:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level27);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 28:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level28);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 29:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level29);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;
                    case 30:
                        if (result.getInt(3) == 1 || result.getInt(1) == 4)
                            imageList.add(R.drawable.level30);
                        else
                            imageList.add(R.drawable.level_lock);
                        break;

                }
            }

        int item = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();

        Cursor cursor = myDB.getAllData();
        while(cursor.moveToNext()){
            if(cursor.getInt(1) == 0)
                status_ratingg.add(R.drawable.zero_star);
//                status_rating[item] = R.drawable.zero_star;
            else if (cursor.getInt(1) == 1)
                status_ratingg.add(R.drawable.one_star);
//                status_rating[item] = R.drawable.one_star;
            else if (cursor.getInt(1) == 2)
                status_ratingg.add(R.drawable.two_star);
//                status_rating[item] = R.drawable.two_star;
            else if (cursor.getInt(1) == 3)
                status_ratingg.add(R.drawable.three_star);
//                status_rating[item] = R.drawable.three_star;
            else if (cursor.getInt(1) == 4)
                status_ratingg.add(R.drawable.zero_star);
//                status_rating[item] = R.drawable.open_lock;
            else if (cursor.getInt(1) == 5) {
                status_ratingg.add(R.drawable.null_pic);
                list.add(item);

            }


            item++;
        }

        CustomAdapter customAdapter = new CustomAdapter(this, status_ratingg, imageList);
        customAdapter.setList(list);
        gridView.setAdapter(customAdapter);
        gridView.setNumColumns(5);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(gameActivity.this, MainActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        int SumScore = myDB.getSumScore();
        if (SumScore != -1)
            textView.setText(""+SumScore);


    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(gameActivity.this, MainActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
            startActivity(new Intent(gameActivity.this, puzzlebaz.example.puzzlebaz.main.MainActivity.class));

    }
}

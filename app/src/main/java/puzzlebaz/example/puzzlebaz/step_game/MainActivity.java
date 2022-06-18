package puzzlebaz.example.puzzlebaz.step_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.puzzlebaz.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_step);

        int position = getIntent().getIntExtra("position", 1);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


        switch (position){
            case 0:
                fragmentTransaction.add(R.id.container, new Step1());
                fragmentTransaction.commit();
                break;
            
        }



    }
}

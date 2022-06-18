package puzzlebaz.example.puzzlebaz.CustomClasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.step_game.GameActivityTransparent;
import puzzlebaz.example.puzzlebaz.step_game.MainActivity;

public class CustomDialogEndTime{

    public void showDialog(final Activity activity, final String step){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog_end_time_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final MediaPlayer mp = MediaPlayer.create(activity, R.raw.button_sound);

        FrameLayout mDialogBack = dialog.findViewById(R.id.FrameDialog_Back);
        mDialogBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(activity, GameActivityTransparent.class);
                activity.startActivity(intent);
                dialog.dismiss();


            }
        });

        FrameLayout mDialogRestart = dialog.findViewById(R.id.FrameDialog_Restart);
        mDialogRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(activity, MainActivity.class);
                switch (step){
                    case "Step1":

                        intent.putExtra("position", 0);
                        activity.startActivity(intent);
                        dialog.dismiss();
                        break;
                   
                }

            }
        });

        dialog.show();
    }

}


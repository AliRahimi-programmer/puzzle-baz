package puzzlebaz.example.puzzlebaz.CustomClasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import androidx.appcompat.widget.SwitchCompat;

import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.database.DataBaseHandler;
import puzzlebaz.example.puzzlebaz.main.LoadingPage;

public class ExitPanel {

    SwitchCompat switchCompat;
    DataBaseHandler myDB;

    Context context;

    public void showDialog(final Activity activity){

        context = activity;
        final Dialog dialog = new Dialog(activity);

        final MediaPlayer mp = MediaPlayer.create(activity, R.raw.button_sound);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_panel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.SettingPanelAnimation;


        ImageButton mDialog_No = dialog.findViewById(R.id.FrameDialog_No);

        mDialog_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.start();

                dialog.cancel();

            }
        });


        ImageButton mDialog_Yes = dialog.findViewById(R.id.FrameDialog_Yes);

        mDialog_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.start();

                Intent intent = new Intent(context, LoadingPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                context.startActivity(intent);

            }
        });

        dialog.show();
    }

}

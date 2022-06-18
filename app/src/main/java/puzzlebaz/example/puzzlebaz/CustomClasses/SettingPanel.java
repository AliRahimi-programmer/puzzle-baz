package puzzlebaz.example.puzzlebaz.CustomClasses;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.database.DataBaseHandler;

public class SettingPanel extends AppCompatActivity {

    SwitchCompat switchCompat;
    DataBaseHandler myDB;

    public void showDialog(final Activity activity, final MediaPlayer mediaPlayer){
        final Dialog dialog = new Dialog(activity);
        myDB = new DataBaseHandler(activity);

        boolean check = myDB.InsertMusicState();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.setting_panel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.SettingPanelAnimation;

        final MediaPlayer mp = MediaPlayer.create(activity, R.raw.button_sound);

        ImageButton mDialogNo = dialog.findViewById(R.id.FrameDialog_Cancel);
        switchCompat = (SwitchCompat)dialog.findViewById(R.id.switchSetting);
        int state = myDB.GetState(1);

        switch (state){
            case 0:
                switchCompat.setChecked(false);
                activity.stopService(new Intent(activity, service.class));
                break;
            case 1:
                switchCompat.setChecked(true);
                activity.startService(new Intent(activity, service.class));
                break;
            case -1:
                Toast.makeText(activity, "occurred an error!", Toast.LENGTH_SHORT).show();
        }
        mDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();

                dialog.cancel();

            }
        });


        ImageButton mDialogRestart = dialog.findViewById(R.id.FrameDialog_Ok);

        mDialogRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();


                if (switchCompat.isChecked()&& switchCompat.isClickable()){

                    activity.startService(new Intent(activity,service.class));
                    myDB.UpdateMusicState(1,1);

                }else {

                    activity.stopService(new Intent(activity,service.class));
                    myDB.UpdateMusicState(1,0);


                }
                dialog.cancel();


            }
        });

        dialog.show();
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}

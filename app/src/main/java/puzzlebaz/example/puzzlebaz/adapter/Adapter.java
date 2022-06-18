package puzzlebaz.example.puzzlebaz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import puzzlebaz.example.puzzlebaz.BaseClass.model;
import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.step_game.GameActivityTransparent;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<model> models, Context context) {

        this.models = models;
        this.context = context;

    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_view_pager, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);

        imageView.setImageResource(models.get(position).getImage());

        container.addView(view, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;

                switch (position){

                    case 0:

                        context.startActivity(new Intent(context, GameActivityTransparent.class));

                        break;
                    case 1:
                        Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
                        break;


                }

            }
        });

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}

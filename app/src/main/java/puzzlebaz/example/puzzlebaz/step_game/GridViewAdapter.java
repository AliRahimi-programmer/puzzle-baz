package puzzlebaz.example.puzzlebaz.step_game;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzlebaz.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GView> {



    List<Integer> imageList = new ArrayList<>();
    List<Integer> status_ratingg = new ArrayList<>();

    private OnItemListener itemListener;

    public GridViewAdapter(List<Integer> imageList, List<Integer> status_ratingg, OnItemListener itemListener) {

        this.imageList = imageList;
        this.status_ratingg = status_ratingg;
        this.itemListener = itemListener;
    }


    @NonNull
    @Override
    public GView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid, parent, false);

        return new GView(view, itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final GView holder, final int position) {

        holder.image.setImageResource(imageList.get(position));
        holder.status.setImageResource(status_ratingg.get(position));

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class GView extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image, status;
        OnItemListener onItemListener;

        public GView(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            status = (ImageView)itemView.findViewById(R.id.status);

            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            onItemListener.OnItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener{
        public void OnItemClick(int position);
    }


}

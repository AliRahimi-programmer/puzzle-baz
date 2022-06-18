package puzzlebaz.example.puzzlebaz.step_game;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import puzzlebaz.example.puzzlebaz.CustomClasses.CustomDialog;
import puzzlebaz.example.puzzlebaz.CustomClasses.CustomDialogEndTime;
import com.example.puzzlebaz.R;
import puzzlebaz.example.puzzlebaz.adapter.PuzzleAdapter;
import puzzlebaz.example.puzzlebaz.database.DataBaseHandler;
import puzzlebaz.example.puzzlebaz.models.Pieces;
import puzzlebaz.example.puzzlebaz.models.PuzzlePiece;
import puzzlebaz.example.puzzlebaz.puzzle.Puzzle;
import com.idlestar.ratingstar.RatingStarView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Step1 extends Fragment {

    RelativeLayout relativeLayout;
    FrameLayout scrollView, frameLayout;
    ImageView imageView;
    Context context;
    List<Pieces> piecesModelListMain = new ArrayList<Pieces>();
    HashMap<String, Pieces> piecesModelHashMap = new HashMap<String, Pieces>();
    int countGrid = 0;
    ArrayList<PuzzlePiece> puzzlePiecesList = new ArrayList<PuzzlePiece>();
    private RecyclerView rvPuzzle;
    private RecyclerView.LayoutManager linearLayoutManager;
    private PuzzleAdapter puzzleListAdapter;
    Puzzle puzzle;

    boolean widthCheck = true;
    int widthFinal;
    int heightFinal;

    TextView text;
    int count = 3;

    RatingStarView rsv_rating;
    CountDownTimer Timer;

    DataBaseHandler myDB;

    ImageView EndGif;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         myDB = new DataBaseHandler(getActivity());



        View view = inflater.inflate(R.layout.fragment_step1, container, false);
        context = getActivity();

        text = (TextView)view.findViewById(R.id.textView);
        rsv_rating = (RatingStarView) view.findViewById(R.id.ratingBar);

        Timer = new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {
                text.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {

                int state = myDB.getStepGame(1).getInt(3);
                if(state == 0){
                    myDB.updateData(1,0, 0, 1);
                }else {

                    int score = myDB.getStepGame(1).getInt(2);

                    myDB.updateData(1, myDB.getStepGame(1).getInt(1), score, 1);
                }

                CustomDialogEndTime dialog = new CustomDialogEndTime();
                dialog.showDialog(getActivity(), "Step1");
            }

        }.start();

        imageView = (ImageView)view.findViewById(R.id.frameImage);
        scrollView = (FrameLayout)view.findViewById(R.id.scrollView);
        scrollView.setOnDragListener(new MyDragListener(null, EndGif));

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        relativeLayout.setOnDragListener(new MyDragListener(null, EndGif));
        rvPuzzle = (RecyclerView)view.findViewById(R.id.listView2);
        rvPuzzle.setOnDragListener(new MyDragListener(null, EndGif));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvPuzzle.setLayoutManager(linearLayoutManager);

        EndGif = (ImageView)view.findViewById(R.id.imageEnd);

        frameLayout = (FrameLayout)view.findViewById(R.id.FrameLayout);
        frameLayout.setOnDragListener(new MyDragListener(null, EndGif));

        puzzle = new Puzzle(R.drawable.angry_birds);
        puzzlePiecesList.clear();


        final ViewTreeObserver obs = scrollView.getViewTreeObserver();
        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (widthCheck) {
                    widthFinal = scrollView.getWidth();
                    heightFinal = scrollView.getHeight();
                    puzzlePiecesList = puzzle.createPuzzlePieces(getActivity(), null, widthFinal, heightFinal, imageView, "/puzzles/", 2, 2);
                    getAdapter();
                    widthCheck = false;
                }
            }
        });

        Cursor state = myDB.getStepGame(1);



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    Timer.cancel();
                    v.setBackgroundColor(Color.BLACK);
                    Intent intent = new Intent(getActivity(), GameActivityTransparent.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }


    public void getAdapter() {
        RelativeLayout.LayoutParams params;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                PuzzlePiece piece = puzzlePiecesList.get(countGrid);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                int dimX = piece.getAnchorPoint().x - piece.getCenterPoint().x;
                int dimY = piece.getAnchorPoint().y - piece.getCenterPoint().y;

                params.setMargins(dimX, dimY, 0, 0);
                final ImageView button2 = new ImageView(getActivity());
                button2.setId(generateViewId());
                button2.setTag(i + "," + j);

                button2.setImageResource(R.drawable.ic_1);

                button2.setOnDragListener(new MyDragListener(button2, EndGif));
                button2.setLayoutParams(params);
                relativeLayout.addView(button2);

                Pieces piecesModel = new Pieces();
                piecesModel.setpX(i);
                piecesModel.setpY(j);
                piecesModel.setPosition(countGrid);
                piecesModel.setOriginalResource(puzzlePiecesList.get(countGrid).getImage());
                piecesModelListMain.add(piecesModel);
                Collections.shuffle(piecesModelListMain);
                piecesModelHashMap.put(i + "," + j, piecesModel);
                piecesModel = null;

                countGrid++;

            }
        }

        if (puzzleListAdapter != null)
            puzzleListAdapter = null;

        puzzleListAdapter = new PuzzleAdapter(getActivity(), piecesModelListMain);
        rvPuzzle.setHasFixedSize(true);

        rvPuzzle.setAdapter(puzzleListAdapter);

        puzzleListAdapter.notifyDataSetChanged();
    }
    public int generateViewId() {
        final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
    public void setPuzzleListAdapter() {
        if (puzzleListAdapter != null)
            puzzleListAdapter = null;

        puzzleListAdapter = new PuzzleAdapter(getActivity(), piecesModelListMain);
        rvPuzzle.setHasFixedSize(true);

        rvPuzzle.setAdapter(puzzleListAdapter);

        puzzleListAdapter.notifyDataSetChanged();
    }

    public class MyDragListener implements View.OnDragListener {

        final ImageView imageView_D;
        ImageView EndGameGif;

        MyDragListener(final ImageView imageView, ImageView EndGameGif) {
            this.imageView_D = imageView;
            this.EndGameGif = EndGameGif;

        }


        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundResource(R.drawable.target_shape);    //change the shape of the view
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundResource(R.drawable.normal_shape);    //change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    //v is the dynamic grid imageView, we accept the drag item
                    //view is listView imageView the dragged item
                    if (v == imageView_D) {
                        View view = (View) event.getLocalState();

                        ViewGroup owner = (ViewGroup) v.getParent();
                        if (owner == relativeLayout) {
                            String selectedViewTag = view.getTag().toString();

                            Pieces piecesModel = piecesModelHashMap.get(v.getTag().toString());
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();

                            if (xy.equals(selectedViewTag)) {
                                ImageView image = (ImageView) v;
                                image.setImageBitmap(piecesModel.getOriginalResource());
                                piecesModelListMain.remove(piecesModel);
                                setPuzzleListAdapter();
                                piecesModel = null;
                                if (piecesModelListMain.size() == 0) {
                                    Timer.cancel();
                                    LayoutInflater inflater = getLayoutInflater();
                                    View inflate = inflater.inflate(R.layout.good_toast, (ViewGroup) view.findViewById(R.id.toast_layout));

                                    final Toast toast = new Toast(getActivity());
                                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.setView(inflate);
                                    toast.show();

                                    scrollView.setVisibility(View.GONE);
                                    imageView.setVisibility(View.GONE);

                                    EndGameGif.setVisibility(View.VISIBLE);

                                    Glide.with(getActivity()).load(R.raw.angry_birds_gif).into(EndGameGif);

                                    int score = 0;

                                    switch (count) {
                                        case 3:
                                            score = 60;
                                            break;
                                        case 2:
                                            score = 40;
                                            break;
                                        case 1:
                                            score = 20;
                                            break;

                                    }

                                    if (myDB.getStepGame(1).getInt(3) == 1) {
                                        if (score >= myDB.getStepGame(1).getInt(2)) {
                                            myDB.updateData(1, count, score, 1);
                                        }
                                        if (myDB.getStepGame(2).getInt(1) == 5){
                                            myDB.updateData(2, 4, 0, 0);
                                        }

                                    } else if (myDB.getStepGame(1).getInt(3) == 0) {
                                        myDB.updateData(1, count, score, 1);
                                        myDB.updateData(2, 4, 0, 0);
                                    }

                                    CountDownTimer timer_dialog = new CountDownTimer(10000, 1000) {
                                        @Override
                                        public void onTick(long l) {

                                        }

                                        @Override
                                        public void onFinish() {
                                            CustomDialog dialog = new CustomDialog();
                                            dialog.showDialog(getActivity(), "Step1");
                                        }
                                    }.start();


                                }
                            } else {
                                piecesModel = null;
                                view.setVisibility(View.VISIBLE);
                                count--;
                                rsv_rating.setRating(count);

                                if(count == 0){

                                    if (myDB.getStepGame(1).getInt(3) == 1 ){
                                            myDB.updateData(1, myDB.getStepGame(1).getInt(1), myDB.getStepGame(1).getInt(2), 1);


                                    }else if (myDB.getStepGame(1).getInt(3) == 0){
                                        myDB.updateData(1,count, 0, 1);
                                    }


                                    Intent intent = new Intent(getActivity(), GameActivityTransparent.class);
                                    intent.putExtra("stepID", 1);
                                    intent.putExtra("numStar", 0);
                                    startActivity(intent);
                                }

                                LayoutInflater inflater = getLayoutInflater();
                                View inflate = inflater.inflate(R.layout.oops_toast, (ViewGroup)view.findViewById(R.id.toast_layout));

                                final Toast toast = new Toast(getActivity());
                                toast.setGravity(Gravity.BOTTOM, 0, 0);
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(inflate);
                                toast.show();
                                break;
                            }
                        } else {
                            View view1 = (View) event.getLocalState();
                            view1.setVisibility(View.VISIBLE);
                            LayoutInflater inflater = getLayoutInflater();
                            View inflate = inflater.inflate(R.layout.oops_toast, (ViewGroup)view.findViewById(R.id.toast_layout));

                            final Toast toast = new Toast(getActivity());
                            toast.setGravity(Gravity.BOTTOM, 0, 0);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.setView(inflate);
                            toast.show();
                            break;
                        }
                    } else if (v == frameLayout) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
                        LayoutInflater inflater = getLayoutInflater();
                        View inflate = inflater.inflate(R.layout.oops_toast, (ViewGroup)view1.findViewById(R.id.toast_layout));

                        final Toast toast = new Toast(getActivity());
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(inflate);
                        toast.show();
                        break;

                    }
                    else if (v == rvPuzzle) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);

                        LayoutInflater inflater = getLayoutInflater();
                        View inflate = inflater.inflate(R.layout.good_toast, (ViewGroup)view1.findViewById(R.id.toast_layout));

                        final Toast toast = new Toast(getActivity());
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(inflate);
                        toast.show();

                        break;
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        LayoutInflater inflater = getLayoutInflater();
                        View inflate = inflater.inflate(R.layout.good_toast, (ViewGroup)view.findViewById(R.id.toast_layout));

                        final Toast toast = new Toast(getActivity());
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(inflate);
                        toast.show();
                        break;
                    }
                    break;

                default:
                    break;
            }
            return true;
        }
    }

}

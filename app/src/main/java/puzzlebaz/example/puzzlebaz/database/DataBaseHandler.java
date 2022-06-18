package puzzlebaz.example.puzzlebaz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import puzzlebaz.example.puzzlebaz.BaseClass.StatusClass;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GameDB.db";
    public final static String TABLE_NAME = "detail";
    public final static String MUSIC_TABLE = "BackgroundMusic";

    public final  static String COL_1 = "ID";
    //Take a few stars at each stage
    public final static String COL_2 = "rating_status";
    //score of every steps
    public final static String COL_3 = "StepScore";
    //A variable to find out if the user has played this step before or not
    public final static String COL_4 = "StateGame";

    public final static String MUSIC_ID = "ID";
    public final static String MUSIC_STATE = "STATE";


    public DataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY , " + COL_2 + " SMALLINT NOT NULL, " + COL_3 + " INTEGER , " + COL_4 + " SMALLINT NOT NULL );");
        sqLiteDatabase.execSQL("create table " + MUSIC_TABLE + " (" + MUSIC_ID + " INTEGER PRIMARY KEY , " + MUSIC_STATE + " SMALLINT NOT NULL );");

//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MUSIC_TABLE);
        onCreate(sqLiteDatabase);
    }


    public boolean insertData(int ID[], int status[]){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor res = null;
        //this variable is to check that all data to be stored in the table
        int item = 0;
        //make loop for insert one by one items into detail table
        for(int i = 0; i < ID.length; i++){
            //first check that the table is empty
            res = SearchData(ID[i]);
            if (res.getCount() == 0){
                contentValues.put(COL_1, ID[i]);

                if(i == 0)
                    contentValues.put(COL_2, status[4]);
                else
                    contentValues.put(COL_2, status[5]);
                contentValues.put(COL_3, 0);
                contentValues.put(COL_4, 0);

                long result = db.insert(TABLE_NAME, null, contentValues);
                if (result != -1)
                    item++;
            }else {
                break;
            }
        }
        if (item == 30)
            return true;
        else
            return false;
    }
    private Cursor SearchData(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM detail WHERE ID = " + i , null);

        return result;
    }
    public Cursor getStepGame(int i) {
        StringBuffer str = new StringBuffer();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM detail WHERE ID = " + i , null);
        if (result.moveToFirst()){
            int id = result.getInt(0);
            int rating = result.getInt(1);
            int stepScore = result.getInt(2);
            int stepGame = result.getInt(3);

            str.append("id : " + id +"\t rating : " + rating + "\t step score : " + stepScore + "\t step game : " + stepGame);
        }

        return result;
    }


    public Cursor getAllData()
    {
        List<StatusClass> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
    public boolean updateData (int id, int status_rating, int StepScore, int StateGame){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, status_rating);
        contentValues.put(COL_3, StepScore);
        contentValues.put(COL_4, StateGame);
        long result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {String.valueOf(id)});

        if (result == -1)
            return false;
        else
            return true;
    }

    public int getSumScore(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(StepScore) FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }else
            return -1;
    }

    public boolean InsertMusicState(){

        int[] ID_M = {1, 2};
        int[] State_M = {0, 1};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor res = null;

        int item = 0;

        for(int i = 0; i < ID_M.length; i++){
            //first check that the table is empty
            res = SearchMusicState(ID_M[i]);
            if (res.getCount() == 0) {
                contentValues.put(MUSIC_ID, ID_M[i]);
                contentValues.put(MUSIC_STATE, State_M[i]);

                long result = db.insert(MUSIC_TABLE, null, contentValues);
                if (result != -1)
                    item++;
            }
            else {
                break;
            }


        }
        if (item == 2)
            return true;
        else
            return false;
    }

    private Cursor SearchMusicState(int i) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM BackgroundMusic WHERE ID = " + i , null);

        return result;
    }

    public boolean UpdateMusicState (int id, int state){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MUSIC_ID, id);
        contentValues.put(MUSIC_STATE, state);
        long result = db.update(MUSIC_TABLE, contentValues, "ID = ?", new String[] {String.valueOf(id)});

        if (result == -1)
            return false;
        else
            return true;
    }

    public int GetState(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + MUSIC_STATE +" FROM " + MUSIC_TABLE + " WHERE ID = " + id, null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }else
            return -1;
    }

}

package com.example.spark.ratingsapp;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Toast;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PET_LOADER = 1;


    CursorAdapter cursorAdapter;

    View v;
    private RecyclerViewAdapter viewAdapter;
    private List<RatingsModel> ratingsList;
    private List<RatingsModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = displayRatings("content://com.example.spark.myapplication.PROVIDER/movierating");
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ratings_recyclerview);
            RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this,list);
            recyclerView.setAdapter(viewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void deleteRatings(View view){
        Uri uri = Uri.parse("content://com.example.spark.myapplication.PROVIDER/movierating");

        String COLUMN_NAME_USERID = "userid";

        int userID = login.userID;

        String selection = COLUMN_NAME_USERID + "=?";
//
        String[] selectionArgs = {""+userID};

        ContentResolver contentResolver = getContentResolver();
        int delete = contentResolver.delete(uri, selection, selectionArgs);

        if(delete <= 0){
            Toast.makeText(this, "No data to delete", Toast.LENGTH_SHORT).show();
        }else if(delete >= 1){
            Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
        }
    }

    public List<RatingsModel> displayRatings(String uris){
        Uri uri = Uri.parse(uris);

        String COLUMN_NAME_USERID = "userid";

        int userID = login.userID;

        String COLUMN_NAME_MOVIENAME = "moviename";
        String COLUMN_NAME_RATING = "rating";
        String COLUMN_NAME_RATINGREVIEW = "ratingreview";

        String[] projection = {
                COLUMN_NAME_USERID,
                COLUMN_NAME_MOVIENAME,
                COLUMN_NAME_RATING,
                COLUMN_NAME_RATINGREVIEW
        };

        String selection = COLUMN_NAME_USERID + "=?";

        String[] selectionArgs = {""+userID};

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri,projection,selection,selectionArgs,null);
        ratingsList = new ArrayList<>();
        try {
            if(cursor != null) {
                while (cursor.moveToNext()) {
                    String movieReview = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_RATINGREVIEW));

                    String movieRating = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_RATING));

                    String movieName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MOVIENAME));


                    ratingsList.add(new RatingsModel(movieRating, movieName, movieReview));
                }
            }else {
                ratingsList.add(new RatingsModel("", "No Data Available", "dsd"));
            }
        }finally {
            cursor.close();
        }
        return ratingsList;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri = Uri.parse("content://com.example.spark.myapplication.PROVIDER/movierating");

        String COLUMN_NAME_USERID = "userid";

        int userID = login.userID;

        String COLUMN_NAME_MOVIENAME = "moviename";
        String COLUMN_NAME_RATING = "rating";
        String COLUMN_NAME_RATINGREVIEW = "ratingreview";

        String[] projection = {
                COLUMN_NAME_USERID,
                COLUMN_NAME_MOVIENAME,
                COLUMN_NAME_RATING,
                COLUMN_NAME_RATINGREVIEW
        };

        String selection = COLUMN_NAME_USERID + "=?";

        String[] selectionArgs = {""+userID};

        return new CursorLoader(
                this,
                uri,
                projection,
                selection,
                selectionArgs,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
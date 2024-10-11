package hallal.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FlagsSQLiteOpenHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME="FlagsInfo";
    public static final int DB_VERSION=1;

    public FlagsSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE FLAG (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "DISPLAY_NAME TEXT," +
                "IMAGE_RESOURCE_ID INTEGER);");

        db.execSQL("CREATE TABLE SCORE(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "SCORE_INFO TEXT);");

        insertFlag(db, "Italy", "", R.drawable.italy_flag_waving_medium);
        insertFlag(db, "Spain", "", R.drawable.spain_flag_waving_medium);
        insertFlag(db, "Germany", "", R.drawable.germany_flag_waving_medium);
        insertFlag(db, "Belgium", "", R.drawable.belgium_flag_waving_medium);
        insertFlag(db, "USA", "",R.drawable.united_states_of_america_flag_waving_medium);
        insertFlag(db, "England", "", R.drawable.england_flag_waving_medium);
        insertFlag(db, "Japan", "", R.drawable.japan_flag_waving_medium);
        insertFlag(db, "China", "", R.drawable.china_flag_waving_medium);
        insertFlag(db, "Russia", "", R.drawable.russia_flag_waving_medium);
        insertFlag(db, "Egypt", "", R.drawable.egypt_flag_waving_medium);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertFlag(SQLiteDatabase db, String name, String displayeName, int imageId)
    {
        ContentValues contentValues= new ContentValues();

        contentValues.put("NAME",name);
        contentValues.put("DISPLAY_NAME", displayeName);
        contentValues.put("IMAGE_RESOURCE_ID", imageId);

        db.insert("FLAG", null, contentValues);
    }

    public static void insertScore(SQLiteDatabase db, String info)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put("SCORE_INFO", info);

        db.insert("SCORE", null, contentValues);
    }
}

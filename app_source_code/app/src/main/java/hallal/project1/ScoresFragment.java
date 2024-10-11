package hallal.project1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class ScoresFragment extends ListFragment {

    SQLiteDatabase db;
    Cursor cursor;

    public ScoresFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {

            SQLiteOpenHelper sqLiteOpenHelper=new FlagsSQLiteOpenHelper(inflater.getContext());
            db=sqLiteOpenHelper.getReadableDatabase();
            cursor=db.query("SCORE", new String[] {"_id", "SCORE_INFO"}, null, null, null , null, null);
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, cursor, new String[] {"SCORE_INFO"}, new int[] {android.R.id.text1},0);


            setListAdapter(cursorAdapter);

        }

        catch (Exception e)
        {
            Toast.makeText(inflater.getContext(), "Database is not available", Toast.LENGTH_SHORT).show();
        }

        finally {


            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
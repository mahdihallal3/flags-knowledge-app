package hallal.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    public static final String SCORE="final score";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar =findViewById(R.id.my_toolbar_results);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();

        int score=intent.getIntExtra(SCORE, 0);

        TextView textView=findViewById(R.id.results_text_view);

        String final_score="Final Score: " +score;

        textView.setText(final_score);

        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        Date date= new Date();

        String date_done=formatter.format(date);

        SQLiteOpenHelper sqLiteOpenHelper=new FlagsSQLiteOpenHelper(this);

        db=sqLiteOpenHelper.getWritableDatabase();

        String info= "Score: " + score+ "    Date & Time:" + date_done;

        FlagsSQLiteOpenHelper.insertScore(db, info);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.action_home)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else
        {
            Intent intent = new Intent(this, ScoresActivity.class);
            startActivity(intent);
            return true;
        }

    }
}
package hallal.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TestActivity5 extends AppCompatActivity {

    public final static String SCORE="test score";
    public final static String PROBA="true or false";
    public final static String ALREADY_DISPLAYED="displayed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);

        Toolbar toolbar= findViewById(R.id.test_toolbar_5);

        setSupportActionBar(toolbar);

        Intent intent=getIntent();

        int score=intent.getIntExtra(SCORE, 0);
        boolean proba=intent.getBooleanExtra(PROBA, false);
        boolean[] displayed_already=intent.getBooleanArrayExtra(ALREADY_DISPLAYED);


        View fragmentContainer= findViewById(R.id.fragment_container_test_5);
        TestFragment5 testFragment_5 = new TestFragment5();

        testFragment_5.setScore(score);
        testFragment_5.setProba(proba);
        testFragment_5.setDisplayedAlready(displayed_already);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container_test_5, testFragment_5);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();


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
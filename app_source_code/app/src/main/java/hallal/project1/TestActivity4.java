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

public class TestActivity4 extends AppCompatActivity {

    public final static String SCORE="test score";
    public final static String PROBA="true or false";
    public final static String ALREADY_DISPLAYED="displayed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        Toolbar toolbar= findViewById(R.id.test_toolbar_4);

        setSupportActionBar(toolbar);

        Intent intent=getIntent();

        int score=intent.getIntExtra(SCORE, 0);
        boolean proba=intent.getBooleanExtra(PROBA, false);
        boolean[] displayed_already=intent.getBooleanArrayExtra(ALREADY_DISPLAYED);


        View fragmentContainer= findViewById(R.id.fragment_container_test_4);
        TestFragment4 testFragment_4 = new TestFragment4();

        testFragment_4.setScore(score);
        testFragment_4.setProba(proba);
        testFragment_4.setDisplayedAlready(displayed_already);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container_test_4, testFragment_4);
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
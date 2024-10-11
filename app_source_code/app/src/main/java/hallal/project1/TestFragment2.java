package hallal.project1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class TestFragment2 extends Fragment {

    private int score;
    private int flagId;
    private boolean proba;
    private int ticks=5;
    private boolean running=true;

    private boolean[] displayed_already;



    public TestFragment2() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState!=null)
        {
            score= savedInstanceState.getInt("score");
            proba=savedInstanceState.getBoolean("proba");
            flagId=savedInstanceState.getInt("flagId");

            for (int i=0; i<displayed_already.length; i++)
            {
                displayed_already[i]=savedInstanceState.getBoolean("displayed_already" +i);
            }

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test2, container, false);
    }

    public void updateDisplayStatus (int flagId)
    {
        displayed_already[flagId-1]=true;
    }

    public static int generateRandom()
    {
        Random random=new Random();
        return 1+  random.nextInt(10);
    }

    @Override
    public void onStart() {
        super.onStart();



        View view=getView();

        if (view!=null) {
            ImageView imageView = view.findViewById(R.id.flag_image_view_2);
            TextView textView = view.findViewById(R.id.flag_name_text_view_2);


            flagId = generateRandom();

            while (displayed_already[flagId - 1]) {
                flagId = generateRandom();
            }

            if (proba) {

                try {
                    SQLiteOpenHelper sqLiteOpenHelper = new FlagsSQLiteOpenHelper(getLayoutInflater().getContext());

                    SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

                    Cursor cursor = db.query("FLAG", new String[]{"NAME", "IMAGE_RESOURCE_ID"}, "_id=?", new String[]{Integer.toString(flagId)}, null, null, null);

                    if (cursor.moveToFirst()) {
                        String flagName = cursor.getString(0);
                        int imageId = cursor.getInt(1);

                        imageView.setImageResource(imageId);
                        textView.setText(flagName);

                    }

                    cursor.close();
                    db.close();

                    updateDisplayStatus(flagId);



                } catch (Exception e) {
                    Toast.makeText(getLayoutInflater().getContext(), "Database Not Available", Toast.LENGTH_SHORT).show();
                }

            }

            else
            {
                int name=generateRandom();

                while (name==flagId || displayed_already[name-1])
                {
                    name=generateRandom();
                }

                try {

                    SQLiteOpenHelper sqLiteOpenHelper = new FlagsSQLiteOpenHelper(getLayoutInflater().getContext());

                    SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

                    Cursor cursor = db.query("FLAG", new String[]{"IMAGE_RESOURCE_ID"}, "_id=?", new String[]{Integer.toString(flagId)}, null, null, null);
                    Cursor cursor1=db.query("FLAG", new String[]{"NAME"}, "_id=?", new String[]{Integer.toString(name)}, null, null, null);
                    if (cursor.moveToFirst()) {

                        int imageId = cursor.getInt(0);

                        imageView.setImageResource(imageId);

                    }

                    if (cursor1.moveToFirst())
                    {
                        String flagName= cursor1.getString(0);

                        textView.setText(flagName);
                    }

                    cursor.close();
                    cursor1.close();
                    db.close();

                    updateDisplayStatus(flagId);
                    updateDisplayStatus(name);


                } catch (Exception e) {
                    Toast.makeText(getLayoutInflater().getContext(), "Database Not Available", Toast.LENGTH_SHORT).show();
                }


            }

        }


        Button button =view.findViewById(R.id.true_button_2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer=((String)button.getText());
                Boolean actual=new Boolean(proba);
                String actualAnswer=actual.toString(actual);

                if (userAnswer.equalsIgnoreCase(actualAnswer))
                {
                    score++;
                    proba=false;
                    running=false;

                    Toast.makeText(getLayoutInflater().getContext(), "Correct!", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getLayoutInflater().getContext(),TestActivity3.class);

                    intent.putExtra(TestActivity3.SCORE, score);
                    intent.putExtra(TestActivity3.ALREADY_DISPLAYED, displayed_already);
                    intent.putExtra(TestActivity3.PROBA, proba);

                    startActivity(intent);


                }

                else
                {
                    proba=true;
                    running=false;

                    Toast.makeText(getLayoutInflater().getContext(), "Wrong!", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getLayoutInflater().getContext(),TestActivity3.class);

                    intent.putExtra(TestActivity3.SCORE, score);
                    intent.putExtra(TestActivity3.ALREADY_DISPLAYED, displayed_already);
                    intent.putExtra(TestActivity3.PROBA, proba);

                    startActivity(intent);




                }
            }
        });


        Button button1= view.findViewById(R.id.false_button_2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userAnswer=((String)button1.getText());
                Boolean actual=new Boolean(proba);
                String actualAnswer=actual.toString(actual);

                if (userAnswer.equalsIgnoreCase(actualAnswer))
                {
                    score++;

                    proba=true;
                    running=false;

                    Toast.makeText(getLayoutInflater().getContext(), "Correct!", Toast.LENGTH_SHORT).show();

                    Intent intent_2=new Intent(getLayoutInflater().getContext(),TestActivity3.class);

                    intent_2.putExtra(TestActivity3.SCORE, score);
                    intent_2.putExtra(TestActivity3.ALREADY_DISPLAYED, displayed_already);
                    intent_2.putExtra(TestActivity3.PROBA, proba);

                    startActivity(intent_2);


                }

                else
                {
                    proba=false;
                    running=false;

                    Toast.makeText(getLayoutInflater().getContext(), "Wrong!", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getLayoutInflater().getContext(),TestActivity3.class);

                    intent.putExtra(TestActivity3.SCORE, score);
                    intent.putExtra(TestActivity3.ALREADY_DISPLAYED, displayed_already);
                    intent.putExtra(TestActivity3.PROBA, proba);

                    startActivity(intent);



                }


            }
        });

        onTimer();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putInt("score", score);
        outState.putInt("flagId", flagId);
        outState.putBoolean("proba", proba);
        for (int i=0; i<displayed_already.length; i++)
        {
            outState.putBoolean("displayed_already" + i, displayed_already[i]);
        }
    }

    public void setScore(int score)
    {
        this.score=score;
    }

    public void setProba(boolean proba)
    {
        this.proba=proba;
    }

    public void setDisplayedAlready(boolean[] displayed_already)
    {
        this.displayed_already=displayed_already;
    }

    public void onTimer()
    {
        View view=getView();

        TextView view2=view.findViewById(R.id.counter_text_view_2);

        Handler handler=new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                if (running) {


                    if (ticks == 0) {

                        running=false;

                        if (proba == false) {
                            proba = true;
                        } else {
                            proba = false;
                        }

                        Toast.makeText(getLayoutInflater().getContext(), "Ran Out Of Time!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getLayoutInflater().getContext(), TestActivity3.class);

                        intent.putExtra(TestActivity3.SCORE, score);
                        intent.putExtra(TestActivity3.ALREADY_DISPLAYED, displayed_already);
                        intent.putExtra(TestActivity3.PROBA, proba);

                        startActivity(intent);

                    }
                    String toDisplay = "" + ticks;
                    view2.setText(toDisplay);
                    ticks--;
                    handler.postDelayed(this, 1000);

                }
            }
        });
    }
}
package jy.flappy_bird;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    Timer mTimer;
    int mTime = 0;
    Handler handler;
    ImageView top;
    ImageView bottom;
    ImageView bird;
    int position;
    int width = 0;
    int height = 0;
    float pipex;
    float pipey;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        width = findViewById(R.id.relative).getWidth();
        height = findViewById(R.id.relative).getWidth();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative);
        final ImageView top = new ImageView(this);
        final ImageView bottom = new ImageView(this);
        final ImageView bird = new ImageView(this);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        bird.setImageResource(R.drawable.bird);
        bird.setX(width / 2);
        bird.setY(height / 2 + 400);
        relative.addView(bird);

        mTimer = new Timer(false);
        handler = new Handler();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTime++;
                        if (mTime % 200 == 0) {
                            Random random = new Random();
                            position = random.nextInt(10);
                            int bottomposition = height * 4 / 5 - 100 + position * 40;
                            top.setY(bottomposition - 1000);
                            bottom.setY(bottomposition);
                            top.setImageResource(R.drawable.top_one);
                            bottom.setImageResource(R.drawable.bottom_one);
                            top.setLayoutParams(params);
                            bottom.setLayoutParams(params);
                            top.setX(width);
                            bottom.setX(width);
                            relative.removeView(top);
                            relative.removeView(bottom);
                            relative.addView(top);
                            relative.addView(bottom);
                        }

                        top.setX(top.getX() - 4);
                        bottom.setX(bottom.getX() - 4);
                        pipex = top.getX() - 4;

                        if (top.getX() <= -120) {
                            relative.removeView(top);
                            relative.removeView(bottom);
                        }

                        if ((top.getX() - top.getWidth() / 2) == (bird.getX() + bird.getWidth() / 2)) {
                            mTimer.cancel();
                        }



                    }
                });
            }
        }, 0, 10);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

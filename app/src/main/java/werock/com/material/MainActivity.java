package werock.com.material;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import werock.com.material.domain.Car;
import werock.com.material.fragments.CarFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Toolbar toolbar_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("  Main Activity");
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        //toolbar.setSubtitle("Subtitle");
        toolbar.setLogo(R.drawable.android);
        setSupportActionBar(toolbar);

        toolbar_bottom = (Toolbar) findViewById(R.id.inc_toolbar_bottom);
        toolbar_bottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = null;
                switch (menuItem.getItemId()) {
                    case R.id.facebook:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.facebook.com"));
                        break;
                    case R.id.youtube:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.youtube.com"));
                        break;
                    case R.id.google_plus:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.plus.google.com"));
                        break;
                    case R.id.linkedin:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.linkedin.com"));
                        break;
                    case R.id.whatsapp:
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.whatsapp.com"));
                        break;
                }
                startActivity(intent);
                return true;
            }
        });
        //menu bottom
        toolbar_bottom.inflateMenu(R.menu.menu_bottom);
        toolbar_bottom.findViewById(R.id.iv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Setting press", Toast.LENGTH_LONG).show();
            }
        });

        //TODO Fragment
        CarFragment carFragment = (CarFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if (carFragment == null) {
            carFragment = new CarFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_container, carFragment, "mainFrag");
            fragmentTransaction.commit();
        }
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
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Car> getSetCarList(int qtd) {
        String[] models = new String[]{"PHP", "C#", "Java", "C++", "C", "SQL", "HTML5", "CSS3"
                , "JavaScript", "Go", "Scala", "Ruby", "Python", "R"};
        String[] brands = new String[]{"Web", "Web Desktop", "Web Desktop", "Desktop", "Desktop", "Database"
                , "Web", "Web", "Web", "Web Server", "Unknow", "Web", "Web Desktop"
                , "Analysis"};
        int[] image = {R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web,
                R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web,
                R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web,
                R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web};

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            Car car = new Car(models[i % models.length], brands[i % brands.length], image[i % image.length]);
            carList.add(car);
        }
        return carList;
    }
}

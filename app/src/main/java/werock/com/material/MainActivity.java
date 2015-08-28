package werock.com.material;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import werock.com.material.domain.Car;
import werock.com.material.fragments.CarFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Toolbar toolbar_bottom;
    private Drawer drawerLeft;
    private Drawer drawerRight;
    private AccountHeader accountHeader;

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

        //TODO NavigationDrawer

        // Create the AccountHeader
        accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bill_gates)
                .addProfiles(
                        new ProfileDrawerItem().withName("Kotchaphan Muangsan").withEmail("kotchaphan.m@demo.com").withIcon(getResources().getDrawable(R.drawable.android))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        PrimaryDrawerItem facebookItem = new PrimaryDrawerItem().withName("Facebook").withIcon(R.drawable.facebook_box);
        PrimaryDrawerItem whatappItem = new PrimaryDrawerItem().withName("Whatsapp").withIcon(R.drawable.whatsapp);
        PrimaryDrawerItem youtubeItem = new PrimaryDrawerItem().withName("Youtube").withIcon(R.drawable.youtube_play);
        PrimaryDrawerItem googleplusItem = new PrimaryDrawerItem().withName("Google plus").withIcon(R.drawable.google_plus_box);
        PrimaryDrawerItem linkedInIntem = new PrimaryDrawerItem().withName("LinkedIn").withIcon(R.drawable.linkedin_box);
        PrimaryDrawerItem settingIntem = new PrimaryDrawerItem().withName("Settings").withIcon(R.drawable.settings);
        //SecondaryDrawerItem secondaryDrawerItem = new SecondaryDrawerItem().withName("Second").withIcon(R.drawable.web);
        drawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withDrawerGravity(Gravity.LEFT)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withSelectedItem(0)
                .addDrawerItems(
                        facebookItem,
                        youtubeItem,
                        whatappItem,
                        googleplusItem,
                        linkedInIntem, new DividerDrawerItem(),
                        settingIntem
                )
                .withActionBarDrawerToggleAnimated(true)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(accountHeader)
                .build();

        /*
        drawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onItemClick : " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int i, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick : " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        drawerLeft.addItem(new ProfileDrawerItem().withName("Facebook"));
        drawerLeft.addItem(new ProfileDrawerItem().withName("Youtube"));
        drawerLeft.addItem(new ProfileDrawerItem().withName("Whatsapp"));
        drawerLeft.addItem(new ProfileDrawerItem().withName("LikedIn"));
        drawerLeft.addItem(new ProfileDrawerItem().withName("Google plus"));
        */
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
        int[] image = {R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming,
                R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming,
                R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming,
                R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming};

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            Car car = new Car(models[i % models.length], brands[i % brands.length], image[i % image.length]);
            carList.add(car);
        }
        return carList;
    }
}

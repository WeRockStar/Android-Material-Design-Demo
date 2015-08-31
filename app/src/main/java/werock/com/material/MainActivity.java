package werock.com.material;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import werock.com.material.domain.Car;
import werock.com.material.domain.Person;
import werock.com.material.fragments.CarFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Toolbar toolbar_bottom;
    //TODO NavigationDrawer
    private Drawer drawerLeft;
    private Drawer drawerRight;
    private AccountHeader accountHeader;
    private FloatingActionMenu fab;
    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;

    private List<PrimaryDrawerItem> listCatefories;
    private List<Person> listProfile;
    private List<Car> listCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mItemDrawerSelected = savedInstanceState.getInt("mItemDrawerSelected", 0);
            mProfileDrawerSelected = savedInstanceState.getInt("mProfileDrawerSelected", 0);
            listCars = savedInstanceState.getParcelableArrayList("listCars");
        } else {
            listCars = getSetCarList(50);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("  Programming");
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        //toolbar.setSubtitle("Subtitle");
        toolbar.setLogo(R.drawable.android);
        setSupportActionBar(toolbar);
        /*
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

        /*
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
        */

        //TODO menu bottom
        /*
        toolbar_bottom.inflateMenu(R.menu.menu_bottom);
        toolbar_bottom.findViewById(R.id.iv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Setting press", Toast.LENGTH_LONG).show();
            }
        });
        */

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
                .withCompactStyle(false) //show other account
                .withHeaderBackground(R.drawable.bill_gates)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Kotchaphan Muangsan")
                                .withEmail("kotchaphan.m@demo.com")
                                .withIcon(getResources().getDrawable(R.drawable.person_1)),
                        new ProfileDrawerItem()
                                .withName("WeRockStar")
                                .withEmail("rock@demo.com")
                                .withIcon(getResources().getDrawable(R.drawable.newspaper))
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
//        PrimaryDrawerItem checkItem = new PrimaryDrawerItem().withName("News").withIcon(R.drawable.newspaper);
        ToggleDrawerItem newsToggleDrawerItem = new ToggleDrawerItem().withName("Auto Update").withChecked(true).withOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, "onCheck : " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });
        SectionDrawerItem socailDrawerItem = new SectionDrawerItem().withName("Social");
        SwitchDrawerItem switcOnOffhDrawerItem = new SwitchDrawerItem().withName("Notification").withChecked(true).withOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this, "onCheck : " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });
        //SecondaryDrawerItem secondaryDrawerItem = new SecondaryDrawerItem().withName("Second").withIcon(R.drawable.web);
        drawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withDrawerGravity(Gravity.LEFT)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(true)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                /*
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View view) {
                        return false;
                    }
                })
                */
                .addDrawerItems(
                        socailDrawerItem,
                        facebookItem,
                        youtubeItem,
                        whatappItem,
                        googleplusItem,
                        linkedInIntem, new DividerDrawerItem(),
                        settingIntem,
                        newsToggleDrawerItem,
                        switcOnOffhDrawerItem
                )
                .withActionBarDrawerToggleAnimated(true)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(accountHeader)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Fragment frag = null;
                        mItemDrawerSelected = i;

                        if (i == 0) { // ALL CARS
                            frag = new CarFragment();
                        } else if (i == 1) { // LUXURY CAR
                            //frag = new LuxuryCarFragment();
                        } else if (i == 2) { // SPORT CAR
                            //frag = new SportCarFragment();
                        } else if (i == 3) { // OLD CAR
                            //frag = new OldCarFragment();
                        } else if (i == 4) { // POPULAR CAR
                            //frag = new PopularCarFragment();
                        }

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                        ft.commit();

                        //toolbar.setTitle(((PrimaryDrawerItem) iDrawerItem).getName());

                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int i, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onLongClick : " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        drawerRight = new DrawerBuilder()
                .withActivity(this)
                        //.withToolbar(toolbar)
                .withDrawerGravity(Gravity.END)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(-1)
                .withActionBarDrawerToggleAnimated(true)
                .withDisplayBelowStatusBar(true)
                .addDrawerItems(
                        socailDrawerItem,
                        facebookItem,
                        youtubeItem,
                        whatappItem,
                        googleplusItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int i, IDrawerItem iDrawerItem) {
                        return false;
                    }
                })
                .build();

        /*drawerLeft = new DrawerBuilder()
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
        listCatefories = getSetCategotyList();
        if (listCatefories != null && listCatefories.size() > 0) {
            for (int i = 0; i < listCatefories.size(); i++) {
                drawerLeft.addItem(listCatefories.get(i));
            }
            drawerLeft.setSelection(mItemDrawerSelected);
        }
        fab = (FloatingActionMenu) findViewById(R.id.fab);
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

    //CATEGOIES
    private List<PrimaryDrawerItem> getSetCategotyList() {
        String[] names = new String[]{"Programming", "Programming", "Programming", "Programming", "Programming"};
        int[] icons = new int[]{R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web};
        int[] iconsSelected = new int[]{R.drawable.car_selected_3, R.drawable.car_selected_3, R.drawable.car_selected_3, R.drawable.car_selected_3,
                R.drawable.car_selected_3};
        List<PrimaryDrawerItem> list = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            PrimaryDrawerItem aux = new PrimaryDrawerItem();
            aux.withName(names[i])
                    .withIcon(getResources().getDrawable(icons[i]))
                    .withTextColor(getResources().getColor(R.color.colorPrimary))
                    .withSelectedIcon(getResources().getDrawable(iconsSelected[i]))
                    .withSelectedColor(getResources().getColor(R.color.colorPrimary));
            //add to list
            list.add(aux);
        }
        return (list);
    }

    //PERSON
    private Person getPersonByEmail(List<Person> list, ProfileDrawerItem p) {
        Person aux = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProfile().getEmail().equals(p.getEmail())) {
                aux = list.get(i);
                break;
            }
        }
        return (aux);
    }

    private List<Person> getSetProfileList() {
        String[] names = new String[]{"User 1", "User 2", "User 3", "User 4"};
        String[] emails = new String[]{"demoUser_1@gmail.com", "demoUser_2@gmail.com", "demoUser_3@gmail.com", "demoUser_4@gmail.com"};
        int[] photos = new int[]{R.drawable.person_1, R.drawable.person_1, R.drawable.person_1, R.drawable.person_1};
        int[] background = new int[]{R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming};
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            ProfileDrawerItem aux = new ProfileDrawerItem();
            aux.withName(names[i]).withEmail(emails[i]).withIcon(getResources().getDrawable(photos[i]));

            Person p = new Person();
            p.setProfile(aux);
            p.setBackground(background[i]);

            list.add(p);
        }
        return (list);
    }

    private int getPersonPositionByEmail(List<Person> list, ProfileDrawerItem p) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProfile().getEmail().equals(p.getEmail())) {
                return (i);
            }
        }
        return (-1);
    }


    //CAR
    public List<Car> getSetCarList(int qtd) {
        return (getSetCarList(qtd, 0));
    }

    public List<Car> getSetCarList(int qtd, int category) {
        String[] models = new String[]{"PHP", "C#", "Java", "C++", "C", "SQL", "HTML5", "CSS3"
                , "JavaScript", "Go", "Scala", "Ruby", "Python", "R"};
        String[] brands = new String[]{"Web", "Web Desktop", "Web Desktop", "Desktop", "Desktop", "Database"
                , "Web", "Web", "Web", "Web Server", "Unknow", "Web", "Web Desktop"
                , "Analysis"};
        int[] categories = new int[]{2, 1, 2, 1, 1, 4, 3, 2, 4, 1};
        String description = "Programming is my life";
        int[] image = {R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming,
                R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming,
                R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming,
                R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming};

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            Car car = new Car(models[i % models.length], brands[i % brands.length], image[i % image.length]);
            car.setDescription(description);
            car.setCategory(categories[i % brands.length]);
            car.setTel("082-1111111");

            if (category != 0 && car.getCategory() != category) {
                continue;
            }
            carList.add(car);
        }
        return carList;
    }

    public List<Car> getCarsByCategory(int category) {
        List<Car> listAux = new ArrayList<>();
        for (int i = 0; i < listCars.size(); i++) {
            if (category != 0 && listCars.get(i).getCategory() != category) {
                continue;
            }

            listAux.add(listCars.get(i));
        }
        return (listAux);
    }

    public List<Car> getListCars() {
        return (listCars);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = drawerRight.saveInstanceState(outState);
        outState = drawerLeft.saveInstanceState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (drawerLeft.isDrawerOpen()) {
            drawerLeft.closeDrawer();
        } else if (fab.isOpened()) {
            fab.close(true);
            //fab.showMenuButton(true);
        } else {
            super.onBackPressed();
        }
    }
}

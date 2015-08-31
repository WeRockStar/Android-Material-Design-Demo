package werock.com.material.domain;

import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

/**
 * Created by Kotchaphan Muangsan on 29/8/2558.
 */
public class Person {
    private ProfileDrawerItem profile;
    private int background;

    public Person() {
    }

    public ProfileDrawerItem getProfile() {
        return profile;
    }

    public void setProfile(ProfileDrawerItem profile) {
        this.profile = profile;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}

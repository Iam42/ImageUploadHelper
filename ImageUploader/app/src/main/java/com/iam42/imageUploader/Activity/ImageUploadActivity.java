package com.iam42.imageUploader.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.iam42.imageUploader.Fragment.ImageChooserFragment;
import com.iam42.imageUploader.R;

/**
 * Created by a42 on 14-2-14.
 */
public class ImageUploadActivity extends ActionBarActivity {

    FragmentManager mFragmentManager;
    ImageChooserFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_select);
        mFragmentManager = getSupportFragmentManager();
        showImageChooser();
    }

    private void showImageChooser(){
        if (mFragmentManager == null) {
            return;
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        mCurrentFragment = new ImageChooserFragment(this);
        ft.replace(R.id.container, mCurrentFragment);
        ft.commitAllowingStateLoss();
    }
}

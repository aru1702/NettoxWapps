package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nettox.nettoxwapps.Adapter.TutorialPagerAdapter;

import static com.nettox.nettoxwapps.StaticFieldVariables.TUTORIALKEY;

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mPager;
    private int[] layoutsArray = {
            R.layout.tutorial_first_slide,
            R.layout.tutorial_second_slide,
            R.layout.tutorial_third_slide,
            R.layout.tutorial_fourth_slide,
    };

    private TutorialPagerAdapter tutorialPagerAdapter;

    private LinearLayout dotsLayout;
    private ImageView[] dots;

    private Button btnSkip, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        mPager = (ViewPager) findViewById(R.id.tutorialAct_viewPager);
        tutorialPagerAdapter = new TutorialPagerAdapter(layoutsArray, this);
        mPager.setAdapter(tutorialPagerAdapter);

        dotsLayout = (LinearLayout) findViewById(R.id.tutorial_dotsLayout);
        createDots(0);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                createDots(i);

                if (i == layoutsArray.length - 1) {
                    btnNext.setText("Start!");
                    btnSkip.setVisibility(View.INVISIBLE);
                } else {
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        btnSkip = (Button) findViewById(R.id.tutorial_btnSkip);
        btnNext = (Button) findViewById(R.id.tutorial_btnNext);

        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void createDots (int current_position) {

        if (dotsLayout != null) {
            dotsLayout.removeAllViews();
        }

        dots = new ImageView[layoutsArray.length];

        for (int i = 0 ; i < layoutsArray.length ; i++) {
            dots[i] = new ImageView(this);

            if (i == current_position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4,0,4,0);

            dotsLayout.addView(dots[i], params);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tutorial_btnSkip: {
                loadHome();
                SharedPreferenceManager.saveIntoPreference(this, "true", TUTORIALKEY);
                break;
            }

            case R.id.tutorial_btnNext: {
                loadNextSlide();
                break;
            }
        }
    }

    private void loadHome () {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void loadNextSlide () {
        int next_slide = mPager.getCurrentItem() + 1;
        if (next_slide < layoutsArray.length) {
            mPager.setCurrentItem(next_slide);
        } else {
            loadHome();
            SharedPreferenceManager.saveIntoPreference(this, "true", TUTORIALKEY);
        }
    }
}
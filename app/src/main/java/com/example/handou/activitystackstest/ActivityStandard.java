package com.example.handou.activitystackstest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by handou on 2014/11/09.
 */
public class ActivityStandard extends AppBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setName(R.string.activity_standard);
    }
}

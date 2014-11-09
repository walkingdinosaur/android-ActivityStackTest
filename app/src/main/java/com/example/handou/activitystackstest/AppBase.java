package com.example.handou.activitystackstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by handou on 2014/11/09.
 */
public class AppBase extends Activity {
    private String name;
    private int nameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
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

        int nameId =AppBase.getNameId(id);
        if (0 != nameId) {
            AppBase.startActivity(this, id, nameId);
        }

        return true;
    }

    protected void setName(int id) {
        this.name = getString(id);
        this.nameId = id;
        TextView text = (TextView) findViewById(R.id.activity_type);
        text.setText(this.name);
    }

    public static int getNameId(int menuId) {
        int nameId = 0;

        if (R.id.activity_standard == menuId) {
            nameId = R.string.activity_standard;
        } else if (R.id.activity_singletop == menuId) {
            nameId = R.string.activity_singletop;
        } else if (R.id.activity_singletask == menuId) {
            nameId = R.string.activity_singletop;
        } else if (R.id.activity_singleinstance == menuId) {
            nameId = R.string.activity_singleinstance;
        } else {
            nameId = 0;
        }

        return nameId;
    }
    public static void startActivity(Activity activity, int id, int nameId) {
        Class cls;
        if (R.id.activity_standard == id) {
            cls = ActivityStandard.class;
        } else if (R.id.activity_singletop == id) {
            cls = ActivitySingleTop.class;
        } else if (R.id.activity_singletask == id) {
            cls = ActivitySingleTask.class;
        } else if (R.id.activity_singleinstance == id) {
            cls = ActivitySingleInstance.class;
        } else {
            cls = null;
        }

        if (null != cls) {
            Intent intent = new Intent(activity, cls);
            activity.startActivityForResult(intent, nameId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ActivityTest", "destroy: " + this.name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name = getString(requestCode);
        Log.d("ActivityTest", "onActivityResult: " + name);
    }
}

package com.cvaghela.customfloatingview.floatingview.fabmenu.floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.cvaghela.customfloatingview.floatingview.fabmenu.floatingactionmenulib.MenuLayout;

public class MainActivity extends AppCompatActivity {

    private MenuLayout menuLayout, menuLayout1, menuLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuLayout = findViewById(R.id.menuLayout);
        menuLayout1 = findViewById(R.id.menuLayout1);
        menuLayout2 = findViewById(R.id.menuLayout2);
        menuLayout.setMainButtonColorAndIcon(R.color.orange, R.drawable.plus)
                .setListImageResource(R.drawable.add_log, R.drawable.c_task)
                .setListText("alarm", "face")
                .setOnItemClickListener(new MenuLayout.OnItemClickListener() {
                    @Override
                    public void onTextItemClickListener(int position, String str) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onImageItemClickListener(int position, int resId) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + resId, Toast.LENGTH_SHORT).show();
                    }
                }).createMenu();


        menuLayout1.setMainButtonColorAndIcon(R.color.white, R.drawable.plus)
                .setListImageResource(R.drawable.baseline_access_alarm_24, R.drawable.baseline_airplanemode_active_24)
                .setListText("alarm", "face")
                .setOnItemClickListener(new MenuLayout.OnItemClickListener() {
                    @Override
                    public void onTextItemClickListener(int position, String str) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onImageItemClickListener(int position, int resId) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + resId, Toast.LENGTH_SHORT).show();
                    }
                }).createMenu();


        menuLayout2.setMainButtonColorAndIcon(R.color.blue, R.drawable.plus)
                .setListImageResource(R.drawable.baseline_access_alarm_24, R.drawable.baseline_airplanemode_active_24,
                        R.drawable.baseline_access_alarm_24, R.drawable.baseline_airplanemode_active_24, R.drawable.baseline_access_alarm_24,
                        R.drawable.baseline_airplanemode_active_24, R.drawable.baseline_access_alarm_24, R.drawable.baseline_airplanemode_active_24,
                        R.drawable.baseline_access_alarm_24, R.drawable.baseline_airplanemode_active_24)
                .setListText("alarm", "face","alarm", "face","alarm", "face","alarm", "face","alarm", "face")
                .setOnItemClickListener(new MenuLayout.OnItemClickListener() {
                    @Override
                    public void onTextItemClickListener(int position, String str) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onImageItemClickListener(int position, int resId) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + resId, Toast.LENGTH_SHORT).show();
                    }
                }).createMenu();

    }
}
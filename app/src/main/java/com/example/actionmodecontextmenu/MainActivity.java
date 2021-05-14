package com.example.actionmodecontextmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnShowActionModeMenu;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        btnShowActionModeMenu = findViewById(R.id.btnShowActionModeMenu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        Toast.makeText(MainActivity.this, "one clicked.", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bluetooth:
                        Toast.makeText(MainActivity.this, "bluetooth clicked.",
                                Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

        btnShowActionModeMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override public boolean onLongClick(View v) {
                if (actionMode != null) {
                    return false;
                }

                actionMode = startSupportActionMode(callback);
                return true;
            }
        });
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.action_mode_context_menu, menu);
            mode.setTitle("Action Mode");
            return true;
        }

        @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.two:
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;

                case R.id.three:
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
            }
            return false;
        }

        @Override public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
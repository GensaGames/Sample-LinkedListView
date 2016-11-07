package linkedlistview.sample.github.linkedlistview.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gensagames.linkedlistview.LinkedListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import linkedlistview.sample.github.linkedlistview.R;
import linkedlistview.sample.github.linkedlistview.controller.adapter.MusicListAdapter;
import linkedlistview.sample.github.linkedlistview.controller.model.MusicItem;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_linkedlistview)
    public LinkedListView linkedListView;

    @BindView(R.id.toolbar)
    public Toolbar mainToolbar;

    private MusicListAdapter musicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindView();

    }

    private void bindView () {
        setSupportActionBar(mainToolbar);
        musicListAdapter = new MusicListAdapter(getApplicationContext());
        linkedListView.setAdapter(musicListAdapter);

        for (int i = 0; i < 5; i++) {
            musicListAdapter.addItemList(new MusicItem());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

package pyxis.uzuki.live.rollingbannersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import pyxis.uzuki.live.rollingbanner.RollingBanner;
import pyxis.uzuki.live.rollingbanner.RollingViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private RollingBanner rollingBanner;

    private String[] txtRes = new String[]{"Purple", "Light Blue", "Cyan", "Teal", "Green"};
    private int[] colorRes = new int[]{0xff9C27B0, 0xff03A9F4, 0xff00BCD4, 0xff009688, 0xff4CAF50};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollingBanner = findViewById(R.id.banner);

        SampleAdapter adapter = new SampleAdapter(new ArrayList<>(Arrays.asList(txtRes)));
        rollingBanner.setAdapter(adapter);
    }

    public class SampleAdapter extends RollingViewPagerAdapter<String> {

        public SampleAdapter(ArrayList<String> itemList) {
            super(itemList);
        }

        @Override
        public View getView(int position) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_pager, null, false);
            FrameLayout container = view.findViewById(R.id.container);
            TextView txtText = view.findViewById(R.id.txtText);

            String txt = getItem(position);
            int index = getItemList().indexOf(txt);
            txtText.setText(txt);
            container.setBackgroundColor(colorRes[index]);
            return view;
        }
    }
}

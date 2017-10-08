# RollingBanner
Infinity RollingBanner with few code

![](https://github.com/WindSekirun/RollingBanner/blob/master/sample.gif)

## Usages

### Add dependency

rootProject/build.gradle
````
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
````

app/build.gradle
````
 implementation 'com.github.WindSekirun:RollingBanner:1.0.0'
````

#### Coding
````XML
 <pyxis.uzuki.live.rollingbanner.RollingBanner
        android:id="@+id/banner"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:bottomMargin="@dimen/default_bottom_margin"
        app:enableIndicator="true"
        app:enableRolling="true"
        app:flingAble="true"
        app:indicatorMargin="@dimen/default_indicator_margin"
        app:rollingDelay="3000"
        app:scrollingDelay="250"
        app:smoothScroll="true" />
````

````Java
RollingBanner rollingBanner = findViewById(R.id.banner);
SampleAdapter adapter = new SampleAdapter(new ArrayList<>(Arrays.asList(txtRes)));
rollingBanner.setAdapter(adapter);
````

````Java
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
````

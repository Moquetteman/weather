package uavignon.fr.weather;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CityView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);

        Intent intent = getIntent();
        City city1 = (City)intent.getSerializableExtra(MainActivity.WEATHER);

        //TextView textView1 = findViewById(R.id.)
        TextView textView = (TextView)findViewById(R.id.textView7);
        textView.setText(city1.getCity());

        TextView textView1 = (TextView)findViewById(R.id.textView8);
        textView1.setText(city1.getCountry());

        TextView textView2 = (TextView)findViewById(R.id.textView9);
        textView2.setText(city1.getWind());

        TextView textView3 = (TextView)findViewById(R.id.textView10);
        textView3.setText(city1.getPressure());

        TextView textView4 = (TextView)findViewById(R.id.textView11);
        textView4.setText(city1.getPressure());

        TextView textView5 = (TextView)findViewById(R.id.textView13);
        textView5.setText(city1.getPressure());

        TextView textView6 = (TextView)findViewById(R.id.textView14);
        textView6.setText(city1.getLastweather());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_city_view, menu);
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

        return super.onOptionsItemSelected(item);
    }
}

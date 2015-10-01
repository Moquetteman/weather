package uavignon.fr.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddCityActivity extends ActionBarActivity {

    public static final String SAVED = "fr.uavignon.SAVED_OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_city, menu);
        return true;
    }
    public void save(View v)
    {

        TextView text = (TextView)findViewById(R.id.editText);
        String city = text.getText().toString();
        text = (TextView)findViewById(R.id.editText2);
        String country = text.getText().toString();

        if(city.isEmpty() || country.isEmpty())
        {
            Toast toast = Toast.makeText(this, "Champs Non Remplis", Toast.LENGTH_LONG);
            toast.show();
            return;
        }


        City city1 = new City(city, country);

        Intent intent = new Intent();
        intent.putExtra(SAVED, city1);
        setResult(Activity.RESULT_OK, intent);

        finish();
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

package uavignon.fr.weather;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity {

    public final static String WEATHER = "fr.uavignon.weather";
    public final static int ADD_CITY = 1;
    ArrayList<City> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        array = new ArrayList<>();
        City city1 = new City("Brest", "France");
        City city2 = new City("Marseille","France");
        City city3 = new City("Montreal","Canada");
        City city4 = new City("Istanbul","Turkey");
        City city5 = new City("Seoul","Korea");

        array.add(city1);
        array.add(city2);
        array.add(city3);
        array.add(city4);
        array.add(city5);

        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
        setListAdapter(adapter);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final City city = array.get(i);

                new AlertDialog.Builder(MainActivity.this).setTitle("Confirmation")
                        .setMessage("Supprimer une ville")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                array.remove(city);
                                ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
                            }
                        }).setNegativeButton(android.R.string.no, null).show();

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        /*if (id == R.id.action_settings) {
            return true;
        }*/
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddCityActivity.class);
            startActivityForResult(intent, ADD_CITY);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(this, CityView.class);
        intent.putExtra(WEATHER, array.get(position));

        startActivity(intent);
    }
}

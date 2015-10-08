package uavignon.fr.weather;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity {

    public final static String WEATHER = "fr.uavignon.weather";
    ArrayList<City> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        array = new ArrayList<>();
        //Création des villes par défaut
        City city1 = new City("Roma", "Italy");
        City city2 = new City("Pescara", "Italy");
        City city3 = new City("Marseille", "France");
        City city4 = new City("Tokyo", "Japan");
        City city5 = new City("Seoul", "Korea");

        // Ajout des villes par défaut dans la liste
        array.add(city1);
        array.add(city2);
        array.add(city3);
        array.add(city4);
        array.add(city5);

        // Permet l'affichage de la liste
        ArrayAdapter<City> adapter = new ArrayAdapter<City>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
        setListAdapter(adapter);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {//Pour une pression longue sur un élément de la liste
                final City city = array.get(i); // Récupère les données de la ville

                new AlertDialog.Builder(MainActivity.this).setTitle("Confirmation")// Affiche la boite de dialogue
                        .setMessage("Supprimer une ville")// Message
                        .setIcon(android.R.drawable.ic_dialog_alert) // Icone
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) { // Bouton d'acceptation
                                array.remove(city);// La supprime de la liste
                                ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();
                            }
                        }).setNegativeButton(android.R.string.no, null).show(); // Bouton de refus

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Pour avoir la barre de menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        //Pour l'action d'ajout se situant dans le menu
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddCityActivity.class);
            startActivityForResult(intent, 0);// Lance l'activité correspondant à l'ajout des villes

        } else if (id == R.id.action_refresh) { // Pour l'action de rafrachissement
            new refreshData().execute(array);// créer un élément refreshData et le lance avec la liste de ville en argument
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        if (result == RESULT_OK) {// Vérifie si le retour de AddCityActivity est correct
            if (request == 0) {
                City city1 = (City) data.getSerializableExtra(WEATHER); // Récupère les données qui ont été envoyées
                array.add(city1);
                ((ArrayAdapter) getListAdapter()).notifyDataSetChanged();// Met à jour la liste

                Toast toast = Toast.makeText(this, "Ville sauvegardée", Toast.LENGTH_SHORT);
                toast.show(); // Affiche un message
            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, CityView.class);
        intent.putExtra(WEATHER, array.get(position)); // Permet d'envoyer les données de la ville qui est sélectionnée

        startActivity(intent); // Lance CityView
    }

    private void refresh(List<City> cities)// Fonction de rafrachissement Des villes
    {
        try{
            URL url;
            URLConnection urlConnection;
            InputStream inputStream = null;
            final XMLResponseHandler xmlResponseHandler = new XMLResponseHandler();// Création d'un XMLResponseHandler
            for(final City city : cities)// Parcours la liste de villes
            {
                //URL de la requète avec la ville et le pays
                url = new URL("http://www.webservicex.net/globalweather.asmx/GetWeather?CityName="+ URLEncoder.encode(city.getCity(),"UTF-8") + "&CountryName=" +URLEncoder.encode(city.getCountry(),"UTF-8"));
                urlConnection = url.openConnection();
                inputStream = urlConnection.getInputStream();
                List<String> data = xmlResponseHandler.handleResponse(inputStream,"UTF-8");

                //Récupération des différentes données
                city.setWind(data.get(0));
                city.setTemperature(data.get(1));
                city.setPressure(data.get(2));
                city.setLastweather(data.get(3));
            }

        }
        catch (Exception e)
        {
            Log.e("Erreur rafraichissement", e.toString());
        }
    }


    private class refreshData extends AsyncTask<List<City>, Void, Void> {

        @Override
        protected Void doInBackground(List<City>... cities) // Lance une action en "Background"
        {
            refresh(cities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) // Affiche un message une fois que l'action lancée en "Background" est terminé
        {
            Toast toast = Toast.makeText(MainActivity.this, "Mis a Jour", Toast.LENGTH_LONG);
            toast.show(); // Affiche le message à l'utilisateur
        }
    }

}
package com.fireblend.restservice.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fireblend.restservice.R;
import com.fireblend.restservice.entities.Photos;
import com.fireblend.restservice.services.GestorMiServicio;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView list;
    final List<Photos> listaFotos= new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (GridView)findViewById(R.id.lista_photos);
/*
        try {
            at.execute(new URL("https://jsonplaceholder.typicode.com/posts"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/


        GestorMiServicio.MiServicio servicio =
                GestorMiServicio.obtenerServicio();
        /*
        Call<List<Post>> llamada = servicio.getPosts();

        llamada.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();

                for(Post post : posts){
                    Log.d("TAG", post.body);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Call<Post> llamada2 = servicio.getPostById("5");
        llamada2.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post post = response.body();

                Log.d("TAG", post.body);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Post postNuevo = new Post();
        postNuevo.id = 500;
        postNuevo.userId = 1000;
        postNuevo.title = "Titulo";
        postNuevo.body = "Cuerpo del mensaje";

        Call<Post> llamada3 = servicio.createPost(postNuevo);

        llamada3.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Post resultado = response.body();

                Log.d("TAG", resultado.body);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
        */

        //****
        Call<List<Photos>> llamadaPhotos = servicio.getPhotos();

        llamadaPhotos.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                List<Photos> photos = response.body();

                for(Photos photo : photos){
                    listaFotos.add(new Photos(photo.albumId,photo.id,photo.title,photo.url,photo.thumbnailUrl));
                    //Log.d("Photos", photo.thumbnailUrl);
                }//fin for


                list.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return listaFotos.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return listaFotos.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        LayoutInflater inflater = getLayoutInflater();
                        View row = inflater.inflate(R.layout.photos_item, parent, false);

                        TextView name;
                        ImageView imageView;
                        imageView=(ImageView)row.findViewById(R.id.imageView);
                        name = (TextView) row.findViewById(R.id.name);
                        //age = (TextView) row.findViewById(R.id.age);
                        //phone = (TextView) row.findViewById(R.id.phone);
                        //email = (TextView) row.findViewById(R.id.email);

                        //Button btn = (Button) row.findViewById(R.id.row_btn);

                        /*

                        final int pos = position;
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Hola, "+contacts.get(pos).name, Toast.LENGTH_SHORT).show();
                            }
                        });
                        */
                        name.setText(listaFotos.get(position).thumbnailUrl);
                        Picasso.get().load(listaFotos.get(position).thumbnailUrl).into(imageView);
                        //age.setText(contacts.get(position).age+"");
                        //phone.setText(contacts.get(position).phone);
                        //email.setText(contacts.get(position).email);

                        return row;
                    }
                });


            }//fin llamadaPhotos

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


        //*****
    }


/*
    AsyncTask<URL, Integer, Boolean> at = new AsyncTask<URL, Integer, Boolean>() {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(URL[] objects) {
            try {

                HttpsURLConnection connection =(HttpsURLConnection)
                        objects[0].openConnection();

                connection.setRequestMethod("GET");

                connection.setRequestProperty("User-Agent", "my-rest-app");
                connection.setRequestProperty("Contact-Me", "sergiome@gmail.com");

                if (connection.getResponseCode() == 200) {
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(connection.getInputStream(), "UTF-8");

                    BufferedReader br = new BufferedReader(responseBodyReader);

                    StringBuilder total = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        total.append(line).append('\n');
                    }

                    String jsonString = total.toString();

                    JSONArray json = new JSONArray(jsonString);

                    JSONObject objeto5 = json.getJSONObject(5);

                    String body = objeto5.getString("body");

                    objeto5.put("atributo", "Hola");

                    objeto5.toString();

                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean fueExitoso) {
            super.onPostExecute(fueExitoso);
            if(fueExitoso){
                Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    };
*/
}

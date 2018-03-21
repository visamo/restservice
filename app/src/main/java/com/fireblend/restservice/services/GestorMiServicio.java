package com.fireblend.restservice.services;

import com.fireblend.restservice.entities.Comment;
import com.fireblend.restservice.entities.Photos;
import com.fireblend.restservice.entities.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class GestorMiServicio {

    private static MiServicio mServicio;

    public interface MiServicio{
        @GET("posts")
        Call<List<Post>> getPosts();

        @GET("posts/{id}")
        Call<Post> getPostById(@Path("id") String valor);

        @GET("/posts/{id}/comments")
        Call<List<Comment>> getCommentsForPost(@Path("id") String valor);

        @Headers({
                "Accept: application/json",
                "User-Agent: Retrofit-Sample-App"
        })
        @POST("posts")
        Call<Post> createPost(@Body Post post);


        @GET("photos")
        Call<List<Photos>> getPhotos();

    }

    public static MiServicio obtenerServicio(){
        if(mServicio == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mServicio = retrofit.create(MiServicio.class);
        }

        return mServicio;
    }//fin MiServicio





}

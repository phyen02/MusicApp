package com.android.app.ServiceAPI;

import com.android.app.Model.Album;
import com.android.app.Model.Artist;
import com.android.app.Model.Common;
import com.android.app.Model.Genre;
import com.android.app.Model.Playlist;
import com.android.app.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    // Gửi phương thức và nhận dữ liệu từ phía Server
    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("genreforcurrentday.php")
    Call<List<Genre>> GetGenreCurrentDay();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("commonsong.php")
    Call<List<Song>> GetCommonSong();

    @GET("artistforcurrentday.php")
    Call<List<Artist>> GetArtist();

    @FormUrlEncoded
    @POST("song.php")
    Call<List<Song>> GetSongPlaylistList(@Field("idPlaylist") String idPlaylist);

    @FormUrlEncoded
    @POST("song.php")
    Call<List<Song>> GetSongGenreList(@Field("idTheLoai") String idPlaylist);

    @FormUrlEncoded
    @POST("song.php")
    Call<List<Song>> GetSongAlbumList(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("song.php")
    Call<List<Song>> GetSongArtistList(@Field("idCaSi") String idCaSi);

    @GET("playlist_List.php")
    Call<List<Playlist>> GetPlaylist_List();

    @GET("genre_List.php")
    Call<List<Genre>> GetGenre_List();

    @GET("album_List.php")
    Call<List<Album>> GetAlbum_List();

    @GET("artist_List.php")
    Call<List<Artist>> GetArtist_List();

    @FormUrlEncoded
    @POST("updateloved.php")
    Call<String> UpdateLoved(@Field("LuotThich") String LuotThich, @Field("idBaihat") String idBaiHat);

    @FormUrlEncoded
    @POST("deletelove.php")
    Call<String> DeleteLove(@Field("LuotThich") String LuotThich, @Field("idBaihat") String idBaiHat);

    @FormUrlEncoded
    @POST("search.php")
    Call<List<Song>> GetSearchSong(@Field("key") String key);

}

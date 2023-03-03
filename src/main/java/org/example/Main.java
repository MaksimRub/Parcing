package org.example;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Main {

    public static final String api_wildeBerries="https://statistics-api.wildberries.ru";
    static String apiKey="";
    public static void main(String[] args){
        while(!isReallyOnline()){

        }

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(api_wildeBerries)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BerriesService berriesService=retrofit.create(BerriesService.class);
        Call<List<BerriesInfo>> call=berriesService.getSpaceInfo(apiKey,"2023-02-03");
        call.enqueue(new BerriesCallBack());
    }
   static class BerriesCallBack implements Callback<List<BerriesInfo>> {

        @Override
        public void onResponse(Call<List<BerriesInfo>> call, Response<List<BerriesInfo>> response) {
            if (response.isSuccessful() && response.body()!=null){
                List<BerriesInfo> berriesInfo= response.body();
                String s="Дата "+berriesInfo.get(0).supplierArticle;
                        //+spaceInfo.nmId+"\n"
                        //+"цена"+spaceInfo.price+"\n"
                        //+"скидка"+spaceInfo.discount+"\n"
                        //+"промокод"+spaceInfo.promoCode;
                System.out.println(s);

            }
        }

        @Override
        public void onFailure(Call<List<BerriesInfo>> call, Throwable t) {
            System.out.println(t.getMessage());
        }
    }
    static public boolean isReallyOnline() {

        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

}
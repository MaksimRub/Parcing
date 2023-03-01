package org.example;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;

public class Main {
    public static final String api_wildeBerries="https://statistics-api.wildberries.ru";
    static String apiKey="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2Nlc3NJRCI6IjQxYjNjZDlkLTEyMzUtNDYxZS1iMWMwLWZiZTc3ZTM4MjY0NiJ9.XAjWGSO9CG7q6nvWioOBdHFkBitQAfm15LBPyVOi5uQ";
    public static void main(String[] args){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(api_wildeBerries)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BerriesService berriesService=retrofit.create(BerriesService.class);
        //apiKey="";
        Call<BerriesInfo> call=berriesService.getSpaceInfo(" "+apiKey,"2019-06-20");
        call.enqueue(new BerriesCallBack());


    }
   static class BerriesCallBack implements Callback<BerriesInfo> {

        @Override
        public void onResponse(Call<BerriesInfo> call, Response<BerriesInfo> response) {
            if (response.isSuccessful() && response.body()!=null){
                BerriesInfo berriesInfo= response.body();
                String s="Дата "+berriesInfo.berries[0].number;
                        //+spaceInfo.nmId+"\n"
                        //+"цена"+spaceInfo.price+"\n"
                        //+"скидка"+spaceInfo.discount+"\n"
                        //+"промокод"+spaceInfo.promoCode;
                System.out.println(s);

            }
        }

        @Override
        public void onFailure(Call<BerriesInfo> call, Throwable t) {
            System.out.println("baaaaaad");
        }
    }

}
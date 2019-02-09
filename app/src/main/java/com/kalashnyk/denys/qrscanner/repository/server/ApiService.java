package com.kalashnyk.denys.qrscanner.repository.server;

import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("product")
    Single<List<ProductEntity>> getAllProducts();

}

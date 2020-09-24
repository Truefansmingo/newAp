package com.flagcamp.secondhands.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.flagcamp.secondhands.ui.fav.FavViewModel;
import com.flagcamp.secondhands.ui.home.HomeViewModel;
import com.flagcamp.secondhands.ui.productDetail.ProductDetailViewModel;

public class ProductViewModelFactory implements ViewModelProvider.Factory{
    private final ProductRepository repository;

    public ProductViewModelFactory(ProductRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository);
        }else if(modelClass.isAssignableFrom(ProductDetailViewModel.class)){
            return (T) new ProductDetailViewModel(repository);
        }else if(modelClass.isAssignableFrom((FavViewModel.class))){
            return (T)new FavViewModel(repository);
        } else{
            throw new IllegalStateException("Unknown ViewModel");
        }
    }
}

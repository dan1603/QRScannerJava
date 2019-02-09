package com.kalashnyk.denys.qrscanner.presentation.item;

public interface IProductItemClickListener<M> {
    void scanItem(M m, int position);
    void openDetail(M m);
}

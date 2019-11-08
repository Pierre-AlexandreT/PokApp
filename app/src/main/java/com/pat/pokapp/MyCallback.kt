package com.pat.pokapp


import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.Pokemons

abstract class MyCallback {

    open fun onSuccess(value: Pokemons) {}

    open fun onSuccess(value: Pokemon) {}

    //public void onSuccessGetPeople(@NonNull People value){}

    abstract fun onError(throwable: Throwable)

}

package com.pat.pokapp


import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.PokemonName
import com.pat.pokapp.entity.Pokemons

abstract class MyCallback {

    open fun onSuccess(value: Pokemons) {}

    open fun onSuccess(value: Pokemon) {}

    open fun onSuccess(value: PokemonName) {}

    //public void onSuccessGetPeople(@NonNull People value){}

    abstract fun onError(throwable: Throwable)

}

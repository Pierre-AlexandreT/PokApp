package com.pat.pokapp


import com.pat.pokapp.entity.Pokemon
import com.pat.pokapp.entity.PokemonName
import com.pat.pokapp.entity.Pokemons
import com.pat.pokapp.entity.PreviewPokemons
import com.pat.pokapp.entity.User.User
import com.pat.pokapp.entity.User.Users

abstract class MyCallback {

    open fun onSuccess(value: Pokemons) {}

    open fun onSuccess(value: Pokemon) {}

    open fun onSuccess(value: PokemonName) {}

    open fun onSuccess(value: PreviewPokemons) {}

    open fun onSuccess(value: Users) {}

    open fun onSuccess(value: User) {}


    //public void onSuccessGetPeople(@NonNull People value){}

    abstract fun onError(throwable: Throwable)

}

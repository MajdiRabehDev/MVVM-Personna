package fr.dev.majdi.personna.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.dev.majdi.personna.model.ResponsePersonna
import fr.dev.majdi.personna.model.Result
import fr.dev.majdi.personna.repository.PersonnaRepository
import org.koin.standalone.KoinComponent

/**
 * Created by Majdi RABEH on 28/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class PersonnaViewModel(val personnaRepository: PersonnaRepository) : ViewModel(), KoinComponent {

    var listOfUsers = MutableLiveData<List<Result>>()

    init {
        listOfUsers.value = listOf()
    }

    fun getUsers() {
        personnaRepository.getUsers(object : PersonnaRepository.OnPersonnaData {
            override fun onSuccess(responsePersonna: ResponsePersonna) {
                listOfUsers.value = responsePersonna.results
            }

            override fun onFailure() {
                listOfUsers.value = listOf()
            }
        })
    }
}
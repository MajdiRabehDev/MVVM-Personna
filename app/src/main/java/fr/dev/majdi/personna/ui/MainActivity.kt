package fr.dev.majdi.personna.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.dev.majdi.personna.R
import fr.dev.majdi.personna.adapter.PersonnaAdapter
import fr.dev.majdi.personna.model.Result
import fr.dev.majdi.personna.viewmodel.PersonnaViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Majdi RABEH on 28/06/2020.
 * Email : m.rabeh.majdi@gmail.com
 */
class MainActivity : AppCompatActivity() {

    private val personnaListModel: PersonnaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarTitle()
        initRecycle()
    }

    private fun setToolbarTitle() {
        supportActionBar?.title = resources.getString(R.string.title_main)
    }

    private fun initRecycle() {
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        personnaListModel.getUsers()
        personnaListModel.listOfUsers.observe(
            this,
            Observer(function = fun(personnaList: List<Result>?) {
                personnaList?.let {
                    if (personnaList.isNotEmpty()) {
                        visibilityRecycle(true)
                        val personnaListAdapter = PersonnaAdapter(personnaList)
                        recycler.adapter = personnaListAdapter
                        personnaListAdapter.setItemClickListener(object :
                            PersonnaAdapter.ItemClickListener {
                            override fun onItemClick(view: View, position: Int) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Clicked User gender is ${personnaList[position].gender}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                    } else {
                        visibilityRecycle(false)
                    }
                }
            })
        )
    }

    private fun visibilityRecycle(setVisible: Boolean) {
        if (setVisible) {
            recycler.visibility = View.VISIBLE
            no_data.visibility = View.GONE
        } else {
            recycler.visibility = View.GONE
            no_data.visibility = View.VISIBLE
        }
    }
}
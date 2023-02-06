package com.demir.kriptoborsa.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.SCROLLBARS_INSIDE_INSET
import com.demir.kriptoborsa.R
import com.demir.kriptoborsa.adepter.Adepter
import com.demir.kriptoborsa.databinding.ActivityMainBinding
import com.demir.kriptoborsa.model.KriptoModel
import com.demir.kriptoborsa.service.KriptoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),Adepter.Listener {
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var kriptoModel:ArrayList<KriptoModel>?=null
    private lateinit var binding: ActivityMainBinding
    private  var recylerViewAdepter:Adepter?=null
    private var compositeDisposable:CompositeDisposable?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable= CompositeDisposable()


        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        loadData()





    }
    private fun loadData(){
        //retrofit oluşturuyoruz
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(KriptoApi::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponce))



        //api ile retrofiti birbirine bağlıyoruz


        /*
        val call = service.getData()
        val service =retrofit.create(KriptoApi::class.java)
        call.enqueue(object :Callback<List<KriptoModel>>{
            override fun onResponse(
                call: Call<List<KriptoModel>>,
                response: Response<List<KriptoModel>>,

            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        kriptoModel= ArrayList(it)

                        kriptoModel?.let {abcd->

                            recylerViewAdepter= Adepter(abcd,this@MainActivity)
                            binding.recyclerView.adapter=recylerViewAdepter




                        }


                    }
                }

            }

            override fun onFailure(call: Call<List<KriptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

         */
    }
    private fun handleResponce(kriptoList: List<KriptoModel> ){
        kriptoModel= ArrayList(kriptoList)

        kriptoModel?.let {

            recylerViewAdepter = Adepter(it, this@MainActivity)
            binding.recyclerView.adapter = recylerViewAdepter
        }

    }

    override fun onClicked(kriptoModel: KriptoModel) {
       Toast.makeText(this  ,"Clicked: ${kriptoModel.currency}",Toast.LENGTH_LONG).show()

    }

    override fun onDestroy() {

        super.onDestroy()
        compositeDisposable?.clear()
    }

}
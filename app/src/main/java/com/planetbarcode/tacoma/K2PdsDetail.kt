package com.planetbarcode.tacoma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.planetbarcode.tacoma.Adapter.K2PdsDetailAdapter

class K2PdsDetail : AppCompatActivity() {
    companion object{
        lateinit var textViewPDS: TextView
        lateinit var textViewTotalRecord: TextView
        lateinit var recyclerView: RecyclerView
        lateinit var cardViewBack : CardView
        private lateinit var viewAdapter: RecyclerView.Adapter<*>
        private lateinit var viewManager: RecyclerView.LayoutManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pds_detail)

        textViewPDS = findViewById(R.id.txt_pds)
        textViewTotalRecord = findViewById(R.id.txt_total_record)
        recyclerView = findViewById(R.id.recycler1)
        cardViewBack = findViewById(R.id.cardView_back)

        textViewPDS.text = "PDS : "+K2.textViewPDS.text.toString()
        textViewTotalRecord.text = totalRecord()
        viewManager = LinearLayoutManager(this)
        viewAdapter = K2PdsDetailAdapter(K2.K2PDSArray, this)

        recyclerView.apply {

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        cardViewBack.setOnClickListener {
            finish();
            super.onBackPressed();
        }

        if(K2.currentIndex != -1){
            recyclerView.smoothScrollToPosition(K2.currentIndex)
        }
    }

    fun totalRecord():String{
        var totalK2Scan = 0
        var totalScan = 0
        for (i in 0 until K2.K2PDSArray.size){
            totalK2Scan += Integer.parseInt(K2.K2PDSArray[i].k2Scan)
            totalScan += Integer.parseInt(K2.K2PDSArray[i].packQty)
        }
        return "$totalK2Scan / $totalScan"
    }
}
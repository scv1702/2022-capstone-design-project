package com.example.capstoneandroid

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.capstoneandroid.databinding.ActivitySelectDocsBinding

class selectDocsActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectDocsBinding.inflate(layoutInflater) }
    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_docs)

        val newCreate = findViewById<ImageButton>(R.id.newCreate)
        val addfileon = findViewById<ImageButton>(R.id.addfileon)


        newCreate.setOnClickListener {
            val intent = Intent(this@selectDocsActivity, stylusActivity::class.java)
            intent.putExtra("번호", 9)
            startActivity(intent)
        }

        addfileon.setOnClickListener {
            val intent = Intent(this@selectDocsActivity, stylusActivity::class.java)
            intent.putExtra("번호", 8)
            startActivity(intent)
        }

        // 배너관련 코드 진행부

        var viewPager2 = findViewById<ViewPager2>(R.id.advertisingBanner)

        //데이터
        var Banners: Array<Int> =
            arrayOf(R.drawable.banner2, R.drawable.banner1, R.drawable.banner3)

        //Adapter 초기화
        var itemAdapter = ItemAdapter(Banners)

        //Adapter 적용
        viewPager2.adapter = itemAdapter
        // 가로방향
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 배너 몇 페이지인지 출력
        var txtCurrentBanner = findViewById<TextView>(R.id.txt_current_banner)
        txtCurrentBanner.setText(getString(R.string.viewpager2_banner, 1, Banners.size))
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //사용자가 스크롤 했을때 position 수정
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                txtCurrentBanner.setText(
                    getString(
                        R.string.viewpager2_banner,
                        position + 1,
                        Banners.size
                    )
                )
                if (currentPosition > 3) currentPosition = 0
            }
        })

        //페이지 변경하기
        fun setPage() {
            if (currentPosition > 3) currentPosition = 0
            viewPager2.setCurrentItem(currentPosition, true)
            currentPosition += 1
        }
        //핸들러 설정
        //ui 변경하기
        val handler = Handler(Looper.getMainLooper()) {
            setPage()
            true
        }

        //2초 마다 페이지 넘기기
        class PagerRunnable : Runnable {
            override fun run() {
                while (true) {
                    Thread.sleep(2000)
                    handler.sendEmptyMessage(0)
                }
            }
        }

        //뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()
    }

}
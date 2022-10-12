package com.example.capstoneandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.capstoneandroid.PaintView.Companion.colorList
import com.example.capstoneandroid.PaintView.Companion.pathList
import com.example.capstoneandroid.databinding.ActivitySelectDocsBinding
import com.google.android.material.card.MaterialCardView
import com.theartofdev.edmodo.cropper.CropImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.reflect.Field

class selectDocsActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelectDocsBinding.inflate(layoutInflater) }


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

    }
}
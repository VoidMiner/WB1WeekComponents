package com.ands.wb1weekcomponents.contentprovider

import com.ands.wb1weekcomponents.R
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contentprovidersinandroid.MyContentProvider


class ContentProviderActivity : AppCompatActivity() {
//доступ к контакнтам, пример: оплата мобильного с любого приложения, геолокация яндекс карты
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        //DataManager, resolver
////        val helper = MyHelper(applicationContext)
////        val db = helper.readableDatabase
////        val rs = db.rawQuery("SELECT * FROM ACTABLE", null)
////        if (rs.moveToNext())
////            Toast.makeText(this, rs.getString(1), Toast.LENGTH_SHORT).show()

    }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            return true
        }

        fun onClickAddDetails(view: View?) {

            // class to add values in the database добавить значение
            val values = ContentValues()

            // fetching text from user ввод текста
            values.put(MyContentProvider.name, (findViewById<View>(R.id.textName) as EditText).text.toString())

            // inserting into database through content URI вставка адрес
            contentResolver.insert(MyContentProvider.CONTENT_URI, values)

            // displaying a toast message сообщение о новой записи
            Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
        }

        fun onClickShowDetails(view: View?) {
            // inserting complete table details in this text field детали поля таблицы
            val resultView = findViewById<View>(R.id.res) as TextView

            // creating a cursor object of the создаем ссылку на объект
            // content URI адрес
            val cursor = contentResolver.query(Uri.parse("content://com.demo.user.provider/users"), null, null, null, null)

            // iteration of the cursor
            // to print whole table
            if (cursor!!.moveToFirst()) {
                val strBuild = StringBuilder()
                while (!cursor.isAfterLast) {
                    strBuild.append("""
	
	${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
	""".trimIndent())
                    cursor.moveToNext()
                }
                resultView.text = strBuild
            } else {
                resultView.text = "No Records Found"
            }
        }
    }




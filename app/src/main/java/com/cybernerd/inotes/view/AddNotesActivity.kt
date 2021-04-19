 package com.cybernerd.inotes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.cybernerd.inotes.R
import com.cybernerd.inotes.database.AppDatabase
import com.cybernerd.inotes.utils.CallbackListener
import com.cybernerd.inotes.utils.showToast
import com.cybernerd.inotes.viewModel.AddNotesViewModel
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.coroutines.launch

 class AddNotesActivity : BaseActivity(), CallbackListener {

     private lateinit var saveNotesBtn:Button
     lateinit var mydatabase: AppDatabase
     private val viewModel: AddNotesViewModel by lazy {
         AddNotesViewModel(this)
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        val intent = intent.extras

        val id = intent?.getInt("id", 0)

        if (id != null){
           editNote(id)
        }

        mydatabase = AppDatabase.invoke(this)


        saveNotesBtn = findViewById(R.id.saveNotesBtn)

        saveNotesBtn.setOnClickListener {

            if (id != null){
                viewModel.editNoteDB(id,titleEditText.text.toString(), descriptionEditText.text.toString(), this)
            }else if (titleEditText.text.isEmpty() and descriptionEditText.text.isEmpty()){
                showToast(this, "Please add title and description")
            }else{
                viewModel.addNoteDB(titleEditText.text.toString(), descriptionEditText.text.toString(), this)
            }

        }

        deleteNotesBtn.setOnClickListener {
            if (id != null) {
                viewModel.deleteNodeDB(id, this)
            }
        }

    }

     override fun callback(finish: Boolean) {
         if (finish) {
             Intent(this, MainActivity::class.java).apply {
                 this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                 startActivity(this)
             }
         }
     }

     private fun editNote(id: Int){
         launch {
             val data = mydatabase.noteDao().getNote(id)
             titleEditText.setText(data.title)
             descriptionEditText.setText(data.description)
             deleteNotesBtn.visibility = View.VISIBLE
         }
     }

     override fun onBackPressed() {
         super.onBackPressed()
         Intent(this, MainActivity::class.java).apply {
             this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
             startActivity(this)
         }
     }




 }

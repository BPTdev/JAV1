package ch.bookmybook

import android.app.AlertDialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import ch.bookmybook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        val buttonSubmit = findViewById(R.id.buttonSubmit) as Button
        val buttonDelete = findViewById(R.id.buttonDelete) as Button

        buttonSubmit.setOnClickListener {
            val inputFirstName = findViewById(R.id.inputFirstname) as EditText
            val inputLastName = findViewById(R.id.inputLastname) as EditText

            val result =  inputFirstName.text.toString() + " " + inputLastName.text.toString()

            val builder = AlertDialog.Builder(this)
            builder.setMessage(result)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
            val dialog = builder.create()
            dialog.show()

        }
        buttonDelete.setOnClickListener {
            val inputFirstName = findViewById(R.id.inputFirstname) as EditText
            val inputLastName = findViewById(R.id.inputLastname) as EditText

            inputFirstName.setText("")
            inputLastName.setText("")
        }


    }


}
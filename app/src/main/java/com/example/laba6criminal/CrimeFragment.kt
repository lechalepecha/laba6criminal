package com.example.laba6criminal
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var tittleField: EditText
    private lateinit var buttonDate: Button
    private lateinit var chekIsSolved: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ):View? {

        val view = inflater.inflate(R.layout.crime_fragment, container, false)
        tittleField = view.findViewById(R.id.crimeTitle) as EditText
        buttonDate = view.findViewById(R.id.dateCrime) as Button
        chekIsSolved = view.findViewById(R.id.isSolved) as CheckBox
        buttonDate.apply { text = crime.date.toString()
                            isEnabled = false
                          }
        return view
    }

    override fun onStart() {
        super.onStart()
        val titlWatcher = object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {


            }
        }
        tittleField.addTextChangedListener(titlWatcher)
        chekIsSolved.apply {
            setOnCheckedChangeListener{ _, isChecked-> crime.isSolved = isChecked}
        }
    }
}
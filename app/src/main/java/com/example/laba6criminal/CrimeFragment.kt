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
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import java.util.Date
import java.util.UUID

private const val TAG = "CrimeFragment"
private const val ARG_CRIME_ID = "crime_id"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0

class CrimeFragment : Fragment(), DatePickerFragment.Callbacks {
    private lateinit var crime: Crime
    private lateinit var tittleField: EditText
    private lateinit var buttonDate: Button
    private lateinit var chekIsSolved: CheckBox
    private val crimeDetailViewModel : CrimeDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()

        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        crimeDetailViewModel.loadCrime(crimeId)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ):View? {

        val view = inflater.inflate(R.layout.crime_fragment, container, false)
        tittleField = view.findViewById(R.id.crimeTitle) as EditText
        buttonDate = view.findViewById(R.id.dateCrime) as Button
        chekIsSolved = view.findViewById(R.id.isSolved) as CheckBox

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            Observer { crime -> crime?.let {
                this.crime = crime
                updateUI()
            }
            })
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

        buttonDate.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply {
                setTargetFragment(this@CrimeFragment, REQUEST_DATE)
                show(this@CrimeFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime)
    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }

        private fun updateUI() {
        tittleField.setText(crime.title)
        buttonDate.text = crime.date.toString()
        chekIsSolved.apply {
            isChecked = crime.isSolved
            jumpDrawablesToCurrentState()
        }

    }

    companion object {
        fun newInstance(crimeId: UUID) : CrimeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }
}
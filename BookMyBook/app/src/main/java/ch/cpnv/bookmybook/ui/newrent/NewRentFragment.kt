package ch.cpnv.bookmybook.ui.newrent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.cpnv.bookmybook.DBHelper
import ch.cpnv.bookmybook.databinding.FragmentNewRentBinding

class NewRentFragment : Fragment() {

    private var _binding: FragmentNewRentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newRentViewModel =
            ViewModelProvider(this).get(NewRentViewModel::class.java)

        _binding = FragmentNewRentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.RentTitle1
        newRentViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.RentButtonSave.setOnClickListener {
            val dateStart = binding.RentEditDateStart.text.toString()
            val dateEnd = binding.RentEditDateEnd.text.toString()
            //val bookId = binding.RentEditBookId.text.toString()
            val bookId = 1
            //val contactId = binding.RentEditContactId.text.toString()
            val contactId = 1

            val db = DBHelper(requireContext(), null)
            db.addRent(bookId, contactId,dateStart, dateEnd)
            Toast.makeText(requireContext(), dateStart+" to "+ dateEnd + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            binding.RentEditDateStart.text.clear()
            binding.RentEditDateEnd.text.clear()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package ch.cpnv.bookmybook.ui.newrent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
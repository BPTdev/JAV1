package ch.cpnv.bookmybook.ui.newbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.cpnv.bookmybook.databinding.FragmentNewBookBinding

class NewBookFragment : Fragment() {

    private var _binding: FragmentNewBookBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newBookViewModel =
            ViewModelProvider(this).get(NewBookViewModel::class.java)

        _binding = FragmentNewBookBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.BookTitle1
        newBookViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
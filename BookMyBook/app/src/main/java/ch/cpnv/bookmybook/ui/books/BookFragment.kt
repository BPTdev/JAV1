package ch.cpnv.bookmybook.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.cpnv.bookmybook.databinding.FragmentBooksBinding

class BookFragment : Fragment() {
    private var _binding: FragmentBooksBinding? = null
private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newRentViewModel =
            ViewModelProvider(this).get(BooksViewModel::class.java)

        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.booksListView

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
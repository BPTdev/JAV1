package ch.cpnv.bookmybook.ui.books

import android.app.Activity
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.cpnv.bookmybook.R
import ch.cpnv.bookmybook.databinding.FragmentBooksBinding
import ch.cpnv.bookmybook.DBHelper
import java.util.Date

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
        val dbHelper = DBHelper(requireContext(), null)
        val bookList: List<Book> = dbHelper.getBooks()
        val listAdapter: ListAdapter = BookAdapter(requireActivity(),bookList)
        listView.adapter=listAdapter
        return root
    }
    class BookAdapter(private val context: Activity, private val bookList: List<Book>) :
        ArrayAdapter<Book>(context, R.layout.listview_books_single_item, bookList) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.listview_books_single_item, null, true)

            val name = rowView.findViewById(R.id.Books_item_name) as TextView
            val isbn = rowView.findViewById(R.id.Books_item_isbn) as TextView

            name.text = bookList[position].name
            isbn.text = bookList[position].isbn

            return rowView
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
data class Book(
    val id: Int,
    val name: String,
    val description: String,
    val isbn: String
)
data class Rent(
    val id: Int,
    val contact: Int,
    val name: String,
    val startDate: String,
    val endDate: Date,
)
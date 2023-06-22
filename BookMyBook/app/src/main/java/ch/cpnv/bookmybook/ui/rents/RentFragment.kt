package ch.cpnv.bookmybook.ui.rents

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ch.cpnv.bookmybook.R
import ch.cpnv.bookmybook.databinding.FragmentRentsBinding
import ch.cpnv.bookmybook.DBHelper
import ch.cpnv.bookmybook.ui.books.Rent
import java.util.Date

class RentFragment : Fragment() {
    private var _binding: FragmentRentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.rentListView
        val dbHelper = DBHelper(requireContext(), null)
        val rentList: List<Rent> = dbHelper.getRents()
        val listAdapter: ListAdapter = RentAdapter(requireActivity(), rentList)
        listView.adapter = listAdapter

        return root
    }

    class RentAdapter(private val context: Activity, private val rentList: List<Rent>) :
        ArrayAdapter<Rent>(context, R.layout.listview_rents_single_item, rentList) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.listview_rents_single_item, parent, false)

            val contactId = rowView.findViewById(R.id.Rents_item_contactId) as TextView
            val booksId = rowView.findViewById(R.id.Rents_item_booksId) as TextView

            contactId.text = rentList[position].contact.toString()
            booksId.text = rentList[position].book.toString()

            return rowView
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

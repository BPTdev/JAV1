package ch.cpnv.bookmybook.ui.newbook

import android.content.Intent
import android.hardware.camera2.CameraCharacteristics
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.cpnv.bookmybook.DBHelper
import ch.cpnv.bookmybook.databinding.FragmentNewBookBinding

class NewBookFragment : Fragment() {

    private var _binding: FragmentNewBookBinding? = null

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
        binding.BookButtonScanner.setOnClickListener {
            openCamera()
        }

        binding.BookButtonSave.setOnClickListener {
            val name = binding.BookEditTextName.text.toString()
            val description = binding.BookEditTextDescription.text.toString()
            val isbn = binding.BookEditTextIsbn.text.toString()
            val db = DBHelper(requireContext(), null)
            db.addBook(name, description, isbn)
            Toast.makeText(requireContext(), name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            binding.BookEditTextName.text.clear()
            binding.BookEditTextDescription.text.clear()
            binding.BookEditTextIsbn.text.clear()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && Build.VERSION.SDK_INT < Build.VERSION_CODES.O -> {
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT)  // Tested on API 24 Android version 7.0(Samsung S6)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT) // Tested on API 27 Android version 8.0(Nexus 6P)
                cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
            }
            else -> cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)  // Tested API 21 Android version 5.0.1(Samsung S4)
        }
        startActivityForResult(cameraIntent, 0)
    }
}
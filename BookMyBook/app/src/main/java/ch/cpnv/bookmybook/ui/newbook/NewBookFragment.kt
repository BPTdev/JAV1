package ch.cpnv.bookmybook.ui.newbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ch.cpnv.bookmybook.databinding.FragmentNewBookBinding
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun scanBarcodes(image: InputImage) {
        // [START set_detector_options]
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC)
            .build()
        // [END set_detector_options]

        // [START get_detector]
        val scanner = BarcodeScanning.getClient()
        // Or, to specify the formats to recognize:
        // val scanner = BarcodeScanning.getClient(options)
        // [END get_detector]

        // [START run_detector]
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                // Task completed successfully
                // [START_EXCLUDE]
                // [START get_barcodes]
                for (barcode in barcodes) {
                    val bounds = barcode.boundingBox
                    val corners = barcode.cornerPoints

                    val rawValue = barcode.rawValue

                    val valueType = barcode.valueType
                    // See API reference for complete list of supported types
                    when (valueType) {
                        Barcode.TYPE_WIFI -> {
                            val ssid = barcode.wifi!!.ssid
                            val password = barcode.wifi!!.password
                            val type = barcode.wifi!!.encryptionType
                        }
                        Barcode.TYPE_URL -> {
                            val title = barcode.url!!.title
                            val url = barcode.url!!.url
                        }
                    }
                }
                // [END get_barcodes]
                // [END_EXCLUDE]
            }
            .addOnFailureListener {
                // Task failed with an exception
                // ...
            }
        // [END run_detector]
    }
}
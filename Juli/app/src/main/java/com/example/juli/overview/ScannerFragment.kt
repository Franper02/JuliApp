package com.example.juli.overview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.juli.R
import com.google.zxing.integration.android.IntentIntegrator
import com.example.juli.databinding.FragmentScannerBinding

class ScannerFragment : Fragment() {

    private val viewModel : OverviewViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentScannerBinding.inflate(inflater)

        //binding.btnScanner.setOnClickListener{ initScanner() }
        binding.viewModel = viewModel
        initScanner()
        return binding.root
    }


    private fun initScanner() {
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null){
            if (result.contents == null){
                Toast.makeText(activity, "Cancelado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "El valor escaneado es: ${result.contents}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_scannerFragment2_to_overviewFragment3)
                viewModel.getFoto(result.contents.toString())

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
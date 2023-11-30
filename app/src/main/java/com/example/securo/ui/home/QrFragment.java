package com.example.securo.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.securo.R;
import com.example.securo.databinding.FragmentProfileBinding;
import com.example.securo.databinding.FragmentQrBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrFragment extends Fragment {

    private FragmentQrBinding binding;
    public static String quer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QrViewModel qrViewModel =
                new ViewModelProvider(this).get(QrViewModel.class);

        binding = FragmentQrBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        binding.button.setOnClickListener(v1 -> {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            try {
                BitMatrix bitMatrix = multiFormatWriter.encode("https://firebasestorage.googleapis.com/v0/b/s3cur0.appspot.com/o/images%2F" + quer + "?alt=media", BarcodeFormat.QR_CODE, 300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                binding.qrCode.setImageBitmap(bitmap);

            }catch (WriterException e){
                throw new RuntimeException(e);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
package com.example.securo.ui.notifications;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.securo.LoginActivity;
import com.example.securo.R;
import com.example.securo.UploadDocsActivity;
import com.example.securo.databinding.FragmentProfileBinding;
import com.example.securo.ui.home.QrFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment{

    FirebaseUser user;
    FirebaseAuth auth;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        Button table = (Button) v.findViewById(R.id.table);
        Button schoolweb = (Button) v.findViewById(R.id.schoolWeb);
        Button logout = (Button) v.findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Button mButtonUpload = (Button) v.findViewById(R.id.buttonUploadDocs);

        if (user == null) {
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        binding.table.setOnClickListener(v1 -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lyceum.nstu.ru/rasp/schedule.html"));
            startActivity(browserIntent);
        });
        binding.schoolWeb.setOnClickListener(v1 -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lyceum.nstu.ru/"));
            startActivity(browserIntent);
        });

        binding.logout.setOnClickListener(v1 -> {
            FirebaseAuth.getInstance().signOut();
            QrFragment.quer = null;
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Query", Context.MODE_PRIVATE);
            QrFragment.quer = sharedPreferences.getString("Q", QrFragment.quer);
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        binding.buttonUploadDocs.setOnClickListener(v1 -> {
            if (QrFragment.quer == null) {
                Intent intent = new Intent(getActivity().getApplicationContext(), UploadDocsActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Вы уже добавили пропуск", Toast.LENGTH_SHORT).show();
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
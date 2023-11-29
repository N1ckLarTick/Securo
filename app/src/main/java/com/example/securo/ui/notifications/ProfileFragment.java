package com.example.securo.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.securo.LoginActivity;
import com.example.securo.R;
import com.example.securo.databinding.FragmentProfileBinding;
import com.google.firebase.Firebase;
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

        if (user == null) {
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        binding.table.setOnClickListener(v1 -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lyceum.nstu.ru/liceistam/itemlist/category/470-raspisanie-zanyatij"));
            startActivity(browserIntent);
        });
        binding.schoolWeb.setOnClickListener(v1 -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lyceum.nstu.ru/"));
            startActivity(browserIntent);
        });

        binding.logout.setOnClickListener(v1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
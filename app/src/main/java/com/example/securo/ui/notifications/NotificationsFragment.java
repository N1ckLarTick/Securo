package com.example.securo.ui.notifications;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.securo.R;
import com.example.securo.databinding.ActivityMainBinding;
import com.example.securo.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment{

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View v = inflater.inflate(R.layout.fragment_notifications, container, false);

        Button table = (Button) v.findViewById(R.id.Table);
        Button schoolweb = (Button) v.findViewById(R.id.SchoolWeb);

        binding.Table.setOnClickListener(v1 -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lyceum.nstu.ru/liceistam/itemlist/category/470-raspisanie-zanyatij"));
            startActivity(browserIntent);
        });
        binding.SchoolWeb.setOnClickListener(v1 -> {
            Intent browserIntent = new
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://lyceum.nstu.ru/"));
            startActivity(browserIntent);
        });





        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package io.github.livenlearnaday.countrylistjava.ui;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.github.livenlearnaday.countrylistjava.R;
import io.github.livenlearnaday.countrylistjava.data.entity.Country;
import io.github.livenlearnaday.countrylistjava.databinding.ActivityMainBinding;
import io.github.livenlearnaday.countrylistjava.ui.fragment_country.CountryFragment;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;
    private int fragmentContainerId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        fragmentContainerId = binding.container.getId();


        loadFragment(CountryFragment.newInstance(fragmentContainerId));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setTitle("Exit Program")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        if(Build.VERSION.SDK_INT>=16 && Build.VERSION.SDK_INT<21){
                            finishAffinity();
                        } else if(Build.VERSION.SDK_INT>=21){
                            finishAndRemoveTask();
                        }
                    }
                }).create().show();

    }


    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(fragmentContainerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); // save the changes
    }


    public void changeFragment(int fragmentContainerId, @NonNull Fragment fragmentToChange){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace( fragmentContainerId,fragmentToChange );
        fragmentTransaction.addToBackStack(null);
//        transaction.disallowAddToBackStack(); // <-- This makes magic!
        fragmentTransaction.commit();

    }






}

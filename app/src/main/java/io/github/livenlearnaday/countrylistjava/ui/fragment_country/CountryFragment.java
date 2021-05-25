package io.github.livenlearnaday.countrylistjava.ui.fragment_country;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.github.livenlearnaday.countrylistjava.R;
import io.github.livenlearnaday.countrylistjava.data.entity.Country;
import io.github.livenlearnaday.countrylistjava.databinding.FragmentCountryBinding;
import io.github.livenlearnaday.countrylistjava.service.APIClient;
import io.github.livenlearnaday.countrylistjava.service.APIInterface;
import io.github.livenlearnaday.countrylistjava.ui.fragment_country.adapter.CountryFragmentAdapter;
import io.github.livenlearnaday.countrylistjava.utils.MessageUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;



public class CountryFragment extends Fragment {

    private RecyclerView mCountryFragmentRecyclerView;
    private ProgressBar progressBar;
    APIInterface apiInterface;
    private FragmentCountryBinding binding;
    private List<Country> mCountryList;
    private int mFragmentContainerId;



    public static CountryFragment newInstance(int fragmentContainerId) {

        Bundle arguments = new Bundle();
        arguments.putInt("fragment_container_id", fragmentContainerId );

        CountryFragment fragment = new CountryFragment();
        fragment.setArguments(arguments);

        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);




    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCountryBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        mCountryList = new ArrayList<>();
        if (getArguments() != null) {
            mFragmentContainerId = getArguments().getInt("fragment_container_id");
        }

        getCountryListData();


        return view;


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.myToolbar.inflateMenu(R.menu.menu_country_fragment);

        binding.myToolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.refresh_menu) {
                
                getCountryListData();
                return true;

            } else {

                return false;

            }
        });


    }

    private void getCountryListData() {

        try {

            Call<List<Country>> call = APIClient.getInstance().getMyApi().getAllCountries();


            call.enqueue(new Callback<List<Country>>() {
                @Override
                public void onResponse(@NotNull Call<List<Country>> call, @NotNull Response<List<Country>> response) {
                    binding.progressbar.setVisibility(View.GONE);
                    mCountryList = response.body();
                    generateDataList(mCountryList);


                }

                @Override
                public void onFailure(@NotNull Call<List<Country>> call, @NotNull Throwable t) {
                    binding.progressbar.setVisibility(View.GONE);
                    call.cancel();
                    MessageUtils.showAlertDialog(getActivity(), "get data error", "");
                }
            });

        } catch (Exception e) {
            Timber.i("autolog Exception");
        }


    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Country> countryList) {

        CountryFragmentAdapter mCountryFragmentAdapter = new CountryFragmentAdapter(countryList, mFragmentContainerId);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mCountryFragmentAdapter);





    }





}

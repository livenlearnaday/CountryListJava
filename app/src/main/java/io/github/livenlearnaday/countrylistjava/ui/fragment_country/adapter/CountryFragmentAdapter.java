package io.github.livenlearnaday.countrylistjava.ui.fragment_country.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import io.github.livenlearnaday.countrylistjava.R;
import io.github.livenlearnaday.countrylistjava.data.entity.Country;
import io.github.livenlearnaday.countrylistjava.databinding.CountryListItemBinding;
import io.github.livenlearnaday.countrylistjava.utils.MessageUtils;
import timber.log.Timber;


public class CountryFragmentAdapter extends RecyclerView.Adapter<CountryFragmentAdapter.CountryViewHolder> {


    private final List<Country> mCountryList;
    public static CountryFragmentAdapterOnItemClickListener mClickListener;


    public CountryFragmentAdapter(List<Country> countryList, int fragmentContainerId) {

        this.mCountryList = countryList;

    }


    @Override
    public @NotNull CountryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        CountryListItemBinding countryListItemBinding = CountryListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);


        return new CountryViewHolder(countryListItemBinding);

    }


    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {


        final Country country = mCountryList.get(position);


        holder.binding.countryItemCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("onClick: clicked on: %d", position);
                Timber.d("onClick: country name: %s", country.getName());
                Timber.d("onClick: country flag: %s", country.getFlag());

                MessageUtils.showAlertDialog(
                        holder.itemView.getContext(),
                        country.getName(),
                        "The capital city of this country is "
                                + country.getCapital()
                                + "."
                );
            }
        });

        holder.binding.countryName.setText(country.getName());

        Uri uri = Uri.parse(country.getFlag());

        GlideToVectorYou
                .init()
                .with(holder.itemView.getContext())
                .setPlaceHolder(R.drawable.border, R.drawable.ic_baseline_error_outline_24)  //loading,error
                .load((uri), holder.binding.imageView);


    }


    @Override
    public int getItemCount() {
        return mCountryList.size();
    }


    public interface CountryFragmentAdapterOnItemClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);

    }


    public static class CountryViewHolder extends RecyclerView.ViewHolder  {

        private final CountryListItemBinding binding;

        public CountryViewHolder(CountryListItemBinding countryListItemBinding) {
            super(countryListItemBinding.getRoot());

            this.binding = countryListItemBinding;



        }



    }


}

package ma.projet.gestionville_zone.ui.ville;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VilleViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VilleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
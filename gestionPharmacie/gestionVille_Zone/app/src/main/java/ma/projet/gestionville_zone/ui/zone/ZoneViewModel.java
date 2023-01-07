package ma.projet.gestionville_zone.ui.zone;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ZoneViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ZoneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
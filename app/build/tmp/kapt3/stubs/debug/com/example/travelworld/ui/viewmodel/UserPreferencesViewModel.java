package com.example.travelworld.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.travelworld.data.SharedPrefsManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\fJ\u000e\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\bJ\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\fR#\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R+\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/example/travelworld/ui/viewmodel/UserPreferencesViewModel;", "Landroidx/lifecycle/ViewModel;", "sharedPrefsManager", "Lcom/example/travelworld/data/SharedPrefsManager;", "(Lcom/example/travelworld/data/SharedPrefsManager;)V", "availableLanguages", "", "Lkotlin/Pair;", "", "getAvailableLanguages", "()Ljava/util/List;", "<set-?>", "", "darkThemeEnabled", "getDarkThemeEnabled", "()Z", "setDarkThemeEnabled", "(Z)V", "darkThemeEnabled$delegate", "Landroidx/compose/runtime/MutableState;", "language", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "language$delegate", "notificationEnabled", "getNotificationEnabled", "setNotificationEnabled", "notificationEnabled$delegate", "updateDarkTheme", "", "enabled", "updateLanguage", "newLanguage", "updateNotifications", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UserPreferencesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.data.SharedPrefsManager sharedPrefsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState darkThemeEnabled$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState notificationEnabled$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState language$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> availableLanguages = null;
    
    @javax.inject.Inject()
    public UserPreferencesViewModel(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.SharedPrefsManager sharedPrefsManager) {
        super();
    }
    
    public final boolean getDarkThemeEnabled() {
        return false;
    }
    
    private final void setDarkThemeEnabled(boolean p0) {
    }
    
    public final boolean getNotificationEnabled() {
        return false;
    }
    
    private final void setNotificationEnabled(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguage() {
        return null;
    }
    
    private final void setLanguage(java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> getAvailableLanguages() {
        return null;
    }
    
    public final void updateDarkTheme(boolean enabled) {
    }
    
    public final void updateNotifications(boolean enabled) {
    }
    
    public final void updateLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String newLanguage) {
    }
}
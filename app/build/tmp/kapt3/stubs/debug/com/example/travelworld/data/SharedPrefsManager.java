package com.example.travelworld.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.travelworld.utils.LanguageChangeUtil;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0007\u001a\u0004\u0018\u00010\u00108F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/travelworld/data/SharedPrefsManager;", "", "preferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "(Landroid/content/SharedPreferences;Landroid/content/Context;)V", "value", "", "darkTheme", "getDarkTheme", "()Z", "setDarkTheme", "(Z)V", "languageChangeUtil", "Lcom/example/travelworld/utils/LanguageChangeUtil;", "", "userLanguage", "getUserLanguage", "()Ljava/lang/String;", "setUserLanguage", "(Ljava/lang/String;)V", "app_debug"})
public final class SharedPrefsManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.utils.LanguageChangeUtil languageChangeUtil = null;
    
    @javax.inject.Inject()
    public SharedPrefsManager(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUserLanguage() {
        return null;
    }
    
    public final void setUserLanguage(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    public final boolean getDarkTheme() {
        return false;
    }
    
    public final void setDarkTheme(boolean value) {
    }
}
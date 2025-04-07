package com.example.travelworld.ui.view.userpref_icon;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.tooling.preview.Preview;
import com.example.travelworld.ui.viewmodel.UserPreferencesViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0007\u001a<\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a@\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0012\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u00a8\u0006\u0018"}, d2 = {"LanguageDropdown", "", "selectedLanguage", "", "onLanguageSelected", "Lkotlin/Function1;", "availableLanguages", "", "PreferenceItem", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "title", "description", "checked", "", "onCheckedChange", "PreferenceItemsSection", "darkThemeEnabled", "notificationEnabled", "onThemeChange", "onNotificationsChange", "UserPreferencesApp", "viewModel", "Lcom/example/travelworld/ui/viewmodel/UserPreferencesViewModel;", "app_debug"})
public final class UserPreferencesSecreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void UserPreferencesApp(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.ui.viewmodel.UserPreferencesViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PreferenceItemsSection(boolean darkThemeEnabled, boolean notificationEnabled, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onThemeChange, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onNotificationsChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PreferenceItem(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, boolean checked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void LanguageDropdown(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedLanguage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onLanguageSelected, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableLanguages) {
    }
}
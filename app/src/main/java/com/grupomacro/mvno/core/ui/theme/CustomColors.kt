package com.grupomacro.mvno.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(

    //Gradiente Splash
    val backgroundSplash1: Color = Color.Unspecified,
    val backgroundSplash2: Color = Color.Unspecified,
    val backgroundSplash3: Color = Color.Unspecified,


    val backgroundMacroPayGeneral: Color = Color.Unspecified,
    val textFieldValue: Color = Color.Unspecified,
    val textFieldHint: Color = Color.Unspecified,
    val textFieldBorder: Color = Color.Unspecified,
    val textFieldBackground: Color = Color.Unspecified,
    val textFieldTopLabel: Color = Color.Unspecified,

    // yellow button
    val buttonFeaturedContent: Color = Color.Unspecified,
    val buttonFeaturedBackground: Color = Color.Unspecified,

    // transparent button
    val buttonFeatured2Content: Color = Color.Unspecified,
    val buttonFeatured2Background: Color = Color.Unspecified,

    // regular button
    val buttonRegularContent: Color = Color.Unspecified,
    val buttonRegularBackground: Color = Color.Unspecified,
    val buttonRegularDisabledBackground: Color = Color.Unspecified,

    // small circular floating button
    val buttonSmallCircularContent: Color = Color.Unspecified,
    val buttonSmallCircularBackground: Color = Color.Unspecified,

    val textButtonRegularContent: Color = Color.Unspecified,

    // titles text (centered)
    val textTitleLargeDefault: Color = Color.Unspecified,
    val textTitleMediumDefault: Color = Color.Unspecified,

    // password indications normal color
    val textPasswordIndications: Color = Color.Unspecified,

    //background gray
    val backgroundGrayScreen: Color = Color.Unspecified,
    val backgroundGrayContainer: Color = Color.Unspecified,
    val backgroundGrayContainerBorder: Color = Color.Unspecified,

    val textTitleA1ADC0: Color = Color.Unspecified,
    val textTitle: Color = Color.Unspecified,
    val textTitleSM: Color = Color.Unspecified,
    val textTitle54B569: Color = Color.Unspecified,

    // Gradiente Card
    val cardTypePayment: Color = Color.Unspecified,
    val cardTypePaymentGradient1: Color = Color.Unspecified,
    val cardTypePaymentGradient2: Color = Color.Unspecified,
    val cardNotifications: Color = Color.Unspecified,

    // Background Delete
    val cardDelete: Color = Color.Unspecified,

    // Background avatar image
    val backgroundAvatarImage: Color = Color.Unspecified,
)

val backgroundSplash1 = blueSplash1
val backgroundSplash2 = blueSplash2
val backgroundSplash3 = blueSplash3

val backgroundMacroPayGeneral = blueMacropay
val LightTextFieldValue = black
val LightTextFieldHint = greyLight6
val LightTextFieldBorder = greyLight4
val LightTextFieldBackground = white
val LightTextFieldTopLabel = greyDark3
val LightButtonFeaturedContent = blueDark1
val LightButtonFeaturedBackground = yellowMacropay
val LightButtonFeatured2Content = white
val LightButtonFeatured2Background = transparent
val LightButtonRegularContent = white
val LightButtonRegularBackground = pinkMacropay
val LightButtonRegularDisabledBackground = greyLight5
val LightButtonSmallCircularContent = pinkMacropay
val LightButtonSmallCircularBackground = white
val LightTextButtonRegularContent = blueLight2
val LightTextTitleLargeDefault = greyDark3
val LightTextTitleMediumDefault = greyDark2
val LightTextPasswordIndications = greyDark1
val LightBackgroundGrayScreen = greyLight1
val LightBackgroundGrayContainer = greyLight6
val LightBackgroundGrayContainerBorder = greyLight3
val LightTextTitle = greyDark1
val LightTextTitleSM = greyDark11
val LightTextTitleA1ADC0 = greyDark4
val LightTextTitle54B569 = greenLight
val LightBackgroundAvatarImage = blueDark2


val DarkTextFieldValue = black
val DarkTextFieldHint = greyLight6
val DarkTextFieldBorder = greyLight4
val DarkTextFieldBackground = white
val DarkTextFieldTopLabel = greyDark3
val DarkButtonFeaturedContent = blueDark1
val DarkButtonFeaturedBackground = yellowMacropay
val DarkButtonFeatured2Content = white
val DarkButtonFeatured2Background = transparent
val DarkButtonRegularContent = white
val DarkButtonRegularBackground = blueMacropay
val DarkButtonRegularDisabledBackground = greyLight5
val DarkButtonSmallCircularContent = pinkMacropay
val DarkButtonSmallCircularBackground = white
val DarkTextButtonRegularContent = blueLight2
val DarkTextTitleLargeDefault = greyDark3
val DarkTextTitleMediumDefault = greyDark2
val DarkTextPasswordIndications = greyDark1
val DarkBackgroundGrayScreen = greyLight1
val DarkBackgroundGrayContainer = greyLight6
val DarkBackgroundGrayContainerBorder = greyLight3
val DarkTextTitle = greyDark1
val DarkTextTitleSM = greyDark11
val DarkTextTitleA1ADC0 = greyDark4
val DarkTextTitle54B569 = greenLight
val DarkBackgroundAvatarImage = blueDark2
val CardTypePayment = orange
val CardTypePaymentGradient1 = orangeGradient1
val CardTypePaymentGradient2 = orangeGradient2
val CardNotifications = blueTransparent
var CardDelete = redDelete


val LightCustomColors = CustomColors(
    backgroundSplash1 = backgroundSplash1,
    backgroundSplash2 = backgroundSplash2,
    backgroundSplash3 = backgroundSplash3,
    backgroundMacroPayGeneral = backgroundMacroPayGeneral,
    textFieldValue = LightTextFieldValue,
    textFieldHint = LightTextFieldHint,
    textFieldBorder = LightTextFieldBorder,
    textFieldBackground = LightTextFieldBackground,
    textFieldTopLabel = LightTextFieldTopLabel,
    buttonFeaturedContent = LightButtonFeaturedContent,
    buttonFeaturedBackground = LightButtonFeaturedBackground,
    buttonFeatured2Content = LightButtonFeatured2Content,
    buttonFeatured2Background = LightButtonFeatured2Background,
    buttonRegularContent = LightButtonRegularContent,
    buttonRegularBackground = LightButtonRegularBackground,
    buttonRegularDisabledBackground = LightButtonRegularDisabledBackground,
    buttonSmallCircularContent = LightButtonSmallCircularContent,
    buttonSmallCircularBackground = LightButtonSmallCircularBackground,
    textButtonRegularContent = LightTextButtonRegularContent,
    textTitleLargeDefault = LightTextTitleLargeDefault,
    textTitleMediumDefault = LightTextTitleMediumDefault,
    textPasswordIndications = LightTextPasswordIndications,
    backgroundGrayScreen = LightBackgroundGrayScreen,
    backgroundGrayContainer = LightBackgroundGrayContainer,
    backgroundGrayContainerBorder = LightBackgroundGrayContainerBorder,
    textTitle = LightTextTitle,
    textTitleSM = LightTextTitleSM,
    textTitleA1ADC0 = LightTextTitleA1ADC0,
    textTitle54B569 = LightTextTitle54B569,
    cardTypePayment = CardTypePayment,
    cardTypePaymentGradient1 = CardTypePaymentGradient1,
    cardTypePaymentGradient2 = CardTypePaymentGradient2,
    cardNotifications = CardNotifications,
    cardDelete = CardDelete,
    backgroundAvatarImage = LightBackgroundAvatarImage,
)

val DarkCustomColors = CustomColors(
    backgroundSplash1 = backgroundSplash1,
    backgroundSplash2 = backgroundSplash2,
    backgroundSplash3 = backgroundSplash3,
    backgroundMacroPayGeneral = backgroundMacroPayGeneral,
    textFieldValue = DarkTextFieldValue,
    textFieldHint = DarkTextFieldHint,
    textFieldBorder = DarkTextFieldBorder,
    textFieldBackground = DarkTextFieldBackground,
    textFieldTopLabel = DarkTextFieldTopLabel,
    buttonFeaturedContent = DarkButtonFeaturedContent,
    buttonFeaturedBackground = DarkButtonFeaturedBackground,
    buttonFeatured2Content = DarkButtonFeatured2Content,
    buttonFeatured2Background = DarkButtonFeatured2Background,
    buttonRegularContent = DarkButtonRegularContent,
    buttonRegularBackground = DarkButtonRegularBackground,
    buttonRegularDisabledBackground = DarkButtonRegularDisabledBackground,
    buttonSmallCircularContent = DarkButtonSmallCircularContent,
    buttonSmallCircularBackground = DarkButtonSmallCircularBackground,
    textButtonRegularContent = DarkTextButtonRegularContent,
    textTitleLargeDefault = DarkTextTitleLargeDefault,
    textTitleMediumDefault = DarkTextTitleMediumDefault,
    textPasswordIndications = DarkTextPasswordIndications,
    backgroundGrayScreen = DarkBackgroundGrayScreen,
    backgroundGrayContainer = DarkBackgroundGrayContainer,
    backgroundGrayContainerBorder = DarkBackgroundGrayContainerBorder,
    textTitle = DarkTextTitle,
    textTitleSM = DarkTextTitleSM,
    textTitleA1ADC0 = DarkTextTitleA1ADC0,
    textTitle54B569 = DarkTextTitle54B569,
    cardTypePayment = CardTypePayment,
    cardTypePaymentGradient1 = CardTypePaymentGradient1,
    cardTypePaymentGradient2 = CardTypePaymentGradient2,
    cardNotifications = CardNotifications,
    cardDelete = CardDelete,
    backgroundAvatarImage = DarkBackgroundAvatarImage,
)

val LocalCustomColors = staticCompositionLocalOf { CustomColors() }

val MaterialTheme.customColors: CustomColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current

package ru.netology.page;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VertificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify] input");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification] input");
    public void verifyVerificationPageVisiblity() {codeField.shouldBe(visible);}
    public void verifyErrorNotificationVisiblity() {errorNotification.shouldBe(visible);}
    public DashBoardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashBoardPage();
    }
    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }
}

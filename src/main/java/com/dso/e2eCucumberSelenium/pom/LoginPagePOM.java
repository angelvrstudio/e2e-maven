package com.dso.e2eCucumberSelenium.pom;

import com.dso.e2eCucumberSelenium.beans.webElements.ElementDTO;
import com.dso.e2eCucumberSelenium.beans.webElements.LabelElementDTO;
import com.dso.e2eCucumberSelenium.exception.NotFoundResourceException;
import com.dso.e2eCucumberSelenium.utilsType.PageHelper;
import com.dso.e2eCucumberSelenium.utilsType.WebSelector;
import com.dso.e2eCucumberSelenium.utilsType.constans.front.common.ElementAttributeKeys;
import com.dso.e2eCucumberSelenium.utilsType.constans.front.common.WebComponentKeys;
import com.dso.e2eCucumberSelenium.utilsType.constans.front.common.WebElementTypesKeys;
import com.dso.e2eCucumberSelenium.utilsType.constans.front.specific.LoginFormKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Hashtable;

public class LoginPagePOM {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoginPagePOM.class);
    private String keyWebComponent =  WebComponentKeys.loginForm.name();

    private String submitTypeElement = WebElementTypesKeys.submit.name();
    private String txtBoxTypeElement = WebElementTypesKeys.txtBox.name();
    private String labelTypeElement = WebElementTypesKeys.label.name();

    private Hashtable<String, ElementDTO> webElementsList = new Hashtable<String, ElementDTO>();

    private String AccessToLoginPageKey = LoginFormKeys.AccessToLoginPage.name();
    private String insertIdUserKey =  LoginFormKeys.InsertName.name();
    private String insertPasswordKey = LoginFormKeys.InsertPassword.name();
    private String submitButtonKey = LoginFormKeys.SubmitButton.name();
    private String logInUnsuccessfullyKey = LoginFormKeys.UnsuccessfullyLabel.name();

    private  WebDriver currentDriver;

    public LoginPagePOM(WebDriver currentDriver) throws NotFoundResourceException {
        this.currentDriver = currentDriver;
        loadByIdElements();
    }

    private void loadByIdElements() throws NotFoundResourceException {
        Hashtable<String, ElementDTO> virtualWebElementsAtr = new Hashtable<String, ElementDTO>();
        ElementDTO virtualWebElement;

        By idElement = WebSelector.getElementAttribute(keyWebComponent, AccessToLoginPageKey);
        virtualWebElement = new ElementDTO(AccessToLoginPageKey,idElement,submitTypeElement);
        virtualWebElementsAtr.put(AccessToLoginPageKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, insertIdUserKey);
        virtualWebElement = new ElementDTO(insertIdUserKey,idElement,txtBoxTypeElement);
        virtualWebElementsAtr.put(insertIdUserKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, insertPasswordKey);
        virtualWebElement = new ElementDTO(insertPasswordKey,idElement,txtBoxTypeElement);
        virtualWebElementsAtr.put(insertPasswordKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, submitButtonKey);
        virtualWebElement = new ElementDTO(submitButtonKey,idElement,submitTypeElement);
        virtualWebElementsAtr.put(submitButtonKey,virtualWebElement);

        idElement = WebSelector.getElementAttribute(keyWebComponent, logInUnsuccessfullyKey);
        String labelText = WebSelector.getElementAttribute(keyWebComponent, logInUnsuccessfullyKey, ElementAttributeKeys.labelText.name());
        virtualWebElement = new LabelElementDTO(logInUnsuccessfullyKey, idElement, labelTypeElement, labelText);
        virtualWebElementsAtr.put(logInUnsuccessfullyKey,virtualWebElement);


        setWebElementsList(virtualWebElementsAtr);
    }

    public void accessToLoginPage() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(AccessToLoginPageKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }

    public void setIdUser(String idUser) throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(insertIdUserKey);
        PageHelper.setTxtBoxText(virtualWebElementDTO,idUser);
    }

    public void goToInsertPWD() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(insertPasswordKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }

    public void setPassword(String password) throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(insertPasswordKey);
        PageHelper.setTxtBoxText(virtualWebElementDTO,password);
    }

    public void commit() throws NotFoundResourceException {
        ElementDTO virtualWebElementDTO = this.getWebElementsList().get(submitButtonKey);
        PageHelper.clickOnElement(virtualWebElementDTO);
    }
    public String loginUnsuccesfully () throws NotFoundResourceException{
        LabelElementDTO virtualWebElementDTO = (LabelElementDTO)this.getWebElementsList().get(logInUnsuccessfullyKey);
        String texto =PageHelper.getLabelText(virtualWebElementDTO).getLabelText();
        return texto;
    }

    public Hashtable<String, ElementDTO> getWebElementsList() {
        return webElementsList;
    }

    private void setWebElementsList(Hashtable<String, ElementDTO> webElementsList) {
        this.webElementsList = webElementsList;
    }

    private void updateWebElementAtrList (String webElementKey , ElementDTO webElementArt){
        this.getWebElementsList().replace(webElementKey,webElementArt);
    }
}

package org.example.web;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.gestionare_taskuri.servicii.TaskService;


@Route("login")
@PageTitle("Login | Vaadin Security")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    //
    private TaskService service;
    private SessionCredentials credentials;
    private final LoginForm login = new LoginForm();
    //
    public LoginView(SessionCredentials sessionCredentials, TaskService dataService){
        // automatic injection
        this.credentials = sessionCredentials;
        this.service = dataService;
        //
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setForgotPasswordButtonVisible(false);
        //
        add(new H1("Vaadin UIX Client"), login);
        //
        login.addLoginListener(loginEvent -> {
            // check login
            System.out.println("Credentials: " + loginEvent.getUsername() + "/" + loginEvent.getPassword());
            credentials.setUsername(loginEvent.getUsername());
            credentials.setPassword(loginEvent.getPassword());
            if(ckeckAuthData()) {
                UI.getCurrent().navigate("");
            }else {
                LoginI18n i18n = LoginI18n.createDefault();
                LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
                i18nErrorMessage.setMessage("User sau parola gresite!");
                login.setI18n(i18n);
                login.setError(true);
            }
        });
        //
        // If Login then logout first
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        this.credentials.resetCredentials();
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    private boolean ckeckAuthData() {
        try {
            //this.service.getAllProjects();
            this.service.getDataProfile();
            System.out.println("AUTH success!");
            return true;
        }catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("AUTH failure! " +  ex.getMessage());
            return false;
        }
    }

}
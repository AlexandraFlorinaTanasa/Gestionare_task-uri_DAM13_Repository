package org.app.sprints.web.viewa;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.router.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.example.web.MainView;
import org.gestionare_taskuri.servicii.SprintService;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;

@PageTitle("sprint")
@Route(value = "sprint", layout = MainView.class)
public class FormProjectView extends VerticalLayout implements HasUrlParameter<Integer> {
    private static final long serialVersionUID = 1L;

    // Definire model date
    private SprintService service;
    private SprintPlanning sprintPlanning = null;
    //	private Binder<SprintPlanning> binder = new Binder<>(SprintPlanning.class);
    private Binder<SprintPlanning> binder = new BeanValidationBinder<>(SprintPlanning.class);

    // Definire componente view
    // Definire Form-Master
    private H1 formTitle = new H1("Form Sprint");
    private IntegerField codSprint = new IntegerField("Sprint cod:");
    private TextField nume = new TextField("Nume Sprint: ");

    // Definire componente actiuni Form-Master-Controller
    private Button cmdAdd = new Button("Add");
    private Button cmdDelete = new Button("Delete");
    private Button cmdAbandon = new Button("Abandon");
    private Button cmdSave = new Button("Save");

    @Autowired
    // Start Form
    public FormSprintView(SprintService service) {
        this.service = service;
        //
        initDataModel();
        //
        initViewLayout();
        //
        initControllerActions();
    }

    // Navigation Management
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer codSprint) {
        System.out.println("Cod sprint: " + codSprint);
        try {
            if (codSprint != null) {
                // EDIT Item
                this.sprintPlanning = service.getSprintById(codSprint);
                System.out.println("Selected sprint to edit:: " + sprintPlanning);
                if (this.sprintPlanning == null) {
                    System.out.println("ADD sprint:: " + sprintPlanning);
                    // NEW Item
                    this.addSprint();
                    this.sprintPlanning.setCodSprint(codSprint);
                    this.sprintPlanning.setNume("NEW Project " + codSprint);
                }
            }
            this.refreshForm();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // init Data Model
    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");

        try {
            this.sprintPlanning = service.getAllSprints().stream().findFirst().orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //
        binder.forField(codSprint).bind("codSprint");
        binder.forField(nume).bind("nume");

        //
        refreshForm();
    }

    // init View Model
    private void initViewLayout() {
        // Form Input components
        FormLayout formLayout = new FormLayout();
        formLayout.add(codSprint, nume);
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
        formLayout.setMaxWidth("400px");
        // Toolbar-Actions
        HorizontalLayout actionToolbar =
                new HorizontalLayout(cmdAdd, cmdDelete, cmdAbandon, cmdSave);
        actionToolbar.setPadding(false);
        // ---------------------------
        this.add(formTitle, formLayout, actionToolbar);
        //
        this.codSprint.setEnabled(false);
    }

    // init Controller components
    private void initControllerActions() {
        // Transactional Master Actions
        cmdAdd.addClickListener(e -> {
            addSprint();
            refreshForm();
        });
        cmdDelete.addClickListener(e -> {
            deleteSprint();
            // Navigate back to NavigableGridProiecteForm
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridSprintsView.class)
            );
        });
        cmdAbandon.addClickListener(e -> {
            // Navigate back to NavigableGridProiecteForm
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridSprintsView.class, this.sprintPlanning.getCodSprint())
            );
        });
        cmdSave.addClickListener(e -> {
            saveSprint();
            // refreshForm();
            // Navigate back to NavigableGridProiecteForm
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridSprintsView.class, this.sprintPlanning.getCodSprint())
            );
        });
    }

    //
    private void refreshForm() {
        System.out.println("Sprint curent: " + this.sprintPlanning);
        if (this.sprintPlanning != null) {
            binder.setBean(this.sprintPlanning);
        }
    }

    // CRUD actions
    private void addSprint() {
        this.sprintPlanning = new SprintPlanning();
        this.sprintPlanning.setCodSprint(999);
        this.sprintPlanning.setNume("Proiect Nou");
        //

    }

    private void deleteSprint() {
        System.out.println("To remove: " + this.sprintPlanning);
        try {
            service.deleteSprint(this.sprintPlanning);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveSprint() {
        try {
            service.addSprint(this.sprintPlanning);
        } catch (Exception ex) {
            System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
            //throw new RuntimeException(ex.getMessage());
            showNotificationError(ex);
        }
    }

    private void showNotificationError(Exception ex) {
        String errorMessage = "Save Error: ";
        if (ex.getCause() instanceof ConstraintViolationException)
            errorMessage += getValidationMessages((ConstraintViolationException) ex.getCause());
        else
            errorMessage += ex.getMessage();
        //
        Notification notification = new Notification(errorMessage, 5000, Position.BOTTOM_STRETCH);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.open();
        //Notification.show(errorMessage, 5000, Position.BOTTOM_STRETCH);
    }

    private String getValidationMessages(ConstraintViolationException ex) {
        System.out.println("handleValidation: ");
        String validationMessages = "";
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            System.out.println("!!! ConstraintViolation: " + cv.getMessage());
            System.out.println("		Property validated: " + cv.getPropertyPath().toString());
            //
            validationMessages += cv.getMessage() + "\n";
        }
        return validationMessages;
    }
}

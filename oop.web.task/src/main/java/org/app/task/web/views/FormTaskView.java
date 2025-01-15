
package org.app.task.web.views;
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
import org.gestionare_taskuri.servicii.TaskService;
import org.gestionare_taskuri.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@PageTitle("task")
@Route(value = "task", layout = MainView.class)
public class FormTaskView extends VerticalLayout implements HasUrlParameter<Integer> {
    private static final long serialVersionUID = 1L;

    // Definire model date
    private TaskService service;
    private Task task = null;
    //  private Binder<Task> binder = new Binder<>(Task.class);
    private Binder<Task> binder = new BeanValidationBinder<>(Task.class);

    // Definire componente view
    // Definire Form-Master
    private H1 formTitle = new H1("Form Task");
    private IntegerField cod = new IntegerField("Cod");
    private TextField nume = new TextField("Nume Task: ");
    private DatePicker dataInceput = new DatePicker("Data inceput:");
    // Definire componente actiuni Form-Master-Controller
    private Button cmdAdd = new Button("Add");
    private Button cmdDelete = new Button("Delete");
    private Button cmdAbandon = new Button("Abandon");
    private Button cmdSave = new Button("Save");

    @Autowired
    // Start Form
    public FormTaskView(TaskService service) {
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
    public void setParameter(BeforeEvent event, @OptionalParameter Integer cod) {
        System.out.println("Task cod: " + cod);
        try {
            if (cod != null) {
                // EDIT Item
                this.task = service.getTaskById(cod);
                System.out.println("Selected task to edit:: " + task);
                if (this.task == null) {
                    System.out.println("ADD task:: " + task);
                    // NEW Item
                    this.addTask();
                    this.task.setTask(cod);
                    this.task.setNume("NEW Task" + cod);
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
            this.task = service.getAllTasks().stream().findFirst().orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //
        binder.forField(cod).bind("cod");
        binder.forField(nume).bind("nume");
        binder.forField(dataInceput).withConverter(new LocalDateToDateConverter())
                .bind("dataInceput");
        //
        refreshForm();
    }

    // init View Model
    private void initViewLayout() {
        // Form Input components
        FormLayout formLayout = new FormLayout();
        formLayout.add(cod, nume, dataInceput);
        formLayout.setResponsiveSteps(new ResponsiveStep("0", 1));
        formLayout.setMaxWidth("400px");
        // Toolbar-Actions
        HorizontalLayout actionToolbar =
                new HorizontalLayout(cmdAdd, cmdDelete, cmdAbandon, cmdSave);
        actionToolbar.setPadding(false);
        // ---------------------------
        this.add(formTitle, formLayout, actionToolbar);
        //
        this.cod.setEnabled(false);
    }

    // init Controller components
    private void initControllerActions() {
        // Transactional Master Actions
        cmdAdd.addClickListener(e -> {
            addTask();
            refreshForm();
        });
        cmdDelete.addClickListener(e -> {
            deleteTask();
            // Navigate back to NavigableGridTasksForm
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridTasksView.class)
            );
        });
        cmdAbandon.addClickListener(e -> {
            // Navigate back to NavigableGridTasksForm
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridTasksView.class, this.task.getCod())
            );
        });
        cmdSave.addClickListener(e -> {
            saveTask();
            // refreshForm();
            // Navigate back to NavigableGridTasksForm
            this.getUI().ifPresent(ui -> ui.navigate(
                    NavigableGridTasksView.class, this.task.getCod())
            );
        });
    }

    //
    private void refreshForm() {
        System.out.println("Task curent: " + this.task);
        if (this.task != null) {
            binder.setBean(this.task);
        }
    }

    // CRUD actions
    private String addTask() {
        this.task = new Task();
        this.task.setTask(999);
        {

            this.task.setNume("Task Nou");
            //
            this.task.setDataInceput(LocalDate.now().plusDays(1));
        }

        private void deleteTask() {
            System.out.println("To remove: " + this.task);
            try {
                service.deleteTask(this.task);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void saveTask() {
            try {
                service.addTask(this.task);
            } catch (Exception ex) {
                System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
                //throw new RuntimeException(ex.getMessage());
                showNotificationError(ex);
            }
        }

        private void showNotificationError ;
        ConstraintViolationException ex;
        {
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

        ConstraintViolationException ex1 = ex;
        private String getValidationMessages ex){
            System.out.println("handleValidation: ");
            String validationMessages = "";
            for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
                System.out.println("!!! ConstraintViolation: " + cv.getMessage());
                System.out.println("       Property validated: " + cv.getPropertyPath().toString());
                //
                validationMessages += cv.getMessage() + "\n";
            }
            return validationMessages;
        }
    }

    public String getValidationMessages(ConstraintViolationException cause) {
        // Extrage toate încălcările de validare
        Set<ConstraintViolation<?>> violations = cause.getConstraintViolations();

        // Construiește un mesaj formatat cu toate erorile
        return violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
    }
}

public void showNotificationError(Exception ex) {
    String errorMessage;

    if (ex instanceof ConstraintViolationException) {
        // Tratarea validărilor specifice
        errorMessage = "Validation Errors: " + getValidationMessages((ConstraintViolationException) ex);
    } else {
        // Tratarea erorilor generale
        errorMessage = "An error occurred: " + ex.getMessage();
    }

    // Simulează afișarea notificării (poate fi conectată la un UI real, cum ar fi Swing, JavaFX sau un framework web)
    System.out.println("ERROR: " + errorMessage);
}


}













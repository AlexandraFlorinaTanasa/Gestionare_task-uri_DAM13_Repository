package org.app.task.web.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.example.web.MainView;
import org.gestionare_taskuri.servicii.TaskService;
import org.gestionare_taskuri.task.Task;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import static java.util.Collections.*;

@PageTitle("tasks")
@Route(value = "tasks", layout = MainView.class)
public class NavigableGridTasksView extends VerticalLayout implements HasUrlParameter<Integer> {
    private static final long serialVersionUID = 1L;

    // Definire model date
    private TaskService service;
    private List<Task> tasks = new ArrayList<>();
    private Task task = null;

    // Definire componente view
    private H1 titluForm = new H1("Tasks List");
    // Definire componente suport navigare
    private VerticalLayout gridLayoutToolbar;
    private TextField filterText = new TextField();
    private Button cmdEditTask = new Button("Edit task...");
    private Button cmdAddTask = new Button("Add task...");
    private Button cmdDeleteTask = new Button("Delete task");
    private Grid<Task> grid = new Grid<>(Task.class);

    // Start Form
    @Autowired
    public NavigableGridTasksView(TaskService service) {
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
        if (cod != null) {
            try {
                this.task = service.getTaskById(cod);
                System.out.println("Back task: " + task);
                if (this.task == null) {
                    // DELETED Item
                    if (!this.tasks.isEmpty())
                        this.task = this.tasks.get(0);
                }
                // else: EDITED or NEW Item
            } catch (Exception e) {
                //throw new RuntimeException(e);
                System.out.println("TASK NOT FOUND");
            }

        }
        this.refreshForm();

    }

    // init Data Model
    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");

        Collection<Task> lst = null;
        try {
            lst = service.getAllTasks();
            this.tasks.addAll(lst);

            if (lst != null && !lst.isEmpty()) {
                sort(this.tasks, (t1, t2) -> t1.getTask().compareTo(t2.getTask()));
                this.task = this.tasks.get(0);
                System.out.println("DEBUG: task init >>> " + task.getTask());
                this.task.getLansare()
                        .sort((l1, l2) -> {
                            return l1.getLansare().compareTo(l2.getLansare());
                        });
            }
            //
            grid.setItems(this.tasks);
            grid.asSingleSelect().setValue(this.task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // init View Model
    private void initViewLayout() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Layout navigare -------------------------------------//
        // Toolbar navigare
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
                cmdEditTask, cmdAddTask, cmdDeleteTask);
        // Grid navigare
        grid.setColumns("projectNo", "name");
        grid
                .addColumn(p -> p.getDataInceput() == null ? null : dateFormat.format(p.getDataInceput()))
                .setHeader("Data Inceput")
                .setKey("DataInceput");

        grid.addComponentColumn(item -> createGridActionsButtons(item)).setHeader("Actions");
        // Init Layout navigare
        gridLayoutToolbar = new VerticalLayout(gridToolbar, grid);
        // ---------------------------
        this.add(titluForm, gridLayoutToolbar);
        //
    }

    // init Controller components
    private void initControllerActions() {
        // Navigation Actions
        filterText.addValueChangeListener(e -> updateList());
        cmdEditTask.addClickListener(e -> {
            editTask();
        });
        cmdAddTask.addClickListener(e -> {
            addTask();
        });
        cmdDeleteTask.addClickListener(e -> {
            deleteTask();
            refreshForm();
        });
    }

    //
    private Component createGridActionsButtons(Task item) {
        //
        Button cmdEditItem = new Button("Edit");
        cmdEditItem.addClickListener(e -> {
            grid.asSingleSelect().setValue(item);
            editTask();
        });
        Button cmdDeleteItem = new Button("Delete");
        cmdDeleteItem.addClickListener(e -> {
            System.out.println("Delete item: " + item);
            grid.asSingleSelect().setValue(item);
            deleteTask();
            refreshForm();
        });
        //
        return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
    }

    //
    private void editTask() {
        this.task = this.grid.asSingleSelect().getValue();
        System.out.println("Selected task:: " + task);
        if (this.task != null) {
            this.getUI().ifPresent(ui -> ui.navigate(
                    FormTaskView.class, this.task.getTask())
            );
        }
    }

    //
    private void updateList() {
        try {
            List<Task> lstTaskuriFiltrate = this.tasks;

            if (filterText.getValue() != null) {
                lstTaskuriFiltrate = this.tasks.stream()
                        .filter(p -> p.getNume().contains(filterText.getValue()))
                        .toList();

                grid.setItems(lstTaskuriFiltrate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    private void refreshForm() {
        System.out.println("Task curent: " + this.task);
        if (this.task != null) {
            grid.setItems(this.tasks);
            grid.select(this.task);
        }
    }

    // CRUD actions
    private void addTask() {
        try {
            Integer newTaskCod = service.createWorkflowTask();
            this.getUI().ifPresent(ui -> ui.navigate(FormTaskView.class, newTaskCod));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteTask() {
        this.task = this.grid.asSingleSelect().getValue();
        System.out.println("To remove: " + this.task);

        try {
            this.tasks.remove(unmodifiableList(this.tasks));
            this.service.deleteTask(this.task);
            if (!this.tasks.isEmpty())
                this.task = this.tasks.get(0);
            else
                this.task = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
package org.app.sprints.web.viewa;
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
import org.gestionare_taskuri.servicii.SprintService;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@PageTitle("sprintsPlanning")
@Route(value = "sprintsPlanning", layout = MainView.class)
public class NavigableGridSprintsView extends VerticalLayout implements HasUrlParameter<Integer> {
    private static final long serialVersionUID = 1L;

    // Definire model date
    private SprintService service;
    private List<SprintPlanning> sprintsPlanning = new ArrayList<>();
    private SprintPlanning sprintPlanning = null;

    // Definire componente view
    private H1 titluForm = new H1("Lista Sprints Planning");
    // Definire componente suport navigare
    private VerticalLayout gridLayoutToolbar;
    private TextField filterText = new TextField();
    private Button cmdEditSprint= new Button("Edit sprint...");
    private Button cmdAddSprint = new Button("Add sprint...");
    private Button cmdDeleteSprint = new Button("Delete sprint");
    private Grid<SprintPlanning> grid = new Grid<>(SprintPlanning.class);

    // Start Form
    @Autowired
    public NavigableGridSprintsView(SprintService service) {
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
        if (codSprint!= null) {
            try {
                this.sprintPlanning = service.getSprintById(codSprint);
                System.out.println("Back sprint: " + sprintPlanning);
                if (this.sprintPlanning == null) {
                    // DELETED Item
                    if (!this.sprintsPlanning.isEmpty())
                        this.sprintPlanning = this.sprintsPlanning.get(0);
                }
                // else: EDITED or NEW Item
            } catch (Exception e) {
                //throw new RuntimeException(e);
                System.out.println("SPRINT NOT FOUND");
            }

        }
        this.refreshForm();

    }

    // init Data Model
    private void initDataModel() {
        System.out.println("DEBUG START FORM >>>  ");

        Collection<SprintPlanning> lst = null;
        try {
            lst = service.getAllSprints();
            this.sprintsPlanning.addAll(lst);

            if (lst != null && !lst.isEmpty()) {
                Collections.sort(this.sprintsPlanning, (s1, s2) -> s1.getSprintPlannings().compareTo(s2.getSprintPlannings()));
                this.sprintPlanning = this.sprintsPlanning.get(0);
                System.out.println("DEBUG: proiect init >>> " + sprintPlanning.getSprintPlanningNo());


            }
            //
            grid.setItems(this.sprintsPlanning);
            grid.asSingleSelect().setValue(this.sprintPlanning);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // init View Model
    private void initViewLayout() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Layout navigare -------------------------------------//
        // Toolbar navigare
        filterText.setPlaceholder("Filter by nume...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
                cmdEditSprint, cmdAddSprint, cmdDeleteSprint);
        // Grid navigare
        grid.setColumns("sprintPlanningNo", "nume");
        grid
               // .addColumn(p -> p.getStartDate() == null ? null : dateFormat.format(p.getStartDate()))
               /// .setHeader("Start Date")
              //  .setKey("startDate");

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
        cmdEditSprint.addClickListener(e -> {
            editSprint();
        });
        cmdAddSprint.addClickListener(e -> {
            addSprint();
        });
        cmdDeleteSprint.addClickListener(e -> {
            deleteSprint();
            refreshForm();
        });
    }

    //
    private Component createGridActionsButtons(SprintPlanning item) {
        //
        Button cmdEditItem = new Button("Edit");
        cmdEditItem.addClickListener(e -> {
            grid.asSingleSelect().setValue(item);
            editSprint();
        });
        Button cmdDeleteItem = new Button("Delete");
        cmdDeleteItem.addClickListener(e -> {
            System.out.println("Delete item: " + item);
            grid.asSingleSelect().setValue(item);
            deleteSprint();
            refreshForm();
        });
        //
        return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
    }

    //
    private void editSprint() {
        this.sprintPlanning = this.grid.asSingleSelect().getValue();
        System.out.println("Selected sprint:: " + sprintPlanning);
        if (this.sprintPlanning != null) {
            this.getUI().ifPresent(ui -> ui.navigate(
                    FormSprintView.class, this.sprintPlanning.getCodSprint())
            );
        }
    }

    //
    private void updateList() {
        try {
            List<SprintPlanning> lstSprinturiFiltrate = this.sprintsPlanning;

            if (filterText.getValue() != null) {
                lstSprinturiFiltrate = this.sprintsPlanning.stream()
                        .filter(p -> p.getNumeSprint().contains(filterText.getValue()))
                        .toList();

                grid.setItems(lstSprinturiFiltrate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    private void refreshForm() {
        System.out.println("Sprint curent: " + this.sprintsPlanning);
        if (this.sprintPlanning != null) {
            grid.setItems(this.sprintsPlanning);
            grid.select(this.sprintPlanning);
        }
    }

    // CRUD actions
    private void addSprint() {
        try {
            Integer newCcodSprint = service.createWorkflowProject();
            this.getUI().ifPresent(ui -> ui.navigate(FormSprintView.class, newCodSprint));
        }catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteSprint() {
        this.sprintPlanning = this.grid.asSingleSelect().getValue();
        System.out.println("To remove: " + this.sprintPlanning);

        try {
            this.sprintsPlanning.remove(this.sprintPlanning);
            this.service.deleteSprint((SprintPlanning) this.sprintsPlanning);
            if (!this.sprintsPlanning.isEmpty())
                this.sprintPlanning = this.sprintsPlanning.get(0);
            else
                this.sprintPlanning = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

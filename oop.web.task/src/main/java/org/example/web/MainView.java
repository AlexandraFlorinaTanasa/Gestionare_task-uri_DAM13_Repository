package org.example.web;



import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.app.sprints.web.viewa.NavigableGridSprintsView;
import org.app.task.web.views.FormTaskView;
import org.app.task.web.views.NavigableGridTasksView;


@Route
public class MainView extends AppLayout
        implements BeforeEnterObserver // activate security basic settings
{

    private SessionCredentials credentials;
    public MainView(SessionCredentials sessionCredentials){
        // automatic injection
        this.credentials = sessionCredentials;
        //
        HorizontalLayout header = createHeader();
        addToNavbar(header);
        //
        Tabs tabs = createTabs();
        //
        addToDrawer(tabs);

    }

    private HorizontalLayout createHeader() {
        H1 title = new H1("Task APP");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        //
        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                title
        );
        //
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        //header.addClassNames("py-0", "px-m");
        //
        return header;
    }

    private Tabs createTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.DASHBOARD, "Main", MainView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Lista Taskuri", NavigableGridTasksView.class));
        tabs.add(createTab(VaadinIcon.PACKAGE, "Form Editare Taskuri", FormTaskView.class));
        tabs.add(createTab(VaadinIcon.RECORDS, "Lista Sprinturi", NavigableGridSprintsView.class));
        tabs.add(createTab(VaadinIcon.PACKAGE, "Form Editare Sprinturi", FormSprintView.class));

        tabs.add(createTab(VaadinIcon.EXIT, "Log out", LoginView.class));
        //
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        //
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName,
                          Class<? extends Component> viewClass) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        //
        link.setRoute(viewClass);
        link.setTabIndex(-1);

        return new Tab(link);
    }
    // Check security
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        System.out.println("Session credentials: " + this.credentials);
        if (!this.credentials.isAuthenticated()) {
            System.out.println("Navigate to login...");
            event.forwardTo("login");
        }
    }
}

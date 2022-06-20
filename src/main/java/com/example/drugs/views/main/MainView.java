package com.example.drugs.views.main;

import com.example.drugs.code.exception.DrugNotFoundException;
import com.example.drugs.code.model.Drug;
import com.example.drugs.code.service.DrugService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;

import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.ArrayList;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {
    Grid<Drug> grid = new Grid<>(Drug.class, false);
    private final Button submitGetById = new Button("OK");
    private final Button submitPost = new Button("OK");
    private final Button submitDeleteById = new Button("OK");
    private final Button submitUpdateById = new Button("OK");
    private final TextField id;
    private final TextField id1;
    private final TextField id2;
    private final TextField id3;
    private final TextField name;
    private final TextField amount;
    private final TextField price;
    private final TextField purpose;
    private final TextField recipe;
    private final TextField type;

    public MainView(DrugService drugService) {

        grid.addColumn(Drug::getId).setHeader("ID");
        grid.addColumn(Drug::getName).setHeader("Название");
        grid.addColumn(Drug::getAmount).setHeader("Количество");
        grid.addColumn(Drug::getPrice).setHeader("Цена");
        grid.addColumn(Drug::getPurpose).setHeader("Назначение");
        grid.addColumn(Drug::getRecipeRequired).setHeader("Рецепт");
        grid.addColumn(Drug::getType).setHeader("Тип");

        id = new TextField("Введите id: ");
        id.setVisible(false);
        id1 = new TextField("Введите id: ");
        id1.setVisible(false);
        id2 = new TextField("Введите id: ");
        id2.setVisible(false);
        id3 = new TextField("Введите id: ");
        id3.setVisible(false);
        name = new TextField("Введите название: ");
        name.setVisible(false);
        amount = new TextField("Введите количество: ");
        amount.setVisible(false);
        price = new TextField("Введите цену: ");
        price.setVisible(false);
        purpose = new TextField("Введите назначение: ");
        purpose.setVisible(false);
        recipe = new TextField("Введите рецепт: ");
        recipe.setVisible(false);
        type = new TextField("Введите тип: ");
        type.setVisible(false);
        submitPost.setVisible(false);
        submitGetById.setVisible(false);
        submitDeleteById.setVisible(false);
        submitUpdateById.setVisible(false);

        List<Drug> drugList = new ArrayList<Drug>();
        grid.setItems(drugList);

        Button post = new Button("Внести информацию о новом медикаменте");
        post.addClickListener(e -> {
            method(true);

            submitGetById.setVisible(false);
            submitDeleteById.setVisible(false);
            submitUpdateById.setVisible(false);
            submitPost.setVisible(true);
            createNotification("Введите данные нового медикамента",3000, NotificationVariant.LUMO_CONTRAST);
        });

        submitPost.addClickListener(buttonClickEvent -> {
            id.setValue("99999");
            Drug drug = new Drug(Integer.parseInt(id.getValue()), name.getValue(), amount.getValue(),
                    Boolean.parseBoolean(recipe.getValue()),
                    purpose.getValue(), price.getValue(), type.getValue());

            drugService.insert(drug);

            grid.setItems(drugService.selectAll());

            id.setVisible(false);
            method(false);
            submitPost.setVisible(false);
        });

        Button getAll = new Button("Вывести все медикаменты");
        getAll.addClickListener(e -> {

            grid.setItems(drugService.selectAll());
            createNotification("Успешно", 3000, NotificationVariant.LUMO_SUCCESS);
        });

        Button getById = new Button("Вывести медикамент по id");
        getById.addClickListener(e -> {
            id.setVisible(false);
            id2.setVisible(false);
            id3.setVisible(false);
            id1.setVisible(true);

            submitUpdateById.setVisible(false);
            submitDeleteById.setVisible(false);
            submitPost.setVisible(false);

            submitGetById.setVisible(true);
            method(false);
            createNotification("Введите иде",3000, NotificationVariant.LUMO_CONTRAST);
        });


        submitGetById.addClickListener(buttonClickEvent -> {
            try {
            grid.setItems(drugService.select(Integer.parseInt(id1.getValue())));
            } catch (DrugNotFoundException drugNotFoundException) {
                createNotification("Такого id не существует",3000, NotificationVariant.LUMO_ERROR);
            }
            submitGetById.setVisible(false);
            id1.setVisible(false);

        });

        Button update = new Button("Редактировать медикамент по id");
        update.addClickListener(buttonClickEvent -> {
            id.setVisible(false);
            id2.setVisible(false);
            id1.setVisible(false);

            id3.setVisible(true);
            method(true);

            submitGetById.setVisible(false);
            submitDeleteById.setVisible(false);
            submitPost.setVisible(false);

            submitUpdateById.setVisible(true);
        });

        submitUpdateById.addClickListener(buttonClickEvent -> {
            id.setValue("99999");
            Drug drug = new Drug(Integer.parseInt(id.getValue()), name.getValue(), amount.getValue(),
                    Boolean.parseBoolean(recipe.getValue()),
                    purpose.getValue(), price.getValue(), type.getValue());
            drugService.update(drug, Integer.parseInt(id3.getValue()));

            grid.setItems(drugService.select(Integer.parseInt(id3.getValue())));

            id.setVisible(false);
            id3.setVisible(false);
            method(false);
            submitUpdateById.setVisible(false);
        });

        Button deleteById = new Button("Удалить медикамент по id");

        deleteById.addClickListener(e -> {

            id.setVisible(false);
            id1.setVisible(false);
            id3.setVisible(false);

            id2.setVisible(true);

            method(false);

            submitGetById.setVisible(false);
            submitPost.setVisible(false);
            submitUpdateById.setVisible(false);

            submitDeleteById.setVisible(true);
        });


        submitDeleteById.addClickListener(buttonClickEvent -> {
            try {
                drugService.delete(Integer.parseInt(id2.getValue()));
                grid.setItems(drugService.selectAll());
                createNotification("Успешно удалил медикамент",3000, NotificationVariant.LUMO_SUCCESS);
            } catch (DrugNotFoundException drugNotFoundException) {
                createNotification("Такого id не существует",3000, NotificationVariant.LUMO_ERROR);
        }
            submitDeleteById.setVisible(false);
            id2.setVisible(false);
        });

        Button deleteAll = new Button("Очистить таблицу");
        deleteAll.addClickListener(buttonClickEvent -> {
            drugService.deleteAll();
            grid.setItems(drugService.selectAll());
            createNotification("Появился как-то в зоне черный сталкер...", 500, NotificationVariant.LUMO_SUCCESS);
        });

        setMargin(true);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(getAll, getById, post, update, deleteById, deleteAll);
        grid.setItems(drugService.selectAll());
        add(buttons, grid);
        add(id, id1, id2, id3, name, amount, price, purpose, recipe, type, submitPost, submitGetById, submitUpdateById, submitDeleteById);

    }

    private void method(boolean flag){
        name.setVisible(flag);
        amount.setVisible(flag);
        price.setVisible(flag);
        purpose.setVisible(flag);
        recipe.setVisible(flag);
        type.setVisible(flag);
    }

    private void createNotification(String message, int duration, NotificationVariant variant) {
        Notification notification = new Notification();
        notification.addThemeVariants(variant);
        notification.setDuration(duration);

        Div text = new Div(new Text(message));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> notification.close());

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.open();

    }

}

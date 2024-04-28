package br.com.wti.inventoryweb.managedbean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.SessionScoped;
import lombok.Getter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 28/04/2024
 */
@Component
@SessionScoped
public class MenuMB extends BaseMB {

    @Getter
    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        DefaultMenuItem home = DefaultMenuItem.builder()
                .value("Home")
                .url("#")
                .icon("pi pi-home")
                .build();

        model.getElements().add(home);

        DefaultSubMenu cadastro = DefaultSubMenu.builder()
                .label("Cadastro")
                .icon("pi pi-fw pi-pencil")
                .build();

        DefaultMenuItem computador = DefaultMenuItem.builder()
                .value("Computador")
                .url("computadores.xhtml")
                .icon("pi pi-fw pi-bookmark")
                .build();
        cadastro.getElements().add(computador);

        DefaultMenuItem monitor = DefaultMenuItem.builder()
                .value("Monitor")
                .url("monitores.xhtml")
                .icon("pi pi-fw pi-desktop")
                .build();
        cadastro.getElements().add(monitor);

        model.getElements().add(cadastro);
    }
}

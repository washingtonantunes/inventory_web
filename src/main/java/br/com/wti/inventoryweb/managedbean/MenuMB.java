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

        DefaultSubMenu operacao = DefaultSubMenu.builder()
                .label("Operacao")
                .icon("pi pi-external-link")
                .build();

        DefaultMenuItem computador = DefaultMenuItem.builder()
                .value("Computador")
                .url("computadores.xhtml")
                .icon("pi pi-bookmark")
                .build();
        operacao.getElements().add(computador);

        DefaultMenuItem monitor = DefaultMenuItem.builder()
                .value("Monitor")
                .url("monitores.xhtml")
                .icon("pi pi-desktop")
                .build();
        operacao.getElements().add(monitor);

        model.getElements().add(operacao);

        DefaultSubMenu financeiro = DefaultSubMenu.builder()
                .label("Financeiro")
                .icon("pi pi-calculator")
                .build();

        DefaultMenuItem notaFiscal = DefaultMenuItem.builder()
                .value("Nota Fiscal")
                .url("notasFiscais.xhtml")
                .icon("pi pi-folder-open")
                .build();
        financeiro.getElements().add(notaFiscal);

        model.getElements().add(financeiro);

        DefaultSubMenu configuracao = DefaultSubMenu.builder()
                .label("Configuração")
                .icon("pi pi-cog")
                .build();

        model.getElements().add(configuracao);
    }
}

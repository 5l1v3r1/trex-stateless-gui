package com.cisco.trex.stl.gui.controllers.dashboard;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.cisco.trex.stl.gui.controllers.dashboard.charts.ChartsController;
import com.cisco.trex.stl.gui.controllers.dashboard.latency.LatencyController;
import com.cisco.trex.stl.gui.controllers.dashboard.ports.PortsController;
import com.cisco.trex.stl.gui.controllers.dashboard.streams.StreamsController;
import com.cisco.trex.stl.gui.storages.StatsStorage;

import com.exalttech.trex.ui.dialog.DialogView;
import com.exalttech.trex.ui.views.statistics.StatsLoader;


public class DashboardController extends DialogView implements Initializable {
    private static final String PORTS_TAB_LABEL = "Ports";
    private static final String STREAMS_TAB_LABEL = "Streams";
    private static final String LATENCY_TAB_LABEL = "Latency";
    private static final String CHARTS_TAB_LABEL = "Charts";

    @FXML
    private TabPane tabPane;
    @FXML
    private PortsController ports;
    @FXML
    private StreamsController streams;
    @FXML
    private LatencyController latency;
    @FXML
    private ChartsController charts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Nothing to do
    }

    @Override
    public void onEnterKeyPressed(final Stage stage) {
        // Nothing to do
    }

    @FXML
    public void handleTabChanged(final Event event) {
        switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
            case PORTS_TAB_LABEL:
                if (ports != null) {
                    ports.setActive(true);
                }
                if (streams != null) {
                    streams.setActive(false);
                }
                if (latency != null) {
                    latency.setActive(false);
                }
                if (charts != null) {
                    charts.setActive(false);
                }
                break;
            case STREAMS_TAB_LABEL:
                ports.setActive(false);
                streams.setActive(true);
                latency.setActive(false);
                charts.setActive(false);
                break;
            case LATENCY_TAB_LABEL:
                ports.setActive(false);
                streams.setActive(false);
                latency.setActive(true);
                charts.setActive(false);
                break;
            case CHARTS_TAB_LABEL:
                ports.setActive(false);
                streams.setActive(false);
                latency.setActive(false);
                charts.setActive(true);
                break;
        }
    }

    @FXML
    public void handleClearCacheButtonClicked(final ActionEvent event) {
        StatsLoader.getInstance().reset();
        StatsStorage.getInstance().getPGIDStatsStorage().reset();
    }
}

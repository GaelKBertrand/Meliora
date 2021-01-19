package com.core.desktop;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static com.core.desktop.Gbc.*;

public class MainFrame extends JFrame {
    private NewScanPanel newScanPanel;
    private HistoryPanel historyPanel;
    private SettingsPanel settingsPanel;
    private AboutPanel aboutPanel;

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Analyzer");
        setVisible(true);

        setLayout(new GridBagLayout());

        newScanPanel = new NewScanPanel();
        add(newScanPanel, Gbc.of(0, 1, 1f, 1f));
        historyPanel = new HistoryPanel();
        settingsPanel = new SettingsPanel();
        aboutPanel = new AboutPanel();

        setUpPanelButtons();
    }

    private void setUpPanelButtons() {
        JButton newScanButton = new PanelButton("+", newScanPanel, List.of(historyPanel, settingsPanel, aboutPanel));
        JButton historyButton = new PanelButton("H", historyPanel, List.of(newScanPanel, settingsPanel, aboutPanel));
        JButton settingsButton = new PanelButton("Set", settingsPanel, List.of(newScanPanel, historyPanel, aboutPanel));
        JButton aboutButton = new PanelButton("Info", aboutPanel, List.of(newScanPanel, historyPanel, settingsPanel));

        GbcPanel panel = new GbcPanel();
        panel.add(newScanButton, Gbc.of(0, 0, 1, 1, Gbc.CENTER, 1f));
        panel.add(historyButton, Gbc.of(1, 0, 1, 1, Gbc.CENTER, 1f));
        panel.add(settingsButton, Gbc.of(2, 0, 1, 1, Gbc.CENTER, 1f));
        panel.add(aboutButton, Gbc.of(3, 0, 1, 1, Gbc.CENTER, 1f));

        add(panel, Gbc.of(0, 0, 1, 0.1, Gbc.NORTH, 10f));


    }

    private class NewScanPanel extends GbcPanel {
        public NewScanPanel() {
            JButton newScanButton = new JButton("New Scan...");
            add(newScanButton, Gbc.of(0, 0, 1, 1, BOTH, CENTER));

            newScanButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter(
                            "JPG image", "jpg"));
                    int val = fileChooser.showOpenDialog(null);
                    if (val == JFileChooser.APPROVE_OPTION) {
                        try {
                            BufferedImage image = ImageIO.read(fileChooser.getSelectedFile());
                            //TODO kernel
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                }
            });
        }
    }

    private class SettingsPanel extends GbcPanel {
        public SettingsPanel() {
            JButton newScanButton = new JButton("Settings...");
            add(newScanButton, Gbc.of(0, 0, 1, 1, BOTH, CENTER));
        }
    }

    private class HistoryPanel extends GbcPanel {
        public HistoryPanel() {
            DefaultTableModel model = new DefaultTableModel(0, 5) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.addRow(new Object[]{0, "Test Person", 2024, 12, "No"});
            JTable historyTable = new JTable(model);
            historyTable.getColumnModel().getColumn(0).setHeaderValue("#");
            historyTable.getColumnModel().getColumn(0).setMinWidth(50);
            historyTable.getColumnModel().getColumn(1).setHeaderValue("Name");
            historyTable.getColumnModel().getColumn(1).setMinWidth(100);
            historyTable.getColumnModel().getColumn(2).setHeaderValue("Analysis date");
            historyTable.getColumnModel().getColumn(2).setMinWidth(100);
            historyTable.getColumnModel().getColumn(3).setHeaderValue("Result");
            historyTable.getColumnModel().getColumn(3).setMinWidth(100);
            historyTable.getColumnModel().getColumn(4).setHeaderValue("More information");
            historyTable.getColumnModel().getColumn(4).setMinWidth(100);
            add(new JScrollPane(historyTable), Gbc.of(0, 0, 1, 1,
                    Gbc.CENTER, Gbc.BOTH, 100f));
        }
    }

    private class AboutPanel extends GbcPanel {
        public AboutPanel() {
            JButton newScanButton = new JButton("About...");
            add(newScanButton, Gbc.of(0, 0, 1, 1, BOTH, CENTER));
        }
    }

    private class GbcPanel extends JPanel {
        public GbcPanel() {
            setLayout(new GridBagLayout());
        }
    }

    private class PanelButton extends JButton {
        public PanelButton(String text, JPanel panelToOpen, List<JPanel> panelsToClose) {
            super(text);
            addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelToOpen.setVisible(true);
                    if (panelToOpen instanceof HistoryPanel) {
                        MainFrame.this.add(panelToOpen,
                                Gbc.of(0, 1, 1f, 1f, Gbc.HORIZONTAL, Gbc.CENTER));
                    } else {
                        MainFrame.this.add(panelToOpen,
                                Gbc.of(0, 1, 1f, 1f));
                    }
                    for (JPanel jPanel : panelsToClose) {
                        jPanel.setVisible(false);
                    }
                    MainFrame.this.repaint();
                    MainFrame.this.revalidate();
                }
            });
        }
    }
}

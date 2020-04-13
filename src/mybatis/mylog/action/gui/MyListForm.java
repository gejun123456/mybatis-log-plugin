package mybatis.mylog.action.gui;

import mybatis.mylog.action.MybatisLogProjectService;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.VerticalFlowLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author bruce ge
 */
public class MyListForm {
    private final Project myProject;
    private JPanel panel1;
    private JPanel thePanel;
    private JPanel toolbarPanel;


    public MyListForm(Project project) {
        myProject = project;
        $$$setupUI$$$();
    }


    public JPanel getThePanel() {
        thePanel.add(new MySqlForm(myProject, "Example", "select * from demo_user;").getThePanel());
        MybatisLogProjectService.getInstance(myProject).setTheSqlPanel(thePanel);
        return panel1;
    }


    private void createUIComponents() {
        thePanel = new JPanel();
        thePanel.setLayout(new VerticalFlowLayout());
        JPanel toolbarPanel = createToolbarPanel(thePanel);
        this.toolbarPanel = toolbarPanel;
    }

    @NotNull
    public static JPanel createToolbarPanel(JPanel thePanel) {
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BorderLayout());
        DefaultActionGroup actionResultGroup = new DefaultActionGroup("MybatisCodeHelper.MybatisLogGroup", true);
        actionResultGroup.add(new RemoveAllAction(thePanel));
        ActionToolbar actionToolBar = ActionManager.getInstance().createActionToolbar("ListGroup", actionResultGroup, true);
        actionToolBar.setLayoutPolicy(ActionToolbar.AUTO_LAYOUT_POLICY);
        JComponent actionToolBarComponent = actionToolBar.getComponent();
        actionToolBarComponent.setBorder(null);
        actionToolBarComponent.setOpaque(false);
        toolbarPanel.add(actionToolBarComponent, BorderLayout.CENTER);
        return toolbarPanel;
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, BorderLayout.CENTER);
        scrollPane1.setViewportView(thePanel);
        panel1.add(toolbarPanel, BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


}

package mybatis.mylog.action.gui;

import mybatis.mylog.MyBatisLogFilter;
import mybatis.mylog.action.MybatisLogProjectService;
import mybatis.mylog.util.ConfigUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.JBDimension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author ob
 * @email huanglingbin@chainfly.com
 * @date 2019/6/20
 */
public class SqlText extends DialogWrapper {

    private JPanel panel1;
    private JButton buttonOK;
    private JTextArea originalTextArea;
    private JButton buttonClear;

    public SqlText(Project project) {
        super(project, true, IdeModalityType.MODELESS);
        this.setTitle("Restore Sql from Text"); //设置标题
        this.setResizable(true);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK(project);
            }
        });
        originalTextArea.setMinimumSize(new JBDimension(500, 500));
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClear();
            }
        });
        setOKActionEnabled(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel1;
    }

    private void onOK(Project project) {
        if (originalTextArea == null || StringUtils.isBlank(originalTextArea.getText())) {
            Messages.showErrorDialog(project, "Text area is empty", "Extract Sql Error");
            return;
        }
        String originalText = originalTextArea.getText();
        final String PREPARING = ConfigUtil.getPreparing(project);
        final String PARAMETERS = ConfigUtil.getParameters(project);
        if (originalText.contains(PREPARING) && originalText.contains(PARAMETERS)) {
            List<String> results = Lists.newArrayList();
            String[] sqlArr = originalText.split("\n");
            if (sqlArr != null && sqlArr.length >= 2) {
                String resultSql = "";
                MyBatisLogFilter myBatisLogFilter = new MyBatisLogFilter(project);
                myBatisLogFilter.reset();
                for (int i = 0; i < sqlArr.length; ++i) {
                    String currentLine = sqlArr[i];
                    myBatisLogFilter.doHandle(currentLine + "\n", sqlArr.length, results);
                }
                myBatisLogFilter.reset();
                if (results.size() == 0) {
                    Messages.showErrorDialog(project, "Cant extract from sql", "Extract Sql Error");
                } else {
                    MyBatisLogFilter.writeAndNavigateToSqlFile(project, Joiner.on("\n\n").join(results), MybatisLogProjectService.getInstance(project));
                }
            } else {
                Messages.showErrorDialog(project, "Cant extract from sql", "Extract Sql Error");
            }
        } else {
            Messages.showErrorDialog(project, "Cant extract from sql", "Extract Sql Error");
        }
    }


    private void onClear() {
        this.originalTextArea.setText("");
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        originalTextArea = new JTextArea();
        originalTextArea.setColumns(40);
        originalTextArea.setEnabled(true);
        originalTextArea.setRows(20);
        scrollPane1.setViewportView(originalTextArea);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("Restore Sql");
        panel3.add(buttonOK, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel3.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonClear = new JButton();
        buttonClear.setText("Clear");
        panel3.add(buttonClear, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

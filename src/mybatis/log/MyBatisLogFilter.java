package mybatis.log;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import mybatis.log.action.MybatisLogProjectService;
import mybatis.log.action.gui.MySqlForm;
import mybatis.log.util.ConfigUtil;
import mybatis.log.util.PrintUtil;
import mybatis.log.util.RestoreSqlUtil;
import mybatis.log.util.StringConst;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 语句过滤器
 * @author ob
 */
public class MyBatisLogFilter implements Filter {
    private final Project project;
    private static String preparingLine = "";
    private static String parametersLine = "";
    private static boolean isEnd = false;

    public MyBatisLogFilter(Project project) {
        this.project = project;
    }

    @Nullable
    @Override
    public Result applyFilter(final String currentLine, int endPoint) {
        if(this.project == null) return null;
        if(ConfigUtil.getRunning(project)) {
            //过滤不显示的语句
            String[] filters = PropertiesComponent.getInstance(project).getValues(StringConst.FILTER_KEY);
            if (filters != null && filters.length > 0 && StringUtils.isNotBlank(currentLine)) {
                for (String filter : filters) {
                    if(StringUtils.isNotBlank(filter) && currentLine.toLowerCase().contains(filter.trim().toLowerCase())) {
                        return null;
                    }
                }
            }
            if(currentLine.contains(ConfigUtil.getPreparing(project))) {
                preparingLine = currentLine;
                return null;
            }
            if(StringUtils.isEmpty(preparingLine)) {
                return null;
            }
            parametersLine = currentLine.contains(ConfigUtil.getParameters(project)) ? currentLine : parametersLine + currentLine;
            if(!parametersLine.endsWith("Parameters: \n") && !parametersLine.endsWith("null\n") && !parametersLine.endsWith(")\n")) {
                return null;
            } else {
                isEnd = true;
            }
            if(StringUtils.isNotEmpty(preparingLine) && StringUtils.isNotEmpty(parametersLine) && isEnd) {
                int indexNum = ConfigUtil.getIndexNum(project);
                String preStr = "--  " + indexNum + "  " + parametersLine.split(ConfigUtil.getParameters(project))[0].trim();//序号前缀字符串
                ConfigUtil.setIndexNum(project, ++indexNum);
                String restoreSql = RestoreSqlUtil.restoreSql(project, preparingLine, parametersLine);
                PrintUtil.println(project, preStr, ConsoleViewContentType.USER_INPUT);
                if(ConfigUtil.getSqlFormat(project)) {
                    restoreSql = PrintUtil.format(restoreSql);
                }
                PrintUtil.println(project, restoreSql);
                PrintUtil.println(project, StringConst.SPLIT_LINE, ConsoleViewContentType.USER_INPUT);
                JComponent mybatisLogToolWindow =
                        ToolWindowManager.getInstance(project).getToolWindow("MybatisLogToolWindow").getContentManager().getComponent();
                JPanel theSqlPanel = MybatisLogProjectService.getInstance(project).getTheSqlPanel();
//                if (mybatisLogToolWindow instanceof JPanel) {
                    String finalRestoreSql = restoreSql;
                    ApplicationManager.getApplication().invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            MySqlForm mySqlForm = new MySqlForm(project, finalRestoreSql);
                            theSqlPanel.add(mySqlForm.getThePanel());
                            theSqlPanel.repaint();
                        }
                    });
//                }
                preparingLine = "";
                parametersLine = "";
                isEnd = false;
            }
        }
        return null;
    }
}

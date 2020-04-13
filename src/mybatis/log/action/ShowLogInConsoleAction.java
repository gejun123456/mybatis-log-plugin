package mybatis.log.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import mybatis.log.action.gui.SqlText;
import mybatis.log.util.ConfigUtil;
import mybatis.log.util.StringConst;


/**
 * 插件入口
 *
 * @author ob
 */
public class ShowLogInConsoleAction extends DumbAwareAction {

    public ShowLogInConsoleAction(Project project) {
        super();
        ConfigUtil.active = true;
        ConfigUtil.init(project);
        ConfigUtil.sqlTextDialog = new SqlText(project);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
        if (project == null) return;
    }

    public void showLogInConsole(final Project project) {
        if (project == null) return;
        ConfigUtil.setRunning(project, true);
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(StringConst.TOOL_WINDOS);
        if (toolWindow != null) {
            toolWindow.activate(null);
        }
    }

}

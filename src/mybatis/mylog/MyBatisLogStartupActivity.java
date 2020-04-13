package mybatis.mylog;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import mybatis.mylog.action.ShowLogInConsoleAction;
import mybatis.mylog.util.ConfigUtil;
import mybatis.mylog.util.StringConst;
import org.jetbrains.annotations.NotNull;

public class MyBatisLogStartupActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        if (project == null) return;
        ConfigUtil.setRunning(project, true);
//        int startup = PropertiesComponent.getInstance(project).getInt(StringConst.STARTUP_KEY, 1);
//        if(startup == 1) {
//            new ShowLogInConsoleAction(project).showLogInConsole(project);
//        }
    }
}

package mybatis.mylog.action.gui;

import mybatis.mylog.action.MybatisLogProjectService;
import com.google.common.collect.Sets;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author bruce ge
 */
public class RemoveAllAction extends AnAction {
    private JPanel myListForm;

    public RemoveAllAction(JPanel myListForm) {
        super("Clear All", "Clear all sql", AllIcons.Actions.GC);
        this.myListForm = myListForm;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        this.myListForm.removeAll();
//        this.myListForm.add(MyListForm.createToolbarPanel(myListForm));
        myListForm.revalidate();
        this.myListForm.repaint();
        MybatisLogProjectService.getInstance(anActionEvent.getProject()).setSqlList(Sets.newHashSet());
    }
}

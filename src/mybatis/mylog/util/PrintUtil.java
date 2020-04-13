package mybatis.mylog.util;


import mybatis.mylog.hibernate.BasicFormatterImpl;

/**
 * 打印简单工具类
 * @author ob
 */
public class PrintUtil {
//    public static ConsoleViewContentType getOutputAttributes(@Nullable Color foregroundColor, @Nullable Color backgroundColor) {
//        //@Nullable Color foregroundColor, @Nullable Color backgroundColor, @Nullable Color effectColor, EffectType effectType, @FontStyle int fontType
//        //针对Darcula主题，背景颜色调整
//        if(UIUtil.isUnderDarcula() && backgroundColor != null) {
//            backgroundColor = null;
//            foregroundColor = Color.YELLOW;
//        }
//        return new ConsoleViewContentType("styleName", new TextAttributes(foregroundColor, backgroundColor, null, null, Font.PLAIN));
//    }
//
//    public static void println(Project project, String line, ConsoleViewContentType consoleViewContentType) {
//        ConsoleView consoleView = ConfigUtil.consoleViewMap.get(project.getBasePath());
//        if (consoleView != null) {
//            consoleView.print(line + "\n", consoleViewContentType);
//        }
//    }
//
//    /**
//     * 增删改sql改用蓝色标识
//     *
//     * @param line
//     */
//    public static void println(Project project, String line) {
//        if (StringUtils.isNotBlank(line)) {
//            String lowerLine = line.toLowerCase().trim();
//            if (lowerLine.startsWith("insert") || lowerLine.startsWith("update")) {
////                println(project, line, ConsoleViewContentType.SYSTEM_OUTPUT);
//                println(project, line, ConsoleViewContentType.USER_INPUT);
//            } else if (lowerLine.startsWith("delete")) {
//                println(project, line, ConsoleViewContentType.USER_INPUT);
//            } else {
//                println(project, line, ConsoleViewContentType.USER_INPUT);
//            }
//        }
//    }

    /**
     * format sql statements
     * @param sql
     * @return
     */
    public static String format(String sql) {
        BasicFormatterImpl formatter = new BasicFormatterImpl();
        return formatter.format(sql);
    }
}

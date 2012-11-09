package gang.li.refile;

import gang.li.refile.util.FileRenameUtil;

public class RenameFileCmdRun {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            printMessage();
            System.exit(0);
        }
        if (!args[0].equals("-p")) {
            printMessage();
            System.exit(0);
        }
        String filePath = args[1];
        if (!args[2].equals("-t")) {
            printMessage();
            System.exit(0);
        }
        String template = args[3];
        try {
            FileRenameUtil.changeNameInOnePathFromTemplate(filePath, template);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("rename successful!");
    }

    protected static void printMessage() {
        String message = "usage: java my.li.runan.refile.RenameFileCmdRun -p filepath -t template";
        System.out.println(message);
    }
}

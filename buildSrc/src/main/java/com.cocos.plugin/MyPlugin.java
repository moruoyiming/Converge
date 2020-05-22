import org.apache.tools.ant.Project;
import org.gradle.api.Plugin;

public class MyPlugin implements Plugin<Project> {
    public static void test(){
    System.out.print("woshi chajian");
    }

    @Override
    public void apply(Project project) {

    }
}
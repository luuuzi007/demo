package com.example.annotations_compiler;

import com.example.annotations.BindPath;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import sun.rmi.runtime.Log;

/**
 * 注解处理器 处理我们定义的注解
 */
@AutoService(Processor.class)//注册注解处理器
public class AnotationCompiler extends AbstractProcessor {
    //生成文件的对象
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    /**
     * 声明注解处理器要处理的注解
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> types = new HashSet<>();
        //处理我们自定义的注解
        types.add(BindPath.class.getCanonicalName());
        return types;
    }

    /**
     * 声明注解处理器支持的源版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    /**
     * 核心方法 (用来做我们需要做的事情)
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        System.out.println("开始写入");
        //拿到的是这个模块的所有的BindPath的类节点
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindPath.class);//去整个app里面去找BindPath注解的节点()
        Map<String, String> map = new HashMap<>();
        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            //获取到key
            BindPath annotation = typeElement.getAnnotation(BindPath.class);
            String value = annotation.value();
            //获取到带包 名的类名
            String activityName = typeElement.getQualifiedName().toString();//获取全类名
            map.put(value, activityName);
        }
        if (map.size() > 0) {
            //开始写文件
            Writer writer = null;
            String activityName = "ActivityUtil" + System.currentTimeMillis();
            try {
                //创建文件
                JavaFileObject javaFileObject = filer.createSourceFile("com.example.demo006." + activityName);//文件名
                writer = javaFileObject.openWriter();
                writer.write("package com.example.demo006.util;\n" +
                        "\n" +
                        "        import com.example.demo006.MainActivity;\n" +
                        "        import com.example.route.Aroute;\n" +
                        "        import com.example.route.Iroute;\n" +
                        "\n" +
                        "public class " + activityName + " implements Iroute {\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void putActivity() {\n");
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String path = iterator.next();
                    String value = map.get(path);
                    writer.write("Aroute.getInstance().putActivity(\"" + path + "\", " + value + ".class);\n");
                }
                writer.write("}\n}");
            } catch (IOException e) {
                System.out.println("错误");
                e.printStackTrace();
            }finally {
                if (writer!=null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return false;
    }
}
//package com.example.demo006.util;
//
//        import com.example.demo006.MainActivity;
//        import com.example.route.Aroute;
//        import com.example.route.Iroute;
//
//public class ActivityUtil implements Iroute {
//
//    @Override
//    public void putActivity() {
//        Aroute.getInstance().putActivity("main/main", MainActivity.class);
//    }
//
//}


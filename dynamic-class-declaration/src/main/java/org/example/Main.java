package org.example;

import javassist.*;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String... args) {
        try {
            new Main().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws CannotCompileException, IllegalAccessException, InstantiationException, InterruptedException {
        System.out.println("sleep 30 sec await starting ...");
        TimeUnit.SECONDS.sleep(30);
        System.out.println("START");

        ClassPool pool = ClassPool.getDefault();
        for (int i = 0; i < 100000; i++) {
            CtClass point = pool.makeClass("org.example.Point" + i);
            addField(point, 64);
            Class clazz = point.toClass();
            point.detach();

            if (i % 1000 == 0) {
                System.out.println("load " + i + " classes");
            }
        }
    }

    private void addField(CtClass clazz, int count) throws CannotCompileException {
        for (int i = 0; i < count; i++) {
            CtField field = new CtField(CtClass.intType, "field" + i, clazz);
            field.setModifiers(Modifier.PRIVATE);
            clazz.addField(field);
            CtMethod getFieldN = CtNewMethod.getter("getField" + i , field);
            clazz.addMethod(getFieldN);
        }
    }
}

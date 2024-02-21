import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import java.io.*;
import java.util.Scanner;

public class gen9 {
    public static void main(String[] args) { 

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program9", null, "java/lang/Object", null);

        // Constructor Method
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0); // load this
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN); // Return 
        mv.visitMaxs(1,1);
        mv.visitEnd();
        // End Constructor

        // Main Method
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        // user input
        // print
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Input Starting Number > ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);

        // Scanner
        mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner"); // Load scanner type
        mv.visitInsn(Opcodes.DUP); // new scanner
        // Input
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"); 
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
        mv.visitVarInsn(Opcodes.ASTORE, 1);
        
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false);
        mv.visitVarInsn(Opcodes.ASTORE, 2);
        // parseInt
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I", false);
        mv.visitVarInsn(Opcodes.ISTORE, 3);

        // loop conditions
        Label startLoopLabel = new Label();
        mv.visitLabel(startLoopLabel);

        mv.visitVarInsn(Opcodes.ILOAD, 3); // x
        mv.visitIntInsn(Opcodes.BIPUSH, 100); // 100

        Label endLoopLabel = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, endLoopLabel); // if x >= 100 -> end.

        // inner loop
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        mv.visitVarInsn(Opcodes.ILOAD, 3); // x
        mv.visitInsn(Opcodes.ICONST_1); // 1
        mv.visitInsn(Opcodes.IADD); // +
        mv.visitVarInsn(Opcodes.ISTORE, 3); // = x

        mv.visitJumpInsn(Opcodes.GOTO, startLoopLabel);
        mv.visitLabel(endLoopLabel);

        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        // return
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Main End

        cw.visitEnd();

        byte[] byteArray = cw.toByteArray();

        try {
            FileOutputStream out = new FileOutputStream("program9.class");
            out.write(byteArray);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
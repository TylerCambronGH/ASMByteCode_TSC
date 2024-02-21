import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import java.io.*;
import java.util.Scanner;

public class gen7 {
    public static void main(String[] args) { 

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program7", null, "java/lang/Object", null);

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

        // counter
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitVarInsn(Opcodes.ISTORE, 1);

        // loop conditions
        Label startLoopLabel = new Label();
        mv.visitLabel(startLoopLabel);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitIntInsn(Opcodes.BIPUSH, 10);
        Label endLoopLabel = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, endLoopLabel); // if x >= 10 -> end.

        // inner loop
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

        mv.visitVarInsn(Opcodes.ILOAD, 1); // x
        mv.visitInsn(Opcodes.ICONST_1); // 1
        mv.visitInsn(Opcodes.IADD); // +
        mv.visitVarInsn(Opcodes.ISTORE, 1); // = x

        mv.visitJumpInsn(Opcodes.GOTO, startLoopLabel);
        mv.visitLabel(endLoopLabel);

        // return
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Main End

        cw.visitEnd();

        byte[] byteArray = cw.toByteArray();

        try {
            FileOutputStream out = new FileOutputStream("program7.class");
            out.write(byteArray);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
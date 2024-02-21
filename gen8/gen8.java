import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import java.io.*;
import java.util.Scanner;

public class gen8 {
    public static void main(String[] args) { 

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program8", null, "java/lang/Object", null);

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

        // x
        mv.visitLdcInsn(215);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        // y
        mv.visitLdcInsn(33);
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        // compare
        mv.visitVarInsn(Opcodes.ILOAD, 1); // x
        mv.visitVarInsn(Opcodes.ILOAD, 2); // y
        Label ifGT = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, ifGT); 
        // if y >= x
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        Label ifEnd = new Label();
        mv.visitJumpInsn(Opcodes.GOTO, ifEnd);
        // else
        mv.visitLabel(ifGT);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        mv.visitLabel(ifEnd);

        // return
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Main End

        cw.visitEnd();

        byte[] byteArray = cw.toByteArray();

        try {
            FileOutputStream out = new FileOutputStream("program8.class");
            out.write(byteArray);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
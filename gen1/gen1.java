import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import java.io.*;

public class gen1 {
    public static void main(String[] args) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "MultiplyNumbers", null, "java/lang/Object", null);

        { // Constructor
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 1); // load this
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        { // Main

            // Multiply Doubles
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            // x
            mv.visitLdcInsn((Double) 4.3);
            mv.visitVarInsn(Opcodes.DSTORE, 1);
            // y
            mv.visitLdcInsn((Double) 2.8);
            mv.visitVarInsn(Opcodes.DSTORE, 3);
            // x * y
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitInsn(Opcodes.DMUL);
            mv.visitVarInsn(Opcodes.DSTORE, 5);
            // print
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            // return
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] byteArray = cw.toByteArray();

        try {
            FileOutputStream out = new FileOutputStream("program1.class");
            out.write(byteArray);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
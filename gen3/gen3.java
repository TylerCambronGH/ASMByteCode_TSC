import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import java.io.*;

public class gen3 {
    public static void main(String[] args) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program3", null, "java/lang/Object", null);

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

        // Divide Doubles
        // x
        mv.visitLdcInsn((Double) 4.3);
        mv.visitVarInsn(Opcodes.DSTORE, 1);
        // y
        mv.visitLdcInsn((Double) 2.8);
        mv.visitVarInsn(Opcodes.DSTORE, 3);
        // x / y
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitInsn(Opcodes.DDIV);
        mv.visitVarInsn(Opcodes.DSTORE, 5);
        // print
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
        
        
        // Divide Integer
        // x
        mv.visitLdcInsn((Integer) 14);
        mv.visitVarInsn(Opcodes.ISTORE, 7);
        // y
        mv.visitLdcInsn((Integer) 2);
        mv.visitVarInsn(Opcodes.ISTORE, 8);
        // x / y
        mv.visitVarInsn(Opcodes.ILOAD, 7);
        mv.visitVarInsn(Opcodes.ILOAD, 8);
        mv.visitInsn(Opcodes.IDIV);
        mv.visitVarInsn(Opcodes.ISTORE, 9);
        // print
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 9);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        
        // Divide Long
        // x
        mv.visitLdcInsn((Long) 562L);
        mv.visitVarInsn(Opcodes.LSTORE, 10);
        // y
        mv.visitLdcInsn((Long) 22L);
        mv.visitVarInsn(Opcodes.LSTORE, 13);
        // x / y
        mv.visitVarInsn(Opcodes.LLOAD, 10);
        mv.visitVarInsn(Opcodes.LLOAD, 13);
        mv.visitInsn(Opcodes.LDIV);
        mv.visitVarInsn(Opcodes.LSTORE, 15);
        // print
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.LLOAD, 15);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
        
        // Divide Float
        // x
        mv.visitLdcInsn((Float) 56.2f);
        mv.visitVarInsn(Opcodes.FSTORE, 17);
        // y
        mv.visitLdcInsn((Float) 2.266f);
        mv.visitVarInsn(Opcodes.FSTORE, 18);
        // x - y
        mv.visitVarInsn(Opcodes.FLOAD, 17);
        mv.visitVarInsn(Opcodes.FLOAD, 18);
        mv.visitInsn(Opcodes.FDIV);
        mv.visitVarInsn(Opcodes.FSTORE, 19);
        // print
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.FLOAD, 19);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
        
        // return
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0,0);
        mv.visitEnd();
        // Main End

        cw.visitEnd();

        byte[] byteArray = cw.toByteArray();

        try {
            FileOutputStream out = new FileOutputStream("program3.class");
            out.write(byteArray);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
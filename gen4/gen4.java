import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;
import java.io.*;

public class gen4 {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program4", null, "java/lang/Object", null);

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
        
        // Compare Short
        // x
        mv.visitIntInsn(Opcodes.SIPUSH, 43);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        // y
        mv.visitIntInsn(Opcodes.SIPUSH, 13);
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        // compare
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        Label s_labelGT = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, s_labelGT); 
        // if y >= x
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        Label s_labelEnd = new Label();
        mv.visitJumpInsn(Opcodes.GOTO, s_labelEnd);
        // if x > y
        mv.visitLabel(s_labelGT);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        mv.visitLabel(s_labelEnd);

        // Compare Integer
        // x
        mv.visitLdcInsn(2);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        // y
        mv.visitLdcInsn(33);
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        // compare
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        Label i_labelGT = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, i_labelGT); 
        // if y >= x
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        Label i_labelEnd = new Label();
        mv.visitJumpInsn(Opcodes.GOTO, i_labelEnd);
        // if x > y
        mv.visitLabel(i_labelGT);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        mv.visitLabel(i_labelEnd);

        // Compare Long
        // x
        mv.visitLdcInsn(210L);
        mv.visitVarInsn(Opcodes.LSTORE, 3);
        // y
        mv.visitLdcInsn(209L);
        mv.visitVarInsn(Opcodes.LSTORE, 5);
        // compare
        Label l_labelGT = new Label();
        mv.visitVarInsn(Opcodes.LLOAD, 3);
        mv.visitVarInsn(Opcodes.LLOAD, 5);
        mv.visitInsn(Opcodes.LCMP);
        mv.visitJumpInsn(Opcodes.IFGT, l_labelGT); 
        // if y >= x
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.LLOAD, 5);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
        Label l_labelEnd = new Label();
        mv.visitJumpInsn(Opcodes.GOTO, l_labelEnd);
        // if x > y
        mv.visitLabel(l_labelGT);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.LLOAD, 3);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
        mv.visitLabel(l_labelEnd);
        
        // return
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        // Main End

        cw.visitEnd();

        byte[] byteArray = cw.toByteArray();

        try {
            FileOutputStream out = new FileOutputStream("program4.class");
            out.write(byteArray);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
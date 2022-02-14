package com.example.converge.note.androidadvanced.opengl;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderHelper {
    

    public static int compileVertexShader(String shaderCode) {
        // 编译顶点着色器
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        // 编译片元着色器
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    public static int compileShader(int type, String shaderCode) {
        // 创建一个新的着色器对象
        final int sharderObjectId = GLES20.glCreateShader(type);
        // 检查是否创建成功
        if (sharderObjectId == 0) {
            Log.e("Test", "create shader error");
            return 0;
        }
        // 上传着色器源码
        GLES20.glShaderSource(sharderObjectId, shaderCode);
        // 编译着色器源码
        GLES20.glCompileShader(sharderObjectId);
        // 获取编译状态
        final int[] compileStatus = new int[2];
        GLES20.glGetShaderiv(sharderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        // 取出日志信息
        Log.e("Test", "compile: " + GLES20.glGetShaderInfoLog(sharderObjectId));
        if (compileStatus[0] == 0) {
            // 编译失败时删除该着色器对象
            GLES20.glDeleteShader(sharderObjectId);
            return 0;
        }
        // 返回着色器id
        return sharderObjectId;
    }

    public static int linkPrograme(int vertexShaderId, int fragmentShaderId) {
        // 创建一个 OpenGL 程序对象
        final int programeObjectId = GLES20.glCreateProgram();
        if (programeObjectId == 0) {
            Log.e("Test", "create programe error");
            return 0;
        }
        // 把顶点着色器和片段着色器附加到程序对象上
        GLES20.glAttachShader(programeObjectId, vertexShaderId);
        GLES20.glAttachShader(programeObjectId, fragmentShaderId);
        // 链接程序
        GLES20.glLinkProgram(programeObjectId);
        // 验证链接的状态
        final int[] linkStatus = new int[2];
        GLES20.glGetProgramiv(programeObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        // 打印链接信息
        Log.e("Test", "link programe: " + GLES20.glGetProgramInfoLog(programeObjectId));
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(programeObjectId);
            Log.e("Test", "lint programe error");
            return 0;
        }
        return programeObjectId;
    }

    public static void validatePrograme(int mPrograme) {
    }
}
